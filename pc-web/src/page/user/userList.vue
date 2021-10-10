<template>
  <div>
    <el-card class="box-card">
      <h3>学生列表</h3>
      <el-form :inline="true" :model="formInline" class="demo-form-inline" style="width: 100%">
        <el-form-item label="学号">
          <el-input v-model="formInline.userCode" placeholder="学号"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="formInline.userName" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchUser">查询</el-button>
        </el-form-item>
      </el-form>

      <div class="addbtn">
        <el-button type="primary" @click="openAdd">新增</el-button>
      </div>

      <el-table :data="tableData" stripe border style="width: 100%" header-row-class-name="header-class"
      >
        <el-table-column property="userCode" label="学号" align="center" width="100"></el-table-column>
        <el-table-column property="userName" label="姓名" align="center" width="100"></el-table-column>
        <el-table-column property="classNo" label="班级" align="center" width="100"></el-table-column>
        <el-table-column property="phone" label="联系方式" align="center" width="110"></el-table-column>
        <el-table-column property="address" label="联系地址" align="center"></el-table-column>
        <el-table-column property="email" label="邮箱" align="center"></el-table-column>
        <el-table-column property="gmtCreate" label="创建时间" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <!--<el-button type="text" @click="checkDetail(scope.row.id)">查看详情</el-button>-->
            <el-button type="text" @click="modifyUser(scope.row)">修改</el-button>
            <el-button type="text" @click="deleteUser(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-row type="flex" justify="end" style="padding:20px 0; ">
        <el-pagination
          :current-page="param.pageNo"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="param.pageSize"
          @size-change="sizeChange"
          @prev-click="currentChange"
          @next-click="currentChange"
          @current-change="currentChange"
          layout="sizes, prev, pager, next"
          :total="total">
        </el-pagination>
      </el-row>
    </el-card>

    <el-dialog title="新增学生账号" :visible.sync="dialogFormVisible" center>
      <el-form :model="userInfo" :rules="rules" ref="userInfo">
        <el-form-item label="学生姓名" :label-width="formLabelWidth" prop="userName">
          <el-input v-model="userInfo.userName" autocomplete="off" placeholder="学生姓名必填"></el-input>
        </el-form-item>
        <el-form-item label="学号" :label-width="formLabelWidth" prop="userCode">
          <el-input v-model="userInfo.userCode" autocomplete="off" placeholder="学号必填"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" :label-width="formLabelWidth" prop="password">
          <el-input v-model="userInfo.password" type="password" autocomplete="off" placeholder="登录密码必填"></el-input>
        </el-form-item>
        <el-form-item label="班级号" :label-width="formLabelWidth" prop="classNo">
          <el-input v-model="userInfo.classNo" autocomplete="off" placeholder="班级号必填"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="userInfo.phone" autocomplete="off" placeholder="联系方式非必填"></el-input>
        </el-form-item>
        <el-form-item label="联系地址" :label-width="formLabelWidth">
          <el-input v-model="userInfo.address" autocomplete="off" placeholder="联系地址非必填"></el-input>
        </el-form-item>
        <el-form-item label="电子邮件" :label-width="formLabelWidth">
          <el-input v-model="userInfo.email" autocomplete="off" placeholder="电子邮件非必填"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser('userInfo')">提 交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改学生信息" :visible.sync="editFormVisible" center>
      <el-form :model="userInfo" :rules="rules" ref="userInfo">
        <el-form-item label="学生姓名" :label-width="formLabelWidth" prop="userName">
          <el-input v-model="userInfo.userName" autocomplete="off" placeholder="学生姓名必填"></el-input>
        </el-form-item>
        <el-form-item label="学号" :label-width="formLabelWidth" prop="userCode">
          <el-input v-model="userInfo.userCode" autocomplete="off" placeholder="学号必填"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" :label-width="formLabelWidth" prop="password">
          <el-input v-model="userInfo.password" type="password" autocomplete="off" placeholder="登录密码必填"></el-input>
        </el-form-item>
        <el-form-item label="班级号" :label-width="formLabelWidth" prop="classNo">
          <el-input v-model="userInfo.classNo" autocomplete="off" placeholder="班级号必填"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="userInfo.phone" autocomplete="off" placeholder="联系方式非必填"></el-input>
        </el-form-item>
        <el-form-item label="联系地址" :label-width="formLabelWidth">
          <el-input v-model="userInfo.address" autocomplete="off" placeholder="联系地址非必填"></el-input>
        </el-form-item>
        <el-form-item label="电子邮件" :label-width="formLabelWidth">
          <el-input v-model="userInfo.email" autocomplete="off" placeholder="电子邮件非必填"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editCancel">取 消</el-button>
        <el-button type="primary" @click="modifySubmit('userInfo')">修 改</el-button>
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
        userCode: '',
        userName: ''
      },
      dialogFormVisible: false,
      editFormVisible: false,
      userInfo: {
        userCode: '',
        userName: '',
        classNo: '',
        phone: '',
        address: '',
        email: '',
        password: ''
      },
      formLabelWidth: '120px',
      param: {
        pageNo: 1,
        pageSize: 10,
        userType: 'S'
      },
      total: 0,
      pages: 0,
      rules: {
        userCode: [
          { required: true, message: '学号必填', trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '姓名必填', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码必填', trigger: 'blur' }
        ],
        classNo: [
          { required: true, message: '班级必填', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {

  },
  created: function () {
    this.getUserList(this.param)
  },
  methods: {
    modifyUser (row) {
      this.userInfo.userCode = row.userCode
      this.userInfo.userName = row.userName
      this.userInfo.classNo = row.classNo
      this.userInfo.phone = row.phone
      this.userInfo.address = row.address
      this.userInfo.email = row.email
      this.userInfo.password = row.password
      this.editFormVisible = true
    },
    modifySubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('确定修改？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.api.post(this.url.manage.editUser, JSON.stringify(this.userInfo), res => {
              this.editFormVisible = false
              if (res.data.success) {
                // 返回正确的处理
                this.$message({
                  message: '修改成功',
                  type: 'success'
                })
                // 刷新页面
                this.getUserList(this.param)
                // 重置表单
                this.resetForm()
              } else {
                // 返回错误的处理
                this.$message.error(res.data.errorInfo)
              }
            })
          }).catch(() => {
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    deleteUser (row) {
      this.$confirm('确定删除--' + row.userName + '？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.api.post(this.url.manage.deleteUser, {id: row.id}, res => {
          if (res.data.success) {
            // 返回正确的处理
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            // 刷新页面
            this.getUserList(this.param)
          } else {
            // 返回错误的处理
            this.$message.error(res.data.errorInfo)
          }
        })
      }).catch(() => {
      })
    },
    onSubmit () {
      console.log('submit!')
    },
    openAdd () {
      this.dialogFormVisible = true
      // 重置表单
      this.resetForm()
    },
    getUserList (param) {
      this.api.post(this.url.manage.queryUserByPage, JSON.stringify(param), res => {
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
    addUser (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('确定添加？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.api.post(this.url.manage.stuRegister, JSON.stringify(this.userInfo), res => {
              if (res.data.success) {
                // 返回正确的处理
                this.dialogFormVisible = false
                this.$message({
                  message: '恭喜你，添加成功',
                  type: 'success'
                })
                // 刷新页面
                this.getUserList(this.param)
                // 重置表单
                this.resetForm()
              } else {
                // 返回错误的处理
                this.$message.error(res.data.errorInfo)
              }
            })
          }).catch(() => {
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    searchUser () {
      var param = {
        userName: this.formInline.userName,
        userCode: this.formInline.userCode,
        pageNo: this.param.pageNo,
        pageSize: this.param.pageSize,
        userType: 'S'
      }
      this.getUserList(param)
    },
    sizeChange (pageSize) {
      this.param.pageSize = pageSize
      this.searchUser()
    },
    currentChange (pageNo) {
      this.param.pageNo = pageNo
      this.searchUser()
    },
    resetForm () {
      this.userInfo = {
        userCode: '',
        userName: '',
        classNo: '',
        phone: '',
        address: '',
        email: '',
        password: ''
      }
    },
    editCancel () {
      this.editFormVisible = false
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
</style>
