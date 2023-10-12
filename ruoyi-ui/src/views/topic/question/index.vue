<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="题目分类">
        <el-select v-model="queryParams.topicSort" placeholder="请选择">
          <el-option
            v-for="item in typelist"
            :key="item.dictCode"
            :label="item.dictLabel"
            :value="item.dictCode">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="题目题型">
        <el-select v-model="queryParams.topicType" placeholder="请选择">
          <el-option
            v-for="item in tixinglist"
            :key="item.dictValue"
            :label="item.dictLabel"
            :value="item.dictValue">
          </el-option>
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
          v-hasPermi="['system:post:add']"
        >新增</el-button>
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
      </el-col> -->
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
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:post:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="postList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="题目标题" align="center" prop="topicContent" />
      <el-table-column label="题目分类" align="center" prop="topicSortName" />
      <el-table-column label="题目题型" align="center" prop="topicTypeName" />
      <el-table-column label="题目选项" align="center" prop="topicOptions" />
      <!-- <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:post:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="hanleInfo(scope.row)"
            v-hasPermi="['system:post:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:post:remove']"
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

    <!-- 添加或修改题目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="题目编号">
          <el-input :disabled="true" :placeholder="topicCode" />
        </el-form-item>

        <el-row :gutter="20" v-show="!open2">
          <el-col :span="12">
            <el-form-item label="题目分类" prop="topictype">
              <el-select v-model="form.topictype" placeholder="请选择">
                <el-option
                  v-for="item in typelist"
                  :key="item.dictCode"
                  :label="item.dictLabel"
                  :value="item.dictCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="题目题型" prop="topictixing">
              <el-select v-model="form.topictixing" placeholder="请选择">
                <el-option
                  v-for="item in tixinglist"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20" v-show="open2">
          <el-col :span="12">
            <el-form-item label="题目分类" prop="topictype">
              <el-select v-model="form.topictype" disabled placeholder="请选择">
                <!-- <el-option
                  v-for="item in typelist"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option> -->
                <el-option
                  v-for="item in typelist"
                  :key="item.dictCode"
                  :label="item.dictLabel"
                  :value="item.dictCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="题目题型" prop="topictixing">
              <el-select v-model="form.topictixing" disabled placeholder="请选择">
                <el-option
                  v-for="item in tixinglist"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="题目内容" prop="topiccont">
          <el-input v-model="form.topiccont" type="textarea" :rows="5" maxlength="200" placeholder="请输入题目内容" />
        </el-form-item>
        <div v-show="form.topictixing != 3">
          <el-form-item label="题目选项">
            <el-button type="primary" @click="addlist()">添加选项</el-button>
            <el-row :gutter="20" style="margin-top:20px" v-for="v,i in topicOptionsList" :key="i">
              <!-- <el-col :span="1">
                <div class="" v-show="i == 0">A</div>
                <div class="" v-show="i == 1">B</div>
                <div class="" v-show="i == 2">C</div>
                <div class="" v-show="i == 3">D</div>
              </el-col> -->
              <el-col :span="18">
                <el-input v-model="v.optionsContent" placeholder="请输入选项内容"/>
              </el-col>
              <el-col :span="6">
                <div class="dianji" style="display: flex;align-items: center;">
                  <i class="el-icon-success" v-show="v.optionsState == '1'"></i>
                  <i class="el-icon-error" v-show="v.optionsState == '0'"></i>
                  <div class="cuowu" @click="changeshow(v,i)" v-show="v.optionsState == '1'">错误</div>
                  <div class="cuowu" @click="changeshow(v,i)" v-show="v.optionsState == '0'">正确</div>
                  <div class="shanchu" @click="dellist(v,i)">删除</div>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
        </div>
        <div v-show="form.topictixing == 3">
          <el-form-item label="题目选项">
            <el-select v-model="topicAnswer" placeholder="请选择">
                <el-option
                  v-for="item in errorrighe"
                  :key="item.Label"
                  :label="item.value"
                  :value="item.Label">
                </el-option>
              </el-select>
          </el-form-item>
        </div>


        <el-form-item label="答案解析" prop="remark">
          <el-input v-model="form.remark" :rows="5" maxlength="200" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    
    <!-- 点击详情展开的列表数据 -->
    <el-dialog :title="title" :visible.sync="open3" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="题目编号">
          <div>{{topicCode}}</div>
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="题目分类" prop="topictype">
              <el-select v-model="form.topictype" disabled placeholder="请选择">
                <!-- <el-option
                  v-for="item in typelist"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option> -->
                <el-option
                  v-for="item in typelist"
                  :key="item.dictCode"
                  :label="item.dictLabel"
                  :value="item.dictCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="题目题型" prop="topictixing">
              <el-select v-model="form.topictixing" disabled placeholder="请选择">
                <el-option
                  v-for="item in tixinglist"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="题目内容" prop="topiccont">
          <div>{{form.topiccont}}</div>
        </el-form-item>
        <div v-show="form.topictixing != 3">
          <el-form-item label="题目选项">
            <el-button type="primary" style="background:#ffffff;border: #ffffff;">添加选项</el-button>
            <el-row :gutter="20" style="margin-top:20px" v-for="v,i in topicOptionsList" :key="i">
              <el-col :span="18">
                <div>{{v.optionsContent}}</div>
              </el-col>
              <el-col :span="6">
                <div class="dianji" style="display: flex;align-items: center;">
                  <i class="el-icon-success" v-show="v.optionsState == '1'"></i>
                  <i class="el-icon-error" v-show="v.optionsState == '0'"></i>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
        </div>
        <div v-show="form.topictixing == 3">
          <el-form-item label="题目选项">
            <el-select v-model="topicAnswer" disabled placeholder="请选择">
                <el-option
                  v-for="item in errorrighe"
                  :key="item.Label"
                  :label="item.value"
                  :value="item.Label">
                </el-option>
              </el-select>
          </el-form-item>
        </div>
        <el-form-item label="答案解析" prop="remark">
          <div>{{form.remark}}</div>
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
import { listPost, getPost, delPost, addPost, updatePost } from "@/api/system/post";
import { questionlist,datalist,topicCode,addQuestionBank,questionBankInfo,updateQuestionBank,removeQuestionBank } from "@/api/topic/question";

export default {
  name: "Post",
  dicts: ['sys_normal_disable'],
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
      //是否是点击修改打开的
      open2:false,
      //是否是点击详情打开的
      open3:false,




      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        topicSort: undefined,
        topicType: undefined,
      },
      // 表单参数
      form: {
        topiccont:'',
        topictype:'',
        topictixing:'',
        remark:'',
      },
      // 表单校验
      rules: {
        topiccont: [
          { required: true, message: "题目内容不能为空", trigger: "blur" }
        ],
        topictype: [
          { required: true, message: "题目分类不能为空", trigger: "blur" }
        ],
        topictixing: [
          { required: true, message: "题目题型不能为空", trigger: "blur" }
        ],
        remark: [
          { required: true, message: "答案解析不能为空", trigger: "blur" }
        ],
      },
      // cece: {
      //   topicoptions: [
      //     { required: true, message: "题目内容不能为空", trigger: "blur" }
      //   ],
      // },
      //题目id
      topicId:'',
      //题目编号
      topicCode:'',
      //题目分类下拉框
      typelist: [],
      //题目题型下拉框
      tixinglist: [],
      //题目选项列表
      topicOptionsList:[
        {optionsContent:'',optionsState:'0'},
      ],
      //当类型为判断题的时候，这个值传1或0，否则为空
      topicAnswer:'',
      // 判断题的选择框
      errorrighe:[
        {value:'正确',Label:'1'},
        {value:'错误',Label:'0'},
      ],
    };
  },
  created() {
    this.getList();
    this.getsortlist();
    this.gettypelist();
    
  },
  methods: {
    //获取题目编号
    async gettopicCode(){
      let res = await topicCode()
      this.topicCode = res.msg
      console.log(res.msg);
    },
    addlist(){
      // this.$refs["topicOptionsList"].validate(valid => {
      //   console.log(valid);
      //   if (valid) {
      //   }
      // })
      this.topicOptionsList.push({optionsContent:'',optionsState:'0'})
    },
    dellist(v,i){
      this.topicOptionsList.splice(i,1)
      console.log(v,i);
    },
    changeshow(v,i){
      if (this.topicOptionsList[i].optionsState == '1') {
        this.topicOptionsList[i].optionsState = '0'
      }else{
        this.topicOptionsList[i].optionsState = '1'
      }
      console.log(v,i);
    },
    //题目分类
    async getsortlist(){
      let res = await datalist({
        dictType:'topic_sort',
        status:'0',
      })
      console.log('push进typelist的数据',res.rows);
      res.rows.forEach((v,i) => {
        this.typelist.push(v)
      });
      console.log('getsortlist',res);
    },
     //题目题型
    async gettypelist(){
      let res = await datalist({
        dictType:'topic_type',
        status:'0',
      })
      res.rows.forEach((v,i) => {
        this.tixinglist.push(v)
      });
      console.log('gettypelist',res);
    },
    /** 查询题目列表 */
    getList() {
      this.loading = true;
      questionlist(this.queryParams).then(response => {
        response.rows.forEach((v,i) => {
          // if (v.topicSort == '1') {
          //   v.topicSortName = '法治思想'
          // }
          // if (v.topicSort == '2') {
          //   v.topicSortName = '民法典'
          // }
          // if (v.topicSort == '3') {
          //   v.topicSortName = '宪法'
          // }
          // if (v.topicSort == '4') {
          //   v.topicSortName = '党内法规'
          // }
          // if (v.topicSort == '5') {
          //   v.topicSortName = '行政处罚法'
          // }
          if (v.topicType == '1') {
            v.topicTypeName = '单选题'
          }
          if (v.topicType == '2') {
            v.topicTypeName = '多选题'
          }
          if (v.topicType == '3') {
            v.topicTypeName = '判断题'
          }
        });
        this.loading = false;
        this.postList = response.rows;
        this.total = response.total;
        console.log(this.postList);
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
        topiccont:'',
        topictype:'',
        topictixing:'',
        remark:'',
      },
      this.topicOptionsList = []
      this.topicAnswer = ''
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
      console.log(selection);
      this.ids = selection.map(item => item.topicId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.gettopicCode()
      console.log(this.topicCode);
      this.reset();
      this.open = true;
      this.open2 = false;
      this.open3 = false;
      this.title = "添加题目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log(row);
      this.reset();
      questionBankInfo({
        topicId:row.topicId
      }).then(response => {
        this.open2 = true;
        this.topicId = response.data.topicId;
        this.topicCode = response.data.topicCode;
        this.form.topiccont = response.data.topicContent;
        this.form.topictype = Number(response.data.topicSort)//不知道为什么用Number别问，问就是组件敏感
        this.form.topictixing = response.data.topicType
        this.topicAnswer = response.data.topicAnswer;
        this.topicOptionsList = response.data.topicOptionsList;
        this.form.remark = response.data.answerAnalysis;

        this.open = true;
        
        this.title = "修改题目";
      });
    },
    //进入详情
    hanleInfo(row){
      console.log(row);
      this.reset();
      questionBankInfo({
        topicId:row.topicId
      }).then(response => {
        this.open2 = true;
        this.topicId = response.data.topicId;
        this.topicCode = response.data.topicCode;
        this.form.topiccont = response.data.topicContent;
        this.form.topictype = Number(response.data.topicSort)
        this.form.topictixing = response.data.topicType
        this.topicAnswer = response.data.topicAnswer;
        this.topicOptionsList = response.data.topicOptionsList;
        this.form.remark = response.data.answerAnalysis;

        this.open3 = true;
        
        this.title = "题目详情";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      
      console.log(this.form.topictype);
      // this.$refs["form"].validate(valid => {
      //   if (valid) {
      //     //判断选项至少要有一个正确的
      //     if (this.form.topictixing != 3) {
      //       let errshuliang = 0
      //       this.topicOptionsList.forEach((v,i) => {
      //         if (v.optionsState == '1') {
      //           errshuliang = errshuliang + 1
      //         }
      //       });
      //       if (errshuliang == 0) {
      //         this.$alert('至少选择一个正确答案', {
      //           confirmButtonText: '确定',
      //         });
      //         return
      //       }
      //     }
          
      //     //判断题选项且至少要有一个正确的
      //     if (this.form.topictixing == 3) {
      //       this.topicOptionsList = []
      //     }
      //     //单选题至少要有一个正确的
      //     if (this.form.topictixing == 1) {
      //       let shuliang = 0
      //       this.topicOptionsList.forEach((v,i) => {
      //         if (v.optionsState == '1') {
      //           shuliang = shuliang + 1
      //         }
      //       });
      //       if (shuliang > 1) {
      //         this.$alert('正确答案不能超过一个', {
      //           confirmButtonText: '确定',
      //         });
      //         return
      //       }
      //     }
      //     // console.log(this.form);
      //     // console.log(this.topicId);
      //     // console.log(this.topicCode);
      //     // console.log(this.topicOptionsList);
      //     // console.log(this.topicAnswer);
      //     if (this.open2) {
      //       updateQuestionBank({
      //         topicId:this.topicId,
      //         topicCode:this.topicCode,
      //         topicContent:this.form.topiccont,
      //         topicSort:this.form.topictype,
      //         topicType:this.form.topictixing,
      //         topicOptionsList:this.topicOptionsList,
      //         answerAnalysis:this.form.remark,
      //         topicAnswer:this.topicAnswer,
      //       }).then(response => {
      //         this.$modal.msgSuccess("修改成功");
      //         this.open = false;
      //         this.open3 = false;
      //         this.getList();
      //       });
      //     } else {
      //       addQuestionBank({
      //         topicCode:this.topicCode,
      //         topicContent:this.form.topiccont,
      //         topicSort:this.form.topictype,
      //         topicType:this.form.topictixing,
      //         topicAnswer:this.topicAnswer,
      //         topicOptionsList:this.topicOptionsList,
      //         answerAnalysis:this.form.remark,
      //       }).then(response => {
      //         this.$modal.msgSuccess("新增成功");
      //         this.open = false;
      //         this.open3 = false;
      //         this.getList();
      //       });
      //     }
      //   }
      // });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      console.log(row);
      const topicIId = row.topicId || this.ids.join(',');
      // removeQuestionBank({topicId:row.topicId}).then((res) =>{
      //   console.log(res);
      //   this.getList();
      //   this.$modal.msgSuccess("删除成功");
      // })
      this.$modal.confirm('是否确认删除该题目？').then(function() {
        return removeQuestionBank({topicId:topicIId});
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/post/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style scoped>
.dianji{
  display: flex;
  align-items: center;
}
.cuowu{
  margin-right: 30px;
  color: rgb(0, 119, 255);
  cursor: pointer;
}
.shanchu{
  color: red;
  cursor: pointer;
}
.el-icon-success{
  color: rgb(0, 255, 136);
  font-size: 30px;
  margin-right: 30px;
}
.el-icon-error{
  color: rgb(124, 0, 0);
  font-size: 30px;
  margin-right: 30px;
}
</style>
