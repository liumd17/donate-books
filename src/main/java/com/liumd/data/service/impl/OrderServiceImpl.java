package com.liumd.data.service.impl;

import com.liumd.data.constant.Constant;
import com.liumd.data.constant.OrderConstant;
import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.vo.BookOrderVo;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.entity.BookEntity;
import com.liumd.data.entity.OrderEntity;
import com.liumd.data.mapper.BookMapper;
import com.liumd.data.mapper.OrderMapper;
import com.liumd.data.service.BookService;
import com.liumd.data.service.OrderService;
import com.liumd.data.service.UserService;
import com.liumd.data.utils.DateUtils;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liumuda
 * @date 2022/2/14 16:04
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookOrderVo getBookOrder(String mailbox, Integer bookId) {

        UserVo userVo = userService.queryUserByMailbox(mailbox);
        BookVo bookVo = bookService.getBookInfo(bookId);

        return new BookOrderVo(userVo, bookVo);
    }

    @Override
    @Transactional
    public Boolean insertOrder(OrderDto orderDto) {
        Integer userId = orderDto.getUserId();
        Integer bookId = orderDto.getBookId();

        BookEntity bookEntity = bookMapper.selectByPrimaryKey(bookId);
        if (ObjectUtils.isEmpty(bookEntity)){
            throw new ServiceException(Constant.NULL_ERROR, "该书籍不存在!");
        }else {
            if (bookEntity.getBookAmount() <= 0){
                throw new ServiceException(Constant.DATA_ERROR, "该书籍库存不足!");
            }
        }

        String nowMonth = DateUtils.formatDate(new Date(), "yyyy-MM");
        Example example =  new Example(OrderEntity.class);
        example.and(example.createCriteria().andEqualTo("userId", userId)
                .orLike("orderDate", "%"+nowMonth+"%").andNotEqualTo("orderStatus", OrderConstant.ORDER_CANCEL));
        List<OrderEntity> orderEntityList = orderMapper.selectByExample(example);
        OrderEntity orderEntity = new OrderEntity();

        if (ObjectUtils.isEmpty(orderEntityList)) {
            Lock lock = new ReentrantLock();
            if (lock.tryLock()){
                try {
                    bookEntity.setBookAmount(bookEntity.getBookAmount() - 1);
                    bookEntity.setUpdateTime(new Date());
                    bookMapper.updateByPrimaryKey(bookEntity);

                    BeanUtils.copyProperties(orderDto, orderEntity);
                    orderEntity.setOrderStatus(OrderConstant.ORDER_PAY);
                    orderEntity.setCreateTime(new Date());
                    return orderMapper.insert(orderEntity) > 0;
                }catch (Exception e){
                    e.printStackTrace();
                    throw new ServiceException(Constant.DATA_ERROR, e.getMessage());
                }finally {
                    lock.unlock();
                }
            }
        }else {
            throw new ServiceException(Constant.DATA_ERROR, "本月存在正在进行的订单!");
        }

        return Boolean.FALSE;
    }
}
