<script>
import { RouterView } from "vue-router"
import HeaderView from "./components/Header.vue"
import FooterView from "./components/Footer.vue"

export default {
  components: {
    HeaderView,
    FooterView
  }
}
</script>

<template>
  <div class="all-area">
    <HeaderView />
    <div class="work-space">
      <RouterView />

    </div>
    <FooterView />
  </div>
</template>

<style lang="scss" scoped>
.all-area {
  height: 100vh;
}

.work-space {
  width: 100vw;
  height: 77%;
  padding-left: 10%;
  padding-right: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #FDF2D7;
  z-index: 1;
}



</style>
