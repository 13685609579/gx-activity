<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="abhsDate">
        <el-date-picker clearable
          v-model="queryParams.abhsDate"
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
      <el-form-item label="触点" prop="contactPoint">
        <el-input
          v-model="queryParams.contactPoint"
          placeholder="请输入触点"
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
          v-hasPermi="['mads:acs:add']"
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
          v-hasPermi="['mads:acs:edit']"
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
          v-hasPermi="['mads:acs:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mads:acs:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="acsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="日期" align="center" prop="abhsDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.abhsDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地市" align="center" prop="city" />
      <el-table-column label="渠道" align="center" prop="channel" />
      <el-table-column label="触点" align="center" prop="contactPoint" />
      <el-table-column label="pv" align="center" prop="pv" />
      <el-table-column label="参与用户数" align="center" prop="uv" />
      <el-table-column label="参与活动用户累计" align="center" prop="ljUv" />
      <el-table-column label="参与活动新增原生用户数" align="center" prop="xzysUv" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mads:acs:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mads:acs:remove']"
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

    <!-- 添加或修改活动触点统计情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="880px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="170px">
        <el-col :span="12">
          <el-form-item label="日期" prop="abhsDate">
            <el-date-picker clearable
              v-model="form.abhsDate"
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
          <el-form-item label="触点" prop="contactPoint">
            <el-input v-model="form.contactPoint" placeholder="请输入触点" />
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAcs, getAcs, delAcs, addAcs, updateAcs } from "@/api/mads/acs";

export default {
  name: "Acs",
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
      // 活动触点统计情况表格数据
      acsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        abhsDate: null,
        city: null,
        channel: null,
        contactPoint: null,
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
    /** 查询活动触点统计情况列表 */
    getList() {
      this.loading = true;
      listAcs(this.queryParams).then(response => {
        this.acsList = response.rows;
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
        abhsDate: null,
        city: null,
        channel: null,
        contactPoint: null,
        pv: null,
        uv: null,
        ljUv: null,
        xzysUv: null
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
      this.title = "添加活动触点统计情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAcs(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动触点统计情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAcs(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAcs(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除活动触点统计情况编号为"' + ids + '"的数据项？').then(function() {
        return delAcs(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mads/acs/export', {
        ...this.queryParams
      }, `acs_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
