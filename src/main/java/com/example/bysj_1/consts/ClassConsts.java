package com.example.bysj_1.consts;

import java.time.LocalTime;
import java.util.HashMap;

public class ClassConsts {
    /**
     * 第一节开始时间
     */
    public static final LocalTime CLASS_1_START_TIME = LocalTime.of(8,20);
    /**
     * 第一节结束时间
     */
    public static final LocalTime CLASS_1_END_TIME = LocalTime.of(9,05);

    /**
     * 第二节开始时间
     */
    public static final LocalTime CLASS_2_START_TIME = LocalTime.of(9,15);
    /**
     * 第二节结束时间
     */
    public static final LocalTime CLASS_2_END_TIME = LocalTime.of(10,0);

    /**
     * 第三节开始时间
     */
    public static final LocalTime CLASS_3_START_TIME = LocalTime.of(10,10);
    /**
     * 第三节结束时间
     */
    public static final LocalTime CLASS_3_END_TIME = LocalTime.of(10,55);

    /**
     * 第四节开始时间
     */
    public static final LocalTime CLASS_4_START_TIME = LocalTime.of(11,10);
    /**
     * 第四节结束时间
     */
    public static final LocalTime CLASS_4_END_TIME = LocalTime.of(11,55);

    /**
     * 第五节开始时间
     */
    public static final LocalTime CLASS_5_START_TIME = LocalTime.of(12,05);
    /**
     * 第五节结束时间
     */
    public static final LocalTime CLASS_5_END_TIME = LocalTime.of(12,40);

    /**
     * 第六节开始时间
     */
    public static final LocalTime CLASS_6_START_TIME = LocalTime.of(13,20);
    /**
     * 第六节结束时间
     */
    public static final LocalTime CLASS_6_END_TIME = LocalTime.of(14,05);

    /**
     * 第七节开始时间
     */
    public static final LocalTime CLASS_7_START_TIME = LocalTime.of(14,15);
    /**
     * 第七节结束时间
     */
    public static final LocalTime CLASS_7_END_TIME = LocalTime.of(15,0);

    /**
     * 第八节开始时间
     */
    public static final LocalTime CLASS_8_START_TIME = LocalTime.of(15,10);
    /**
     * 第八节结束时间
     */
    public static final LocalTime CLASS_8_END_TIME = LocalTime.of(15,55);

    /**
     * 第九节开始时间
     */
    public static final LocalTime CLASS_9_START_TIME = LocalTime.of(16,05);
    /**
     * 第九节结束时间
     */
    public static final LocalTime CLASS_9_END_TIME = LocalTime.of(16,50);

    /**
     * 第十节开始时间
     */
    public static final LocalTime CLASS_10_START_TIME = LocalTime.of(18,30);
    /**
     * 第十节结束时间
     */
    public static final LocalTime CLASS_10_END_TIME = LocalTime.of(19,15);

    /**
     * 第十一节开始时间
     */
    public static final LocalTime CLASS_11_START_TIME = LocalTime.of(19,30);
    /**
     * 第十一节结束时间
     */
    public static final LocalTime CLASS_11_END_TIME = LocalTime.of(20,15);

    private static HashMap<String,LocalTime> classTimes = new HashMap<>();

    public static HashMap CLASS_TIMES(){
        classTimes.put("class_1_start",LocalTime.of(8,20));
        classTimes.put("class_1_end",LocalTime.of(9,05));
        classTimes.put("class_2_start",LocalTime.of(9,15));
        classTimes.put("class_2_end",LocalTime.of(10,0));
        classTimes.put("class_3_start",LocalTime.of(10,10));
        classTimes.put("class_3_end", LocalTime.of(10,55));
        classTimes.put("class_4_start", LocalTime.of(11,10));
        classTimes.put("class_4_end", LocalTime.of(11,55));
        classTimes.put("class_5_start",LocalTime.of(8,20));
        classTimes.put("class_5_end",LocalTime.of(8,20));
        classTimes.put("class_6_start",LocalTime.of(8,20));
        classTimes.put("class_6_end",LocalTime.of(8,20));
        classTimes.put("class_7_start",LocalTime.of(8,20));
        classTimes.put("class_7_end",LocalTime.of(8,20));
        classTimes.put("class_8_start",LocalTime.of(8,20));
        classTimes.put("class_8_end",LocalTime.of(8,20));
        classTimes.put("class_9_start",LocalTime.of(8,20));
        classTimes.put("class_9_end",LocalTime.of(8,20));
        classTimes.put("class_10_start",LocalTime.of(8,20));
        classTimes.put("class_10_end",LocalTime.of(8,20));
        classTimes.put("class_11_start",LocalTime.of(8,20));
        classTimes.put("class_11_end",LocalTime.of(8,20));
        return classTimes;
    }
}
