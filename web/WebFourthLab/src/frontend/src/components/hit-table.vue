<template>
  <table>
    <colgroup>
      <col width="10%">
      <col width="10%">
      <col width="10%">
    </colgroup>

    <tr>
      <th colspan="3">Данные для проверки</th>
      <th>Результат</th>
      <th>Время отправки</th>
      <th>Время исполнения</th>
    </tr>

    <tr v-for="hit in hits.value" :key="hit.id">
      <td>{{ hit.r }}</td>
      <td>{{ hit.x }}</td>
      <td>{{ hit.y }}</td>
      <td>{{ hit.result }}</td>
      <td>{{ hit.requestTime }}</td>
      <td>{{ hit.handleTime }}</td>
    </tr>
  </table>
</template>

<script>
export default {
  inject: ['hits']
}
</script>

<style scoped>
@media (width < 877px) {
  table {
    font-size: small;
  }
}

@media (877px <= width) {
  table {
    font-size: medium;
  }
}


table {
  border-collapse: collapse;
  border: 2px solid black;
  width: 90%;
  height: auto;
  text-align: center;
  margin: 0.3% auto 0.3% auto;
}

td, th {
  border: 1px solid black;
}
</style>