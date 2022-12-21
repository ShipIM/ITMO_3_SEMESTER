<template>
  <table v-if="authError == null" class="frame">
    <tr>
      <th id="header"><b>ФИО: Шипунов Илья Михайлович. Группа: P32111 Вариант: 11106</b></th>
    </tr>
    <tr>
      <td>
        <p>Переписать приложение из предыдущей лабораторной работы с использованием следующих технологий:</p>
        <p>Приложение должно быть реализовано в соответствии с шаблоном MVC и состоять из следующих элементов:</p>
        <ul>
          <li><span>Уровень back-end должен быть основан на Spring.</span></li>
          <li>
            <span>Уровень front-end должен быть построен на Vue.js с использованием обычных полей ввода HTML</span>
          </li>
          <li>
            <span>Взаимодействие между уровнями back-end и front-end должно быть организовано посредством REST API.</span>
          </li>
        </ul>
        <p>Приложение по-прежнему должно включать в себя 2 страницы - стартовую и основную страницу приложения. Обе
          страницы приложения должны быть адаптированы для отображения в 3 режимах:</p>
        <ul>
          <li><span>"Десктопный" - для устройств, ширина экрана которых равна или превышает 1062 пикселей.</span>
          </li>
          <li><span>"Планшетный" - для устройств, ширина экрана которых равна или превышает 877, но меньше 1062 пикселей.</span>
          </li>
          <li><span>"Мобильный"- для устройств, ширина экрана которых меньше 877 пикселей.</span></li>
        </ul>
        <p><b>Стартовая страница должна содержать следующие элементы:</b></p>
        <ul>
          <li><span>"Шапку", содержащую ФИО студента, номер группы и номер варианта.</span></li>
          <li><span>Форму для ввода логина и пароля. Информация о зарегистрированных в системе пользователях должна храниться в отдельной таблице БД (пароль должен храниться в виде хэш-суммы). Доступ неавторизованных пользователей к основной странице приложения должен быть запрещён.</span>
          </li>
        </ul>
        <p><b>Основная страница приложения должна содержать следующие элементы:</b></p>
        <ul>
          <li><span>Набор полей ввода для задания координат точки и радиуса области в соответствии с вариантом задания: Text (-3 ... 5) для координаты по оси X, Text (-5 ... 3) для координаты по оси Y, и Text (-3 ... 5) для задания радиуса области. Если поле ввода допускает ввод заведомо некорректных данных (таких, например, как буквы в координатах точки или отрицательный радиус), то приложение должно осуществлять их валидацию.</span>
          </li>
          <li><span>Динамически обновляемую картинку, изображающую область на координатной плоскости в соответствии с номером варианта и точки, координаты которых были заданы пользователем. Клик по картинке должен инициировать сценарий, осуществляющий определение координат новой точки и отправку их на сервер для проверки её попадания в область. Цвет точек должен зависить от факта попадания / непопадания в область. Смена радиуса также должна инициировать перерисовку картинки.</span>
          </li>
          <li><span>Таблицу со списком результатов предыдущих проверок.</span></li>
          <li><span>Кнопку, по которой аутентифицированный пользователь может закрыть свою сессию и вернуться на стартовую страницу приложения.</span>
          </li>
        </ul>
        <p><b>Дополнительные требования к приложению:</b></p>
        <ul>
          <li><span>Все результаты проверки должны сохраняться в базе данных под управлением СУБД PostgreSQL.</span>
          </li>
          <li><span>Для доступа к БД необходимо использовать Spring Data JPA.</span></li>
        </ul>
        <p class="action">
          <button @click="logout">Выход из аккаунта</button>
        </p>
      </td>
    </tr>
    <tr>
      <td>
        <hit-form @fetch="parse"></hit-form>
      </td>
    </tr>
    <tr v-if="requestError != null">
      <td>
        <p class="error">{{ requestError }}</p>
      </td>
    </tr>
    <tr>
      <td>
        <hit-table></hit-table>
      </td>
    </tr>
  </table>
  <table v-else class="frame">
    <tr v-if="authError != null">
      <td>
        <p class="error">{{ authError }}</p>
      </td>
    </tr>
    <tr>
      <td class="action">
        <p>
          <router-link to="/login">Войти в аккаунт</router-link>
        </p>
      </td>
    </tr>
  </table>
</template>

<script>
import hitForm from './hit-form.vue'
import hitTable from './hit-table.vue'
import {computed} from 'vue'
import {eraseCookie, getCookie} from "@/cookies";
import {router} from "@/router";

export default {
  components: {
    hitForm, hitTable
  },
  data() {
    return {
      hits: null,
      authError: null,
      requestError: null
    }
  },
  provide() {
    return {
      hits: computed(() => this.hits),
    }
  },
  methods: {
    parse(response) {
      this.requestError = null;

      if (response.ok) {
        response.text().then((text) =>
            this.hits = JSON.parse(text));
      } else if (response.status === 400) {
        response.text().then((text) =>
            this.requestError = JSON.parse(text).message);
      } else {
        response.text().then((text) =>
            this.authError = JSON.parse(text).message);
      }
    },
    logout() {
      eraseCookie("token");

      router.push("login");
    },
  },
  mounted() {
    fetch("api/hits", {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + (getCookie("token") != null ? getCookie("token") : "")
      },
    })
        .then((response) => this.parse(response));
  }
}
</script>

<style scoped>
@media (width < 877px) {
  .frame {
    width: 400px;
  }
}

@media (877px <= width < 1062px) {
  .frame {
    width: 600px;
  }
}

@media (1062px <= width) {
  .frame {
    width: 800px;
  }
}

.frame {
  height: auto;
  text-align: justify;
  margin: auto;
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

ul, ol {
  padding-right: 3%;
  padding-left: 7%;
}

ol ul {
  padding-left: 5%;
}

li {
  padding-top: 1%;
}

.error {
  color: red;
  font-size: medium;
  padding-left: 3%;
  padding-right: 3%;
  font-family: 'Times New Roman', Times, serif;
  text-align: center;
}

tr, td, th {
  border-radius: 12px;
  background-color: wheat;
}

.action {
  text-align: center;
}
</style>
