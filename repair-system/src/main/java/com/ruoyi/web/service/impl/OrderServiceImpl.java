package com.ruoyi.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.OrderMapper;
import com.ruoyi.web.domain.Order;
import com.ruoyi.web.service.IOrderService;

/**
 * 报修信息Service业务层处理
 *
 * @author guo
 * @date 2021-12-24
 */
@Service
public class OrderServiceImpl implements IOrderService
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询报修信息
     *
     * @param id 报修信息主键
     * @return 报修信息
     */
    @Override
    public Order selectOrderById(Long id)
    {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询报修信息列表
     *
     * @param order 报修信息
     * @return 报修信息
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增报修信息
     *
     * @param order 报修信息
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改报修信息
     *
     * @param order 报修信息
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除报修信息
     *
     * @param ids 需要删除的报修信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] ids)
    {
        return orderMapper.deleteOrderByIds(ids);
    }

    /**
     * 删除报修信息信息
     *
     * @param id 报修信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id)
    {
        return orderMapper.deleteOrderById(id);
    }
}
