package com.liumd.data.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author liumuda
 * @date 2022/1/10 11:15
 */
@Data
public class BaseEntity {

    @Id
    private Long id;

    private Date createTime;

    private Date updateTime;

}
