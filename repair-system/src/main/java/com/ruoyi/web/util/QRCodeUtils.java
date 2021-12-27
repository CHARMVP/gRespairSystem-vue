package com.ruoyi.web.util;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.google.zxing.WriterException;
import com.ruoyi.web.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author guo Email:guoshuaihui20@mails.ucas.ac.cn
 * @date 2021/12/23 16:14
 */
@Component
public class QRCodeUtils {

    @Autowired
    ResourceLoader resourceLoader;

    /**
     * @param domain
     * @param list
     * @return存储图片的文件夹地址
     */
    public String generateQRCode(String domain, List<Device> list) throws IOException {
        //if (deviceStartNum.equals(deviceEndNum)) {//如果只生成一台电脑的二维码时（即开始编号和结束编号一致）
        if (false)   {
            return null;
        } else {
            BufferedImage bufferedImage = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = simpleDateFormat.format(new Date());
            String DIRPATH = "D:/ruoyi/uploadPath/download/QrCode/";
            String realPath = DIRPATH ;

            File outPutFileDir = new File(realPath);
            if (!outPutFileDir.exists()) {
                outPutFileDir.mkdirs();
            }
            for (int i = 0; i < list.size(); i++) {
                try {
                    bufferedImage = QrCodeGenWrapper.of("http://" + domain + "?number=" + list.get(i).getNumber() + "&name=" + list.get(i).getName() + "&productionLine=" + list.get(i).getProductionLine() + "&buyTime" + list.get(i).getBuyTime() + "&computerNum=" + i)
                            .setW(300)
                            .setH(300)
                            .asBufferedImage();
                    BufferedImage picture = new BufferedImage(700, 300, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = (Graphics2D) picture.getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, 700, 300);
                    g.drawImage(bufferedImage, 0, 0, null);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                    g.drawString("设备编号:" + list.get(i).getName(), 350, 25);
                    g.drawString("设备名称:" + list.get(i).getNumber(), 350, 75);
                    g.drawString("生产线:" + list.get(i).getProductionLine(), 350, 125);
                    g.drawString("生产日期:" + simpleDateFormat.format(list.get(i).getBuyTime()), 350, 175);
                    g.drawString("若当前设备出现问题", 350, 225);
                    g.drawString("请扫描左侧二维码进行报修", 350, 275);
                    g.dispose();
                    String fileName = list.get(i).getName() + "-" + list.get(i).getNumber() + "-" + i + ".jpg";
                    File outPutFile = new File(realPath + fileName);
                    ImageIO.write(picture, "jpg", outPutFile);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
            return realPath;
        }
    }
}
