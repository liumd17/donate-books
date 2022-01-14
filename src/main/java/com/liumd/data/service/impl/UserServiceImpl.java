package com.liumd.data.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.liumd.data.constant.Constant;
import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.entity.UserEntity;
import com.liumd.data.mapper.UserMapper;
import com.liumd.data.service.UserService;
import com.liumd.data.utils.MD5Util;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author liumuda
 * @date 2022/1/10 11:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Override
    public UserVo userLogin(UserDto userDto) {
        String mailbox = userDto.getMailbox();
        if (StringUtil.isNullOrEmpty(mailbox)){
            throw new ServiceException(401, "用户邮箱为空!");
        }

        String passw0rd = userDto.getPassw0rd();
        if (StringUtil.isNullOrEmpty(passw0rd)){
            throw new ServiceException(401, "用户密码为空!");
        }

        //检查用户登录信息
        UserEntity userEntity = userMapper.selUserByMailbox(mailbox);
        if (ObjectUtils.isNotEmpty(userEntity)) {
            String password = userEntity.getPassw0rd();
            if (password.equals(MD5Util.encrypt(passw0rd))){
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(userEntity, userVo);
                valueOperations.set(Constant.USER_LOGIN_TOKEN + mailbox, userEntity,
                        Constant.EXPIRE_TIME_30_MINS, TimeUnit.MILLISECONDS); //设置用户登录Token, 过期时间30分钟
                return userVo;
            }else {
                throw new ServiceException(401, "登录密码错误!");
            }
        }else {
            throw new ServiceException(401, "用户不存在!");
        }
    }

    @Transactional
    @Override
    public void userRegister(UserDto userDto) {
        String mailbox = userDto.getMailbox();
        if (StringUtil.isNullOrEmpty(mailbox)){
            throw new ServiceException(401, "用户邮箱为空!");
        }

        String passw0rd = userDto.getPassw0rd();
        if (StringUtil.isNullOrEmpty(passw0rd)){
            throw new ServiceException(401, "用户密码为空!");
        }
        if (ObjectUtils.isNotEmpty(userMapper.selUserByMailbox(mailbox))) {
            throw new ServiceException(401, "该用户已存在!");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setId(null);
        userEntity.setPassw0rd(MD5Util.encrypt(userDto.getPassw0rd()));
        userEntity.setCreateTime(new Date());
        userMapper.insert(userEntity);

    }

    @Override
    public UserVo queryUserByMailbox(String mailbox) {
        UserEntity userEntity = userMapper.selUserByMailbox(mailbox);
        return null;
    }

}
