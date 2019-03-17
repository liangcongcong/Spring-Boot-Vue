<template>
  <div>
    <blog-header></blog-header>
    <hr>
    <Form ref="formInline" :model="loginInfoVo" :rules="ruleInline" inline>
        <FormItem prop="username">
            <Input type="text" v-model="loginInfoVo.username" placeholder="Username">
                <Icon type="ios-person-outline" slot="prepend"></Icon></Input>
        </FormItem>
        <br/>
        <FormItem prop="password">
            <Input type="password" v-model="loginInfoVo.password" placeholder="Password">
                <Icon type="ios-lock-outline" slot="prepend"></Icon></Input>
        </FormItem>
         <br/>
        <FormItem>
            <Button type="primary" @click=" UserLogin">Signin</Button>
        </FormItem>
    </Form>
    <hr>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import blogHeader from "@/components/common/header.vue";
import blogFooter from "@/components/common/footer.vue";
import { UserLoginAction } from "../../api/user.js";

export default {
  name: "Login",
  // blogHeader、blogFooter组件给申明到components里面然后在template里面使用
  components: { blogHeader, blogFooter },
  data() {
    return {
      formInline: {
                    username: '',
                    password: ''
                },
      loginInfoVo: { username: "", password: "" },
      responseResult: [],
      login: true,
      ruleInline: {
                    username: [
                        { required: true, message: 'Please fill in the user name', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: 'Please fill in the password.', trigger: 'blur' },
                        { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
                    ]
                }
    };
  },
  methods: {
    UserLogin() {
      UserLoginAction(this.loginInfoVo).then(res => {
        if (
          this.loginInfoVo.username == "" ||
          this.loginInfoVo.password == ""
        ) {
          this.$Message.success("账号或密码不能为空!");
        } 
        else if (res.data==true) {
              this.$Message.success("成功！");
              this.$router.push({ path: `/HelloWorld` });
            } 
            else{
               this.$Message.success("账号或密码错误！！");
            }
       
        })
     
    
  }
}
}
</script>
