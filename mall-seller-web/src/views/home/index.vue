<template>
  <div class="app-container">
    <div class="header">
      <h2>Welcome to Seller Center</h2>
      <div class="auth-buttons">
        <template v-if="!isLoggedIn">
          <el-button type="primary" @click="goToLogin">เข้าสู่ระบบ</el-button>
          <el-button type="success" @click="goToRegister">สมัครร้านค้าใหม่</el-button>
        </template>
        <template v-else>
          <el-button type="danger" @click="handleLogout">ออกจากระบบ</el-button>
        </template>
      </div>
    </div>
    
    <el-divider></el-divider>
    
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="always">
          <h3>Total Sales</h3>
          <p class="stat-value">฿ 0.00</p>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always">
          <h3>Total Orders</h3>
          <p class="stat-value">0</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      isLoggedIn: false
    }
  },
  mounted() {
    this.checkLoginStatus()
  },
  methods: {
    checkLoginStatus() {
      const token = localStorage.getItem('seller_token')
      this.isLoggedIn = !!token
    },
    goToLogin() {
      this.$router.push('/login')
    },
    goToRegister() {
      this.$router.push('/register')
    },
    handleLogout() {
      localStorage.removeItem('seller_token')
      this.isLoggedIn = false
      this.$message.success('Logout Success')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.auth-buttons {
  display: flex;
  gap: 10px;
}
.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #409EFF;
}
</style>


