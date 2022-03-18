package com.liumd.data.dto;

import com.liumd.data.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseEntity {

    /**
     * 用户邮箱(账号)
     */
    @NotBlank(message = "用户邮箱不能为空")
    private String mailbox;

    /**
     * 用户密码
     */
    private String passw0rd;

    /**
     * 确认密码
     */
    private String surePassw0rd;

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
