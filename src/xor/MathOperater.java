package xor;

/**
 * @author renjie
 * @description 模拟四则运算符
 * @date 2022/6/9 13:10
 */
public class MathOperater {

    public static int add(int a, int b) {
        int res = a;
        while (b != 0) {
            res = a ^ b;
            b = (a & b) << 1;
            a = res;
        }
        return res;
    }

    public static int neg(int num) {
        return ~num + 1;
    }

    public static int minus(int a, int b) {
        return add(a, neg(b));
    }

    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a = a << 1;
            b = b >>> 1;
        }
        return res;
    }

    //两个正数相除
    public static int devide(int a, int b) {
        int res = 0;
        for (int i = 31; i >= 0; i = minus(i, 1)) {
            if ((a >> i) >= b) {
                res |= 1 << i;
                a = minus(a, b << i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.print(devide(32, 2));
    }
}
