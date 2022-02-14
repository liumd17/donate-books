package com.liumd.data.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.liumd.data.constant.Constant;
import com.liumd.data.dto.AdminDto;
import com.liumd.data.dto.vo.AdminVo;
import com.liumd.data.entity.AdminEntity;
import com.liumd.data.mapper.AdminMapper;
import com.liumd.data.service.AdminService;
import com.liumd.data.utils.MD5Util;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liumuda
 * @date 2022/2/14 16:04
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Override
    public AdminVo adminLogin(AdminDto adminDto) {
        String account = adminDto.getAccount();
        if (StringUtil.isNullOrEmpty(account)){
            throw new ServiceException(Constant.NULL_ERROR, "管理员账号为空!");
        }

        String passw0rd = adminDto.getPassw0rd();
        if (StringUtil.isNullOrEmpty(passw0rd)){
            throw new ServiceException(Constant.NULL_ERROR, "管理员密码为空!");
        }

        //检查管理员登录信息
        AdminEntity adminEntity = adminMapper.selAdminByAccount(account);
        if (ObjectUtils.isNotEmpty(adminEntity)) {
            String password = adminEntity.getPassw0rd();
            if (password.equals(MD5Util.encrypt(passw0rd))){
                AdminVo adminVo = new AdminVo();
                BeanUtils.copyProperties(adminEntity, adminVo);
                valueOperations.set(Constant.USER_LOGIN_TOKEN + account, adminEntity,
                        Constant.EXPIRE_TIME_30_MINS, TimeUnit.MILLISECONDS); //设置用户登录Token, 过期时间30分钟
                return adminVo;
            }else {
                throw new ServiceException(Constant.DATA_ERROR, "登录密码错误!");
            }
        }else {
            throw new ServiceException(Constant.DATA_ERROR, "管理员不存在!");
        }

    }

    @Override
    public AdminVo queryAdminByAccount(String account) {
        AdminEntity adminEntity = adminMapper.selAdminByAccount(account);
        AdminVo adminVo = new AdminVo();
        if (ObjectUtils.isNotEmpty(adminEntity)){
            BeanUtils.copyProperties(adminEntity, adminVo);
        }

        return adminVo;
    }

    @Override
    public Boolean updateAdmin(AdminEntity adminEntity) {
        Integer id = adminEntity.getId();
        if (ObjectUtils.isNotEmpty(id)){
            throw new ServiceException(Constant.NULL_ERROR, "管理员id不能为空!");
        }
        AdminEntity updateAdminEntity = adminMapper.selectById(id);
        if (ObjectUtils.isNotEmpty(updateAdminEntity)) {
            adminEntity.setPassw0rd(MD5Util.encrypt(adminEntity.getPassw0rd()));
            BeanUtils.copyProperties(adminEntity, updateAdminEntity);
        } else {
            throw new ServiceException(Constant.DATA_ERROR, "管理员不存在!");
        }

        return adminMapper.updateById(updateAdminEntity) > 0;
    }
}
