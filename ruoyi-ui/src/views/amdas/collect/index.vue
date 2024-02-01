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
          v-hasPermi="['amdas:collect:add']"
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
          v-hasPermi="['amdas:collect:edit']"
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
          v-hasPermi="['amdas:collect:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['amdas:collect:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="collectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="月份" align="center" prop="month" />
      <el-table-column label="参与活动个数" align="center" prop="activityNum" />
      <el-table-column label="参与人数" align="center" prop="participantsNum" />
      <el-table-column align="center" label="各渠道参与情况">
        <el-table-column label="和掌柜客户数" align="center" prop="hzgClientNum" />
        <el-table-column label="微厅客户数" align="center" prop="wtClientNum" />
        <el-table-column label="wap客户数" align="center" prop="wapClientNum" />
        <el-table-column label="手厅客户数" align="center" prop="stClientNum" />
      </el-table-column>
      <el-table-column align="center" label="参与活动用户连续活跃情况">
        <el-table-column label="一个月" align="center" prop="oneMonthValue" />
        <el-table-column label="占比" align="center" prop="oneMonthRatio" />
        <el-table-column label="两个月" align="center" prop="twoMonthValue" />
        <el-table-column label="占比" align="center" prop="twoMonthRatio" />
        <el-table-column label="三个月" align="center" prop="threeMonthValue" />
        <el-table-column label="占比" align="center" prop="threeMonthRatio" />
        <el-table-column label="四个月" align="center" prop="fourMonthValue" />
        <el-table-column label="占比" align="center" prop="fourMonthRatio" />
        <el-table-column label="五个月" align="center" prop="fiveMonthValue" />
        <el-table-column label="占比" align="center" prop="fiveMonthRatio" />
        <el-table-column label="六个月" align="center" prop="sixMonthValue" />
        <el-table-column label="占比" align="center" prop="sixMonthRatio" />
      </el-table-column>
      <el-table-column align="center" label="高粘性行为客户情况">
        <el-table-column label="查充办客户数" align="center" prop="ccbClientNum" />
        <el-table-column label="查充办客户占比" align="center" prop="ccbClientRatio" />
      </el-table-column>
      <el-table-column align="center" label="新增客户参与情况">
        <el-table-column label="当月新增客户数" align="center" prop="dyxzClientNum" />
        <el-table-column label="新增占比" align="center" prop="dyxzClientRatio" />
      </el-table-column>
      <el-table-column align="center" label="营销活动资源使用情况">
        <el-table-column label="流量资源使用" align="center" prop="trafficResourceUsage" />
        <el-table-column label="话费资源使用" align="center" prop="phoneResourceUsage" />
        <el-table-column label="折让资源使用" align="center" prop="crResourceUsage" />
        <el-table-column label="其他资源使用" align="center" prop="otherResourceUsage" />
      </el-table-column>
      <el-table-column align="center" label="参与活动客户销售贡献情况">
        <el-table-column label="有销量客户数" align="center" prop="yxlClientNum" />
        <el-table-column label="有销量客户占比" align="center" prop="yxlClientRatio" />
        <el-table-column label="参与活动用户业务总销量" align="center" prop="cyhdyhywzxl" />
        <el-table-column label="销售贡献(笔/人)" align="center" prop="salesContribution" />
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['amdas:collect:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['amdas:collect:remove']"
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

    <!-- 添加或修改汇总对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="170px">
        <el-col :span="8">
          <el-form-item label="月份" prop="month">
            <el-input v-model="form.month" placeholder="请输入月份" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="参与活动个数" prop="activityNum">
            <el-input v-model="form.activityNum" placeholder="请输入参与活动个数" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="参与人数" prop="participantsNum">
            <el-input v-model="form.participantsNum" placeholder="请输入参与人数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="和掌柜客户数" prop="hzgClientNum">
            <el-input v-model="form.hzgClientNum" placeholder="请输入和掌柜客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="微厅客户数" prop="wtClientNum">
            <el-input v-model="form.wtClientNum" placeholder="请输入微厅客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="wap客户数" prop="wapClientNum">
            <el-input v-model="form.wapClientNum" placeholder="请输入wap客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手厅客户数" prop="stClientNum">
            <el-input v-model="form.stClientNum" placeholder="请输入手厅客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="一个月" prop="oneMonthValue">
            <el-input v-model="form.oneMonthValue" placeholder="请输入一个月" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="oneMonthRatio">
            <el-input v-model="form.oneMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="两个月" prop="twoMonthValue">
            <el-input v-model="form.twoMonthValue" placeholder="请输入两个月" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="twoMonthRatio">
            <el-input v-model="form.twoMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="三个月" prop="threeMonthValue">
            <el-input v-model="form.threeMonthValue" placeholder="请输入三个月" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="threeMonthRatio">
            <el-input v-model="form.threeMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="四个月" prop="fourMonthValue">
            <el-input v-model="form.fourMonthValue" placeholder="请输入四个月" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="fourMonthRatio">
            <el-input v-model="form.fourMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="五个月" prop="fiveMonthValue">
            <el-input v-model="form.fiveMonthValue" placeholder="请输入五个月" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="fiveMonthRatio">
            <el-input v-model="form.fiveMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="六个月" prop="sixMonthValue">
            <el-input v-model="form.sixMonthValue" placeholder="请输入六个月" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="sixMonthRatio">
            <el-input v-model="form.sixMonthRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="查充办客户数" prop="ccbClientNum">
            <el-input v-model="form.ccbClientNum" placeholder="请输入查充办客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="查充办客户占比" prop="ccbClientRatio">
            <el-input v-model="form.ccbClientRatio" placeholder="请输入查充办客户占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="当月新增客户数" prop="dyxzClientNum">
            <el-input v-model="form.dyxzClientNum" placeholder="请输入当月新增客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="新增占比" prop="dyxzClientRatio">
            <el-input v-model="form.dyxzClientRatio" placeholder="请输入新增占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="流量资源使用" prop="trafficResourceUsage">
            <el-input v-model="form.trafficResourceUsage" placeholder="请输入流量资源使用" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="话费资源使用" prop="phoneResourceUsage">
            <el-input v-model="form.phoneResourceUsage" placeholder="请输入话费资源使用" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="折让资源使用" prop="crResourceUsage">
            <el-input v-model="form.crResourceUsage" placeholder="请输入折让资源使用" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="其他资源使用" prop="otherResourceUsage">
            <el-input v-model="form.otherResourceUsage" placeholder="请输入其他资源使用" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="有销量客户数" prop="yxlClientNum">
            <el-input v-model="form.yxlClientNum" placeholder="请输入有销量客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="有销量客户占比" prop="yxlClientRatio">
            <el-input v-model="form.yxlClientRatio" placeholder="请输入有销量客户占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参与活动用户业务总销量" prop="cyhdyhywzxl">
            <el-input v-model="form.cyhdyhywzxl" placeholder="请输入参与活动用户业务总销量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="销售贡献(笔/人)" prop="salesContribution">
            <el-input v-model="form.salesContribution" placeholder="请输入销售贡献(笔/人)" />
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
import { listCollect, getCollect, delCollect, addCollect, updateCollect } from "@/api/amdas/collect";

export default {
  name: "Collect",
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
      // 汇总表格数据
      collectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        month: null,
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
    /** 查询汇总列表 */
    getList() {
      this.loading = true;
      listCollect(this.queryParams).then(response => {
        this.collectList = response.rows;
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
        month: null,
        activityNum: null,
        participantsNum: null,
        hzgClientNum: null,
        wtClientNum: null,
        wapClientNum: null,
        stClientNum: null,
        oneMonthValue: null,
        oneMonthRatio: null,
        twoMonthValue: null,
        twoMonthRatio: null,
        threeMonthValue: null,
        threeMonthRatio: null,
        fourMonthValue: null,
        fourMonthRatio: null,
        fiveMonthValue: null,
        fiveMonthRatio: null,
        sixMonthValue: null,
        sixMonthRatio: null,
        ccbClientNum: null,
        ccbClientRatio: null,
        dyxzClientNum: null,
        dyxzClientRatio: null,
        trafficResourceUsage: null,
        phoneResourceUsage: null,
        crResourceUsage: null,
        otherResourceUsage: null,
        yxlClientNum: null,
        yxlClientRatio: null,
        cyhdyhywzxl: null,
        salesContribution: null
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
      this.title = "添加汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCollect(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改汇总";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCollect(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCollect(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除汇总编号为"' + ids + '"的数据项？').then(function() {
        return delCollect(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('amdas/collect/export', {
        ...this.queryParams
      }, `collect_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
