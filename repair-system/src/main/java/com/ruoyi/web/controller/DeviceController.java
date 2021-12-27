package com.ruoyi.web.controller;

import java.io.*;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.util.QRCodeUtils;
import com.ruoyi.web.util.ZipUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.domain.Device;
import com.ruoyi.web.service.IDeviceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备信息Controller
 *
 * @author guo
 * @date 2021-12-24
 */
@RestController
@RequestMapping("/repair-system/device")
public class DeviceController extends BaseController
{
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private QRCodeUtils qrCodeUtils;

    /**
     * 查询设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('repair-system:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(Device device)
    {
        startPage();
        List<Device> list = deviceService.selectDeviceList(device);
        return getDataTable(list);
    }

    /**
     * 导出设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('repair-system:device:export')")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Device device)
    {
        List<Device> list = deviceService.selectDeviceList(device);
        ExcelUtil<Device> util = new ExcelUtil<Device>(Device.class);
        util.exportExcel(response, list, "设备信息数据");
    }

    /**
     * 导出二维码
     */
    @PreAuthorize("@ss.hasPermi('repair-system:device:exportQRCode')")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/exportQRCode")
    public String exportQRCode(HttpServletResponse response, Device device)
    {
        List<Device> list = deviceService.selectDeviceList(device);
        String domain = "http://localhost/order/order";
        String dirPath = null;
        try {
            ZipUtils.deleteFile("D:/ruoyi/uploadPath/download/QrCode");

            dirPath = qrCodeUtils.generateQRCode(domain, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String zipFileName = "QRCode"+ ".zip";
        String zipFilePath = dirPath + zipFileName;
        boolean zipResult = ZipUtils.singleFileCompress(dirPath, zipFilePath, null);
        System.out.println(zipResult);
        if (zipResult) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=QRCode.zip");// 设置文件名
            File file = new File(zipFilePath);
            if (file.exists()) {
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
//                    IOUtils.copy(fis, response.getOutputStream());
//                    response.flushBuffer();
                    System.out.println("success");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return zipFilePath;
    }

    /**
     * 获取设备信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('repair-system:device:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(deviceService.selectDeviceById(id));
    }

    /**
     * 新增设备信息
     */
    @PreAuthorize("@ss.hasPermi('repair-system:device:add')")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Device device)
    {
        return toAjax(deviceService.insertDevice(device));
    }

    /**
     * 修改设备信息
     */
    @PreAuthorize("@ss.hasPermi('repair-system:device:edit')")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Device device)
    {
        return toAjax(deviceService.updateDevice(device));
    }

    /**
     * 删除设备信息
     */
    @PreAuthorize("@ss.hasPermi('repair-system:device:remove')")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(deviceService.deleteDeviceByIds(ids));
    }
}
