package com.ruoyi.web.util;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.google.zxing.WriterException;
import com.ruoyi.web.domain.Device;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author guo Email:guoshuaihui20@mails.ucas.ac.cn
 * @date 2021/12/23 16:14
 */
public class QRCodeUtils {

    /**
     * @param domain
     * @param device
     * @param deviceStartNum
     * @param deviceEndNum
     * @return存储图片的文件夹地址
     */
    public static String generateQRCode(String domain, Device device, Integer deviceStartNum, Integer deviceEndNum) {
        //if (deviceStartNum.equals(deviceEndNum)) {//如果只生成一台电脑的二维码时（即开始编号和结束编号一致）
        if (true)   {
            //测试用
            if (device==null) device = new Device();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataStr = simpleDateFormat.format(device.getBuyTime());
            BufferedImage bufferedImage = null;
            try {
                //调用第三方二维码生成工具，记录二维码信息
                bufferedImage = QrCodeGenWrapper.of("http://" + domain + "?number=" + device.getNumber() + "&name=" + device.getName() + "&productionLine=" + device.getProductionLine() + "&buyTime" + device.getBuyTime())
                        .setW(300)
                        .setH(300)
                        .asBufferedImage();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriterException e) {
                e.printStackTrace();
            }

            //新建画布，重画二维码
            BufferedImage picture = new BufferedImage(700, 300, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) picture.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 700, 300);
            g.drawImage(bufferedImage, 0, 0, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            g.drawString("设备编号:" + device.getNumber(), 350, 25);
            g.drawString("设备名称:" + device.getNumber(), 350, 75);
            g.drawString("生产线:" + device.getProductionLine(), 350, 125);
            g.drawString("生产日期:" + dataStr, 350, 175);
            g.drawString("若当前设备出现问题", 350, 225);
            g.drawString("请扫描左侧二维码进行报修", 350, 275);
            g.dispose();

            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //测试用
            String currentDate = simpleDateFormat.format(new Date());

            //新建文件夹存储二维码图像，文件夹路径为"/opt/QRCode/当前日期年-月-日/UUID"
            String realPath = ConstantUtils.Path.DIRPATH + ConstantUtils.Path.QRCODEPATH + "/" + currentDate + "/" + UUID.randomUUID() + "/";
            String fileName = device.getName() + "-" + device.getNumber() + ".jpg";

            File outPutFileDir = new File(realPath);
            if (!outPutFileDir.exists()) {
                outPutFileDir.mkdirs();
            }

            File outPutFile = new File(realPath + fileName);
            try {
                ImageIO.write(picture, "jpg", outPutFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return realPath;

        } else {
            BufferedImage bufferedImage = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = simpleDateFormat.format(new Date());
            String realPath = ConstantUtils.Path.DIRPATH + ConstantUtils.Path.QRCODEPATH + "/" + currentDate + "/" + UUID.randomUUID() + "/";

            File outPutFileDir = new File(realPath);
            if (!outPutFileDir.exists()) {
                outPutFileDir.mkdirs();
            }

            for (int i = deviceStartNum; i <= deviceEndNum; i++) {

                try {
                    bufferedImage = QrCodeGenWrapper.of("http://" + domain + "?number=" + device.getNumber() + "&name=" + device.getName() + "&productionLine=" + device.getProductionLine() + "&buyTime" + device.getBuyTime() + "&computerNum=" + i)
                            .setW(300)
                            .setH(300)
                            .asBufferedImage();
                    BufferedImage picture = new BufferedImage(700, 300, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = (Graphics2D) picture.getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, 700, 300);
                    g.drawImage(bufferedImage, 0, 0, null);
                    g.setColor(Color.BLACK);
                    g.drawString("设备编号:" + device.getNumber(), 350, 50);
                    g.drawString("设备名称:" + device.getNumber(), 350, 100);
                    g.drawString("生产线:" + device.getProductionLine(), 350, 150);
                    g.drawString("生产日期:" + device.getBuyTime(), 350, 200);
                    g.drawString("若当前设备出现问题", 350, 250);
                    g.drawString("请扫描左侧二维码进行报修", 350, 300);
                    g.dispose();
                    String fileName = device.getName() + "-" + device.getNumber() + "-" + i + ".jpg";
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