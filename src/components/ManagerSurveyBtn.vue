<script>
import indexStore from "../stores/indexStore"
import { mapActions, mapState } from "pinia";

export default {
  props: {
    showManagerBtn: false,
  },
  data() {
    return {
      
    }
  },
  methods: {
    ...mapActions(indexStore, ["updateLocation"])
  },
  computed: {
    ...mapState(indexStore, ["location", "getLocation"])
  }
}
</script>

<template>
  <div>
    <div class="manager-btn" :v-show="showManagerBtn">
      <p>{{ getLocation }}</p>
      <RouterLink class="link" :class="{ local: location === 1 }" to="/manager/create-survey">問卷</RouterLink>
      <RouterLink class="link" :class="{ local: location === 2 }" to="/manager/create-survey/question">題目</RouterLink>
      <RouterLink class="link" :class="{ local: location === 3 }" to="/manager/create-survey">回饋</RouterLink>
      <RouterLink class="link" :class="{ local: location === 4 }" to="/manager/create-survey">統計</RouterLink>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .manager-btn {
    height: 100px;
    display: flex;
    align-items: end;
    margin-left: 1.5rem;
  }

  .link {
    text-decoration: none;
    color: #ffffff;
    background-color: #b6b6b6;
    border-radius: 6px;
    padding: 0.5rem 1.5rem;
    cursor: pointer;
    transition: 0.5s;
    // margin: 0 1.5rem;

    // &:hover {
    //   scale: 1.03;
    // }
  }

  .local{
      color: #ff7300 !important;
      background-color: white;
      border-radius: 6px;
      padding: 0.5rem 1.5rem;
    }
</style>