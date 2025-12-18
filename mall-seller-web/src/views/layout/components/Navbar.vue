<template>
  <div class="navbar">
    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <span class="user-name">Seller Account</span>
          <i class="el-icon-caret-bottom"></i>
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link class="inlineBlock" to="/">
            <el-dropdown-item>หน้าแรก</el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout">ออกจากระบบ</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  methods: {
    logout() {
      localStorage.removeItem('seller_token')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  background-color: #fff;
  border-bottom: 1px solid #d8dce5;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  display: flex;
  justify-content: flex-end; /* Right align items */
  padding-right: 20px;
}
.avatar-wrapper {
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 5px;
}
</style>
