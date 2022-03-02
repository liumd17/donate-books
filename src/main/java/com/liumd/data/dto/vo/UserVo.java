package com.liumd.data.dto.vo;

import com.liumd.data.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@Data
public class UserVo extends BaseEntity {

    /**
     * 用户邮箱(账号)
     */
    private String mailbox;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 收货地址
     */
    private String receivingAddress;

}
