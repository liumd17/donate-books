package com.liumd.data.dto.vo;

import lombok.Data;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@Data
public class UserLoginVo {

    /**
     * 用户账户
     */
    private String mailbox;

    /**
     * 登录token
     */
    private String token;

}
