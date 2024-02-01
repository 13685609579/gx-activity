<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24" class="card-box">
        <el-card>
          <div class="content">
            <div class="li">
              <div class="li_top">
                <div class="top_left">
                  <img class="left_img" :src="getPvImgUrl()" alt="">
                </div>
                <div class="top_right">
                  <div class="right_top">{{yxhdpv}}</div>
                  <div class="right_bottom">pv</div>
                </div>
              </div>
              <div class="li_bottom">
                <div class="bottom_text">同比：{{pvtb}}</div>
                <div class="bottom_text">环比：{{pvhb}}</div>
              </div>
            </div>
            <div class="li">
              <div class="li_top">
                <div class="top_left">
                  <img class="left_img" :src="getUvImgUrl()" alt="">
                </div>
                <div class="top_right">
                  <div class="right_top">{{yxhduv}}</div>
                  <div class="right_bottom">日参与用户数长</div>
                </div>
              </div>
              <div class="li_bottom">
                <div class="bottom_text">同比：{{uvtb}}</div>
                <div class="bottom_text">环比：{{uvhb}}</div>
              </div>
            </div>
            <div class="li">
              <div class="li_top">
                <div class="top_left">
                  <img class="left_img" :src="getLjUvImgUrl()" alt="">
                </div>
                <div class="top_right">
                  <div class="right_top">{{ljuv}}</div>
                  <div class="right_bottom">参与活动用户累计</div>
                </div>
              </div>
              <div class="li_bottom">
                <div class="bottom_text">同比：{{ljuvtb}}</div>
                <div class="bottom_text">环比：{{ljuvhb}}</div>
              </div>
            </div>
            <div class="li">
              <div class="li_top">
                <div class="top_left">
                  <img class="left_img" :src="getXzysUvImgUrl()" alt="">
                </div>
                <div class="top_right">
                  <div class="right_top">{{xzysuv}}</div>
                  <div class="right_bottom">参与活动新增原生用户数</div>
                </div>
              </div>
              <div class="li_bottom">
                <div class="bottom_text">同比：{{xzysuvtb}}</div>
                <div class="bottom_text">环比：{{xzysuvhb}}</div>
              </div>
            </div>
            <div class="li">
              <div class="li_top">
                <div class="top_left">
                  <img class="left_img" :src="getXzyhUvImgUrl()" alt="">
                </div>
                <div class="top_right">
                  <div class="right_top">{{xzyhuv}}</div>
                  <div class="right_bottom">参与活动新增月活用户数</div>
                </div>
              </div>
              <div class="li_bottom">
                <div class="bottom_text">同比：{{xzyhuvtb}}</div>
                <div class="bottom_text">环比：{{xzyhuvhb}}</div>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-pie-chart"></i> 营销活动页面浏览次数分析</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <div ref="yxhdfx" style="height: 420px" />
          </div>
        </el-card>
      </el-col>
      <el-col :span="12" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-pie-chart"></i> 营销活动日参与用户数分析</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <div ref="yxhdcy" style="height: 420px" />
          </div>
        </el-card>
      </el-col>
      <el-col :span="12" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-data-line"></i> 营销活动日参与用户数分析</span></div>
          <div id="yxhdymfx" style="height:400px"></div>
        </el-card>
      </el-col>
      <el-col :span="12" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-s-data"></i> 营销活动日参与用户数分析</span></div>
          <div id="cyhdyhhyqk" style="height:400px"></div>
        </el-card>
      </el-col>

      <el-col :span="24" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-monitor"></i> 基本信息</span></div>
          <el-table v-loading="loading" :data="cyqkList">
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
          </el-table>

          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
  .content{
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .content .li{}
  .content .li .li_top{
    display: flex;
    align-items: center;
  }
  .content .li .li_top .top_left{
    margin-right: 10px;
  }
  .content .li .li_top .top_left .left_img{
    width: 50px;
    height: 50px;
    border-radius: 50%;
  }
  .content .li .li_top .top_right{}
  .content .li .li_top .top_right .right_top{
    height: 25px;
  }
  .content .li .li_top .top_right .right_bottom{}
  .content .li .li_bottom{
    display: flex;
    align-items: center;
    margin-top: 10px;
  }
  .content .li .li_bottom .bottom_text{
    margin-right: 10px;
  }
</style>
<script>
  import { yxhdfxList, yxhdcyList, yxhdymfxList, cyhdyhhyqkList, cyPvUv } from "@/api/mads/index";
  import { listAp } from "@/api/mads/ap";
  import * as echarts from "echarts";

  export default {
    name: "Yxhd",
    data() {
      return {
        city: []
        ,pv: []
        ,uv: []
        ,ljMonth: []
        // 总条数
        ,total: 0
        // 营销活动参与情况数据统计表格数据
        ,cyqkList: []
        ,queryParams: {
          pageNum: 1,
          pageSize: 10,
          /*city: null,
          hdcyDate: null,
          channel: null,*/
        }
        ,yxhdpv: 0
        ,yxhduv: 0
        ,ljuv: 0
        ,xzysuv: 0
        ,xzyhuv: 0

        ,pvtb: ''
        ,uvtb: ''
        ,ljuvtb: ''
        ,xzysuvtb: ''
        ,xzyhuvtb: ''

        ,pvhb: ''
        ,uvhb: ''
        ,ljuvhb: ''
        ,xzysuvhb: ''
        ,xzyhuvhb: ''
      }
    },
    created() {
      this.getCyPvUvList();
      this.fxList();
      this.cyList();
      this.ymfxList();
      this.yhhyqkList();
      this.getList();
      this.openLoading();
    },
    methods: {
      /** 参与活动浏览数用户活跃数 */
      getCyPvUvList() {
        cyPvUv(this.queryParams).then(response => {
          console.log('cyPvUv---->'+JSON.stringify(response))
          if(200==response.code){
            this.yxhdpv = response.data.pv
            this.yxhduv = response.data.uv
            this.ljuv = response.data.ljuv
            this.xzysuv = response.data.xzysuv
            this.xzyhuv = response.data.xzyhuv

            if(null != response.data.pvtb && '' != response.data.pvtb){
              this.pvtb = response.data.pvtb*100;
              if(this.pvtb>0){
                this.pvtb = '+'+this.pvtb+'%';
              }
              if(this.pvtb==0){
                this.pvtb = '0%';
              }
              if(this.pvtb<=0){
                this.pvtb = this.pvtb+'%';
              }
            }
            if(null != response.data.uvtb && '' != response.data.uvtb){
              this.uvtb = response.data.uvtb*100;
              if(this.uvtb>0){
                this.uvtb = '+'+this.uvtb+'%';
              }
              if(this.uvtb==0){
                this.uvtb = '0%';
              }
              if(this.uvtb<=0){
                this.uvtb = this.uvtb+'%';
              }
            }
            if(null != response.data.ljuvtb && '' != response.data.ljuvtb){
              this.ljuvtb = response.data.ljuvtb*100;
              if(this.ljuvtb>0){
                this.ljuvtb = '+'+this.ljuvtb+'%';
              }
              if(this.ljuvtb==0){
                this.ljuvtb = '0%';
              }
              if(this.ljuvtb<=0){
                this.ljuvtb = this.ljuvtb+'%';
              }
            }
            if(null != response.data.xzysuvtb && '' != response.data.xzysuvtb){
              this.xzysuvtb = response.data.xzysuvtb*100;
              if(this.xzysuvtb>0){
                this.xzysuvtb = '+'+this.xzysuvtb+'%';
              }
              if(this.xzysuvtb==0){
                this.xzysuvtb = '0%';
              }
              if(this.xzysuvtb<=0){
                this.xzysuvtb = this.xzysuvtb+'%';
              }
            }
            if(null != response.data.xzyhuvtb && '' != response.data.xzyhuvtb){
              this.xzyhuvtb = response.data.xzyhuvtb*100;
              if(this.xzyhuvtb>0){
                this.xzyhuvtb = '+'+this.xzyhuvtb+'%';
              }
              if(this.xzyhuvtb==0){
                this.xzyhuvtb = '0%';
              }
              if(this.xzyhuvtb<=0){
                this.xzyhuvtb = this.xzyhuvtb+'%';
              }
            }

            if(null != response.data.pvhb && '' != response.data.pvhb){
              this.pvhb = response.data.pvhb*100;
              if(this.pvhb>0){
                this.pvhb = '+'+this.pvhb+'%';
              }
              if(this.pvhb==0){
                this.pvhb = '0%';
              }
              if(this.pvhb<=0){
                this.pvhb = this.pvhb+'%';
              }
            }
            if(null != response.data.uvhb && '' != response.data.uvhb){
              this.uvhb = response.data.uvhb*100;
              if(this.uvhb>0){
                this.uvhb = '+'+this.uvhb+'%';
              }
              if(this.uvhb==0){
                this.uvhb = '0%';
              }
              if(this.uvhb<=0){
                this.uvhb = this.uvhb+'%';
              }
            }
            if(null != response.data.ljuvhb && '' != response.data.ljuvhb){
              this.ljuvhb = response.data.ljuvhb*100;
              if(this.ljuvhb>0){
                this.ljuvhb = '+'+this.ljuvhb+'%';
              }
              if(this.ljuvhb==0){
                this.ljuvhb = '0%';
              }
              if(this.ljuvhb<=0){
                this.ljuvhb = this.ljuvhb+'%';
              }
            }
            if(null != response.data.xzysuvhb && '' != response.data.xzysuvhb){
              this.xzysuvhb = response.data.xzysuvhb*100;
              if(this.xzysuvhb>0){
                this.xzysuvhb = '+'+this.xzysuvhb+'%';
              }
              if(this.xzysuvhb==0){
                this.xzysuvhb = '0%';
              }
              if(this.xzysuvhb<=0){
                this.xzysuvhb = this.xzysuvhb+'%';
              }
            }
            if(null != response.data.xzyhuvhb && '' != response.data.xzyhuvhb){
              this.xzyhuvhb = response.data.xzyhuvhb*100;
              if(this.xzyhuvhb>0){
                this.xzyhuvhb = '+'+this.xzyhuvhb+'%';
              }
              if(this.xzyhuvhb==0){
                this.xzyhuvhb = '0%';
              }
              if(this.xzyhuvhb<=0){
                this.xzyhuvhb = this.xzyhuvhb+'%';
              }
            }
          }
        });
      },
      /** 营销活动页面浏览次数分析 */
      fxList() {
        yxhdfxList().then((response) => {
          this.$modal.closeLoading();

          var yxhdfx = echarts.init(this.$refs.yxhdfx, "macarons");
          yxhdfx.setOption({
            tooltip: {
              trigger: "item",
              formatter: "{a} <br/>{b} : {c} ({d}%)",
            },
            series: [
              {
                name: "pv",
                type: "pie",
                roseType: "radius",
                radius: [15, 95],
                center: ["50%", "38%"],
                data: response.data.yxhdfxList,
                animationEasing: "cubicInOut",
                animationDuration: 1000,
              }
            ]
          });
          window.addEventListener("resize", () => {
            this.yxhdfx.resize();
          });
        });
      },
      /** 营销活动日参与用户数分析 */
      cyList() {
        yxhdcyList().then((response) => {
          this.$modal.closeLoading();

          var yxhdcy = echarts.init(this.$refs.yxhdcy, "macarons");
          yxhdcy.setOption({
            tooltip: {
              trigger: "item",
              formatter: "{a} <br/>{b} : {c} ({d}%)",
            },
            series: [
              {
                name: "日参与用户数",
                type: "pie",
                roseType: "radius",
                radius: [35, 75],
                center: ["50%", "38%"],
                data: response.data.yxhdcyList,
                animationEasing: "cubicInOut",
                animationDuration: 1000,
              }
            ]
          });
          window.addEventListener("resize", () => {
            yxhdcy.resize();
          });
        });
      },
      /** 营销活动页面分析 */
      ymfxList() {
        yxhdymfxList().then((response) => {
          var result = response.data;
          result.forEach(item => {
            this.city.push(item.city);
            this.pv.push(item.pv);
            this.uv.push(item.uv);
          });
          var yxhdymfx = echarts.init(document.getElementById('yxhdymfx'));
          var charOption = {
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow'
              }
            },
            xAxis: {
              type: 'category',
              data:  this.city
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                name: 'pv',
                data: this.pv,
                type: 'line'
              },
              {
                name: 'uv',
                data: this.uv,
                type: 'bar'
              }
            ]
          };
          yxhdymfx.setOption(charOption);
          window.addEventListener("resize", () => {
            yxhdymfx.resize();
          });
        });
      },
      /** 营销活动页面分析 */
      yhhyqkList(){
        cyhdyhhyqkList().then((response) => {
          var result = response.data;
          result.forEach(item => {
            this.ljMonth.push(item.ljMonth);
            this.uv.push(item.uv);
          });
          var cyhdyhhyqk = echarts.init(document.getElementById('cyhdyhhyqk'));
          var option = {
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow'
              }
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis: [
              {
                type: 'category',
                data: this.ljMonth,
                axisTick: {
                  alignWithLabel: true
                }
              }
            ],
            yAxis: [
              {
                type: 'value'
              }
            ],
            series: [
              {
                name: 'uv',
                type: 'bar',
                data: this.uv
              }
            ]
          };
          cyhdyhhyqk.setOption(option);
          window.addEventListener("resize", () => {
            cyhdyhhyqk.resize();
          });
        });
      },
      getList() {
        this.loading = true;
        listAp(this.queryParams).then(response => {
          this.cyqkList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 打开加载层
      openLoading() {
        this.$modal.loading("正在加载营销活动数据，请稍候！");
      },
      getPvImgUrl(){
        return require("@/assets/images/mads/pv.png");
      },
      getUvImgUrl(){
        return require("@/assets/images/mads/uv.png");
      },
      getLjUvImgUrl(){
        return require("@/assets/images/mads/ljuv.png");
      },
      getXzysUvImgUrl(){
        return require("@/assets/images/mads/xzysuv.png");
      },
      getXzyhUvImgUrl(){
        return require("@/assets/images/mads/xzyhuv.png");
      }
    }
  };
</script>
