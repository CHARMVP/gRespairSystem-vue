package com.ruoyi.web.mapper;

import java.util.List;
import com.ruoyi.web.domain.Device;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备信息Mapper接口
 *
 * @author guo
 * @date 2021-12-24
 */
@Mapper
public interface DeviceMapper
{
    /**
     * 查询设备信息
     *
     * @param id 设备信息主键
     * @return 设备信息
     */
    public Device selectDeviceById(Long id);

    /**
     * 查询设备信息列表
     *
     * @param device 设备信息
     * @return 设备信息集合
     */
    public List<Device> selectDeviceList(Device device);

    /**
     * 新增设备信息
     *
     * @param device 设备信息
     * @return 结果
     */
    public int insertDevice(Device device);

    /**
     * 修改设备信息
     *
     * @param device 设备信息
     * @return 结果
     */
    public int updateDevice(Device device);

    /**
     * 删除设备信息
     *
     * @param id 设备信息主键
     * @return 结果
     */
    public int deleteDeviceById(Long id);

    /**
     * 批量删除设备信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceByIds(Long[] ids);
}
