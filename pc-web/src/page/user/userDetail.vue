<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>用户详情</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="modifyUser">编辑信息</el-button>
      </div>
      <div class="text item">
        <label>姓名：</label>{{userDetail.userName}}
      </div>
      <div class="text item">
        <label>账号：</label>{{userDetail.userCode}}
      </div>
      <div class="text item">
        <label>联系方式：</label>{{userDetail.phone}}
      </div>
      <div class="text item">
        <label>联系地址：</label>{{userDetail.address}}
      </div>
      <div class="text item">
        <label>邮箱：</label>{{userDetail.email}}
      </div>
    </el-card>

    <el-dialog title="修改信息" :visible.sync="editFormVisible" center>
      <el-form :model="userDetail" :rules="rules" ref="userDetail">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="userName">
          <el-input v-model="userDetail.userName" autocomplete="off" placeholder="姓名必填"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" :label-width="formLabelWidth" prop="password">
          <el-input v-model="userDetail.password" type="password" autocomplete="off" placeholder="登录密码必填"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="userDetail.phone" autocomplete="off" placeholder="联系方式非必填"></el-input>
        </el-form-item>
        <el-form-item label="联系地址" :label-width="formLabelWidth">
          <el-input v-model="userDetail.address" autocomplete="off" placeholder="联系地址非必填"></el-input>
        </el-form-item>
        <el-form-item label="电子邮件" :label-width="formLabelWidth">
          <el-input v-model="userDetail.email" autocomplete="off" placeholder="电子邮件非必填"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="modifySubmit('userDetail')">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'userList',
  data: function () {
    return {
      userDetail: {
        userCode: '',
        userName: '',
        classNo: '',
        phone: '',
        address: '',
        email: '',
        password: ''
      },
      formLabelWidth: '120px',
      editFormVisible: false,
      rules: {
        userName: [
          { required: true, message: '姓名必填', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码必填', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {

  },
  created: function () {
    var userInfo = window.sessionStorage.getItem('userInfo')
    this.userDetail = JSON.parse(userInfo)
    if (this.userDetail.userType === 'S') {
      this.userDetail.userType = '学生'
    } else {
      this.userDetail.userType = '教师'
    }
  },
  methods: {
    modifyUser (row) {
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
            this.api.post(this.url.manage.editUser, JSON.stringify(this.userDetail), res => {
              this.editFormVisible = false
              if (res.data.success) {
                // 返回正确的处理
                this.$message({
                  message: '修改成功',
                  type: 'success'
                })
                // 刷新页面
                window.sessionStorage.setItem('userInfo', JSON.stringify(this.userDetail))
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
    }
  }
}
</script>
<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
</style>
