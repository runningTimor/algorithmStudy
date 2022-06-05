package recursive;

/**
 * @author renjie
 * @description 最大得分
 * @date 2022/6/5 19:34
 */
public class MaxPoint {

    public static int firstProcess(int[] arrays, int L, int R) {
        if (L == R) {
            return arrays[L];
        }
        int chooseLeft = arrays[L] + secondProcess(arrays, L + 1, R);
        int chooseRight = arrays[R] + secondProcess(arrays, L, R - 1);
        return Math.max(chooseLeft, chooseRight);
    }

    public static int secondProcess(int[] arrays, int L, int R) {
        if (L == R) {
            return 0;
        }
        int chooseLeft = firstProcess(arrays, L + 1, R);
        int chooseRight = firstProcess(arrays, L, R - 1);
        return Math.min(chooseLeft, chooseRight);
    }

    public static void main(String[] args) {
        int[] array = {1,2,100,4};
        int res = Math.max(firstProcess(array,0,array.length-1),secondProcess(array,0,array.length-1));
        System.out.println(res);

    }
}
