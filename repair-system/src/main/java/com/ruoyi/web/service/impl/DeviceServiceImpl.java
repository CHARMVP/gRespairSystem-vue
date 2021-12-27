package com.ruoyi.web.service.impl;

import java.io.*;
import java.util.Date;
import java.util.List;

import com.ruoyi.web.util.QRCodeUtils;
import com.ruoyi.web.util.ZipUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.DeviceMapper;
import com.ruoyi.web.domain.Device;
import com.ruoyi.web.service.IDeviceService;

import javax.servlet.http.HttpServletResponse;

/**
 * 设备信息Service业务层处理
 *
 * @author guo
 * @date 2021-12-24
 */
@Service
public class DeviceServiceImpl implements IDeviceService
{
    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 查询设备信息
     *
     * @param id 设备信息主键
     * @return 设备信息
     */
    @Override
    public Device selectDeviceById(Long id)
    {
        return deviceMapper.selectDeviceById(id);
    }

    /**
     * 查询设备信息列表
     *
     * @param device 设备信息
     * @return 设备信息
     */
    @Override
    public List<Device> selectDeviceList(Device device)
    {
        return deviceMapper.selectDeviceList(device);
    }

    /**
     * 新增设备信息
     *
     * @param device 设备信息
     * @return 结果
     */
    @Override
    public int insertDevice(Device device)
    {
        return deviceMapper.insertDevice(device);
    }

    /**
     * 修改设备信息
     *
     * @param device 设备信息
     * @return 结果
     */
    @Override
    public int updateDevice(Device device)
    {
        return deviceMapper.updateDevice(device);
    }

    /**
     * 批量删除设备信息
     *
     * @param ids 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceByIds(Long[] ids)
    {
        return deviceMapper.deleteDeviceByIds(ids);
    }

    /**
     * 删除设备信息信息
     *
     * @param id 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceById(Long id)
    {
        return deviceMapper.deleteDeviceById(id);
    }

    @Override
    public Boolean downloadQRCode(List<Device> list) {
        String domain = "http://localhost/order/order";
        Device device = new Device();
//        String dirPath = QRCodeUtils.generateQRCode(domain, list);
//        String zipFileName = "QRCode"+ new Date() + ".zip";
//        String zipFilePath = dirPath + zipFileName;
//        boolean zipResult = ZipUtils.singleFileCompress(dirPath, zipFilePath, null);
//        if (zipResult) {
//            response.setContentType("application/force-download");// 设置强制下载不打开
//            response.addHeader("Content-Disposition", "attachment;fileName=QRCode.zip");// 设置文件名
//            File file = new File(zipFilePath);
//            if (file.exists()) {
//
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    IOUtils.copy(fis, response.getOutputStream());
//                    response.flushBuffer();
//                    System.out.println("success");
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
        return null;
    }
}
