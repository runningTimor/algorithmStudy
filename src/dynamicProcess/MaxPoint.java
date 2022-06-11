package dynamicProcess;

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

    public static int winnerPoint(int[] points, int L, int R) {
        int length = points.length;
        int[][] dpFirst = new int[length][length];
        int[][] dpSecond = new int[length][length];
        for (int i = 0; i < length; i++) {
            dpFirst[i][i] = points[i];
        }

        for (int offset = 1; offset < length; offset++) {
            int r = 0;
            int c = offset;
            while (c <= length - 1) {
                dpFirst[r][c] = Math.max(points[c] + dpSecond[r][c - 1], points[r] + dpSecond[r + 1][c]);
                dpSecond[r][c] = Math.min(dpFirst[r][c - 1], dpFirst[r + 1][c]);
                r++;
                c++;
            }
        }
        for (int i = 0; i < length - 1; i++) {
            dpFirst[i][i + 1] = Math.max(points[i + 1] + dpSecond[i][i], points[i] + dpSecond[i + 1][i + 1]);
            dpSecond[i][i + 1] = Math.min(dpFirst[i][i], dpFirst[i + 1][i + 1]);
        }

        return Math.max(dpFirst[L][R], dpSecond[L][R]);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 100, 4};
        int res1 = Math.max(firstProcess(array, 0, array.length - 1), secondProcess(array, 0, array.length - 1));
        int res2 = winnerPoint(array, 0, array.length - 1);
        System.out.println(res1);
        System.out.println(res2);

    }
}
