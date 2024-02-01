<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="地市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入地市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日期" prop="hdcyDate">
        <el-date-picker clearable
          v-model="queryParams.hdcyDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="渠道" prop="channel">
        <el-input
          v-model="queryParams.channel"
          placeholder="请输入渠道"
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
          v-hasPermi="['mads:ap:add']"
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
          v-hasPermi="['mads:ap:edit']"
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
          v-hasPermi="['mads:ap:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mads:ap:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="地市" align="center" prop="city" />
      <el-table-column label="日期" align="center" prop="hdcyDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.hdcyDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="渠道" align="center" prop="channel" />
      <el-table-column align="center" label="活动页面">
        <el-table-column label="pv" align="center" prop="pv" />
        <el-table-column label="参与用户数" align="center" prop="uv" />
      </el-table-column>
      <el-table-column label="参与活动用户累计" align="center" prop="ljUv" />
      <el-table-column label="参与活动新增原生用户数" align="center" prop="xzysUv" />
      <el-table-column label="占比" align="center" prop="xzysRatio" />
      <el-table-column label="参与活动新增月活用户数" align="center" prop="xzyhUv" />
      <el-table-column label="占比" align="center" prop="xzyhRatio" />
      <el-table-column align="center" label="参与活动用户活跃情况">
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
      <el-table-column label="有业务办理行为客户数" align="center" prop="yywblxwVv" />
      <el-table-column label="占比" align="center" prop="yywblxwRatio" />
      <el-table-column label="业务办理成功客户数" align="center" prop="ywblcgVv" />
      <el-table-column label="占比" align="center" prop="ywblcgRatio" />
      <el-table-column label="分享用户数" align="center" prop="fxUv" />
      <el-table-column label="占比" align="center" prop="fxRatio" />
      <el-table-column label="受邀未参与活动用户数" align="center" prop="sywcyhdUv" />
      <el-table-column label="占比" align="center" prop="sywcyhdRatio" />
      <el-table-column label="任务一参与客户数" align="center" prop="rwycyVv" />
      <el-table-column label="任务一完成客户数" align="center" prop="rwywcVv" />
      <el-table-column label="任务二参与客户数" align="center" prop="rwecyVv" />
      <el-table-column label="任务二完成客户数" align="center" prop="rwewcVv" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mads:ap:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mads:ap:remove']"
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

    <!-- 添加或修改活动参与情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="170px">
        <el-col :span="12">
          <el-form-item label="地市" prop="city">
            <el-input v-model="form.city" placeholder="请输入地市" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="日期" prop="hdcyDate">
            <el-date-picker clearable
                            v-model="form.hdcyDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="渠道" prop="channel">
            <el-input v-model="form.channel" placeholder="请输入渠道" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="pv" prop="pv">
            <el-input v-model="form.pv" placeholder="请输入pv" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参与用户数" prop="uv">
            <el-input v-model="form.uv" placeholder="请输入参与用户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参与活动用户累计" prop="ljUv">
            <el-input v-model="form.ljUv" placeholder="请输入参与活动用户累计" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参与活动新增原生用户数" prop="xzysUv">
            <el-input v-model="form.xzysUv" placeholder="请输入参与活动新增原生用户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="xzysRatio">
            <el-input v-model="form.xzysRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参与活动新增月活用户数" prop="xzyhUv">
            <el-input v-model="form.xzyhUv" placeholder="请输入参与活动新增月活用户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="xzyhRatio">
            <el-input v-model="form.xzyhRatio" placeholder="请输入占比" />
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
          <el-form-item label="有业务办理行为客户数" prop="yywblxwVv">
            <el-input v-model="form.yywblxwVv" placeholder="请输入有业务办理行为客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="yywblxwRatio">
            <el-input v-model="form.yywblxwRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务办理成功客户数" prop="ywblcgVv">
            <el-input v-model="form.ywblcgVv" placeholder="请输入业务办理成功客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="ywblcgRatio">
            <el-input v-model="form.ywblcgRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分享用户数" prop="fxUv">
            <el-input v-model="form.fxUv" placeholder="请输入分享用户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="fxRatio">
            <el-input v-model="form.fxRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="受邀未参与活动用户数" prop="sywcyhdUv">
            <el-input v-model="form.sywcyhdUv" placeholder="请输入受邀未参与活动用户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比" prop="sywcyhdRatio">
            <el-input v-model="form.sywcyhdRatio" placeholder="请输入占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="任务一参与客户数" prop="rwycyVv">
            <el-input v-model="form.rwycyVv" placeholder="请输入任务一参与客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="任务一完成客户数" prop="rwywcVv">
            <el-input v-model="form.rwywcVv" placeholder="请输入任务一完成客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="任务二参与客户数" prop="rwecyVv">
            <el-input v-model="form.rwecyVv" placeholder="请输入任务二参与客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="任务二完成客户数" prop="rwewcVv">
            <el-input v-model="form.rwewcVv" placeholder="请输入任务二完成客户数" />
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
import { listAp, getAp, delAp, addAp, updateAp } from "@/api/mads/ap";

export default {
  name: "Ap",
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
      // 活动参与情况表格数据
      apList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        city: null,
        hdcyDate: null,
        channel: null,
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
    /** 查询活动参与情况列表 */
    getList() {
      this.loading = true;
      listAp(this.queryParams).then(response => {
        this.apList = response.rows;
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
        city: null,
        hdcyDate: null,
        channel: null,
        pv: null,
        uv: null,
        ljUv: null,
        xzysUv: null,
        xzysRatio: null,
        xzyhUv: null,
        xzyhRatio: null,
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
        yywblxwVv: null,
        yywblxwRatio: null,
        ywblcgVv: null,
        ywblcgRatio: null,
        fxUv: null,
        fxRatio: null,
        sywcyhdUv: null,
        sywcyhdRatio: null,
        rwycyVv: null,
        rwywcVv: null,
        rwecyVv: null,
        rwewcVv: null
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
      this.title = "添加活动参与情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAp(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动参与情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAp(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除活动参与情况编号为"' + ids + '"的数据项？').then(function() {
        return delAp(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mads/ap/export', {
        ...this.queryParams
      }, `ap_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
