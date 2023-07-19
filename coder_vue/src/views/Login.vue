<template>
  <div class="login-container">
    <el-form :model="ruleForm" :rules="rules" status-icon ref="ruleForm" label-position="left" label-width="0px" class="demo-ruleForm">
      <h3 class="title" style="text-align:center;margin:10px 0 20px 0;color: #063631;">系统登陆</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="ruleForm.userId" autocomplete="off" placeholder="用户名" ref="userIdInput"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="ruleForm.userPassword" autocomplete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="button1" @click="submitForm('ruleForm')">登录</el-button>
        <el-button type="button2" @click="resetForm()">重置</el-button>
        <span style="margin-left:20px;color: #797979;">如果您还没有账号请先 <span style="color:#6EA665;cursor:pointer" @click="register()">注册</span></span>
    </el-form-item>
    </el-form>
  </div>
<!-- <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"> -->
    <!-- <el-form-item label="密码" prop="pass">
      <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="年龄" prop="age">
      <el-input v-model.number="ruleForm.age"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item> -->
  <!-- </el-form> -->
  </template>
  <script>
  import axios from 'axios'
    export default {
      data() {
        var checkname = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('用户名不能为空'));
          }
          // setTimeout(() => {
          //   if (!Number.isInteger(value)) {
          //     callback(new Error('请输入数字值'));
          //   } else {
          //     if (value < 18) {
          //       callback(new Error('必须年满18岁'));
          //     } else {
          //       callback();
          //     }
          //   }
          // }, 1000);
        };
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          }
        };
        // var validatePass2 = (rule, value, callback) => {
        //   if (value === '') {
        //     callback(new Error('请再次输入密码'));
        //   } else if (value !== this.ruleForm.pass) {
        //     callback(new Error('两次输入密码不一致!'));
        //   } else {
        //     callback();
        //   }
        // };
        return {
          // username: '',//传参用
          ruleForm: {},//{
            // userId: '',
            // userPassword: '',
          //},
          initialForm: {},
          rules: {
            name: [
              { validator: checkname, trigger: 'blur'}
            ],
            pass: [
              { validator: validatePass, trigger: 'blur' }
            ],
            // checkPass: [
            //   { validator: validatePass2, trigger: 'blur' }
            // ],
            // age: [
            //   { validator: checkAge, trigger: 'blur' }
            // ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              // 校验通过，发送登录请求
              axios.post('/stu/info/acmer/user/login', this.ruleForm)//{
                // userId: this.ruleForm.userId,
                // userPassword: this.ruleForm.userPassword
              //})
              .then(response => {
                 // 登录成功
                console.log(response.data);
                if (response.data.code === 200) {
                  // 登录成功，跳转到其他页面或执行其他操作
                  alert('登录成功！');
                  // const userInput = this.$refs.userIdInput.value;
                  // console.log(userInput)
                  // this.$router.push({ name: 'SelectAll', params: { username: userInput } });
                  this.$router.push({ path: "/SelectAll" });
                } else {
                  // 登录失败，显示错误信息
                  alert(response.data.message)
                  this.$message.error(response.data.message);
                }
              })
              .catch(error => {
                // 登录失败
                console.error(error);
                // 根据错误进行相应的处理
              });
              // alert('登录成功！');
              // this.$router.push({ path: "/SelectAll" });
            } else {
              console.log('error submit');
              return false;
            }
          });
        },
        resetForm() {
          console.log('reset')
          //this.$refs[formName].resetFields();
          this.ruleForm = { ...this.initialForm };
        },
        register () {
          console.log('regi')
          this.$router.push({ path: "/Register" });
        }
      }
    }
  </script>

<style>
.login-container {
    width: 100%;
    height: 100%;
    background-image: url("../assets/images/green.jpg");
    background-size: cover;
    background-position: center;
    position: fixed;
}
.demo-ruleForm {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
}
.el-button--button1 {
    color: white;
    background-color: #AAD19F;
    border-color: #AAD19F;
  }
.el-button--button1:hover {
    color: #AAD19F;
    background-color: white;
    border-color: #AAD19F;
  }
  .el-button--button2 {
    color: white;
    background-color: #97C2AD;
    border-color: #97C2AD;
  }
  .el-button--button2:hover {
    color: #97C2AD;
    background-color: white;
    border-color: #97C2AD;
  }
</style>