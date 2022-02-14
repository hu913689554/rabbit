<template>
    <div class="login-container">
        <el-form :model="loginForm" :rules="loginRules"
         status-icon
         ref="loginForm" 
         label-position="left" 
         label-width="0px" 
         class="demo-ruleForm login-page">
            <h3 class="title">系统登录</h3>
            <el-form-item prop="username">
                <el-input type="text" 
                    v-model="loginForm.username" 
                    auto-complete="off" 
                    placeholder="用户名"
                ></el-input>
            </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" 
                        v-model="loginForm.password" 
                        auto-complete="off" 
                        placeholder="密码"
                    ></el-input>
                </el-form-item>
            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100%;" @click="this.send" >登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import {setToken} from '@/router/auth.js'  //auth.js

export default {
  name: "login",
  data() {
    return {
      codeUrl: "",
      cookiePassword: "",
      loginForm: {
        username: "admin",
        password: "abc123",
        rememberMe: false,
        code: "",
        uuid: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" },
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" },
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }],
      },
      loading: false,
      // 验证码开关
      captchaOnOff: false,
      // 注册开关
      register: false,
      redirect: undefined,
    };
  },
  methods: {
    send() {
        this.axios({
        method: "post",
        // api 是 http://127.0.0.1:8000 的简写
        url: "api/system/post/postLogin",
        data: this.loginForm,
      })
        .then((res) => {
          console.log(res);
          if (res.data.code === 1) {
            setToken(res.data.data.token)
            this.$router.push({ path: this.redirect || "index" }).catch(()=>{});
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((error) => {
          this.$message.error("服务器连接失败");
        });
    },
  },
};
</script>

