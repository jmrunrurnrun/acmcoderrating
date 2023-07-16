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
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" style="width: 100%;float: right;" @select="handleSelect">
      <!-- <el-menu-item index="1">处理中心</el-menu-item> -->
      <el-submenu index="1">
        <template slot="title">用户管理</template>
        <el-menu-item index="2-1">修改密码</el-menu-item>
        <el-menu-item index="2-2">退出登录</el-menu-item>
        <!-- <el-menu-item index="2-3">选项3</el-menu-item> -->
        <!-- <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu> -->
      </el-submenu>
      <!-- <el-menu-item index="3" disabled>消息中心</el-menu-item>
      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item> -->
    </el-menu>

  </div>
  <!-- <div>
    <header>
     
      <span>{{ userInfo.username }}</span>
     
      <button @click="changePassword">修改密码</button>
      <button @click="logout">退出</button>
    </header>
   
  </div> -->
  <div class="container clearfix" ref="page">


    <!-- <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" style="width: 100%;float: right;" @select="handleSelect">
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>
      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu> -->



    <!-- 左侧导航栏开始 243946 96afbe ffffff-->
    <el-row class="tac">
      <el-col :span="12" class="navBox">
        <el-menu
          :default-active="defaultActive"
          class="el-menu-vertical-demo"
          background-color="#030923"
          text-color="#d5c5e9"
          active-text-color="#FFFFFF"
        >
          <h2>BUCTCODER</h2>

          <el-submenu index="1">
            <template slot="title">
              <i class="iconfont icon-sousuo2"></i>
              <span>信息查询</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="borrow()">
                <i class="iconfont icon-sousuo2"></i>
                <span slot="title">Atcoder</span>
              </el-menu-item>
              <el-menu-item index="1-2" @click="borrow2()">
                <i class="iconfont icon-sousuo2"></i>
                <span slot="title">Codeforces</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <!-- <el-menu-item index="1" @click="borrow()">
            <i class="iconfont icon-sousuo2"></i>
            <span slot="title">信息查询</span>
          </el-menu-item> -->
          <el-menu-item index="2" @click="stuInfo()">
            <i class="iconfont icon-geren3"></i>
            <span slot="title">学生基本信息</span>
          </el-menu-item>

          <!-- <el-menu-item index="3" @click="histogram()">
            <i class="iconfont icon-geren3"></i>
            <span slot="title">图</span>
          </el-menu-item> -->

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-data-analysis"></i>
              <span>数据可视化</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1" @click="histogram()">
                <i class="iconfont icon-geren3"></i>
                <span slot="title">柱状图</span>
              </el-menu-item>
              <el-menu-item index="3-2" @click="bing()">
                <i class="iconfont icon-geren3"></i>
                <span slot="title">饼状图</span>
              </el-menu-item>
            </el-menu-item-group>
            <!-- <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu> -->
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

export default {
  data () {
    return {
      clientHeight: '',
      defaultActive: '1',

      activeIndex: '1'
    }
  },
  async created () {
    if (this.$route.name === 'SelectAll') {
      this.defaultActive = '1'
    }
  },
  mounted () {
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
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  margin: 0 auto;
  background-color: #f3f5f6;
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
  float: right;
  margin-right: 18px;
}
.notice-info .el-tag {
  font-size: 20px;
  width: calc(100% - 10px);
  margin: 5px;
}

.el-menu.el-menu--horizontal {
  border-bottom: none !important;
  float: right;
  margin-left: 100px;
  background: #030923;
}
/* .el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 2px solid #3989fa;
  color: #3989fa;
  font-weight: bold;
} */
.el-menu--horizontal > .el-menu-item {
  font-size: 16px;
  margin: 0 15px 0 300px;
}
</style>
