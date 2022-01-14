package com.liumd.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liumuda
 * @date 2022/1/10 11:15
 */
@Data
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
