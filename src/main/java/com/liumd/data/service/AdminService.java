package com.liumd.data.service;

import com.liumd.data.dto.AdminDto;
import com.liumd.data.dto.vo.AdminVo;
import com.liumd.data.entity.AdminEntity;

/**
 * @author liumuda
 * @date 2022/2/14 16:04
 */
public interface AdminService {

    /**
     * 管理员账号登录
     * @param adminDto
     * @return
     */
    AdminVo adminLogin(AdminDto adminDto);

    /**
     * 查询管理员信息
     * @param account
     * @return
     */
    AdminVo queryAdminByAccount(String account);

    /**
     * 管理员信息修改
     * @param adminEntity
     * @return
     */
    Boolean updateAdmin(AdminEntity adminEntity);
}
