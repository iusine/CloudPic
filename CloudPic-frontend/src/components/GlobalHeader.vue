<template>
  <div id="GlobalHeader">
    <a-row :wrap="false">
      <a-col flex="200px">
        <router-link to="/">
          <div class="title-bar">
            <img class="logo" src="../assets/logo.png" alt="logo" />
            <div class="title">云图库</div>
          </div>
        </router-link>
      </a-col>
      <a-col flex="auto">
        <a-menu
          v-model:selectedKeys="current"
          mode="horizontal"
          :items="items"
          @click="doMenuClick"
        />
      </a-col>
      <a-col flex="120px">
        <div class="user-info">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown>
              <ASpace>
                <a-avatar :src="loginUserStore.loginUser.userAvatar" />
                {{ loginUserStore.loginUser.userName ?? '无名' }}
              </ASpace>
              <template #overlay>
                <a-menu>
                  <a-menu-item @click="doLogout">
                    <LogoutOutlined />
                    退出登录
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts" setup>
// @ts-nocheck
import { h, ref, onMounted } from 'vue'
import { HomeOutlined , LogoutOutlined } from '@ant-design/icons-vue'
import { type MenuProps, message } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import { useLoginUserStore } from '@/stores/userLoginUserStore.ts'
import { userLogout } from '@/api/userController.ts'
import checkAccess from '@/access/checkAccess.ts'
import ACCESS_ENUM from '@/access/accessEnum.ts'

const loginUserStore = useLoginUserStore()

const menus = ref<MenuProps['items']>([
  {
    key: '/home',
    icon: () => h(HomeOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/admin/manage',
    label: '用户管理',
    title: '用户管理',
    meta: {
      hideInMenu: ACCESS_ENUM.ADMIN
    },
  },
  {
    key: '/about',
    label: '其他',
    title: '其他',
  },
])

const menuToRouteItem = (menu) => {
  return {
    key: menu.key,
    label: menu.label,
    icon: menu.icon ? () => h(menu.icon) : undefined,
    title: menu.label,
    meta: {
      hideInMenu: typeof menu.meta?.hideInMenu === 'string' ? menu.meta.hideInMenu : '',
    },
  }
}

const router = useRouter()
const current = ref<string[]>([])

// 路由监听, 设置当前选中菜单高亮
router.afterEach((to, from, next) => {
  current.value = [to.path]
})

// 菜单点击路由跳转
const doMenuClick = ({ key }) => {
  router.push({
    path: key,
  })
}

onMounted(() => {
  const route = router.currentRoute.value // 获取当前路由
  current.value = [route.path] // 初始化选中菜单项
})

// 用户注销
const doLogout = async () => {
  const res = await userLogout()
  console.log(res)
  if (res.data.code === 200) {
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success('成功退出')
    await router.push('/user/login')
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}

// 过滤菜单项
const items = menus.value.filter((menu) => {
  const item = menuToRouteItem(menu);
  // 如果需要隐藏，且用户没有权限，则返回 false
  if (item.meta?.hideInMenu && !checkAccess(loginUserStore.loginUser, item.meta.hideInMenu)) {
    return false;
  }
  return true; // 允许显示其他菜单
});

</script>

<style scoped>
#GlobalHeader .title-bar {
  display: flex;
  align-items: center;
}

.logo {
  width: 48px;
  height: 48px;
  margin-right: 8px;
}

.title {
  font-size: 18px;
  font-weight: bold;
  color: #000000;
  margin-left: 16px;
}
</style>
