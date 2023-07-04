<script>
import { RouterLink } from "vue-router"
import indexStore from "../stores/indexStore"
import { mapActions, mapState } from "pinia";
import MngSurveyBtnView from "../components/ManagerSurveyBtn.vue"

export default {
  components: {
    MngSurveyBtnView
  },
  data() {
    return {
      // showManagerBtn: true, // 創問卷頁籤顯示
    }
  },
  mounted() {
    this.updateLocation(0)
  },
  methods: {
    ...mapActions(indexStore, ['updateLocation'])
  },
  computed: {
    ...mapState(indexStore, ["location", "getLocation"])
  }
}
</script>

<template>
  <header>

      <RouterLink class="button" to="/"></RouterLink>
      <!-- <MngSurveyBtnView :showManagerBtn="showManagerBtn" /> -->
  </header>
</template>

<style lang="scss" scoped>
header {
  width: 100vw;
  height: 15%;
  padding-left: 1rem;
  // background-color: #F8DC95;
  display: flex;
  // justify-content: center;
  align-items: center;
  // z-index: -1;
  background-color: #F8DC95;
  font-size: 1.5rem;

  
  .button {
    width: 5rem;
    height: 4.6rem;
    background-image: url(../../public/questionnaire.png);
    background-repeat: no-repeat;
    background-size: contain;
    transition: 0.3s;
    
    &:hover {
      cursor: pointer;
      scale: 1.05;
    }
    
    &:active {
      scale: 0.95;
    }
  }
  
  
}
</style>