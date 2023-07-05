<script>
export default {
  props: {
    
  },
  data() {
    return {

    }
  }
}
</script>

<template>
  <div class="qTable-warp">

    <div class="one-question">

      <div class="question">
        <p>第 {{ index + 1 }} 題</p>
        <p>question{{ question.question }}</p>
      </div>
      <div class="option" for="">
        <!-- <input type="checkbox" name="" id="" v-if="">
        <input type="radio" name="" id="" v-if=""> -->
        <label for="">{{ question.option }}</label>
      </div>

    </div>

  </div>
</template>

<style lang="scss" scoped>
  
</style>