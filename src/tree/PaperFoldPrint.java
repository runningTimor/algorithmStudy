package tree;

/**
 * @description 纸条对折N次，打印折痕
 * @auther renjie
 * @date 2022/6/2 22:53
 */
public class PaperFoldPrint {

    public static void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }

        process(i + 1, n, true);
        System.out.println(down ? "凹" : "凸");
        process(i + 1, n, false);

    }

    public static void main(String[] args) {
        process(1, 3, true);
    }
}
