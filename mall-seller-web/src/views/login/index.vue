<template>
  <div class="login-container">
    <el-card class="login-card">
      <div slot="header" class="clearfix">
        <span class="title">เข้าสู่ระบบร้านค้า (Seller Login)</span>
      </div>
      <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="80px">
        <el-form-item label="User" prop="username">
          <el-input v-model="loginForm.username" placeholder="ชื่อผู้ใช้" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item label="Pass" prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="รหัสผ่าน" prefix-icon="el-icon-lock" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" :loading="loading" style="width: 100%;">เข้าสู่ระบบ</el-button>
        </el-form-item>
        <div style="text-align: center;">
          <router-link to="/register">
            <el-link type="primary">ยังไม่มีร้านค้า? สมัครสมาชิกที่นี่</el-link>
          </router-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { login } from '@/api/seller'

export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{ required: true, message: 'กรุณากรอกชื่อผู้ใช้', trigger: 'blur' }],
        password: [{ required: true, message: 'กรุณากรอกรหัสผ่าน', trigger: 'blur' }]
      },
      loading: false
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          login(this.loginForm).then(response => {
            this.loading = false
            const token = response.data.token
            const tokenHead = response.data.tokenHead
            localStorage.setItem('seller_token', tokenHead + token) // Simple storage
            this.$message.success('Login Success')
            this.$router.push('/home')
          }).catch(() => {
            this.loading = false
          })
        } else {
          return false
        }
      })
    }
  }
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
