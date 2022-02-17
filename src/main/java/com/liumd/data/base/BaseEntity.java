package com.liumd.data.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liumuda
 * @date 2022/1/10 11:15
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
