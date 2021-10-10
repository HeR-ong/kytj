<template>
  <div class="bgimg">
    <div style="width: 100%;text-align: center;margin-top: 60px;">
      <h2>考研报考院校历史数据分析与推荐系统</h2>
    </div>
    <div class="content">
      <!--  登录面板    -->
      <div class="panel">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
          <!--  账号和密码组    -->
          <div class="group">
            <label>账号</label>
            <el-form-item prop="userCode">
              <el-input type="text" v-model="ruleForm.userCode" placeholder="请输入账号"></el-input>
            </el-form-item>
          </div>
          <div class="group">
            <label>密码</label>
            <el-form-item prop="pass">
              <el-input type="password" v-model="ruleForm.pass" placeholder="请输入密码"></el-input>
            </el-form-item>
          </div>
          <!--  登录按钮    -->
          <div class="login">
            <el-button class="button" @click="submitForm('ruleForm')">登录</el-button>
          </div>
        </el-form>
      </div>
      <!--  注册按钮    -->
      <div class="register">
        <el-button @click="register">注册</el-button>
      </div>

    </div>

    <el-dialog title="注册账号" :visible.sync="dialogFormVisible" center :modal-append-to-body='false' top="5vh">
      <el-form :model="userInfo" :rules="rules" ref="userInfo">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="userName" required>
          <el-input v-model="userInfo.userName" autocomplete="off" placeholder="姓名必填"></el-input>
        </el-form-item>
        <el-form-item label="账号" :label-width="formLabelWidth" prop="userCode" required>
          <el-input v-model="userInfo.userCode" autocomplete="off" placeholder="账号必填"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" :label-width="formLabelWidth" prop="password" required>
          <el-input v-model="userInfo.password" type="password" autocomplete="off" placeholder="登录密码必填"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth" prop="ackPass" required>
          <el-input v-model="userInfo.ackPass" type="password" autocomplete="off" placeholder="确认密码必填"></el-input>
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
        <el-button type="primary" @click="registerSubmit('userInfo')">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        // if (this.ruleForm.pass !== '') {
        //   this.$refs.ruleForm.validateField('pass')
        // }
        callback()
      }
    }
    var validateUserCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'))
      } else {
        // if (this.ruleForm.userCode !== '') {
        //   this.$refs.ruleForm.validateField('userCode')
        // }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.userInfo.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        pass: '',
        userCode: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        userCode: [
          { validator: validateUserCode, trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '姓名必填', trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        ackPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      dialogFormVisible: false,
      userInfo: {
        userCode: '',
        userName: '',
        classNo: '',
        phone: '',
        address: '',
        email: '',
        password: '',
        ackPass: ''
      },
      formLabelWidth: '120px'
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log('参数' + this.ruleForm)
          this.api.post(this.url.manage.login, {
            userAccount: this.ruleForm.userCode,
            password: this.ruleForm.pass
          }, res => {
            if (res.data.success) {
              // 返回正确的处理
              window.sessionStorage.setItem('userInfo', JSON.stringify(res.data.result))
              this.$message({
                message: '登陆成功',
                type: 'success'
              })
              this.$router.push('/')
            } else {
              // 返回错误的处理
              this.$message.error(res.data.errorInfo)
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    register () {
      this.dialogFormVisible = true
    },
    registerSubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('确定注册？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.api.post(this.url.manage.register, JSON.stringify(this.userInfo), res => {
              if (res.data.success) {
                // 返回正确的处理
                this.dialogFormVisible = false
                this.$message({
                  message: '恭喜你，注册成功',
                  type: 'success'
                })
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
  .bgimg{
    position:fixed;
    top: 0;
    left: 0;
    width:100%;
    height:100%;
    z-index:-10;
    zoom: 1;
    background-color: #fff;
    background: url(./assets/image/bg2.jpg) no-repeat;
    background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-position: center 0;
  }
  /*设置内容模块距离顶部一个有一段距离150px*/
  .content {
    margin-top: 80px;
  }

  /*登录和注册按钮的整体样式*/
  .content button ,.button{
    height: 30px;/*登录和注册按钮的高度*/
    color:  white;/*登录和注册按钮字体颜色为白色*/
    font-size: 18px;/*登录和注册按钮的字体大小*/
    border: 0px;/*无边框*/
    padding: 0px;/*无内边距*/
    cursor: pointer;/*登录和注册按钮的选择时为手形状*/
  }

  /*登录面板*/
  .content .panel {
    background-color: white;/*登录面板背景颜色为白色*/
    width: 302px;/*宽度为302px*/
    text-align: center;/*子内容居中*/
    margin: 0px auto;/*自身居中*/
    padding-top: 20px;/*顶部的内边距为20px*/
    padding-bottom: 20px;/*底部的内边距为20px*/
    border: 1px solid #ddd;/*边框颜色为灰色*/
    border-radius: 5px;/*边框边角有5px的弧度*/

  }

  /*登录和密码组*/
  .content .panel .group {
    text-align: left;/*子内容居中*/
    width: 262px;/*宽度为262px*/
    margin: 0px auto 20px;/*自身居中，并距离底部有20px的间距*/
  }
  .content .panel .group label {
    line-height: 30px;/*高度为30px*/
    font-size: 18px;/*字体大小为18px*/
  }
  .content .panel .group .input {
    display: block;/*设置为块，是为了让输入框独占一行*/
    width: 250px;/*宽度为250px*/
    height: 30px;/*高度为30px*/
    padding: 0px 0px 0px 10px;/*左边内边距为10px，显得美观*/
    font-size: 16px;/*字体大小*/
  }
  .content .panel .group input:focus{

    border-left: 1px solid #CC865E;/*当输入框成为焦点时，左边框颜色编程褐色*/
  }
  .content .panel .button  {
    background-color: #CC865E;/*按钮的背景颜色*/
    width: 260px;/*按钮的宽度*/
  }
  .content .panel .button:hover {
    background-color: white;/*按钮选中后背景颜色为白色*/
    border: 1px solid #CC865E;/*按钮选中后边框颜色为褐色*/
  }

  /*注册按钮*/
  .content .register {
    text-align: center;/*子内容居中*/
    margin-top: 20px;/*顶部的内边距为20px*/
  }
  .content .register button {
    background-color: #466BAF;/*按钮的背景颜色为蓝色*/
    width: 180px;/*按钮的宽度*/
  }

  .content .register button:hover {
    background-color: white;/*按钮选中后背景颜色为白色*/
    color: #466BAF;/*按钮选中后字体颜色为蓝色*/
    border: 1px solid #466BAF;/*按钮选中后边框颜色为蓝色*/
  }
</style>
