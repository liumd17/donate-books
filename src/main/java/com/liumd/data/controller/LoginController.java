package com.liumd.data.controller;

import com.liumd.data.dto.UserDto;
import com.liumd.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liumuda
 * @date 2021/12/23 14:13
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public Boolean userLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

}
