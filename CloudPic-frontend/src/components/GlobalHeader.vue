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
        <a-menu v-model:selectedKeys="current" mode="horizontal" :items="items" @click="doMenuClick"/>
      </a-col>
      <a-col flex="120px">
        <div class="user-info">
          <a-button type="primary" style="margin-left: 16px">登录</a-button>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts" setup>
// @ts-nocheck
import { h, ref } from 'vue'
import { HomeOutlined } from '@ant-design/icons-vue'
import type { MenuProps } from 'ant-design-vue'
import { useRouter } from 'vue-router'

const items = ref<MenuProps['items']>([
  {
    key: '/',
    icon: () => h(HomeOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/about',
    label: '关于',
    title: '关于',
  },
  {
    key: '/others',
    label: h('a', { href: 'https://antdv.com', target: '_blank' }, '其他'),
    title: '其他',
  },
])

// 菜单点击路由跳转
const router = useRouter();
const doMenuClick = ({ key }) => {
  router.push({
    path: key,
  })
};

// 路由监听, 设置当前选中菜单高亮
const current = ref<string[]>([]);
router.afterEach((to, from, next) => {
  current.value = [to.path];
})
/*router.afterEach(() => {
  current.value = [router.currentRoute.value.path];
});*/

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
