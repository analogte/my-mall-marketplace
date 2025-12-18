<template>
  <div class="login-container">
    <el-card class="login-card">
      <div slot="header" class="clearfix">
        <span class="title">ลงทะเบียนเปิดร้านค้า (Seller Register)</span>
      </div>
      <el-form :model="registerForm" :rules="rules" ref="registerForm" label-width="120px" class="register-form">
        <el-form-item label="Username" prop="username">
          <el-input v-model="registerForm.username" placeholder="ตั้งชื่อผู้ใช้"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="registerForm.password" placeholder="ตั้งรหัสผ่าน"></el-input>
        </el-form-item>
        <el-form-item label="ชื่อร้านค้า" prop="shopName">
          <el-input v-model="registerForm.shopName" placeholder="เช่น Analog Shop"></el-input>
        </el-form-item>
        <el-form-item label="รูปโลโก้ (URL)" prop="logo">
          <el-input v-model="registerForm.logo" placeholder="http://..."></el-input>
        </el-form-item>
        <el-form-item label="Commission" prop="commissionRate">
          <el-input-number v-model="registerForm.commissionRate" :precision="2" :step="0.01" :max="1.0" :min="0.0"></el-input-number>
          <span style="margin-left: 10px; color: #888;">(0.0 - 1.0)</span>
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="handleRegister" :loading="loading" style="width: 100%;">สมัครสมาชิก</el-button>
        </el-form-item>
        <div style="text-align: center;">
          <router-link to="/login">
            <el-link type="primary">กลับไปหน้าเข้าสู่ระบบ</el-link>
          </router-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { register } from '@/api/seller'

export default {
  name: 'Register',
  data() {
    return {
      registerForm: {
        username: '',
        password: '',
        shopName: '',
        logo: '',
        commissionRate: 0.05
      },
      rules: {
        username: [{ required: true, message: 'กรุณากรอกชื่อผู้ใช้', trigger: 'blur' }],
        password: [{ required: true, message: 'กรุณากรอกรหัสผ่าน', trigger: 'blur' }],
        shopName: [{ required: true, message: 'กรุณากรอกชื่อร้านค้า', trigger: 'blur' }]
      },
      loading: false
    }
  },
  methods: {
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.loading = true
          register(this.registerForm).then(response => {
            this.$message.success('ลงทะเบียนสำเร็จ! กรุณาเข้าสู่ระบบ')
            this.$router.push('/login')
          }).catch(error => {
            this.loading = false
          }) // Request interceptor handles error message
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
  background-color: #2d3a4b;
}
.login-card {
  width: 500px;
}
.title {
  font-size: 20px;
  font-weight: bold;
}
</style>
