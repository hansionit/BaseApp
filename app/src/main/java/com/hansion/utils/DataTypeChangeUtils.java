package com.hansion.utils;

/**
 * Created by Administrator on 2016/9/14.
 */
public class DataTypeChangeUtils {

    /**
     * 将byte转为int
     */
    public static int unsignedByteToInt(byte b) {
        return b & 0xFF;
    }

    /**
     * 将byte转为16进制字符串
     */
    public static String byteToHexString(byte b) {
        int i = b & 0xFF;
        return Integer.toHexString(i);
    }

    /**
     * 将byte数组转为16进制字符串
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv.toUpperCase()).append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 将byte数组转为连续的16进制字符串
     */
    public static String bytesToHexString2(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }


    /**
     * 将4byte的数组转换成int
     */
    public static long unsigned4BytesToInt(byte[] buf) {
        int firstByte, secondByte, thirdByte, fourthByte, index = 0;
        firstByte = (0x000000FF & (buf[index]));
        secondByte = (0x000000FF & (buf[index + 1]));
        thirdByte = (0x000000FF & (buf[index + 2]));
        fourthByte = (0x000000FF & (buf[index + 3]));
        return (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte) & 0xFFFFFFFFL;
    }

    /**
     * 将byte的数组转换成short数组
     */
    public short[] byteAryToShortAry(byte[] buf) {
        int arySize = buf.length / 2;
        short[] result = new short[arySize];
        int firstByte;
        int secondByte;
        for (int i = 0; i < arySize; i += 2) {
            firstByte = (0x000000FF & (buf[i]));
            secondByte = (0x000000FF & (buf[i + 1]));
            result[i] = (short) ((firstByte << 8 | secondByte) & 0xffff);
        }
        return result;
    }

    /**
     * 将short的数组转换成byte数组
     */
    public byte[] shortAryToByteAry(short[] buf, int size) {
        int arySize = buf.length * 2;
        byte[] result = new byte[arySize];
        for (int i = 0; i < arySize; i += 2) {
            result[i] = (byte) (0xFF & (buf[i / 2] >> 8));
            result[i + 1] = (byte) (0xFF & (buf[i / 2]));

        }
        return result;
    }

    /**
     * 将short转换成长度为2的byte数组
     */
    public static byte[] shortToByteArray(short s) {
        byte[] targets = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((s >>> offset) & 0xff);
        }
        return targets;
    }

    /**
     * 将int转换成长度为4的byte数组
     */
    public static byte[] intToByteArray(int s) {
        byte[] targets = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((s >>> offset) & 0xff);
        }
        return targets;
    }

    /**
     * 将int转换成长度为2的byte数组
     */
    public static byte[] intTo2ByteArray(int s) {
        byte[] targets = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((s >>> offset) & 0xff);
        }
        return targets;
    }

    /**
     * 将long转换成byte数组
     */
    public static byte[] longToByteArray(long s) {
        byte[] targets = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((s >>> offset) & 0xff);
        }
        return targets;
    }

    /**
     * int转长度为4的byte数组
     */
    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     * 将长度为2的byte数组转换为int
     */
    public static int byte2int(byte[] res) {
        // res = InversionByte(res);
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00); // | 表示安位或
        return targets;
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式\n 如："2B44EFD9" to byte[]{0x2B, 0×44,
     * 0xEF,0xD9}
     *
     * @param src String 传入的字符串
     * @return byte[] 返回的数组
     */
    public static byte[] HexString2Bytes(String src) {
        int leng = src.length() / 2;
        byte[] ret = new byte[leng];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < leng; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 将两个ASCII字符合成一个字节； 如："EF" to 0xEF
     *
     * @param src0 ASCII字符1
     * @param src1 ASCII字符2
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0}));
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1}));
        return (byte) (_b0 ^ _b1);
    }

    // char转换为byte[2]数组
    public static byte[] getByteArray(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xff00) >> 8);
        b[1] = (byte) (c & 0x00ff);
        return b;
    }

    // 从byte数组的index处的连续两个字节获得一个char
    public static char getChar(byte[] arr, int index) {
        return (char) (0xff00 & arr[index] << 8 | (0xff & arr[index + 1]));
    }

    // short转换为byte[2]数组
    public static byte[] getByteArray(short s) {
        byte[] b = new byte[2];
        b[0] = (byte) ((s & 0xff00) >> 8);
        b[1] = (byte) (s & 0x00ff);
        return b;
    }

    // 从byte数组的index处的连续两个字节获得一个short
    public static short getShort(byte[] arr, int index) {
        return (short) (0xff00 & arr[index] << 8 | (0xff & arr[index + 1]));
    }

    // int转换为byte[4]数组
    public static byte[] getByteArray(int i) {
        byte[] b = new byte[4];
        b[0] = (byte) ((i & 0xff000000) >> 24);
        b[1] = (byte) ((i & 0x00ff0000) >> 16);
        b[2] = (byte) ((i & 0x0000ff00) >> 8);
        b[3] = (byte) (i & 0x000000ff);
        return b;
    }

    // 从byte数组的index处的连续4个字节获得一个int
    public static int getInt(byte[] arr, int index) {
        return (0xff000000 & (arr[index + 0] << 24)) |
                (0x00ff0000 & (arr[index + 1] << 16)) |
                (0x0000ff00 & (arr[index + 2] << 8)) |
                (0x000000ff & arr[index + 3]);
    }

    // float转换为byte[4]数组
    public static byte[] getByteArray(float f) {
        int intbits = Float.floatToIntBits(f);//将float里面的二进制串解释为int整数
        return getByteArray(intbits);
    }

    // 从byte数组的index处的连续4个字节获得一个float
    public static float getFloat(byte[] arr, int index) {
        return Float.intBitsToFloat(getInt(arr, index));
    }

    // long转换为byte[8]数组
    public static byte[] getByteArray(long l) {
        byte b[] = new byte[8];
        b[0] = (byte) (0xff & (l >> 56));
        b[1] = (byte) (0xff & (l >> 48));
        b[2] = (byte) (0xff & (l >> 40));
        b[3] = (byte) (0xff & (l >> 32));
        b[4] = (byte) (0xff & (l >> 24));
        b[5] = (byte) (0xff & (l >> 16));
        b[6] = (byte) (0xff & (l >> 8));
        b[7] = (byte) (0xff & l);
        return b;
    }

    // 从byte数组的index处的连续8个字节获得一个long
    public static long getLong(byte[] arr, int index) {
        return (0xff00000000000000L & ((long) arr[index + 0] << 56)) |
                (0x00ff000000000000L & ((long) arr[index + 1] << 48)) |
                (0x0000ff0000000000L & ((long) arr[index + 2] << 40)) |
                (0x000000ff00000000L & ((long) arr[index + 3] << 32)) |
                (0x00000000ff000000L & ((long) arr[index + 4] << 24)) |
                (0x0000000000ff0000L & ((long) arr[index + 5] << 16)) |
                (0x000000000000ff00L & ((long) arr[index + 6] << 8)) |
                (0x00000000000000ffL & (long) arr[index + 7]);
    }

    // double转换为byte[8]数组 （经过反转）
    public static byte[] getByteArray(double d) {
        long longbits = Double.doubleToLongBits(d);
        return reverseBytes(getByteArray(longbits));
    }

    // 从byte数组的index处的连续8个字节获得一个double
    public static double getDouble(byte[] arr, int index) {
        return Double.longBitsToDouble(getLong(arr, index));
    }

    //反转数组
    public static byte[] reverseBytes(byte[] byteArray) {
        for (int start = 0, end = byteArray.length - 1; start < end; start++, end--) {
            byte temp = byteArray[end];
            byteArray[end] = byteArray[start];
            byteArray[start] = temp;
        }
        return byteArray;
    }



    /**
     * 拼接两个byte数组
     *
     * @param bytes1
     * @param bytes2
     * @return
     */
    public static byte[] joinBytes(byte[] bytes1, byte[] bytes2) {
        int bytes1Length = bytes1.length;
        int bytes2Length = bytes2.length;
        byte[] newBytes = new byte[bytes1Length + bytes2Length];
        System.arraycopy(bytes1, 0, newBytes, 0, bytes1Length);
        System.arraycopy(bytes2, 0, newBytes, bytes1Length, bytes2Length);
        return newBytes;
    }
}
