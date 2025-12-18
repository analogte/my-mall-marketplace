<template>
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>ค้นหา</span>
      </div>
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
          <el-form-item label="ชื่อสินค้า:">
            <el-input style="width: 203px" v-model="listQuery.keyword" placeholder="พิมพ์ชื่อสินค้า/คำค้นหา"></el-input>
          </el-form-item>
          <el-button style="float: right" @click="handleSearchList()" type="primary" size="small">
            ค้นหา
          </el-button>
          <el-button style="float: right;margin-right: 15px" @click="handleResetSearch()" size="small">
            รีเซ็ต
          </el-button>
        </el-form>
      </div>
    </el-card>

    <div class="operate-container" shadow="never">
      <el-card class="operate-container" shadow="never">
        <i class="el-icon-tickets"></i>
        <span>รายการสินค้า</span>
        <el-button class="btn-add" @click="handleAddProduct()" size="mini">
          เพิ่มสินค้า
        </el-button>
      </el-card>
    </div>

    <div class="table-container">
      <el-table ref="productTable"
                :data="list"
                style="width: 100%"
                v-loading="listLoading"
                border>
        <el-table-column label="รหัส" width="100" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="รูปภาพ" width="120" align="center">
          <template slot-scope="scope"><img style="height: 80px" :src="scope.row.pic"></template>
        </el-table-column>
        <el-table-column label="ชื่อสินค้า" align="center">
          <template slot-scope="scope">
            <p>{{scope.row.name}}</p>
            <p>แบรนด์: {{scope.row.brandName || '-'}}</p>
          </template>
        </el-table-column>
        <el-table-column label="ราคา" width="140" align="center">
          <template slot-scope="scope">
            <p>ขาย: ฿{{scope.row.price}}</p>
            <p>เต็ม: ฿{{scope.row.originalPrice || '-'}}</p>
          </template>
        </el-table-column>
        <el-table-column label="สถานะ" width="160" align="center">
          <template slot-scope="scope">
            <p>เผยแพร่: <el-switch
              @change="handlePublishStatusChange(scope.$index, scope.row)"
              :active-value="1"
              :inactive-value="0"
              v-model="scope.row.publishStatus">
            </el-switch></p>
            <p>สินค้าใหม่: <el-switch
              @change="handleNewStatusChange(scope.$index, scope.row)"
              :active-value="1"
              :inactive-value="0"
              v-model="scope.row.newStatus">
            </el-switch></p>
            <p>แนะนำ: <el-switch
              @change="handleRecommendStatusChange(scope.$index, scope.row)"
              :active-value="1"
              :inactive-value="0"
              v-model="scope.row.recommandStatus">
            </el-switch></p>
          </template>
        </el-table-column>
        <el-table-column label="คลัง" width="80" align="center">
          <template slot-scope="scope">{{scope.row.stock}}</template>
        </el-table-column>
        <el-table-column label="จัดการ" width="160" align="center">
          <template slot-scope="scope">
            <p>
              <el-button size="mini" @click="handleUpdateProduct(scope.$index, scope.row)">แก้ไข</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">ลบ</el-button>
            </p>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes,prev, pager, next,jumper"
        :page-size="listQuery.pageSize"
        :page-sizes="[5,10,15]"
        :current-page.sync="listQuery.pageNum"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>


<script>
  import { fetchList, updateDeleteStatus, updatePublishStatus, updateNewStatus, updateRecommendStatus } from '@/api/product'

  const defaultListQuery = {
    keyword: null,
    pageNum: 1,
    pageSize: 5
  };

  export default {
    name: "productList",
    data() {
      return {
        listQuery: Object.assign({}, defaultListQuery),
        list: null,
        total: null,
        listLoading: true
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        this.listLoading = true;
        fetchList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
        });
      },
      handleSearchList() {
        this.listQuery.pageNum = 1;
        this.getList();
      },
      handleAddProduct() {
        this.$router.push({path:'/product/add'});
      },
      handleBatchOperate() {
        // TODO: Implement batch operations
      },
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getList();
      },
      handleResetSearch() {
        this.listQuery = Object.assign({}, defaultListQuery);
      },
      handleDelete(index, row) {
        this.$confirm('คุณต้องการลบสินค้านี้หรือไม่?', 'ยืนยันการลบ', {
          confirmButtonText: 'ยืนยัน',
          cancelButtonText: 'ยกเลิก',
          type: 'warning'
        }).then(() => {
          updateDeleteStatus({ ids: row.id, deleteStatus: 1 }).then(() => {
            this.$message.success('ลบสินค้าสำเร็จ');
            this.getList();
          });
        }).catch(() => {
          this.$message.info('ยกเลิกการลบ');
        });
      },
      handleUpdateProduct(index, row) {
        this.$router.push({path: '/product/update', query: {id: row.id}});
      },
      handlePublishStatusChange(index, row) {
        updatePublishStatus({ ids: row.id, publishStatus: row.publishStatus }).then(() => {
          this.$message.success('อัพเดตสถานะเผยแพร่สำเร็จ');
        }).catch(() => {
          this.$message.error('อัพเดตสถานะไม่สำเร็จ');
          this.getList(); // Revert on failure
        });
      },
      handleNewStatusChange(index, row) {
        updateNewStatus({ ids: row.id, newStatus: row.newStatus }).then(() => {
          this.$message.success('อัพเดตสถานะสินค้าใหม่สำเร็จ');
        }).catch(() => {
          this.$message.error('อัพเดตสถานะไม่สำเร็จ');
          this.getList();
        });
      },
      handleRecommendStatusChange(index, row) {
        updateRecommendStatus({ ids: row.id, recommendStatus: row.recommandStatus }).then(() => {
          this.$message.success('อัพเดตสถานะแนะนำสำเร็จ');
        }).catch(() => {
          this.$message.error('อัพเดตสถานะไม่สำเร็จ');
          this.getList();
        });
      }

    }
  }
</script>


<style scoped>
.filter-container {
  margin-bottom: 20px;
}
.operate-container {
  margin-bottom: 20px;
}
.btn-add {
  float: right;
}
</style>
