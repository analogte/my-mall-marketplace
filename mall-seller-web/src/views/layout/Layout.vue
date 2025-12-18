<template>
  <div class="app-wrapper">
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <navbar></navbar>
      <app-main></app-main>
    </div>
  </div>
</template>

<script>
import { Navbar, Sidebar, AppMain } from './components'

export default {
  name: 'Layout',
  components: {
    Navbar,
    Sidebar,
    AppMain
  }
}
</script>

<style scoped>
.app-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
}
.sidebar-container {
  width: 250px; /* Shopee style width */
  height: 100%;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 1001;
  overflow-y: auto;
  background-color: #304156; /* Professional Dark Blue */
}
.main-container {
  min-height: 100%;
  transition: margin-left .28s;
  margin-left: 250px;
  background-color: #f0f2f5;
}
</style>
