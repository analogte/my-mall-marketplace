<template>
  <div class="app-container">
    <h2>Welcome to Seller Center</h2>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="always">
          <h3>Total Sales</h3>
          <p>฿ 0.00</p>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always">
          <h3>Total Orders</h3>
          <p>0</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>
