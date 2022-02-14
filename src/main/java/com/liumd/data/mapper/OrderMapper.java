package com.liumd.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liumd.data.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liumuda
 * @date 2022/2/14 16:03
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
}
