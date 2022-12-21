<template>
  <form @submit.prevent>
    <table>

      <colgroup>
        <col width="50%">
      </colgroup>

      <tr>
        <td class="label"><label for="username">Введите имя пользователя:</label></td>
        <td class="input"><input type="text" id="username" :maxlength="limit" v-model="username"></td>
      </tr>
      <tr>
        <td class="label"><label for="password">Введите пароль:</label></td>
        <td class="input"><input type="password" id="password" :maxlength="limit" v-model="password"></td>
      </tr>
      <tr>
        <td class="confirm" colspan="2">
          <button :disabled="disabled" @click="submit">{{ action }}</button>
        </td>
      </tr>
    </table>
  </form>
</template>

<script>

export default {
  props: {
    destination: String,
    action: String,

    limit: Number
  },
  data() {
    return {
      username: null,
      password: null,

      passwordInvalid: true,
      usernameInvalid: true
    }
  },
  emits: ['fetch'],
  computed: {
    disabled() {
      return this.passwordInvalid || this.usernameInvalid;
    }
  },
  methods: {
    validate(value) {
      if (value.length === 0) return true;
    },
    submit() {
      fetch(this.destination, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({username: this.username, password: this.password})
      }).then((response) => this.update(response));
    },
    update(data) {
      this.$emit('fetch', data);
    }
  },
  watch: {
    'password'(newValue) {
      newValue = newValue.trim();
      this.password = newValue

      this.passwordInvalid = this.validate(newValue);
    },
    'username'(newValue) {
      newValue = newValue.trim();
      this.username = newValue

      this.usernameInvalid = this.validate(newValue);
    }
  }
}
</script>

<style scoped>
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