<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="月份" prop="month">
        <el-input
          v-model="queryParams.month"
          placeholder="请输入月份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动名称" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入活动名称"
          clearable
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
          v-hasPermi="['amdas:apa:add']"
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
          v-hasPermi="['amdas:apa:edit']"
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
          v-hasPermi="['amdas:apa:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['amdas:apa:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apaList" :span-method="objectSpanMethod">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="月份" align="center" prop="month" />
      <el-table-column label="活动名称" align="center" prop="activityName" />
      <el-table-column label="参与人数" align="center" prop="participantsNum" />
      <el-table-column label="总资源（元）" align="center" prop="totalResource" />
      <el-table-column label="已兑换资源（元）" align="center" prop="convertedResource" />
      <el-table-column label="人均使用资源（元）" align="center" prop="perCapitaUseResource" />
      <el-table-column label="和掌柜资源（话费：元）" align="center" prop="shopkeeperResourceBill" />
      <el-table-column label="和掌柜资源（流量：GB）" align="center" prop="shopkeeperResourceFlowRate" />
      <el-table-column label="和掌柜资源（折让：元）" align="center" prop="shopkeeperResourceAllowance" />
      <el-table-column label="微厅资源（话费：元）" align="center" prop="wtResourceBill" />
      <el-table-column label="微厅资源（流量：GB）" align="center" prop="wtResourceFlowRate" />
      <el-table-column label="微厅资源（折让：元）" align="center" prop="wtResourceAllowance" />
      <el-table-column label="手厅资源（话费：元）" align="center" prop="stResourceBill" />
      <el-table-column label="手厅资源（流量：GB）" align="center" prop="stResourceFlowRate" />
      <el-table-column label="手厅资源（折让：元）" align="center" prop="stResourceAllowance" />
      <el-table-column label="仅当月活跃" align="center" prop="monthActive" />
      <el-table-column label="占比" align="center" prop="monthRatio" />
      <el-table-column label="连续活跃2月" align="center" prop="towMonthActive" />
      <el-table-column label="占比" align="center" prop="towMonthRatio" />
      <el-table-column label="连续活跃3月" align="center" prop="threeMonthActive" />
      <el-table-column label="占比" align="center" prop="threeMonthRatio" />
      <el-table-column label="连续活跃4月" align="center" prop="fourMonthActive" />
      <el-table-column label="占比" align="center" prop="fourMonthRatio" />
      <el-table-column label="连续活跃5月" align="center" prop="fiveMonthActive" />
      <el-table-column label="占比" align="center" prop="fiveMonthRatio" />
      <el-table-column label="连续活跃6月" align="center" prop="sixMonthActive" />
      <el-table-column label="占比" align="center" prop="sixMonthRatio" />
      <el-table-column label="查充办客户数" align="center" prop="checkCustomersNum" />
      <el-table-column label="占比" align="center" prop="checkCustomersRatio" />
      <el-table-column label="当月新增客户数" align="center" prop="monthNewcustomersNum" />
      <el-table-column label="占比" align="center" prop="monthNewcustomersRatio" />
      <el-table-column label="有销量客户数" align="center" prop="salesCustomersNum" />
      <el-table-column label="占比" align="center" prop="salesCustomersRatio" />
      <el-table-column label="业务总销量" align="center" prop="totalBusinessSales" />
      <el-table-column label="人均销量" align="center" prop="salesPerCapita" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['amdas:apa:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['amdas:apa:remove']"
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

    <!-- 添加或修改活动参与分析对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1250px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-col :span="8">
          <el-form-item label="月份" prop="month">
            <el-input v-model="form.month" placeholder="请输入月份" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="活动名称" prop="activityName">
            <el-input v-model="form.activityName" placeholder="请输入活动名称" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="参与人数" prop="participantsNum">
            <el-input v-model="form.participantsNum" placeholder="请输入参与人数" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="总资源（元）" prop="totalResource">
            <el-input v-model="form.totalResource" placeholder="请输入总资源（元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="已兑换资源（元）" prop="convertedResource">
            <el-input v-model="form.convertedResource" placeholder="请输入已兑换资源（元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人均使用资源（元）" prop="perCapitaUseResource">
            <el-input v-model="form.perCapitaUseResource" placeholder="请输入人均使用资源（元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="和掌柜资源（话费：元）" prop="shopkeeperResourceBill">
            <el-input v-model="form.shopkeeperResourceBill" placeholder="请输入和掌柜资源（话费：元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="和掌柜资源（流量：GB）" prop="shopkeeperResourceFlowRate">
            <el-input v-model="form.shopkeeperResourceFlowRate" placeholder="请输入和掌柜资源（流量：GB）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="和掌柜资源（折让：元）" prop="shopkeeperResourceAllowance">
            <el-input v-model="form.shopkeeperResourceAllowance" placeholder="请输入和掌柜资源（折让：元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="微厅资源（话费：元）" prop="wtResourceBill">
            <el-input v-model="form.wtResourceBill" placeholder="请输入微厅资源（话费：元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="微厅资源（流量：GB）" prop="wtResourceFlowRate">
            <el-input v-model="form.wtResourceFlowRate" placeholder="请输入微厅资源（流量：GB）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="微厅资源（折让：元）" prop="wtResourceAllowance">
            <el-input v-model="form.wtResourceAllowance" placeholder="请输入微厅资源（折让：元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手厅资源（话费：元）" prop="stResourceBill">
            <el-input v-model="form.stResourceBill" placeholder="请输入手厅资源（话费：元）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手厅资源（流量：GB）" prop="stResourceFlowRate">
            <el-input v-model="form.stResourceFlowRate" placeholder="请输入手厅资源（流量：GB）" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手厅资源（折让：元）" prop="stResourceAllowance">
            <el-input v-model="form.stResourceAllowance" placeholder="请输入手厅资源（折让：元）" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="仅当月活跃" prop="monthActive">
            <el-input v-model="form.monthActive" placeholder="请输入仅当月活跃" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="monthRatio">
            <el-input v-model="form.monthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="连续活跃2月" prop="towMonthActive">
            <el-input v-model="form.towMonthActive" placeholder="请输入连续活跃2月" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="towMonthRatio">
            <el-input v-model="form.towMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="连续活跃3月" prop="threeMonthActive">
            <el-input v-model="form.threeMonthActive" placeholder="请输入连续活跃3月" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="threeMonthRatio">
            <el-input v-model="form.threeMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="连续活跃4月" prop="fourMonthActive">
            <el-input v-model="form.fourMonthActive" placeholder="请输入连续活跃4月" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="fourMonthRatio">
            <el-input v-model="form.fourMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="连续活跃5月" prop="fiveMonthActive">
            <el-input v-model="form.fiveMonthActive" placeholder="请输入连续活跃5月" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="fiveMonthRatio">
            <el-input v-model="form.fiveMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="连续活跃6月" prop="sixMonthActive">
            <el-input v-model="form.sixMonthActive" placeholder="请输入连续活跃6月" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="sixMonthRatio">
            <el-input v-model="form.sixMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="查充办客户数" prop="checkCustomersNum">
            <el-input v-model="form.checkCustomersNum" placeholder="请输入查充办客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="checkCustomersRatio">
            <el-input v-model="form.checkCustomersRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="当月新增客户数" prop="monthNewcustomersNum">
            <el-input v-model="form.monthNewcustomersNum" placeholder="请输入当月新增客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="monthNewcustomersRatio">
            <el-input v-model="form.monthNewcustomersRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="有销量客户数" prop="salesCustomersNum">
            <el-input v-model="form.salesCustomersNum" placeholder="请输入有销量客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="占比" prop="salesCustomersRatio">
            <el-input v-model="form.salesCustomersRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="业务总销量" prop="totalBusinessSales">
            <el-input v-model="form.totalBusinessSales" placeholder="请输入业务总销量" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="人均销量" prop="salesPerCapita">
            <el-input v-model="form.salesPerCapita" placeholder="请输入人均销量" />
          </el-form-item>
        </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listApa, getApa, delApa, addApa, updateApa } from "@/api/amdas/apa";

export default {
  name: "Apa",
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
      // 活动参与分析表格数据
      apaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        month: null,
        activityName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      monthIndexArr: [], // 存放month列每一行记录的合并数  控制month的合并
      monthIndexRecode: 0 // monthIndexArr的索引
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询活动参与分析列表 */
    getList() {
      this.loading = true;
      listApa(this.queryParams).then(response => {
        this.apaList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.getSpanArr(this.apaList)
      });
    },
    // 合并表格
    getSpanArr(data) {
      if (data.length > 0) {
        this.monthIndexArr = []
        for (var i = 0; i < data.length; i++) {
          if (i === 0) {
            this.monthIndexArr.push(1)
            this.monthIndexRecode = 0
          } else {
            // 判断当前元素与上一个元素是否相同(第2列)
            if (data[i].month === data[i - 1].month) {
              this.monthIndexArr[this.monthIndexRecode] += 1
              this.monthIndexArr.push(0)
            } else {
              this.monthIndexArr.push(1)
              this.monthIndexRecode = i
            }
          }
        }
      }
    },
    objectSpanMethod ({ row, column, rowIndex, columnIndex }) { // 框架自带合并方法
      if (columnIndex > 3) {
        return
      }
      if(columnIndex === 1){
        const _row = this.monthIndexArr[rowIndex]
        const _col = _row > 0 ? 1 : 0
        return {
          rowspan: _row,
          colspan: _col,
        }
      }
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
        month: null,
        activityName: null,
        participantsNum: null,
        totalResource: null,
        convertedResource: null,
        perCapitaUseResource: null,
        shopkeeperResourceBill: null,
        shopkeeperResourceFlowRate: null,
        shopkeeperResourceAllowance: null,
        wtResourceBill: null,
        wtResourceFlowRate: null,
        wtResourceAllowance: null,
        stResourceBill: null,
        stResourceFlowRate: null,
        stResourceAllowance: null,
        monthActive: null,
        monthRatio: null,
        towMonthActive: null,
        towMonthRatio: null,
        threeMonthActive: null,
        threeMonthRatio: null,
        fourMonthActive: null,
        fourMonthRatio: null,
        fiveMonthActive: null,
        fiveMonthRatio: null,
        sixMonthActive: null,
        sixMonthRatio: null,
        checkCustomersNum: null,
        checkCustomersRatio: null,
        monthNewcustomersNum: null,
        monthNewcustomersRatio: null,
        salesCustomersNum: null,
        salesCustomersRatio: null,
        totalBusinessSales: null,
        salesPerCapita: null
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
      this.title = "添加活动参与分析";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApa(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动参与分析";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApa(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApa(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除活动参与分析编号为"' + ids + '"的数据项？').then(function() {
        return delApa(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('amdas/apa/export', {
        ...this.queryParams
      }, `apa_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
