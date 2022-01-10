package com.liumd.data.service.impl;

import com.liumd.data.dto.UserDto;
import com.liumd.data.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author liumuda
 * @date 2022/1/10 11:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Boolean userLogin(UserDto userDto) {
        return Boolean.TRUE;
    }

}
