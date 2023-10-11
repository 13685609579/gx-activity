<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="考试标题" prop="examTitle">
        <el-input
          v-model="queryParams.examTitle"
          placeholder="请输入考试标题名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="岗位名称" prop="postName">
        <el-input
          v-model="queryParams.postName"
          placeholder="请输入岗位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="岗位状态"
          clearable
        >
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['system:post:add']"
          >新增</el-button
        >
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:post:edit']"
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
          v-hasPermi="['system:post:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:post:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="postList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="rowNum" />
      <el-table-column label="考试标题" align="center" prop="examTitle" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime,'{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime,'{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:post:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-zoom-in"
            @click="hanleInfo(scope.row)"
            v-hasPermi="['system:post:edit']"
            >详情</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="changeupdatestate(scope.row)"
            v-if="scope.row.publishState == '0'"
            v-hasPermi="['system:post:edit']"
            >发布</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-close"
            @click="changeupdatestate(scope.row)"
            v-if="scope.row.publishState == '1'"
            v-hasPermi="['system:post:edit']"
            >取消发布</el-button
          >
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:post:remove']"
          >删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改岗位对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1000px"
      append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="135px">
        <el-form-item label="考试标题" prop="examinationName">
          <el-input
            v-model="form.examinationName"
            placeholder="请输入考试标题"/>
        </el-form-item>
        <el-form-item label="考试时间" prop="startTime">
          <!-- <el-date-picker
            v-model="value1"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker> -->
          <el-date-picker
            v-model="value1"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
          <!-- <el-input v-model="form.startTime" placeholder="请输入开始时间" /> -->
        </el-form-item>
        <el-form-item label="完成获取学时">
            <el-row :gutter="20" class="examination_xueshi">
                <el-col :span="11" class="examination_xueshicont">
                    <el-row :gutter="20" class="examination_cont">
                        <el-col :span="24" class="examination_cont_title">执法司法人员</el-col>
                        <el-col :span="24" v-for="(v, i) in form.turelist" :key="i">
                            <el-form-item
                            :label="v.dictLabel"
                            class="examination_cont_cont"
                            :prop="'turelist.' + i + '.targetHours'"
                            :rules="{ required: true, message: '内容不能为空', trigger: 'blur',}" >
                            <el-input v-model="v.targetHours" placeholder="请输入考试标题" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="11" class="examination_xueshicont">
                    <el-row :gutter="20" class="examination_cont">
                        <el-col :span="24" class="examination_cont_title">非执法司法人员</el-col>
                        <el-col :span="24" v-for="(v, i) in form.falselist" :key="i">
                            <el-form-item
                            :label="v.dictLabel"
                            class="examination_cont_cont"
                            :prop="'falselist.' + i + '.targetHours'"
                            :rules="{ required: true, message: '内容不能为空', trigger: 'blur',}" >
                            <el-input v-model="v.targetHours" placeholder="请输入考试标题" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
        </el-form-item>
        <el-form-item label="是否发布" prop="status">
            <el-switch v-model="showfabu">
            </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>








    <el-dialog
      :title="title"
      :visible.sync="open3"
      width="1000px"
      append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="135px">
        <el-form-item label="考试标题" prop="examinationName">
          <div>{{form.examinationName}}</div>
        </el-form-item>
        <el-form-item label="考试时间" prop="startTime">
          <el-date-picker
            v-model="value1"
            value-format="yyyy-MM-dd"
            disabled
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成获取学时">
            <el-row :gutter="20" class="examination_xueshi">
                <el-col :span="11" class="examination_xueshicont">
                    <el-row :gutter="20" class="examination_cont">
                        <el-col :span="24" class="examination_cont_title">执法司法人员</el-col>
                        <el-col :span="24" v-for="(v, i) in form.turelist" :key="i">
                            <el-form-item
                            :label="v.dictLabel"
                            class="examination_cont_cont">
                              <div>{{v.targetHours}}</div>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="11" class="examination_xueshicont">
                    <el-row :gutter="20" class="examination_cont">
                        <el-col :span="24" class="examination_cont_title">非执法司法人员</el-col>
                        <el-col :span="24" v-for="(v, i) in form.falselist" :key="i">
                            <el-form-item
                            :label="v.dictLabel"
                            class="examination_cont_cont">
                              <div>{{v.targetHours}}</div>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
        </el-form-item>
        <el-form-item label="是否发布" prop="status">
            <el-switch v-model="showfabu" disabled></el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="open3 = false">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listPost,getPost,delPost,addPost,updatePost,} from "@/api/system/post";
import { datalist } from "@/api/topic/question";
import { getmanagelist,insertExamManageData,updateExamManage,examManageInfo,updatePublicState } from "@/api/topic/examination";
export default {
  name: "Post",
  dicts: ["sys_normal_disable"],
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
      // 岗位表格数据
      postList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //是否点击修改
      open2: false,
      //是否点击查看详情
      open3: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        examTitle: "",
      },
      // 表单参数
      form: {
        turelist: [],
        falselist: [],
        examinationName:'',
      },
      // 表单校验
      rules: {
        examinationName: [
          { required: true, message: "考试标题不能为空", trigger: "blur" },
        ],
      },
      value1: [new Date(), new Date()],
      //执法司法人员列表
      turelist: [],
      //非执法司法人员列表
      falselist: [],
      //是否发布
      showfabu:true,
    };
  },
  watch: {
    value1(newval, oldval) {
      console.log(this.value1);
    },
  },
  created() {
    this.getList();
    this.getsortlist();
  },
  methods: {
    async getsortlist() {
      let res = await datalist({
        dictType: "topic_sort",
        status: "0",
      });
      this.turelist = JSON.parse(JSON.stringify(res.rows))
      this.turelist.forEach((v,i) => {
            v.personType = "1";
            v.targetHours = "";
      });
      this.falselist = JSON.parse(JSON.stringify(res.rows))
      this.falselist.forEach((v,i) => {
            v.personType = "2";
            v.targetHours = "";
      });
    //   res.rows.forEach((v, i) => {
    //     v.personType = "1";
    //     v.targetHours = "";
    //     this.turelist.push(v);
    //   });
    //   res.rows.forEach((v, i) => {
    //     v.personType2 = "2";
    //     v.targetHours = "";
    //     this.falselist.push(v);
    //   });
      console.log("getsortlist", res.rows);
    },
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      getmanagelist(this.queryParams).then((response) => {
        this.postList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open2 = false;
      this.open3 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        turelist: [],
        falselist: [],
        examinationName:'',
      },
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
      this.ids = selection.map((item) => item.postId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.$set(this.form,'turelist',JSON.parse(JSON.stringify(this.turelist)))
      this.$set(this.form,'falselist',JSON.parse(JSON.stringify(this.falselist)))
      console.log(this.form.turelist);
      this.open = true;
      this.open2 = false;
      this.open3 = false;
      this.title = "添加考试";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.loading = true
      examManageInfo({examId:row.examId}).then((res) =>{
        console.log(res);
        this.form.examId = res.data.examId
        this.form.examinationName = res.data.examTitle
        this.$set(this.value1,0,res.data.startTime)
        this.$set(this.value1,1,res.data.endTime)
        if (res.data.publishState == '1') {
          this.showfabu = true
        }else{
          this.showfabu = false
        }
        this.form.turelist = res.data.sfClassHours.zf
        this.form.falselist = res.data.sfClassHours.fzf
        this.loading = false
        this.open = true;
        this.open2 = true;
        this.title = "修改考试";
      })
    },
    //详情按钮操作
    hanleInfo(row){
      this.reset();
      this.loading = true
      examManageInfo({examId:row.examId}).then((res) =>{
        console.log(res);
        this.form.examId = res.data.examId
        this.form.examinationName = res.data.examTitle
        this.$set(this.value1,0,res.data.startTime)
        this.$set(this.value1,1,res.data.endTime)
        if (res.data.publishState == '1') {
          this.showfabu = true
        }else{
          this.showfabu = false
        }
        this.form.turelist = res.data.sfClassHours.zf
        this.form.falselist = res.data.sfClassHours.fzf
        this.loading = false
        this.open2 = true;
        this.open3 = true;
        this.title = "修改考试";
      })
    },
    //更改发布或者不发布
    changeupdatestate(row){
      updatePublicState({examId:row.examId}).then((res) =>{
        console.log(res);
        this.$modal.msgSuccess("操作成功");
        this.getList();
      })
    },
    /** 提交按钮 */
    submitForm: function () {
    //   console.log('form数据',this.form);
    //   console.log('执法人员',this.form.turelist);
    //   console.log('非执法人员',this.form.falselist);
    //   console.log('是否发布',this.showfabu);
      this.$refs["form"].validate((valid) => {
        if (valid) {
            if (this.open2) {//open2主要就是再这里做判断，判断是点击修改提交还是新增提交
                console.log('open2 = true');
                let classHourSfList = []
                this.form.turelist.forEach((v,i) => {
                    classHourSfList.push({hourId:v.hourId,targetHours:v.targetHours,personType:v.personType})
                });
                this.form.falselist.forEach((v,i) => {
                    classHourSfList.push({hourId:v.hourId,targetHours:v.targetHours,personType:v.personType})
                });
                updateExamManage({
                    examId:this.form.examId,
                    examTitle:this.form.examinationName,
                    startTime:this.value1[0],
                    endTime:this.value1[1],
                    publishState:this.showfabu == true ? 1 : 2,
                    classHourSfList:classHourSfList,
                }).then(response => {
                    this.$modal.msgSuccess("修改成功");
                    this.open = false;
                    this.open2 = false;
                    this.getList();
                });
            } else {
                console.log('open2 = false');
                let classHourSfList = []
                this.form.turelist.forEach((v,i) => {
                    classHourSfList.push({dictCode:v.dictCode,targetHours:v.targetHours,personType:v.personType})
                });
                this.form.falselist.forEach((v,i) => {
                    classHourSfList.push({dictCode:v.dictCode,targetHours:v.targetHours,personType:v.personType})
                });
                insertExamManageData({
                    examTitle:this.form.examinationName,
                    startTime:this.value1[0],
                    endTime:this.value1[1],
                    publishState:this.showfabu == true ? 1 : 2,
                    classHourSfList:classHourSfList,
                }).then(response => {
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
      const postIds = row.postId || this.ids;
      this.$modal
        .confirm('是否确认删除岗位编号为"' + postIds + '"的数据项？')
        .then(function () {
          return delPost(postIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/post/export",
        {
          ...this.queryParams,
        },
        `post_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
<style>
.examination_xueshi {
}
.examination_xueshi .examination_xueshicont {
  border: 1px solid #000000;
}
.examination_xueshi .examination_xueshicont:first-child {
  margin-right: 45px;
}
.examination_cont_title {
  text-align: center;
}
.examination_cont_cont .el-form-item__label {
  text-align: left !important;
  margin-bottom: 15px;
}
</style>
