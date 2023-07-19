<template>
  <!-- <el-container>
    <el-header>Header</el-header>
    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-main>Main</el-main>
    </el-container>
  </el-container> -->
<div>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" style="width: 90%;float: right;position: fixed;" @select="handleSelect"
    background-color="#063631"
    text-color="#85CCB1"
    active-text-color="#F2F7D2">
      <!-- <el-menu-item index="1">处理中心</el-menu-item> -->
      <el-submenu index="1" style="margin-left: 1080px;">
        <template slot="title">用户管理</template>
        <el-menu-item index="2-1" @click="modify()">修改密码</el-menu-item>
        <el-menu-item index="2-2" @click="exit()">退出登录</el-menu-item>
      </el-submenu>
    </el-menu>

    <el-dialog
      :visible.sync="showDialog"
      :title="dialogtitle">

      <el-form :model="formData" :rules="rules" status-icon ref="formData" label-position="left" label-width="0px" class="demo-formData">
        <el-form-item prop="username">
          <el-input :disabled="true" type="text" v-model="this.userid" autocomplete="off" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="formData.userPassword" autocomplete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
          <el-input type="password" v-model="formData.checkPass" autocomplete="off" placeholder="确认密码"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="submit('formData')"> 确认</el-button>
        <el-button @click="cancel()"> 取消</el-button>
      </span>
    </el-dialog>
  </div>

  <div class="container clearfix" ref="page">
    <!-- 左侧导航栏开始 243946 96afbe ffffff-->
    <el-row class="tac">
      <el-col :span="12" class="navBox">
        <el-menu
          :default-active="defaultActive"
          class="el-menu-vertical-demo"
          background-color="#063631"
          text-color="#85CCB1"
          active-text-color="#F2F7D2"
        >
          <h2>BUCTCODER</h2>

          <el-submenu index="1">
            <template slot="title">
              <i class="iconfont icon-sousuo2" style="color:#85CCB1"></i>
              <span>信息查询</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="borrow()">
                <i class="el-icon-medal" style="color:#85CCB1"></i>
                <span slot="title">Atcoder</span>
              </el-menu-item>
              <el-menu-item index="1-2" @click="borrow2()">
                <i class="el-icon-trophy" style="color:#85CCB1"></i>
                <span slot="title">Codeforces</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-menu-item index="2" @click="stuInfo()">
            <i class="iconfont icon-geren3" style="color:#85CCB1"></i>
            <span slot="title">学生基本信息</span>
          </el-menu-item>


          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-data-analysis" style="color:#85CCB1"></i>
              <span>数据可视化</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1" @click="histogram()">
                <i class="el-icon-s-data" style="color:#85CCB1"></i>
                <span slot="title">柱状图</span>
              </el-menu-item>
              <el-menu-item index="3-2" @click="bing()">
                <i class="el-icon-pie-chart" style="color:#85CCB1"></i>
                <span slot="title">饼状图</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>

        </el-menu>
      </el-col>
    </el-row>
    <!-- 左侧导航栏结束 -->
    <router-view class="right_bottom"></router-view>
  </div>
</div>
</template>

<script>
import axios from 'axios';


export default {
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.formData.userPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      clientHeight: '',
      defaultActive: '1',

      activeIndex: '1',
      userid: '',
      showDialog: false,
      dialogtitle: '修改密码',
      formData: {},
      initialForm: {
        // userId: this.userid,
        // userPassword: '',
        // checkPass: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
      }
    }
  },
  async created () {
    if (this.$route.name === 'SelectAll') {
      this.defaultActive = '1'
    }
  },
  mounted () {
    // 获取传递过来的用户名参数
    //const username = this.$route.params.username;
    // 使用获取到的用户名参数进行其他操作
    axios.post('/stu/info/acmer/user/data')
    .then(response => {
      console.log(response)
      if(response.data.code === 400){
        this.userid=response.data.data.userId;
      }
    })
    .catch(error => {
      // 获取失败
      console.error(error);
      // 根据错误进行相应的处理
    });
    console.log('当前用户的用户名:'+this.userid);
    //this.userid=username
    console.log(this.userid)
    // 获取浏览器可视区域高度
    this.clientHeight = `${document.documentElement.clientHeight}`
    const that = this
    window.onresize = function () {
      this.clientHeight = `${document.documentElement.clientHeight}`
      if (that.$refs.page) {
        that.$refs.page.style.height = this.clientHeight + 'px'
      }
    }
  },
  watch: {
    // 如果clientHeight 发生改变，这个函数就会运行
    clientHeight () {
      this.changeFixed(this.clientHeight)
    }
  },
  methods: {
    // 控制浏览器高度
    changeFixed (clientHeight) {
      if (this.$refs.page) {
        this.$refs.page.style.height = clientHeight + 'px'
      }
    },


    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },


    borrow () {
      if (this.$route.name !== 'SelectAll') {
        this.$router.replace({ path: '/SelectAll' })
      }
    },
    borrow2 () {
      if (this.$route.name !== 'SelectAllcf') {
        this.$router.replace({ path: '/SelectAllcf' })
      }
    },
    stuInfo () {
      if (this.$route.name !== 'StuInfo') {
        this.$router.replace({ path: '/StuInfo' })
      }
    },
    histogram () {
      if (this.$route.name !== 'VisualHistogram') {
        this.$router.replace({ path: '/VisualHistogram' })
      }
    },
    bing () {
      if (this.$route.name !== 'VisualB') {
        this.$router.replace({ path: '/VisualB' })
      }
    },
    modify () {
      console.log('用户名：'+this.userid)
      this.showDialog = true
      this.formData.userId=this.username
    },
    submit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 校验通过，发送修改请求
          console.log(this.formData)
          this.formData.userId=this.userid
          this.$axios.post('/stu/info/acmer/user/update', this.formData)
            .then(response => {
              // 修改成功
              console.log(response.data);
              if (response.data.code === 200) {
                    // 修改成功
                    alert('修改成功！');
                    this.formData = {...this.initialForm};
                    this.showDialog = false;
                  } else {
                    // 修改失败，显示错误信息
                    alert(response.data.message)
                    this.$message.error(response.data.message);
                  }
                })
                .catch(error => {
                  // 修改失败
                  console.error(error);
                  // 根据错误进行相应的处理
                });
              } else {
                console.log('error submit');
                return false;
              }
            });
    },
    cancel () {
      this.formData = {...this.initialForm};
      this.showDialog = false;
    },
    exit () {
      this.$router.push({ path: "/" });
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  background-color: #F2F7D2;
  /* background: url('../assets/images/background.jpeg');#f3f5f6 */
}
.container .tac {
  float: left;
    position: fixed;
}
.container .tac,
.container .tac .navBox,
.container .tac .navBox ul {
  width: 220px;
  height: 100%;
}
.container .tac .navBox h2 {
  margin-left: 20px;
  line-height: 56px;
  color: white;
}
.container .tac .navBox p {
  line-height: 56px;
  color: #96afbe;
  margin-left: 10px;
}
.container .tac .navBox .iconfont {
  margin-right: 5px;
  width: 24px;
  text-align: center;
  font-size: 18px;
  vertical-align: middle;
  font-style: normal;
  font-weight: 400;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  display: inline-block;
}
.container .right_head {
  width: calc(100% - 220px);
  height: 55px;
  background-color: white;
  float: right;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
  padding: 0px 18px;
  margin-bottom: 18px;
  box-shadow: 0px 2px 5px #dbdedf;
}
.container .right_head .logo {
  width: 40px;
  height: 40px;
}
.container .right_head div {
  color: #a9acae;
}
.container .right_head div img {
  width: 30px;
  height: 30px;
  display: inline-block;
  border-radius: 50%;
  vertical-align: middle;
}
.container .right_head span {
  font-weight: bold;
}

.container .right_head .el-button {
  background-color: #fff;
  color: black;
  font-size: 20px;
  border: 0px;
}
.container .right_head .el-button:hover {
  color: #409eff;
}
.container .right_bottom {
  width: calc(100% - 256px);
  height: auto;
  float: right;
  margin-top: 5.5%;
  margin-right: 18px;
}
.notice-info .el-tag {
  font-size: 20px;
  width: calc(100% - 10px);
  margin: 5px;
}

.el-menu {
  border:0!important;
}

.el-menu.el-menu--horizontal {
  border-bottom: none !important;
  float: right;
  /* position: fixed; */
  margin-left: 200px;
  background: #454333;
}
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 2px solid #3989fa;
  color: #3989fa;
  font-weight: bold;
}
.el-menu--horizontal > .el-menu-item {
  font-size: 16px;
  margin: 0 15px 0 300px;
}
</style>
