<template>
  <div id="canvas-container">
    <canvas ref="hitCanvas" @click="tryHit"/>
    <p class="error">{{ errorMessage }}</p>
  </div>
</template>

<script>

export default {
  inject: ['hits', 'r'],
  data() {
    let coordinates = new Image();
    let red_dot = new Image();
    let green_dot = new Image();

    coordinates.src = require('../assets/images/coordinates.svg');
    red_dot.src = require('../assets/images/red_dot.svg');
    green_dot.src = require('../assets/images/green_dot.svg');

    return {
      errorMessage: null,

      coordinates: coordinates,
      redDot: red_dot,
      greenDot: green_dot,

      canvas: null,
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.canvas = this.$refs.hitCanvas;

      let that = this;
      window.onresize = () => {
        that.drawPicture(this.r.value.content);
      }

      this.coordinates.onload = () => {
        that.drawPicture(that.r.value.content);
      }
    })
  },
  methods: {
    tryHit(event) {
      if (!this.r.value.invalid) {
        let rect = this.canvas.getBoundingClientRect();
        let size = rect.width / 2 * 0.935 / this.r.value.content;

        this.hit((-1 * (rect.width / 2 - Math.abs(event.clientX - rect.left)) / size).toFixed(4),
            ((rect.height / 2 - Math.abs(event.clientY - rect.top)) / size).toFixed(4));
      } else {
        this.errorMessage = "Радиус некорректен!";
      }
    },
    drawPicture(radius) {
      let rect = this.canvas.getBoundingClientRect();

      this.canvas.width = rect.width;
      this.canvas.height = rect.height;

      let context = this.canvas.getContext('2d');
      context.clearRect(0, 0, rect.width, rect.height);
      context.drawImage(this.coordinates, 0, 0, rect.width, rect.height);

      this.errorMessage = null;

      if (radius != null && this.hits.value != null) {
        let size = rect.width / 2 * 0.935 / radius;
        for (let hit of this.hits.value) {
          switch (hit.result) {
            case "TRUE": {
              context.drawImage(this.greenDot, hit.x * size + rect.width / 2
                  - rect.width * 0.025 / 2, (-1 * hit.y * size + rect.height / 2
                  - rect.width * 0.025 / 2), rect.width * 0.025, rect.height * 0.025);
              break;
            }
            case "FALSE": {
              context.drawImage(this.redDot, hit.x * size + rect.width / 2
                  - rect.width * 0.025 / 2, (-1 * hit.y * size + rect.height / 2
                  - rect.width * 0.025 / 2), rect.width * 0.025, rect.height * 0.025);
            }
          }
        }
      }
    },
    hit(x, y) {
      this.$emit('hit', x, y);
    }
  },
  watch: {
    r: {
      handler: function () {
        this.drawPicture(!this.r.value.invalid ? this.r.value.content : null);
      },
      deep: true
    },
    hits: {
      handler: function () {
        this.drawPicture(this.r.value.content);
      },
      deep: true
    }
  }
}
</script>

<style>
@media (width < 877px) {
  canvas {
    width: 300px;
    height: 300px;
  }
}

@media (877px <= width < 1062px) {
  canvas {
    width: 350px;
    height: 350px;
  }
}

@media (1062px <= width) {
  canvas {
    width: 400px;
    height: 400px;
  }
}

canvas {
  display: inline;
}

.error {
  color: red;
  font-size: medium;
  padding-left: 3%;
  padding-right: 3%;
  font-family: 'Times New Roman', Times, serif;
  text-align: center;
}

#canvas-container {
  width: 100%;
  text-align: center;
  padding: 2% 2% 2% 2%;
}
</style>