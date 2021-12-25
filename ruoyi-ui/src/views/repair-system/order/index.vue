<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备编号" prop="deviceNumber">
        <el-input
          v-model="queryParams.deviceNumber"
          placeholder="请输入设备编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备生产线" prop="deviceProductionLine">
        <el-input
          v-model="queryParams.deviceProductionLine"
          placeholder="请输入设备生产线"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报修时间">
        <el-date-picker
          v-model="daterangeSubmitTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="维修开始时间">
        <el-date-picker
          v-model="daterangeRepairStartTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="维修结束时间">
        <el-date-picker
          v-model="daterangeRepairEndTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="报修人" prop="submitMan">
        <el-input
          v-model="queryParams.submitMan"
          placeholder="请输入报修人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维修人" prop="repairMan">
        <el-input
          v-model="queryParams.repairMan"
          placeholder="请输入维修人"
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
          v-hasPermi="['repair-system:order:add']"
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
          v-hasPermi="['repair-system:order:edit']"
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
          v-hasPermi="['repair-system:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['repair-system:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="维修记录id" align="center" prop="id" />
      <el-table-column label="设备编号" align="center" prop="deviceNumber" />
      <el-table-column label="设备名称" align="center" prop="deviceName" />
      <el-table-column label="设备购买时间" align="center" prop="deviceBuyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deviceBuyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="设备生产线" align="center" prop="deviceProductionLine" />
      <el-table-column label="报修时间" align="center" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="维修开始时间" align="center" prop="repairStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.repairStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="维修结束时间" align="center" prop="repairEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.repairEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="故障部位" align="center" prop="faultPosition" />
      <el-table-column label="故障原因" align="center" prop="faultReason" />
      <el-table-column label="修理过程" align="center" prop="repairProcess" />
      <el-table-column label="更换设备情况" align="center" prop="deviceReplacement" />
      <el-table-column label="修理情况" align="center" prop="repairResult" />
      <el-table-column label="报修人" align="center" prop="submitMan" />
      <el-table-column label="维修人" align="center" prop="repairMan" />
      <el-table-column label="验收人" align="center" prop="checkMan" />
      <el-table-column label="备注" align="center" prop="note" />
      <el-table-column label="更换备件型号" align="center" prop="sparePartType" />
      <el-table-column label="备件剩余库存" align="center" prop="sparePartStockNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['repair-system:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['repair-system:order:remove']"
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

    <!-- 添加或修改报修信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备编号" prop="deviceNumber">
          <el-input v-model="form.deviceNumber" placeholder="请输入设备编号" />
        </el-form-item>
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备购买时间" prop="deviceBuyTime">
          <el-date-picker clearable size="small"
            v-model="form.deviceBuyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择设备购买时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="设备生产线" prop="deviceProductionLine">
          <el-input v-model="form.deviceProductionLine" placeholder="请输入设备生产线" />
        </el-form-item>
        <el-form-item label="报修时间" prop="submitTime">
          <el-date-picker clearable size="small"
            v-model="form.submitTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择报修时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="维修开始时间" prop="repairStartTime">
          <el-date-picker clearable size="small"
            v-model="form.repairStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择维修开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="维修结束时间" prop="repairEndTime">
          <el-date-picker clearable size="small"
            v-model="form.repairEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择维修结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="故障部位" prop="faultPosition">
          <el-input v-model="form.faultPosition" placeholder="请输入故障部位" />
        </el-form-item>
        <el-form-item label="故障原因" prop="faultReason">
          <el-input v-model="form.faultReason" placeholder="请输入故障原因" />
        </el-form-item>
        <el-form-item label="修理过程" prop="repairProcess">
          <el-input v-model="form.repairProcess" placeholder="请输入修理过程" />
        </el-form-item>
        <el-form-item label="更换设备情况" prop="deviceReplacement">
          <el-input v-model="form.deviceReplacement" placeholder="请输入更换设备情况" />
        </el-form-item>
        <el-form-item label="修理情况" prop="repairResult">
          <el-input v-model="form.repairResult" placeholder="请输入修理情况" />
        </el-form-item>
        <el-form-item label="报修人" prop="submitMan">
          <el-input v-model="form.submitMan" placeholder="请输入报修人" />
        </el-form-item>
        <el-form-item label="维修人" prop="repairMan">
          <el-input v-model="form.repairMan" placeholder="请输入维修人" />
        </el-form-item>
        <el-form-item label="验收人" prop="checkMan">
          <el-input v-model="form.checkMan" placeholder="请输入验收人" />
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="form.note" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="更换备件型号" prop="sparePartType">
          <el-input v-model="form.sparePartType" placeholder="请输入更换备件型号" />
        </el-form-item>
        <el-form-item label="备件剩余库存" prop="sparePartStockNumber">
          <el-input v-model="form.sparePartStockNumber" placeholder="请输入备件剩余库存" />
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/repair-system/order";

export default {
  name: "Order",
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
      // 报修信息表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备件剩余库存时间范围
      daterangeSubmitTime: [],
      // 备件剩余库存时间范围
      daterangeRepairStartTime: [],
      // 备件剩余库存时间范围
      daterangeRepairEndTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceNumber: null,
        deviceName: null,
        deviceProductionLine: null,
        submitTime: null,
        repairStartTime: null,
        repairEndTime: null,
        submitMan: null,
        repairMan: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询报修信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeSubmitTime && '' != this.daterangeSubmitTime) {
        this.queryParams.params["beginSubmitTime"] = this.daterangeSubmitTime[0];
        this.queryParams.params["endSubmitTime"] = this.daterangeSubmitTime[1];
      }
      if (null != this.daterangeRepairStartTime && '' != this.daterangeRepairStartTime) {
        this.queryParams.params["beginRepairStartTime"] = this.daterangeRepairStartTime[0];
        this.queryParams.params["endRepairStartTime"] = this.daterangeRepairStartTime[1];
      }
      if (null != this.daterangeRepairEndTime && '' != this.daterangeRepairEndTime) {
        this.queryParams.params["beginRepairEndTime"] = this.daterangeRepairEndTime[0];
        this.queryParams.params["endRepairEndTime"] = this.daterangeRepairEndTime[1];
      }
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        deviceNumber: null,
        deviceName: null,
        deviceBuyTime: null,
        deviceProductionLine: null,
        submitTime: null,
        repairStartTime: null,
        repairEndTime: null,
        faultPosition: null,
        faultReason: null,
        repairProcess: null,
        deviceReplacement: null,
        repairResult: null,
        submitMan: null,
        repairMan: null,
        checkMan: null,
        note: null,
        sparePartType: null,
        sparePartStockNumber: null
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
      this.daterangeSubmitTime = [];
      this.daterangeRepairStartTime = [];
      this.daterangeRepairEndTime = [];
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
      this.title = "添加报修信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改报修信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除报修信息编号为"' + ids + '"的数据项？').then(function() {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('repair-system/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
