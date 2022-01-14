package com.liumd.data.controller;

import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liumuda
 * @date 2021/12/23 14:13
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public UserVo userLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Boolean userRegister(@RequestBody UserDto userDto) {
        userService.userRegister(userDto);
        return Boolean.TRUE;
    }


}
