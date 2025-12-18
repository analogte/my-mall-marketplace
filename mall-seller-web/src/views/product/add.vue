<template>
  <div class="app-container">
    <h2>Product Management > Add New Product</h2>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>เพิ่มสินค้าใหม่</span>
      </div>
      
      <el-form :model="product" :rules="rules" ref="productForm" label-width="150px" class="product-form">
        
        <el-form-item label="ชื่อสินค้า" prop="name">
          <el-input v-model="product.name" placeholder="ระบุชื่อสินค้า (เช่น เสื้อยืดลายแมว Cotton 100%)"></el-input>
        </el-form-item>

        <el-form-item label="รายละเอียดย่อ" prop="subTitle">
          <el-input v-model="product.subTitle" placeholder="คำโปรยสินค้า (เช่น ใส่สบาย ไม่ย้วย)"></el-input>
        </el-form-item>

        <el-form-item label="รหัสสินค้า (SKU)" prop="productSn">
          <el-input v-model="product.productSn" placeholder="ระบุรหัสสินค้า (ถ้ามี)"></el-input>
        </el-form-item>

        <el-form-item label="ราคา (บาท)" prop="price">
          <el-input-number v-model="product.price" :min="0" :precision="2" :step="10"></el-input-number>
        </el-form-item>

        <el-form-item label="ราคาเต็ม (ถ้ามี)" prop="originalPrice">
          <el-input-number v-model="product.originalPrice" :min="0" :precision="2" :step="10"></el-input-number>
        </el-form-item>

        <el-form-item label="จำนวนสินค้า (Stock)" prop="stock">
          <el-input-number v-model="product.stock" :min="0" :step="1"></el-input-number>
        </el-form-item>

        <el-form-item label="ลิงก์รูปภาพ" prop="pic">
           <el-input v-model="product.pic" placeholder="URL ของรูปภาพสินค้า"></el-input>
        </el-form-item>

        <el-form-item label="รายละเอียดสินค้า" prop="description">
          <el-input 
             type="textarea" 
             :rows="4" 
             placeholder="ระบุรายละเอียดสินค้า..." 
             v-model="product.description">
          </el-input>
        </el-form-item>
        
        <el-form-item label="การแสดงผล">
           <el-radio-group v-model="product.publishStatus">
            <el-radio :label="1">วางจำหน่ายทันที</el-radio>
            <el-radio :label="0">เก็บในคลัง (ไม่แสดง)</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit('productForm')">บันทึกสินค้า</el-button>
          <el-button @click="resetForm('productForm')">ล้างค่า</el-button>
        </el-form-item>

      </el-form>
    </el-card>
  </div>
</template>

<script>
import { createProduct } from '@/api/product'

export default {
  name: 'AddProduct',
  data() {
    return {
      product: {
        name: '',
        subTitle: '',
        productSn: '',
        price: 0,
        originalPrice: 0,
        stock: 0,
        pic: '',
        description: '',
        publishStatus: 1,
        sort: 0
      },
      rules: {
        name: [
          { required: true, message: 'กรุณาระบุชื่อสินค้า', trigger: 'blur' },
          { min: 2, max: 100, message: 'ความยาวต้องอยู่ระหว่าง 2 ถึง 100 ตัวอักษร', trigger: 'blur' }
        ],
        price: [
           { required: true, message: 'กรุณาระบุราคา', trigger: 'blur' }
        ],
        stock: [
           { required: true, message: 'กรุณาระบุจำนวนสินค้า', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          createProduct(this.product).then(() => {
             this.$message({
                message: 'บันทึกสินค้าสำเร็จ!',
                type: 'success',
                duration: 1000
              });
              // Reset or redirect
              this.resetForm(formName);
              // potentially redirect to list
          }).catch(error => {
              console.error(error);
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.box-card {
    width: 800px;
    margin: 20px auto;
}
</style>
