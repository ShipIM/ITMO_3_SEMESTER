<template>
  <form id="fields_form" @submit.prevent>
    <hit-image @hit="hit"></hit-image>
    <table>

      <colgroup>
        <col width="50%">
      </colgroup>

      <tr>
        <td class="label"><label for="r_input"><b>Введите значение R:</b></label></td>
        <td class="input">
          <number-input @content="contentR"
                        :lower-bound="0"
                        :upper-bound="5"
                        :limit="6"></number-input>
        </td>
      </tr>
      <tr>
        <td class="label"><label for="x_input"><b>Введите значение X:</b></label></td>
        <td class="input">
          <number-input @content="contentX"
                        :lower-bound="-3"
                        :upper-bound="5"
                        :limit="6"></number-input>
        </td>
      </tr>
      <tr>
        <td class="label"><label for="y_input"><b>Введите значение Y:</b></label></td>
        <td class="input">
          <number-input @content="contentY"
                        :lower-bound="-5"
                        :upper-bound="3"
                        :limit="6"></number-input>
        </td>
      </tr>
      <tr>
        <td class="confirm" colspan="2">
          <button :disabled="submit_condition" @click="send">Отправить</button>
        </td>
      </tr>
    </table>
  </form>
</template>

<script>
import NumberInput from "./number-input.vue";
import hitImage from "./hit-image"
import {computed} from "vue"
import {getCookie} from "@/cookies";

export default {
  components: {
    NumberInput, hitImage
  },
  data() {
    return {
      x: {
        content: null,
        invalid: true
      },
      y: {
        content: null,
        invalid: true
      },
      r: {
        content: null,
        invalid: true
      }
    }
  },
  provide() {
    return {
      r: computed(() => this.r)
    }
  },
  emits: ['fetch'],
  methods: {
    contentX(condition, content) {
      this.x.invalid = condition;
      this.x.content = content;
    },
    contentY(condition, content) {
      this.y.invalid = condition;
      this.y.content = content;
    },
    contentR(condition, content) {
      this.r.invalid = condition;
      this.r.content = content;
    },
    submit(r, x, y) {
      fetch("api/hits", {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + (getCookie("token") != null ? getCookie("token") : "")
        },
        body: JSON.stringify({r: r, x: x, y: y})
      }).then((response) => this.update(response))
    },
    update(response) {
      this.$emit('fetch', response);
    },
    hit(x, y) {
      this.submit(this.r.content, x, y);
    },
    send() {
      this.submit(this.r.content, this.x.content, this.y.content);
    },
  },
  computed: {
    submit_condition() {
      return this.x.invalid || this.y.invalid || this.r.invalid;
    }
  }
}
</script>

<style>
table {
  width: 100%;
}

.input {
  text-align: left;
  padding: 1% 1% 1% 1%;
}

.label {
  text-align: right;
  padding: 1% 1% 1% 1%;
}

.confirm {
  text-align: center;
  padding: 2% 1% 1% 1%;
}
</style>