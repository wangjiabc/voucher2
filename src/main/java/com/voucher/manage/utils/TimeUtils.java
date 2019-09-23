package com.voucher.manage.utils;

import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lz
 * @description 时间工具类
 * @date 2019/6/6
 */
public class TimeUtils extends DateUtil {
    public final static String exp1 = "yyyy-MM-dd";
    public final static String exp2 = "yyyy-MM-dd HH:mm:ss";

    public static String formatTime(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }

    /**
     * @Author lz
     * @Description: 获取两个时间之间的月数, 格式是时间戳
     * @param: [beginDate, endDate]
     * @Date: 2019/7/10 15:52
     **/
    public static Integer getMonths(Long beginDate, Long endDate) {
        if (endDate < beginDate) {
            return -1;
        }
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new Date(beginDate));
        c2.setTime(new Date(endDate));
        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        //开始日期若小于结束日期
        return year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }
}
