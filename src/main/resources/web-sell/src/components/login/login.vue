<template>
    <div class="login-section">
        <div>
            用户名：<input name="userName" type="text" v-model="formData.userName"  class="login-input" placeholder="请输入用户名" />
        </div>
        <div>
            密码：<input type="password" class="login-input" v-model="formData.password" maxlength="32" placeholder="请输入密码"/>
        </div>
        <div>
            <input type="button" name="Logon" value="登录" class="login-btn" @click="login"/>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">
    import config from 'config';
    export default {
        name: 'Login',
        components: {

        },
        props: {

        },
        data() {
            return {
                formData: {
                    userName: '',
                    password: ''
                }
            };
        },
        methods: {
            //登录
            login() {
                const url = config.BASE_URL +'/user/login'
                this.$http.post(url, {
                    userName: this.formData.userName,
                    password: this.formData.password
                }).then((response) => {
                    var result = response.body;
                    console.log(result)
                });
            }
        },
        created() {

        },
        mounted() {

        }

    };
</script>

<style lang="stylus" rel="stylesheet/stylus">
    .login-section{
        padding: 20px;
        background: #f1f1f1;

    }

</style>
