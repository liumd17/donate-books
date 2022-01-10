package com.liumd.data.service;

import com.liumd.data.dto.UserDto;

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
    Boolean userLogin(UserDto userDto);

}
