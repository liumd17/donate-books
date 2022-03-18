package com.liumd.data.controller;

import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.entity.UserEntity;
import com.liumd.data.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liumuda
 * @date 2022/2/14 11:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Boolean editUser(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    public UserVo queryUser(@RequestBody String mailbox) {
        return userService.queryUserByMailbox(mailbox);
    }

}
