package com.ruoyi.web.util;

import com.ruoyi.web.domain.Device;

/**
 * @author guo Email:guoshuaihui20@mails.ucas.ac.cn
 * @date 2021/12/23 10:22
 */

public class QrCodeTest {

    public static void main(String[] args) throws Exception {
        String domain = "www.baidu.com";
        Device device = new Device();
        Integer deviceStartNum = 1;
        Integer deviceEndNum = 1;
        String realPath = QRCodeUtils.generateQRCode(domain,device,deviceStartNum,deviceEndNum);
        System.out.println(realPath);

    }

}
