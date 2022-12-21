<template>
  <div>
    <input type="text"
           :placeholder="range"
           :maxlength="limit"
           v-model="contents">
    <span class="error">{{ error }}</span>
  </div>
</template>

<script>
export default {
  props: {
    limit: Number,
    lowerBound: Number,
    upperBound: Number
  },
  emits: ['content'],
  data() {
    return {
      contents: null,
      error: null
    }
  },
  computed: {
    range() {
      return this.lowerBound + " ... " + this.upperBound;
    }
  },
  methods: {
    validate(condition, content) {
      this.$emit('content', condition, content);
    }
  },
  watch: {
    'contents'(newValue) {
      let value = newValue.replace(',', '.').replace(/\s/, '');

      this.contents = value;

      if (!isNaN(parseFloat(value)) && isFinite(value)) {
        if (this.lowerBound < value && value < this.upperBound) {
          this.error = null;
          this.validate(false, value);
        } else {
          this.error = 'Число должно быть в диапазоне от ' + this.lowerBound + ' до ' + this.upperBound + '.';
          this.validate(true, value);
        }
      } else {
        value === "" ? this.error = null : this.error = 'Необходимо ввести число.';
        this.validate(true, value);
      }
    }
  }
}
</script>

<style scoped>
.error {
  color: red;
  font-size: medium;
  padding-left: 3%;
  padding-right: 3%;
  font-family: 'Times New Roman', Times, serif;
  text-align: center;
}
</style>