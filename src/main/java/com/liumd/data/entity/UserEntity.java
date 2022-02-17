package com.liumd.data.entity;

import com.liumd.data.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "bds_user")
public class UserEntity extends BaseEntity {

    /**
     * 用户邮箱(账号)
     */
    @Column(name = "mailbox")
    private String mailbox;

    /**
     * 用户密码
     */
    @Column(name = "passw0rd")
    private String passw0rd;

    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

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

    /**
     * 收货地址
     */
    @Column(name = "receiving_address")
    private String receivingAddress;

    /**
     * 推荐关键字
     */
    @Column(name = "recommend_keyword")
    private String recommendKeyword;
}
