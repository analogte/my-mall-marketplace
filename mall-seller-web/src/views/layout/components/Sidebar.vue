<template>
  <el-scrollbar wrap-class="scrollbar-wrapper">
    <el-menu
      :default-active="$route.path"
      background-color="#304156"
      text-color="#bfcbd9"
      active-text-color="#409EFF"
      :collapse="false"
      mode="vertical"
      router
    >
      <!-- Dashboard -->
      <el-menu-item index="/home">
        <i class="el-icon-s-home"></i>
        <span slot="title">หน้าแรก</span>
      </el-menu-item>

      <!-- Product Management -->
      <el-submenu index="product">
        <template slot="title">
          <i class="el-icon-s-goods"></i>
          <span slot="title">จัดการสินค้า</span>
        </template>
        <el-menu-item index="/product/list">รายการสินค้า</el-menu-item>
        <el-menu-item index="/product/add">เพิ่มสินค้าใหม่</el-menu-item>
      </el-submenu>

      <!-- Order Management -->
      <el-submenu index="order">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span slot="title">คำสั่งซื้อ</span>
        </template>
        <el-menu-item index="/order/list">รายการคำสั่งซื้อ</el-menu-item>
        <el-menu-item index="/order/return">การคืนเงิน/คืนสินค้า</el-menu-item>
      </el-submenu>
      
      <!-- Marketing (Placeholder) -->
      <el-submenu index="marketing">
        <template slot="title">
          <i class="el-icon-s-ticket"></i>
          <span slot="title">Marketing Centre</span>
        </template>
        <el-menu-item index="/marketing/coupon" disabled>คูปองส่วนลด (เร็วๆนี้)</el-menu-item>
      </el-submenu>

      <!-- Finance (Placeholder) -->
      <el-submenu index="finance">
        <template slot="title">
           <i class="el-icon-money"></i>
           <span slot="title">การเงิน</span>
        </template>
        <el-menu-item index="/finance/income" disabled>รายรับของฉัน (เร็วๆนี้)</el-menu-item>
        <el-menu-item index="/finance/bank" disabled>บัญชีธนาคาร (เร็วๆนี้)</el-menu-item>
      </el-submenu>
      
      <!-- Shop Settings -->
      <el-submenu index="setting">
         <template slot="title">
             <i class="el-icon-s-shop"></i>
             <span slot="title">ตั้งค่าร้านค้า</span>
         </template>
         <el-menu-item index="/setting/profile">โปรไฟล์ร้านค้า</el-menu-item>
      </el-submenu>

    </el-menu>
  </el-scrollbar>
</template>

<script>
export default {
  name: 'Sidebar'
}
</script>

<style scoped>
.scrollbar-wrapper {
  height: 100%;
}
</style>
