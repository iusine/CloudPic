// @ts-nocheck
import { defineStore } from 'pinia'
import { getLoggingUser } from '@/api/userController.ts'
import  type { LoggingUserVO } from '@/api/typings' // 导入 LoggingUserVO 类型

export const useLoginUserStore = defineStore('loginUser', {
  state: (): { loginUser: LoggingUserVO | null } => ({
    loginUser: {
      userName: '未登录',
    },
  }),
  getters: {
    getLoginUser: (state) => state.loginUser,
  },
  actions: {
    async fetchLoginUser() {
      const res = await getLoggingUser()
      if (res.data.code === 200 && res.data.data) {
        this.loginUser = res.data.data as LoggingUserVO
      }
    },
    setLoginUser(newLoginUser: LoggingUserVO) {
      this.loginUser = newLoginUser
    },
    clearLoginUser() {
      this.loginUser = null
    },
  },
})
