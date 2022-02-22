package com.liumd.data.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liumd.data.constant.Constant;
import com.liumd.data.constant.OrderConstant;
import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.OrderPageDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.vo.BookOrderVo;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.dto.vo.OrderVo;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.entity.BookEntity;
import com.liumd.data.entity.OrderEntity;
import com.liumd.data.mapper.BookMapper;
import com.liumd.data.mapper.OrderMapper;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.BookService;
import com.liumd.data.service.OrderService;
import com.liumd.data.service.UserService;
import com.liumd.data.utils.DataUtil;
import com.liumd.data.utils.DateUtils;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

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

        String nowMonth = DateUtils.getNowYesMonth();
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
                    orderEntity.setOrderDate(DateUtils.getNowYesMonth());
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

    @SneakyThrows
    @Override
    public ResponsePageDto<OrderVo> getOrderPageInfo(OrderPageDto orderPageDto, Paging paging) {
        PageHelper.startPage(paging.getPageNum(), paging.getPageSize());
        Example example = new Example(OrderEntity.class);
        Example.Criteria criteria = example.createCriteria();

        Integer userId = orderPageDto.getUserId();
        if(ObjectUtils.isEmpty(userId)){
            throw new ServiceException(Constant.NULL_ERROR, "用户ID为空!");
        }

        criteria.andEqualTo("userId", userId);
        if (StringUtil.isNotEmpty(orderPageDto.getBookName())){
            criteria.andLike("bookName", "%" + orderPageDto.getBookName()+ "%");
        }

        if (StringUtil.isNotEmpty(orderPageDto.getOrderStatus())){
            criteria.andEqualTo("orderStatus", orderPageDto.getOrderStatus());
        }

        if (StringUtil.isNotEmpty(orderPageDto.getOrderStartDate())){
            criteria.andGreaterThanOrEqualTo("orderDate", orderPageDto.getOrderStartDate());
        }

        if (StringUtil.isNotEmpty(orderPageDto.getOrderEndDate())){
            criteria.andLessThanOrEqualTo("orderDate", orderPageDto.getOrderEndDate());
        }

        example.orderBy("createTime").desc();

        PageInfo<OrderEntity> pageInfo = new PageInfo<>(orderMapper.selectByExample(example));

        return new ResponsePageDto<>(
                DataUtil.getDataList(pageInfo.getList(), OrderVo.class),
                pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    @Transactional
    public OrderVo cancelOrder(OrderDto orderDto) {
        Integer orderId = orderDto.getOrderId();
        Integer bookId = orderDto.getBookId();
        OrderVo  orderVo = new OrderVo();
        if (ObjectUtils.isEmpty(orderId)){
            throw new ServiceException(Constant.NULL_ERROR, "订单ID为空!");
        }
        Example example = new Example(OrderEntity.class);
        example.and(example.createCriteria().andEqualTo("id", orderId)
                .andEqualTo("orderStatus", OrderConstant.ORDER_PAY));
        OrderEntity payOrder = orderMapper.selectOneByExample(example);
        if (ObjectUtils.isEmpty(payOrder)){
            throw new ServiceException(Constant.DATA_ERROR, "该笔订单无法取消!");
        }else {
            payOrder.setOrderStatus(OrderConstant.ORDER_CANCEL);
            payOrder.setUpdateTime(new Date());
            orderMapper.updateByPrimaryKey(payOrder);

            BookEntity bookEntity = bookMapper.selectByPrimaryKey(bookId);
            bookEntity.setBookAmount(bookEntity.getBookAmount() + 1);
            bookEntity.setUpdateTime(new Date());
            bookMapper.updateByPrimaryKey(bookEntity);

            BeanUtils.copyProperties(bookEntity, orderVo);
            return orderVo;
        }

    }
}
