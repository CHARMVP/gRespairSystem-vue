package com.ruoyi.web.config;

import com.ruoyi.web.util.ConstantUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author guo Email:guoshuaihui20@mails.ucas.ac.cn
 * @date 2021/12/23 20:40
 */
@Component
public class SchedulerTask {

    /**
     * 每天凌晨3点删除生成的QRCode文件
     */
    @Scheduled(cron = "0 0 3 * * ?")
    private void deleteQRCodeFile(){
        File file = new File(ConstantUtils.Path.DIRPATH+ConstantUtils.Path.QRCODEPATH);
        if (file.exists()){
            try {
                FileUtils.cleanDirectory(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
