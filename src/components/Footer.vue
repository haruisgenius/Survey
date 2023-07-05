<script>
import { RouterLink } from "vue-router"
</script>

<template>
  <div class="warp">
    <RouterLink class="button" to="/manager">管理者</RouterLink>
  </div>
</template>

<style lang="scss" scoped>
.warp {
  width: 100%;
  height: 8%;
  padding: 0.5rem 2rem;
  background-color: #FDF2D7;
  display: flex;
  justify-content: end;
  // z-index: -1;

  .button {
    text-decoration: none;
    color: #F895BE;
    width: 4rem;
    height: 100%;
    display: flex;
    justify-content: center;
    background-color: #FDD7EE;
    border: solid #F895BE 2px;
    border-radius: 0.5rem;
    // position: fixed;  // 固定位置

    // 按鈕變絲滑
    transition: 0.3s;

    &:hover {
      cursor: pointer;
      scale: 1.05;
    }

    // 點擊時效果
    &:active {
      scale: 0.95;
    }
  }
}
</style>