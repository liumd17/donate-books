package com.liumd.data.service;

import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.entity.UserEntity;

/**
 * @author liumuda
 * @date 2022/1/10 11:07
 */
public interface UserService {

    /**
     * 用户登录验证
     * @param userDto
     * @return
     */
    UserVo userLogin(UserDto userDto);

    /**
     * 用户注册
     * @param userDto
     * @return
     */
    Boolean userRegister(UserDto userDto);

    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    Boolean updateUser(UserEntity userEntity);

    /**
     * 通过邮箱账号查询用户
     * @param mailbox
     * @return
     */
    UserVo queryUserByMailbox(String mailbox);

    /**
     * 通过用户id查询用户
     * @param userId
     * @return
     */
    UserVo queryUserById(Integer userId);
}
