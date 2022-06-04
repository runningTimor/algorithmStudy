package xor;

/**
 * @author renjie
 * @description 八皇后问题用位运算加速
 * @date 2022/6/4 22:50
 */
public class EightQuene {

    public static int getWay(int n) {
        if (n < 1 || n > 64) {
            return -1;
        }
        long limit = n == 64 ? -1 : (1 << n) - 1;
        return process(limit, 0L, 0L, 0L);

    }

    public static int process(long limit, long colLimit, long leftLimit, long rightLimit) {
        if (limit == colLimit) {
            return 1;
        }
        long totalLimit = colLimit | leftLimit | rightLimit;
        long position = limit & (~totalLimit);
        int res = 0;
        while (position != 0) {
            long mostRightOne = position & (~position + 1);
            position -= mostRightOne;
            res +=
                process(limit, colLimit | mostRightOne, (leftLimit | mostRightOne) << 1, (rightLimit | mostRightOne) >>> 1);

        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(getWay(2));

    }

}
