<template>
  <div id="BasicLayout">
    <a-layout style="min-height: 100vh">
    <a-layout-header  class="header"><GlobalHeader/></a-layout-header>
    <a-layout-content class="content">
      <RouterView/>
    </a-layout-content>
    <a-layout-footer class="footer">
      <a href="https://github.com/iusine/CloudPic">
        GitHub地址, 欢迎Star
      </a>
    </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts">

import GlobalHeader from '@/components/GlobalHeader.vue'
</script>

<style scoped>
#BasicLayout .header{
  padding-inline: 20px;
  background: #fff;
  color: unset;
  margin-bottom: 16px;
}

#BasicLayout .content{
  padding: 20px;
  background: #fff;
  margin-bottom: 35px;
}
#BasicLayout .footer{
  text-align: center;
  background: azure;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 10px;
}

</style>
