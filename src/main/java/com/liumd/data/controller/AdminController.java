package com.liumd.data.controller;

import com.liumd.data.dto.vo.AdminVo;
import com.liumd.data.entity.AdminEntity;
import com.liumd.data.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumuda
 * @date 2022/2/14 16:23
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public AdminVo queryAdmin(@RequestBody String account) {
        return adminService.queryAdminByAccount(account);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Boolean editAdmin(@RequestBody AdminEntity adminEntity) {
        return adminService.updateAdmin(adminEntity);
    }

}
