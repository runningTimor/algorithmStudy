package dynamicProcess;

/**
 * @author renjie
 * @description 汉诺塔
 * @date 2022/6/5 17:34
 */
public class Hanoi {

    public static void process(int i, String from, String to, String other) {
        if (i == 1) {
            System.out.println("把1从" + from + "移动到" + to);
        } else {
            process(i - 1, from, other, to);
            System.out.println("把" + i + "从" + from + "移动到" + to);
            process(i - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        process(3, "左", "中", "右");
    }

}
