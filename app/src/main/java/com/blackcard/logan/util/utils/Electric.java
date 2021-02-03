package com.blackcard.logan.util.utils;

/**
 * Created by Logan on 2019/3/28.
 */
public class Electric {

    /**
     * 基站电量值转换
     *
     * ＳＴ－ＢＬE 基站MAP表
     * 电量剩余百分比(%)	电压ADC值	数据计算方式
     * 100	3020	比如胸卡里面的数据的第18个字节是1a,第19个字节是20，0x1D转十进制是29,再29*100=2900；0x20转十进制是32，那么电池电压的值是;2900+32=2932
     * 99	3012
     * 98	3004
     * 97	2996
     * 96	2988
     * 95	2980
     * 94	2972
     * 93	2964
     * 92	2956
     * 91	2948
     * 90	2940
     * 89	2932
     * 88	2924
     * 87	2916
     * 86	2908
     * 85	2900
     * 84	2892
     * 83	2884
     * 82	2876
     * 81	2868
     * 80	2860
     * 79	2852
     * 78	2844
     * 77	2836
     * 76	2828
     * 75	2820
     * 74	2812
     * 73	2804
     * 72	2796
     * 71	2788
     * 70	2780
     * 69	2772
     * 68	2764
     * 67	2756
     * 66	2748
     * 65	2740
     * 64	2732
     * 63	2724
     * 62	2716
     * 61	2708
     * 60	2700
     * 59	2692
     * 58	2684
     * 57	2676
     * 56	2668
     * 55	2660
     * 54	2652
     * 53	2644
     * 52	2636
     * 51	2628
     * 50	2620
     * 49	2612
     * 48	2604
     * 47	2596
     * 46	2588
     * 45	2580
     * 44	2572
     * 43	2564
     * 42	2556
     * 41	2548
     * 40	2540
     * 39	2532
     * 38	2524
     * 37	2516
     * 36	2508
     * 35	2500
     * 34	2492
     * 33	2484
     * 32	2476
     * 31	2468
     * 30	2460
     * 29	2452
     * 28	2444
     * 27	2436
     * 26	2428
     * 25	2420
     * 24	2412
     * 23	2404
     * 22	2396
     * 21	2388
     * 20	2380
     * 19	2372
     * 18	2364
     * 17	2356
     * 16	2348
     * 15	2340
     * 14	2332
     * 13	2324
     * 12	2316
     * 11	2308
     * 10	2300
     * 9	2292
     * 8	2284
     * 7	2276
     * 6	2268
     * 5	2260
     * 4	2252
     * 3	2244
     * 2	2236
     * 1	2228
     * 0	2220
     *
     * @return 返回电量
     */
    public static int bety2Electric(byte qppDatum, byte qppDatum1) {
        int electric = qppDatum * 100 + qppDatum1;
        if (electric <= 2228) return 1;
        else if (electric >= 3020) return 100;
        else return (electric - 2220) / 8;
    }

    //16进制字符集
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    //将多个字节码转换成16进制字符串
    public static String byteToHex(byte... bt) {
        String bs = null;
        for (byte b : bt) bs = (bs == null ? "" : bs) + byteToHex(b);
        return bs;
    }

    //将单个字节码转换成16进制字符串
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }
}