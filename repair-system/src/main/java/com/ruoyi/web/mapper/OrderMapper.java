package com.ruoyi.web.mapper;

import java.util.List;
import com.ruoyi.web.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报修信息Mapper接口
 *
 * @author guo
 * @date 2021-12-24
 */
@Mapper
public interface OrderMapper
{
    /**
     * 查询报修信息
     *
     * @param id 报修信息主键
     * @return 报修信息
     */
    public Order selectOrderById(Long id);

    /**
     * 查询报修信息列表
     *
     * @param order 报修信息
     * @return 报修信息集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增报修信息
     *
     * @param order 报修信息
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改报修信息
     *
     * @param order 报修信息
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除报修信息
     *
     * @param id 报修信息主键
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 批量删除报修信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByIds(Long[] ids);
}
