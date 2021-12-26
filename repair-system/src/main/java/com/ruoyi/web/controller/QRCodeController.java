package com.ruoyi.web.controller;

import com.ruoyi.web.domain.Device;
import com.ruoyi.web.util.QRCodeUtils;
import com.ruoyi.web.util.ZipUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author guo Email:guoshuaihui20@mails.ucas.ac.cn
 * @date 2021/12/23 20:30
 */
@RestController
@RequestMapping("/repair-system/QRCode")
public class QRCodeController {

//    @ApiOperation(value = "下载二维码图片")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "domain", value = "当前域名", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "deviceNumber", value = "设备编号", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "deviceName", value = "设备名", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "productionLine", value = "生产线", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "buyTime", value = "购买时间", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "deviceStartNum", value = "设备开始编号", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "deviceEndNum", value = "设备结束编号", required = true, dataType = "String", paramType = "query"),
//
//    })
    @GetMapping("/QRCodeDownLoad")
    public String downloadQRCode(String domain, String deviceNumber, String deviceName, String productionLine, Date buyTime,
                                 Integer deviceStartNum, Integer deviceEndNum, HttpServletResponse response) {
        String domain1 = "www.baidu.com";
        Device device = new Device();
        device.setNumber("000");
        device.setName("name");
        device.setProductionLine("line");
        device.setBuyTime(new Date());

        String dirPath = QRCodeUtils.generateQRCode(domain1, device, 1, 1);
        String zipFileName = deviceName + "&" + buyTime + ".zip";
        String zipFilePath = dirPath + zipFileName;
        boolean zipResult = ZipUtils.singleFileCompress(dirPath, zipFilePath, null);
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
                    IOUtils.copy(fis, response.getOutputStream());
                    response.flushBuffer();
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
        return null;
    }

}

