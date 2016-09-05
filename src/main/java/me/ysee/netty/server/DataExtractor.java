package me.ysee.netty.server;

/**
 * Created by herosea on 16/8/21.
 */
public class DataExtractor {

    static char[] DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static byte NotThreeZero = ~0x30;

    public static void show(byte[] bytes) {
        String s = toHex(bytes);
        if (s != null) {
            display(s);
        }
    }

    private static String toHex(byte[] bytes) {
        if (bytes[0] == 0x40 && bytes[bytes.length - 1] == 0x0D) {
            char[] chars = new char[bytes.length - 2];
            for (int i = 1; i < bytes.length - 1; i++) {
                chars[i - 1] = DIGITS_UPPER[bytes[i] & NotThreeZero];
            }
            return new String(chars);
        }
        return null;
    }

    private static void display(String hex) {
        System.out.println("控制电流1:" + Integer.parseInt(hex.substring(0, 4), 16));
        System.out.println("控制电流2:" + Integer.parseInt(hex.substring(4, 8), 16));
        System.out.println("控制电压:" + Integer.parseInt(hex.substring(8, 12), 16));
        System.out.println("干球温度:" + Integer.parseInt(hex.substring(12, 16), 16));
        System.out.println("湿球温度:" + Integer.parseInt(hex.substring(16, 20), 16));
        System.out.println("阶段时间:" + Integer.parseInt(hex.substring(20, 24), 16));
        System.out.println("总时间:" + Integer.parseInt(hex.substring(24, 28), 16));
        System.out.println("屏幕设置信息:" + hex.substring(28, 28 + 98));
        System.out.println("烤数:" + Integer.parseInt(hex.substring(126, 130), 16));
        System.out.println("设备状态标志:" + hex.substring(130, 136));
    }
}
