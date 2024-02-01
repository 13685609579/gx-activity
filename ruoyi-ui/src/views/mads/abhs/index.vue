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
          v-hasPermi="['mads:abhs:add']"
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
          v-hasPermi="['mads:abhs:edit']"
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
          v-hasPermi="['mads:abhs:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mads:abhs:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="abhsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="日期" align="center" prop="abhsDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.abhsDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地市" align="center" prop="city" />
      <el-table-column label="渠道" align="center" prop="channel" />
      <el-table-column label="活动参与量" align="center" prop="hdcyl" />
      <el-table-column label="业务点击量" align="center" prop="clkValue" />
      <el-table-column label="业务办理量" align="center" prop="handleValue" />
      <el-table-column label="业务办理成功量" align="center" prop="handleSuccessValue" />
      <el-table-column label="业务占当日线上销量占比" align="center" prop="bussnessSaleRatio" />
      <el-table-column label="参与活动用户业务办理量" align="center" prop="participateHandleValue" />
      <el-table-column label="中奖客户数" align="center" prop="prizeUv" />
      <el-table-column label="获奖用户业务办理成功量" align="center" prop="prizeHandleSuccess" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mads:abhs:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mads:abhs:remove']"
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

    <!-- 添加或修改活动业务办理情况对话框 -->
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
          <el-form-item label="活动参与量" prop="hdcyl">
            <el-input v-model="form.hdcyl" placeholder="请输入活动参与量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务点击量" prop="clkValue">
            <el-input v-model="form.clkValue" placeholder="请输入业务点击量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务办理量" prop="handleValue">
            <el-input v-model="form.handleValue" placeholder="请输入业务办理量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务办理成功量" prop="handleSuccessValue">
            <el-input v-model="form.handleSuccessValue" placeholder="请输入业务办理成功量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务占当日线上销量占比" prop="bussnessSaleRatio">
            <el-input v-model="form.bussnessSaleRatio" placeholder="请输入业务占当日线上销量占比" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参与活动用户业务办理量" prop="participateHandleValue">
            <el-input v-model="form.participateHandleValue" placeholder="请输入参与活动用户业务办理量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="中奖客户数" prop="prizeUv">
            <el-input v-model="form.prizeUv" placeholder="请输入中奖客户数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="获奖用户业务办理成功量" prop="prizeHandleSuccess">
            <el-input v-model="form.prizeHandleSuccess" placeholder="请输入获奖用户业务办理成功量" />
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
import { listAbhs, getAbhs, delAbhs, addAbhs, updateAbhs } from "@/api/mads/abhs";

export default {
  name: "Abhs",
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
      // 活动业务办理情况表格数据
      abhsList: [],
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
    /** 查询活动业务办理情况列表 */
    getList() {
      this.loading = true;
      listAbhs(this.queryParams).then(response => {
        this.abhsList = response.rows;
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
        hdcyl: null,
        clkValue: null,
        handleValue: null,
        handleSuccessValue: null,
        bussnessSaleRatio: null,
        participateHandleValue: null,
        prizeUv: null,
        prizeHandleSuccess: null
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
      this.title = "添加活动业务办理情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAbhs(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动业务办理情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAbhs(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAbhs(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除活动业务办理情况编号为"' + ids + '"的数据项？').then(function() {
        return delAbhs(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mads/abhs/export', {
        ...this.queryParams
      }, `abhs_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
