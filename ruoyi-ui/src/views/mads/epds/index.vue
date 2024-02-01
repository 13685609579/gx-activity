<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="epdsDate">
        <el-date-picker clearable
          v-model="queryParams.epdsDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="地市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入地市"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['mads:epds:add']"
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
          v-hasPermi="['mads:epds:edit']"
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
          v-hasPermi="['mads:epds:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mads:epds:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="epdsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="日期" align="center" prop="epdsDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.epdsDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地市" align="center" prop="city" />
      <el-table-column label="渠道" align="center" prop="channel" />
      <el-table-column label="活动参与用户数" align="center" prop="hdcyUv" />
      <el-table-column label="流量券发放量" align="center" prop="llqffl" />
      <el-table-column label="流量券使用量" align="center" prop="llqsyl" />
      <el-table-column label="兑换率" align="center" prop="llqDhRate" />
      <el-table-column label="话费券发放量" align="center" prop="hfqffl" />
      <el-table-column label="话费券使用量" align="center" prop="hfqsyl" />
      <el-table-column label="兑换率" align="center" prop="hfqDhRate" />
      <el-table-column label="业务券发放量" align="center" prop="ywqffl" />
      <el-table-column label="业务券使用量" align="center" prop="ywqsyl" />
      <el-table-column label="兑换率" align="center" prop="ywqDhRate" />
      <el-table-column label="充值券发放量" align="center" prop="czqffl" />
      <el-table-column label="充值券使用量" align="center" prop="czqsyl" />
      <el-table-column label="兑换率" align="center" prop="czqDhRate" />
      <el-table-column label="获奖用户数" align="center" prop="hjUv" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mads:epds:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mads:epds:remove']"
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

    <!-- 添加或修改活动奖品发放情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="880px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-col :span="12">
          <el-form-item label="日期" prop="epdsDate">
            <el-date-picker clearable
                            v-model="form.epdsDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地市" prop="city">
            <el-input v-model="form.city" placeholder="请输入地市" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="渠道" prop="channel">
            <el-input v-model="form.channel" placeholder="请输入渠道" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="活动参与用户数" prop="hdcyUv">
            <el-input v-model="form.hdcyUv" placeholder="请输入活动参与用户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="流量券发放量" prop="llqffl">
            <el-input v-model="form.llqffl" placeholder="请输入流量券发放量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="流量券使用量" prop="llqsyl">
            <el-input v-model="form.llqsyl" placeholder="请输入流量券使用量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="兑换率" prop="llqDhRate">
            <el-input v-model="form.llqDhRate" placeholder="请输入兑换率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="话费券发放量" prop="hfqffl">
            <el-input v-model="form.hfqffl" placeholder="请输入话费券发放量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="话费券使用量" prop="hfqsyl">
            <el-input v-model="form.hfqsyl" placeholder="请输入话费券使用量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="兑换率" prop="hfqDhRate">
            <el-input v-model="form.hfqDhRate" placeholder="请输入兑换率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务券发放量" prop="ywqffl">
            <el-input v-model="form.ywqffl" placeholder="请输入业务券发放量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务券使用量" prop="ywqsyl">
            <el-input v-model="form.ywqsyl" placeholder="请输入业务券使用量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="兑换率" prop="ywqDhRate">
            <el-input v-model="form.ywqDhRate" placeholder="请输入兑换率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="充值券发放量" prop="czqffl">
            <el-input v-model="form.czqffl" placeholder="请输入充值券发放量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="充值券使用量" prop="czqsyl">
            <el-input v-model="form.czqsyl" placeholder="请输入充值券使用量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="兑换率" prop="czqDhRate">
            <el-input v-model="form.czqDhRate" placeholder="请输入兑换率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="获奖用户数" prop="hjUv">
            <el-input v-model="form.hjUv" placeholder="请输入获奖用户数" />
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
import { listEpds, getEpds, delEpds, addEpds, updateEpds } from "@/api/mads/epds";

export default {
  name: "Epds",
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
      // 活动奖品发放情况表格数据
      epdsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        epdsDate: null,
        city: null,
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
    /** 查询活动奖品发放情况列表 */
    getList() {
      this.loading = true;
      listEpds(this.queryParams).then(response => {
        this.epdsList = response.rows;
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
        epdsDate: null,
        city: null,
        channel: null,
        hdcyUv: null,
        llqffl: null,
        llqsyl: null,
        llqDhRate: null,
        hfqffl: null,
        hfqsyl: null,
        hfqDhRate: null,
        ywqffl: null,
        ywqsyl: null,
        ywqDhRate: null,
        czqffl: null,
        czqsyl: null,
        czqDhRate: null,
        hjUv: null
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
      this.title = "添加活动奖品发放情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEpds(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动奖品发放情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEpds(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEpds(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除活动奖品发放情况编号为"' + ids + '"的数据项？').then(function() {
        return delEpds(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mads/epds/export', {
        ...this.queryParams
      }, `epds_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
