package io.github.xiechanglei.base.common.base.array;

public class ArrayHelper {
    /**
     * 翻转数组
     */
    public static <T> T[] reverse(T[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            T temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static boolean[] reverse(boolean[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            boolean temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static byte[] reverse(byte[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            byte temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static short[] reverse(short[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            short temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static char[] reverse(char[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static int[] reverse(int[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            int temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static long[] reverse(long[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            long temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }


    public static float[] reverse(float[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            float temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static double[] reverse(double[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            double temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }


    /**
     * 合并数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(T[] array1, T[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        T[] result = (T[]) new Object[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static boolean[] concat(boolean[] array1, boolean[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        boolean[] result = new boolean[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static byte[] concat(byte[] array1, byte[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        byte[] result = new byte[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static short[] concat(short[] array1, short[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        short[] result = new short[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static char[] concat(char[] array1, char[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        char[] result = new char[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static int[] concat(int[] array1, int[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        int[] result = new int[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static long[] concat(long[] array1, long[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        long[] result = new long[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static float[] concat(float[] array1, float[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        float[] result = new float[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    public static double[] concat(double[] array1, double[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        double[] result = new double[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    //覆盖数组,将array2的内容覆盖到array1中
    public static <T> T[] cover(T[] array1, T[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static boolean[] cover(boolean[] array1, boolean[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static byte[] cover(byte[] array1, byte[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static short[] cover(short[] array1, short[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static char[] cover(char[] array1, char[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static int[] cover(int[] array1, int[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static long[] cover(long[] array1, long[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static float[] cover(float[] array1, float[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

    public static double[] cover(double[] array1, double[] array2) {
        System.arraycopy(array2, 0, array1, 0, Math.min(array1.length, array2.length));
        return array1;
    }

}
