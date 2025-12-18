<template>
  <div class="login-container">
    <el-card class="login-card">
      <div slot="header" class="clearfix">
        <span>Seller Center Login</span>
      </div>
      <el-form ref="loginForm" label-width="120px">
        <el-form-item label="Username">
          <el-input placeholder="Please enter username"></el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input type="password" placeholder="Please enter password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">Login</el-button>
          <el-button type="text">Register New Shop</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Login'
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}
.login-card {
  width: 400px;
}
</style>
