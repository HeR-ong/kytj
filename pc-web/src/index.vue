<template>
  <div id="index">
    <!-- 头部导航 -->
    <header class="header" :class="{ 'header-fixed' : headerFixed }">
      <div style="display: flex;">
        <div class="title">考研报考院校历史数据分析与推荐系统</div>
        <el-menu :default-active="1" class="el-menu-demo" mode="horizontal" background-color="#545c64"
                 text-color="#fff"
                 active-text-color="white" @select="handleSelect">
          <el-submenu index="2">
            <template slot="title">
              <div>
                <el-row class="demo-avatar demo-basic">
                  <el-col :span="12">
                    <div class="demo-basic--circle" style="display: flex">
                      <div class="block"><el-avatar :size="30" :src="circleUrl"></el-avatar></div>
                      <div style="margin-left: 10px">{{userName}}</div>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </template>
            <el-menu-item index="2-1" @click="logout">注销登录</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </header>
    <div v-show="headerFixed" style="position: relative;height: 60px;width: 100%;"></div>
    <main>
      <!-- 左侧导航 -->
      <div class="main-left">
        <el-menu default-active="/welcome" class="el-menu-vertical-demo" :router="true" background-color="#545c64"
                 text-color="#fff"
                 >
          <el-menu-item index="/welcome" :class="{'isActive': welcomeActive}">用户首页</el-menu-item>
          <el-menu-item index="/scoreList" :class="{'isActive': !active}">历史考研数据管理</el-menu-item>
          <el-menu-item index="/userDetail" :class="{'isActive': !active}">个人信息管理</el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
          <el-menu-item></el-menu-item>
        </el-menu>
      </div>

      <!-- 右侧主内容区 -->
      <div  class="main-right" >
        <transition name="fade">
          <router-view class="view"></router-view>
        </transition>
      </div>
    </main>
  </div>
</template>

<script>
// import Vue from 'vue'
// import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
// Vue.use(ElementUI)

export default {
  name: 'index',
  data: function () {
    return {
      welcomeActive: true,
      active: true,
      headerFixed: true,
      isTeacher: false,
      isStudent: false,
      userName: '张老师',
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    }
  },
  created: function () {
    if (window.sessionStorage.getItem('userInfo')) {
      this.$router.push('/welcome')
      var userInfo = window.sessionStorage.getItem('userInfo')
      userInfo = JSON.parse(userInfo)
      this.userName = userInfo.userName
      if (userInfo.userType === 'A') {
        this.isTeacher = true
        this.isStudent = true
      } else if (userInfo.userType === 'T') {
        this.isTeacher = true
      } else {
        this.isStudent = true
      }
    } else {
      this.$router.push('/login')
    }
  },
  methods: {
    logout () {
      this.$confirm('确认注销登录？')
        .then(_ => {
          window.sessionStorage.removeItem('userInfo')
          this.$router.push('/login')
        })
        .catch(_ => {})
    }
  },
  watch: {
    '$route': function (to, from) {
      if (to.path === '/welcome') {
        this.welcomeActive = true
      } else {
        this.welcomeActive = false
      }
    }
  }
}

</script>

<style>

  body{margin: 0;}
  #app {
    min-width: 1200px;
    margin: 0 auto;
    font-family: "Helvetica Neue","PingFang SC",Arial,sans-serif;
  }
  /* 头部导航 */
  header{z-index: 1000;min-width: 1200px;transition: all 0.5s ease; background-color: #fff;  box-shadow: 0 2px 4px 0 rgba(0,0,0,.12),0 0 6px 0 rgba(0,0,0,.04);  }
  header.header-fixed{position: fixed;top: 0;left: 0;right: 0;background-color:#545c64;text-color:#fff}
  /*header .el-menu-demo{padding-left: 300px!important;}*/

  /* 主内容区 */
  main{display: -webkit-box;display: -ms-flexbox;display: flex;  border-top: solid 2px #E9ECF1;  background-color: #FCFCFC;  }
  main .main-left{text-align: center;-webkit-box-flex: 0;-ms-flex: 0 0 200px;flex: 0 0 200px;}
  main .main-right{-webkit-box-flex:1;-ms-flex:1;flex:1; background-color: #fff;padding: 10px 10px;}
  main .el-menu{background-color: transparent!important;}
  /*  */
  .router-link{display:inline-block;width:100%;height:100%;text-align:center;color:#475669;text-decoration: none; }
  .is-active .router-link{color:#20a0ff; }

  /* 单选框 */
  .el-form-item .el-radio+.el-radio {
    margin-left: 30px!important;
  }
  /* 路由切换动效 */
  .fade-enter-active, .fade-leave-active {
    /*transition: all .5s;*/
  }
  .fade-enter, .fade-leave-active {
    opacity: 0;
  }

  .list-enter-active, .list-leave-active {
    transition: all 1s;
  }
  .list-enter, .list-leave-active {
    opacity: 0;
    transform: translateY(30px);
  }

  /* 导航栏菜单选中效果 */
  .isActive{color: #20a0ff!important;}
  #app main .aside .is-active{color: #475669;}

  /* 卡片 */
  .el-card{overflow: visible!important;}
  .title{
    height: 55px;
    width: 100%;
    text-align: center;
    border: none;
    z-index: 1000;
    line-height: 55px;
    font-size: 20px;
    font-weight: bold;
    color: white;
  }

  .el-menu--horizontal>.el-submenu .el-submenu__icon-arrow {
    display: none;
  }
</style>
