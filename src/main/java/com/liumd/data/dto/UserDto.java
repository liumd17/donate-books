package com.liumd.data.dto;

import com.liumd.data.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
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
     * 手机号
     */
    private String mobile;

    /**
     * 收货地址
     */
    private String receivingAddress;

    /**
     * 推荐关键字
     */
    private String recommendKeyword;
}
