package com.liumd.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author liumuda
 * @date 2022/2/14 11:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@TableName("bds_admin")
public class AdminEntity extends BaseEntity {

    /**
     * 管理员账号
     */
    @Column(name = "account")
    private String account;

    /**
     * 密码
     */
    @Column(name = "passw0rd")
    private String passw0rd;

    /**
     * 管理员昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 管理员姓名
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Boolean sex;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

}
