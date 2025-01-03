package com.github.cloudpic.model.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author iusie
 * @description
 * @date 2024/12/27
 */

@Data
public class UserUpdateRequest implements Serializable {
        /**
         * id
         */
        private Long id;

        /**
         * 密码
         */
        private String userPassword;

        /**
         * 用户昵称
         */
        private String userName;

        /**
         * 用户头像
         */
        private String userAvatar;

        /**
         * 用户简介
         */
        private String userProfile;

        /**
         * 电话
         */
        private String phone;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 用户角色：user/admin
         */
        private String userRole;

        private static final long serialVersionUID = 1L;
}
