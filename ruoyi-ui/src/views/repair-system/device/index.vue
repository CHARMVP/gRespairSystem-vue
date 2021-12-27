<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备购买日期">
        <el-date-picker
          v-model="daterangeBuyTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="生产线" prop="productionLine">
        <el-input
          v-model="queryParams.productionLine"
          placeholder="请输入生产线"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产厂家" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入生产厂家"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备型号" prop="type">
        <el-input
          v-model="queryParams.type"
          placeholder="请输入设备型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="固定资产号" prop="fixedAssetId">
        <el-input
          v-model="queryParams.fixedAssetId"
          placeholder="请输入固定资产号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商联系方式" prop="supplierPhoneNumber">
        <el-input
          v-model="queryParams.supplierPhoneNumber"
          placeholder="请输入供应商联系方式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编号" prop="number">
        <el-input
          v-model="queryParams.number"
          placeholder="请输入设备编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['repair-system:device:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['repair-system:device:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['repair-system:device:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['repair-system:device:export']"
        >导出</el-button>
      </el-col>
        <!-- 下载二维码 -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportQRCode"
          v-hasPermi="['repair-system:device:export']"
        >下载二维码</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备主键" align="center" prop="id" />
      <el-table-column label="设备名称" align="center" prop="name" />
      <el-table-column label="设备购买日期" align="center" prop="buyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产线" align="center" prop="productionLine" />
      <el-table-column label="生产厂家" align="center" prop="manufacturer" />
      <el-table-column label="设备型号" align="center" prop="type" />
      <el-table-column label="固定资产号" align="center" prop="fixedAssetId" />
      <el-table-column label="供应商联系方式" align="center" prop="supplierPhoneNumber" />
      <el-table-column label="设备编号" align="center" prop="number" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['repair-system:device:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['repair-system:device:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改设备信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备购买日期" prop="buyTime">
          <el-date-picker clearable size="small"
            v-model="form.buyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择设备购买日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="生产线" prop="productionLine">
          <el-input v-model="form.productionLine" placeholder="请输入生产线" />
        </el-form-item>
        <el-form-item label="生产厂家" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入生产厂家" />
        </el-form-item>
        <el-form-item label="设备型号" prop="type">
          <el-input v-model="form.type" placeholder="请输入设备型号" />
        </el-form-item>
        <el-form-item label="固定资产号" prop="fixedAssetId">
          <el-input v-model="form.fixedAssetId" placeholder="请输入固定资产号" />
        </el-form-item>
        <el-form-item label="供应商联系方式" prop="supplierPhoneNumber">
          <el-input v-model="form.supplierPhoneNumber" placeholder="请输入供应商联系方式" />
        </el-form-item>
        <el-form-item label="设备编号" prop="number">
          <el-input v-model="form.number" placeholder="请输入设备编号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listDevice, getDevice, delDevice, addDevice, updateDevice, downloadQRCode} from "@/api/repair-system/device";
import axios from "axios";

export default {
  name: "Device",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 设备信息表格数据
      deviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 设备编号时间范围
      daterangeBuyTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        buyTime: null,
        productionLine: null,
        manufacturer: null,
        type: null,
        fixedAssetId: null,
        supplierPhoneNumber: null,
        number: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //下载路径
      path: null
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeBuyTime && '' != this.daterangeBuyTime) {
        this.queryParams.params["beginBuyTime"] = this.daterangeBuyTime[0];
        this.queryParams.params["endBuyTime"] = this.daterangeBuyTime[1];
      }
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        buyTime: null,
        productionLine: null,
        manufacturer: null,
        type: null,
        fixedAssetId: null,
        supplierPhoneNumber: null,
        number: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeBuyTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDevice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDevice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDevice(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除设备信息编号为"' + ids + '"的数据项？').then(function() {
        return delDevice(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {

      this.download('repair-system/device/export', {
        ...this.queryParams
      }, `device_${new Date().getTime()}.xlsx`)
    },

    /** 导出二维码操作 */
    handleExportQRCode() {
      downloadQRCode();
      const fileName = "/profile/upload/download/QRCode.zip";
      this.$download.resource(fileName);
    },

  }
};
</script>
