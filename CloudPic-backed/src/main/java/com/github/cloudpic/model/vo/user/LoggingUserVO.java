package com.github.cloudpic.model.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author iusie
 * @description 登录的用户信息
 * @date 2024/12/27
 */
@Data
public class LoggingUserVO implements Serializable {

    private static final long serialVersionUID = -1110235170505958882L;

    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;


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

    /**
     * 创建时间
     */
    private Date createTime;

}