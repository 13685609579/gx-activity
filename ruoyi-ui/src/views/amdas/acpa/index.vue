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
      <el-form-item label="期刊" prop="periodical">
        <el-select v-model="queryParams.periodical" placeholder="请选择期刊" clearable>
          <el-option
            v-for="pdical in periodicals"
            :key="pdical"
            :label="pdical"
            :value="pdical"
          />
        </el-select>
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
          v-hasPermi="['amdas:acpa:add']"
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
          v-hasPermi="['amdas:acpa:edit']"
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
          v-hasPermi="['amdas:acpa:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['amdas:acpa:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="acpaList" :span-method="objectSpanMethod" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="月份" align="center" prop="month" />
      <el-table-column label="活动名称" align="center" prop="activityName" />
      <el-table-column label="期刊" align="center" prop="periodical" />
      <el-table-column label="数额" align="center" prop="amount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['amdas:acpa:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['amdas:acpa:remove']"
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

    <!-- 添加或修改活动交叉参与分析对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="月份" prop="month">
          <el-input v-model="form.month" placeholder="请输入月份" />
        </el-form-item>
        <el-form-item label="活动名称" prop="activityName">
          <el-input v-model="form.activityName" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="期刊" prop="periodical">
          <el-input v-model="form.periodical" placeholder="请输入期刊名称" />
        </el-form-item>
        <el-form-item label="数额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入数额" />
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
import { getPeriodicals, listAcpa, getAcpa, delAcpa, addAcpa, updateAcpa } from "@/api/amdas/acpa";

export default {
  name: "Acpa",
  data() {
    return {
      // 选中数组
      // 遮罩层
      loading: true,
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 活动交叉参与分析表格数据
      acpaList: [],
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
        periodical: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      periodicals: [],
      monthIndexArr: [], // 存放month列每一行记录的合并数  控制month的合并
      monthIndexRecode: 0, // monthIndexArr的索引
      activityNameIndexArr: [], // 存放activityName列每一行记录的合并数  控制activityName列的合并
      activityNameIndexRecode: 0 // activityNameIndexArr的索引
    };
  },
  created() {
    this.getPeriodicals();
    this.getList();
    this.getSpanArr(this.acpaList);
  },
  methods: {
    getPeriodicals() {
      this.loading = true;
      getPeriodicals().then(response => {
        this.periodicals = response.data;
      });
    },
    /** 查询活动交叉参与分析列表 */
    getList() {
      this.loading = true;
      listAcpa(this.queryParams).then(response => {
        this.acpaList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.getSpanArr(this.acpaList);
      });
    },
    getSpanArr (data) { // 获取
      // monthIndexArr来存放要合并的格数
      if (data.length > 0) {
        this.monthIndexArr = []
        this.activityNameIndexArr = []
        for (var i = 0; i < data.length; i++) {
          if (i === 0) {
            this.monthIndexArr.push(1)
            this.monthIndexRecode = 0
            this.activityNameIndexArr.push(1)
            this.activityNameIndexRecode = 0
          } else {
            // 判断当前元素与上一个元素是否相同(第2列)
            if (data[i].month === data[i - 1].month) {
              this.monthIndexArr[this.monthIndexRecode] += 1
              this.monthIndexArr.push(0)
            } else {
              this.monthIndexArr.push(1)
              this.monthIndexRecode = i
            }
            // 判断当前元素与上一个元素是否相同(第3列)
            if (data[i].activityName === data[i - 1].activityName) {
              this.activityNameIndexArr[this.activityNameIndexRecode] += 1
              this.activityNameIndexArr.push(0)
            } else {
              this.activityNameIndexArr.push(1)
              this.activityNameIndexRecode = i
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
      if(columnIndex === 2){
        const _row = this.activityNameIndexArr[rowIndex]
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
        periodical: null,
        amount: null
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
      this.title = "添加活动交叉参与分析";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAcpa(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动交叉参与分析";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAcpa(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAcpa(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除活动交叉参与分析编号为"' + ids + '"的数据项？').then(function() {
        return delAcpa(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('amdas/acpa/export', {
        ...this.queryParams
      }, `acpa_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
