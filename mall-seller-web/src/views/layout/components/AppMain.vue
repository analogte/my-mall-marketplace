<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <router-view></router-view>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain'
}
</script>

<style scoped>
.app-main {
  /* 50 = navbar height  */
  min-height: calc(100vh - 50px);
  position: relative;
  overflow: hidden;
  padding: 20px;
}
</style>
