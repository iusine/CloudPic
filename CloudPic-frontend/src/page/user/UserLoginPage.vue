<template>
  <div id="userLoginPage" class="userLoginPage">
    <h2 class="title">云图库 - 用户登录</h2>
    <div class="desc">企业级智能协同云图库</div>
    <a-form :model="formState" name="basic" autocomplete="off" @finish="handleSubmit">
      <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
        <a-input v-model:value="formState.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        name="userPassword"
        :rules="[
          { required: true, message: '请输入密码' },
          { min: 8, message: '密码不能小于 8 位' },
        ]"
      >
        <a-input-password v-model:value="formState.userPassword" placeholder="请输入密码" />
      </a-form-item>
      <a-form-item name="remember">
        <a-checkbox v-model:checked="rememberMe" @change="handleRememberMeChange"
          >保存密码</a-checkbox
        >
      </a-form-item>
      <div class="tips">
        <RouterLink class="home" to="/home">返回首页</RouterLink>
        没有账号？
        <RouterLink to="/user/register">去注册</RouterLink>
      </div>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%">登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { message } from 'ant-design-vue'
import { userLogin } from '@/api/userController.ts'
import { useLoginUserStore } from '@/stores/userLoginUserStore.ts'

const formState = reactive<API.UserLoginRequest>({
  userAccount: '',
  userPassword: '',
})

const router = useRouter()
const loginUserStore = useLoginUserStore()
const rememberMe = ref(false)

const handleRememberMeChange = (checked: boolean) => {
  if (checked) {
    localStorage.setItem('userAccount', formState.userAccount)
    localStorage.setItem('userPassword', formState.userPassword)
  } else {
    localStorage.removeItem('userAccount')
    localStorage.removeItem('userPassword')
  }
}

const handleSubmit = async (values: API.UserLoginRequest) => {
  try {
    const res = await userLogin(values)
    const loginUserVO = res.data.data
    if (res.data.code === 200 && loginUserVO) {
      loginUserStore.setLoginUser(loginUserVO)
      handleRememberMeChange(rememberMe.value)
      message.success('登录成功')
      setTimeout(() => {
        router.push('/home')
      }, 500)
    } else {
      message.error('登录失败，' + res.data.message)
    }
  } catch (error) {
    message.error('登录请求失败，请稍后再试')
  }
}

onMounted(() => {
  const savedAccount = localStorage.getItem('userAccount')
  const savedPassword = localStorage.getItem('userPassword')
  if (savedAccount) {
    formState.userAccount = savedAccount
  }
  if (savedPassword) {
    formState.userPassword = savedPassword
  }
})


</script>

<style scoped>
#userLoginPage {
  width: 350px;
  height: 480px;
  margin: 0 auto;
  z-index: 10;
}

.userLoginPage {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  padding: 20px;
  border-radius: 8px;
}

.title {
  text-align: center;
  margin-bottom: 16px;
}

.desc {
  text-align: center;
  color: #bbb;
  margin-bottom: 16px;
}

.tips {
  .home {
    margin-right: 8px;
  }

  margin-bottom: 16px;
  color: #bbb;
  font-size: 13px;
  text-align: right;
}
</style>
