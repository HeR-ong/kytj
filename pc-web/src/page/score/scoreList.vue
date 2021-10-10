<template>
  <div>
    <el-card class="box-card">
      <h3>历史考研数据列表</h3>
      <el-form :inline="true" :model="formInline" class="demo-form-inline" style="width: 100%">
        <el-form-item label="学校名称">
          <el-input v-model="formInline.name" placeholder="学校名称"></el-input>
        </el-form-item>
        <el-form-item label="年份">
          <el-input v-model="formInline.year" placeholder="年份"></el-input>
        </el-form-item>
        <el-form-item label="专业名称">
          <el-input v-model="formInline.subject" placeholder="专业名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchScore">查询</el-button>
          <el-button type="primary" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="tableData" stripe border style="width: 100%" header-row-class-name="header-class"
      >
        <el-table-column property="year" label="年份" align="center" width="70"></el-table-column>
        <el-table-column show-overflow-tooltip="true" property="name" label="学校名称" align="center"></el-table-column>
        <el-table-column show-overflow-tooltip="true" property="classify" label="院系名称" align="center"></el-table-column>
        <el-table-column show-overflow-tooltip="true" property="subjectCode" label="专业代码" align="center" width="100"></el-table-column>
        <el-table-column show-overflow-tooltip="true" property="subject" label="专业名称" align="center"></el-table-column>
        <el-table-column show-overflow-tooltip="true" property="sumScore" label="分数线" align="center" width="100"></el-table-column>
        <el-table-column property="gmtCreate" label="创建时间" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="detail(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-row type="flex" justify="end" style="padding:20px 0; ">
        <el-pagination
          :current-page="param.pageNo"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="param.pageSize"
          @size-change="sizeChange"
          @prev-click="prePage"
          @next-click="nextPage"
          @current-change="currentChange"
          layout="sizes, prev, pager, next"
          :total="total">
        </el-pagination>
      </el-row>
    </el-card>

    <el-dialog title="院校专业录取详情" :visible.sync="viewFormVisible" center>
      <el-form :model="scoreDetail">
        <el-form-item label="年份" :label-width="formLabelWidth" prop="year">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.year" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="学校名称" :label-width="formLabelWidth" prop="name">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.name" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="院系名称" :label-width="formLabelWidth" prop="classify">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.classify" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="专业代码" :label-width="formLabelWidth" prop="subjectCode">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.subjectCode" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="专业名称" :label-width="formLabelWidth" prop="subject">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.subject" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="分数线" :label-width="formLabelWidth" prop="sumScore">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.sumScore" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="政治/科目一" :label-width="formLabelWidth" prop="one">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.one" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="外语/科目二" :label-width="formLabelWidth" prop="two">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.two" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="科目三" :label-width="formLabelWidth" prop="three">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.three" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="科目四" :label-width="formLabelWidth" prop="four">
          <div style="width: 42%;">
            <el-input class="inputWidth" disabled="true" v-model="scoreDetail.four" autocomplete="off"></el-input>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="viewFormVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'userList',
  data: function () {
    return {
      tableData: [],
      formInline: {
        year: '',
        name: '',
        subject: ''
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
      formLabelWidth: '120px',
      viewFormVisible: false,
      param: {
        pageNo: 1,
        pageSize: 10
      },
      total: 0,
      pages: 0
    }
  },
  computed: {

  },
  created: function () {
    this.getScoreList(this.param)
  },
  methods: {
    change (e) {
      this.$forceUpdate()
    },
    reset () {
      this.formInline.year = ''
      this.formInline.name = ''
      this.formInline.subject = ''
      this.searchScore()
    },
    detail (row) {
      this.scoreDetail = row
      this.viewFormVisible = true
    },
    getScoreList (param) {
      this.api.post(this.url.manage.queryWordByPage, JSON.stringify(param), res => {
        if (res.data.success) {
          // 返回正确的处理
          this.tableData = res.data.result.rows
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
    searchScore () {
      var param = {
        year: this.formInline.year,
        name: this.formInline.name,
        subject: this.formInline.subject,
        pageNo: this.param.pageNo,
        pageSize: this.param.pageSize
      }
      this.getScoreList(param)
    },
    sizeChange (pageSize) {
      this.param.pageSize = pageSize
      this.searchScore()
    },
    prePage (pageNo) {
      this.param.pageNo = pageNo - 1
      this.searchScore()
    },
    nextPage (pageNo) {
      this.param.pageNo = pageNo
      this.searchScore()
    },
    currentChange (pageNo) {
      this.param.pageNo = pageNo
      this.searchScore()
    }
  }
}
</script>
<style>
  .allActive > .head > .el-col > .el-col {
    padding: 20px 0;
    border-right: solid 1px #fff;
  }

  .allActive .head {
    text-align: center;
    color: #fff;
    font-size: 30px;
    margin-bottom: 50px;
  }

  .allActive .head span {
    font-size: 16px;
  }

  .addbtn{
    float: right;
    margin-bottom: 5px;
  }
  .header-class{
    background: darkturquoise;
  }
  .inputWidth{
    width: 420px;
  }
</style>
