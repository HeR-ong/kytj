<template>
  <div class="activePublic">
    <div class="welcome">Hello，{{userName}}，欢迎使用~</div>
    <div class="demo-image__placeholder">
      <div class="block">
        <el-form :inline="true" :model="formInline" class="demo-form-inline" style="width: 100%;padding-top: 50px;text-align: center;">
          <el-form-item>
            <el-input v-model="formInline.name" placeholder="请输入学校名称进行推荐（支持模糊匹配）" style="width: 300px;"></el-input>
            <el-input v-model="formInline.subject" placeholder="请输入专业名称进行推荐（支持模糊匹配）" style="width: 300px;"></el-input>
            <el-input v-model="formInline.sumScore" placeholder="请输入考研分数进行推荐" style="width: 200px;"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="openAdd">推荐</el-button>
            <el-button type="primary" @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="search-content" v-if="dialogFormVisible">
        <el-table :data="tableData" stripe border style="width: 100%;" header-row-class-name="header-class"
        >
          <el-table-column show-overflow-tooltip="true" property="name" label="学校名称" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" property="classify" label="院系名称" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" property="subjectCode" label="专业代码" align="center" width="100"></el-table-column>
          <el-table-column show-overflow-tooltip="true" property="subject" label="专业名称" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" property="sumScore" label="历年均分" align="center" width="100"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" @click="getHisData(scope.row)">查看历年数据</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <el-dialog title="历年详情" :visible.sync="hisFormVisible" center>
        <el-table :data="hisdata" stripe border style="width: 900px;" header-row-class-name="header-class"
        >
          <el-table-column property="year" label="年份" align="center"></el-table-column>
          <el-table-column property="name" label="学校名称" align="center" width="150"></el-table-column>
          <el-table-column property="classify" label="院系名称" align="center" width="100"></el-table-column>
          <el-table-column property="subjectCode" label="专业代码" align="center"></el-table-column>
          <el-table-column property="subject" label="专业名称" align="center" width="150"></el-table-column>
          <el-table-column property="sumScore" label="分数线" align="center"></el-table-column>
          <el-table-column property="gmtCreate" label="创建时间" align="center" width="160"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="text" @click="detail(scope.row)">详情</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
          <el-button @click="hisFormVisible = false">关 闭</el-button>
        </div>
      </el-dialog>

      <el-dialog title="院校专业录取详情" :visible.sync="viewFormVisible" center>
        <el-form :model="scoreDetail">
          <el-form-item label="年份" :label-width="formLabelWidth" prop="year">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.year" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="学校名称" :label-width="formLabelWidth" prop="name">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.name" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="院系名称" :label-width="formLabelWidth" prop="classify">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.classify" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="专业代码" :label-width="formLabelWidth" prop="subjectCode">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.subjectCode" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="专业名称" :label-width="formLabelWidth" prop="subject">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.subject" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="分数线" :label-width="formLabelWidth" prop="sumScore">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.sumScore" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="政治/科目一" :label-width="formLabelWidth" prop="one">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.one" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="外语/科目二" :label-width="formLabelWidth" prop="two">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.two" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="科目三" :label-width="formLabelWidth" prop="three">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.three" autocomplete="off"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="科目四" :label-width="formLabelWidth" prop="four">
            <div style="width: 80%;">
              <el-input class="inputWidth" disabled="true" v-model="scoreDetail.four" autocomplete="off"></el-input>
            </div>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="viewFormVisible = false">关 闭</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  name: 'welcome',
  data: function () {
    return {
      tableData: [],
      hisdata: [],
      formInline: {
        name: '',
        subject: '',
        sumScore: ''
      },
      scoreDetail: {
        id: '',
        year: '',
        name: '',
        classify: '',
        subjectCode: '',
        subject: '',
        sumScore: '',
        one: '',
        two: '',
        three: '',
        four: ''
      },
      dialogFormVisible: false,
      hisFormVisible: false,
      viewFormVisible: false,
      formLabelWidth: '120px',
      param: {
        pageNo: 1,
        pageSize: 10
      },
      total: 0,
      pages: 0
    }
  },
  methods: {
    detail (row) {
      this.scoreDetail = row
      this.viewFormVisible = true
    },
    openAdd () {
      if (this.formInline.sumScore === '') {
        this.$message.error('请输入考研分数再匹配')
        return
      }
      this.searchScore()
      this.dialogFormVisible = true
    },
    reset () {
      this.formInline.name = ''
      this.formInline.subject = ''
      this.formInline.sumScore = ''
      this.dialogFormVisible = false
      this.tableData = []
    },
    searchScore () {
      var param = {
        name: this.formInline.name,
        subject: this.formInline.subject,
        sumScore: this.formInline.sumScore
      }
      this.getScoreList(param)
    },
    getScoreList (param) {
      this.api.post(this.url.manage.recommendTop10, JSON.stringify(param), res => {
        if (res.data.success) {
          // 返回正确的处理
          this.tableData = res.data.result
          this.total = res.data.result.total
          this.pages = res.data.result.pages
          this.param.pageNo = res.data.result.pageNo
          console.log('----' + this.tableData)
        } else {
          // 返回错误的处理
          this.$message.error(res.data.errorInfo)
        }
      })
    },
    getHisData (row) {
      this.hisFormVisible = true
      var param = {
        name: row.name,
        subject: row.subject,
        pageNo: 1,
        pageSize: 10
      }
      this.api.post(this.url.manage.queryWordByPage, JSON.stringify(param), res => {
        if (res.data.success) {
          // 返回正确的处理
          this.hisdata = res.data.result.rows
          this.total = res.data.result.total
          this.pages = res.data.result.pages
          this.param.pageNo = res.data.result.pageNo
          console.log('----' + this.hisdata)
        } else {
          // 返回错误的处理
          this.$message.error(res.data.errorInfo)
        }
      })
    }
  },
  created: function () {
    var userInfo = window.sessionStorage.getItem('userInfo')
    userInfo = JSON.parse(userInfo)
    this.userName = userInfo.userName
  },
  watch: {
    '$route': function (to, from) {
      this.isRouter = true
    }
  }
}

</script>

<style>
  .activePublic{
    top: 0;
    left: 0;
    width:100%;
    height:100%;
    zoom: 1;
    background-color: #fff;
    background: url(../../assets/image/bg.jpg) no-repeat;
    background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-position: center 0;
  }
  .welcome{
    font-size: 18px;
    color: #e8efeb;
    font-weight: bold;
    padding-top: 25px;
    padding-left: 80px;
  }
  .search-content{
    width: 80%;
    margin-left: 110px;
    margin-top: 30px;
  }
</style>
