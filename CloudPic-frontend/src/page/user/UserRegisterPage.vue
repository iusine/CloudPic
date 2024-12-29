<template>
  <div id="UserRegisterPage" class="UserRegisterPage">
    <h2 class="title">云图库 - 用户注册</h2>
    <a-form :model="formStates" name="basic" autocomplete="off" @finish="handleSubmits">
      <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
        <a-input v-model:value="formStates.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        name="userPassword"
        :rules="[
          { required: true, message: '请输入密码' },
          { min: 8, message: '密码不能小于 8 位' },
        ]"
      >
        <a-input-password v-model:value="formStates.userPassword" placeholder="设置密码" />
      </a-form-item>
      <a-form-item
        name="checkPassword"
        :rules="[
          { required: true, message: '请输入确认密码' },
          { min: 8, message: '密码不能小于 8 位' }
        ]"
      >
        <a-input-password v-model:value="formStates.checkPassword" placeholder="确认密码" />
      </a-form-item>
      <div class="tips">
        <RouterLink class="home" to="/home" >返回首页</RouterLink>
        已有账号？
        <RouterLink to="/user/login" >去登录</RouterLink>
      </div>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%">注册</a-button>
      </a-form-item>
    </a-form>

  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { userRegister } from '@/api/userController.ts'
import { message } from 'ant-design-vue'

const formStates = reactive<API.UserRegisterRequest>({
  userAccount: '',
  userPassword: '',
  checkPassword: '',
})

const handleSubmits = async () => {
  // 检查密码是否一致
  if (formStates.userPassword !== formStates.checkPassword) {
    message.error('密码不一致');
    return;
  }
  try {
    const res = await userRegister(formStates);
    if (res.data.code === 200) {
      message.success('注册成功');
      formStates.userAccount = '';
      formStates.userPassword = '';
      formStates.checkPassword = '';
    } else {
      message.error('注册失败，' + res.data.message);
    }
  } catch (error) {
    message.error('注册请求失败，请稍后再试');
  }
}


</script>

<style scoped>
#UserRegisterPage {
  width: 350px;
  margin: 0 auto;
  height: 480px;
  z-index: 10; /* 设置较高的 z-index */
}

.UserRegisterPage {
  position: fixed; /* 使其固定在视口 */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%); /* 居中显示 */
  background: white; /* 背景颜色 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 阴影效果 */
  padding: 20px; /* 内边距 */
  border-radius: 8px; /* 边框圆角 */
}

.title {
  text-align: center;
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
