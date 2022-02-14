package com.liumd.data.dto.vo;

import com.liumd.data.entity.BaseEntity;
import lombok.Data;

/**
 * @author liumuda
 * @date 2022/2/14 11:28
 */
@Data
public class AdminVo extends BaseEntity {

    /**
     * 管理员账号
     */
    private String account;

    /**
     * 密码
     */
    private String passw0rd;

    /**
     * 管理员昵称
     */
    private String nickname;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 手机号
     */
    private String mobile;

}
