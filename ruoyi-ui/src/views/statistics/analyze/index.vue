<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px" >
      <el-form-item label="所属单位名称" prop="unitName">
        <el-input v-model="queryParams.unitName" placeholder="请输入所属单位名称" clearable  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="考试年份" prop="examYear">
        <el-input v-model="queryParams.examYear" placeholder="请输入手机号" clearable  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="postList">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="rowNum" />
      <el-table-column label="考生姓名" align="center" prop="candidateName" />
      <el-table-column label="手机号" align="center" prop="mobile" />
      <el-table-column label="人员类别" align="center" prop="personCategory" />
      <el-table-column label="所属单位" align="center" prop="unitName" />
      <el-table-column label="状态" align="center" prop="personState" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="goon(scope.row)"
            v-if="scope.row.personState == '待审核'"
            v-hasPermi="['system:post:edit']"
            >通过</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-close"
            @click="notgoon(scope.row)"
            v-if="scope.row.personState == '待审核'"
            v-hasPermi="['system:post:edit']"
            >不通过</el-button
          >
          
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList" />
  </div>
</template>

<script>
import { getlist } from "@/api/statistics/analyze";
export default {
  name: "Post",
  dicts: ["sys_normal_disable"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 岗位表格数据
      postList: [],
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        unitName: "",
        examYear: "",
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      getlist(this.queryParams).then((response) => {
        console.log(response);
        this.postList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    openinfo(row){
      console.log(row);
    //   updatePersonState({
    //     candidateId:row.candidateId,
    //     personState:1
    //   }).then((response) => {
    //     this.$modal.msgSuccess("操作成功");
    //     this.getList()
    //   })
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
  },
};
</script>
