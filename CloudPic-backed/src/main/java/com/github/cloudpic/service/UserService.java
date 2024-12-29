package com.github.cloudpic.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.cloudpic.model.dto.user.UserLoginRequest;
import com.github.cloudpic.model.dto.user.UserQueryRequest;
import com.github.cloudpic.model.dto.user.UserRegisterRequest;
import com.github.cloudpic.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.cloudpic.model.vo.user.LoggingUserVO;
import com.github.cloudpic.model.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author admin
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-12-27 08:26:07
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userRegisterRequest 注册信息实体
     * @return 返回用户id
     */
    long userRegister(UserRegisterRequest userRegisterRequest);


    /**
     * 用户登录
     *
     * @param userLoginRequest 用户账号密码实体
     * @param request 请求体
     * @return LoggingUserVO
     */
    LoggingUserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);


    /**
     * 获取用户权限和信息
     *
     * @param request 请求体
     * @return User
     */
    User getgetLoggingUser(HttpServletRequest request);

    /**
     * 用户信息脱敏
     *
     * @param user 用户实体类
     * @return LoggingUserVO
     */
    LoggingUserVO getLoggingUserVO(User user);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获得脱敏后的用户信息
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获得脱敏后的用户信息列表
     *
     * @param userList
     * @return 脱敏后的用户列表
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 获取查询条件
     * @param userQueryRequest
     * @return
     */
    Wrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
