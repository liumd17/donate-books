package com.liumd.data.service;

import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.UserVo;

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
    void userRegister(UserDto userDto);

    /**
     * 通过邮箱账号查询用户
     * @param mailbox
     * @return
     */
    UserVo queryUserByMailbox(String mailbox);
}
