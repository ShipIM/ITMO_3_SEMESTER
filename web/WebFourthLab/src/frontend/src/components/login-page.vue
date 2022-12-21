<template>
  <table id="frame">
    <tr>
      <th id="header"><b>ФИО: Шипунов Илья Михайлович. Группа: P32111 Вариант: 11106</b></th>
    </tr>
    <tr>
      <td>
        <user-form :destination="'api/users/login'" :action="'Войти'" :limit="20" @fetch="parse"/>
        <p>
          <router-link to="/register">Ещё нет аккаунта?</router-link>
        </p>
      </td>
    </tr>
    <tr v-if="error != null">
      <td>
        <p class="error">{{ error }}</p>
      </td>
    </tr>
  </table>
</template>

<script>
import userForm from "@/components/user-form";
import {addCookie, getCookie} from "@/cookies";
import {router} from "@/router";

export default {
  components: {
    userForm
  },
  data() {
    return {
      error: null
    }
  },
  beforeRouteEnter(to, from, next) {
    if (getCookie("token") != null)
      next("main");
    else next();
  },
  methods: {
    async parse(response) {
      if (response.ok) {
        await response.text().then((text) => addCookie("token", text));
        await router.push({path: "/main"});
      } else {
        response.text().then((text) => {
          this.error = JSON.parse(text).message;
        })
      }
    }
  }
}
</script>

<style scoped>
@media (width < 877px) {
  #frame {
    width: 400px;
  }
}

@media (877px <= width < 1062px) {
  #frame {
    width: 600px;
  }
}

@media (1062px <= width) {
  #frame {
    width: 800px;
  }
}

#frame {
  margin: auto;
  height: auto;
  text-align: justify;
}

#header {
  color: black;
  font-size: medium;
  font-family: serif;
  text-align: center;
}

td p {
  padding-left: 3%;
  padding-right: 3%;
}

.error {
  color: red;
  font-size: medium;
  padding-left: 3%;
  padding-right: 3%;
  font-family: 'Times New Roman', Times, serif;
  text-align: center;
}

p {
  text-align: center;
}

tr, td, th {
  border-radius: 12px;
  background-color: wheat;
}
</style>