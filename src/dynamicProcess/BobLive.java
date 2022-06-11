package dynamicProcess;

/**
 * @author renjie
 * @description 每次随机上下左右移动，从指定位置开始运动K步后，存活的概率
 * @date 2022/6/11 12:32
 */
public class BobLive {

    //能够存活的方法数
    public static int process(int row, int cow, int step, int x, int y) {
        if (x < 0 || x > cow - 1 || y < 0 || y > row - 1) {
            return 0;
        }
        if (step == 0) {
            return 1;
        }
        int ways = 0;
        ways += process(row, cow, step - 1, x - 1, y);
        ways += process(row, cow, step - 1, x + 1, y);
        ways += process(row, cow, step - 1, x, y - 1);
        ways += process(row, cow, step - 1, x, y + 1);
        return ways;

    }

    //非负正整数的最大公约数
    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static void main(String[] args) {

    }
}
