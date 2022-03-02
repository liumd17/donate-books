package com.liumd.data.controller;

import com.liumd.data.dto.AdminDto;
import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.AdminVo;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.service.AdminService;
import com.liumd.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author liumuda
 * @date 2021/12/23 14:13
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserVo userLogin(@RequestBody @Validated UserDto userDto) {
        return userService.userLogin(userDto);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Boolean userRegister(@RequestBody UserDto userDto) {
        return userService.userRegister(userDto);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public AdminVo adminLogin(@RequestBody AdminDto adminDto) {
        return adminService.adminLogin(adminDto);
    }

}
