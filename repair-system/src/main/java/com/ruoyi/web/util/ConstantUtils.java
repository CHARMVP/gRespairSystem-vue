package com.ruoyi.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * @author guo Email:guoshuaihui20@mails.ucas.ac.cn
 * @date 2021/12/23 16:08
 */
public interface ConstantUtils {

    /**
     * 维修单状态码
     */
    public static class OrderStatus{

        /**
         * 未维修状态码：0
         */
        public static final Integer NOTREPAIRED = 0;

        /**
         * 维修中状态码：1
         */
        public static final Integer REPAIRING = 1;

        /**
         * 维修完成状态码：2
         */
        public static final Integer REPAIRED = 2;
    }

    public static class Page{

        //一页展示多少条
        public static final Integer PAGESIZE = 10;

        //页码展示数量
        public static final Integer PAGESNUM = 4;
    }

    class Path{


        //虚拟目录地址
        public static final String DIRPATH = "D:/Images";
//        public static final String DIRPATH = new ClassPathResource("resources.properties",ConstantUtils.class.getClassLoader()).getPath();

        public static final String QRCODEPATH = "/QRCODE";

    }

    class Cookie{

        //Cookie最长存活时间(单位：秒)
        public static final int COOKIE_MAX_TIME = 3*24*3600;
    }

//    class Redis{
//
//        public static final long REDIS_MAX_TIME = 3*24*3600;
//    }

    class Mail{
        public static final String FROM_MAIL="FSPT_RepairManagement@outlook.com";
    }
}
