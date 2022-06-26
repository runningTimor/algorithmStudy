package preprocess;

/**
 * @author: renjie
 * @description: 求N*N矩阵(值全部为0或1)中边长全部为1的最大正方形
 * @date: 2022/6/26 11:01
 */
public class MaxSquare {

    public static int getMaxSquare(int[][] matrix) {
        int M = matrix.length;
        //从当前位置开始右边有多少个连续的1
        int[][] right = new int[M + 1][M + 1];
        for (int i = 0; i < M; i++) {
            for (int j = M - 1; j >= 0; j--) {
                right[i][j] = matrix[i][j] == 1 ? right[i][j + 1] + 1 : 0;
            }
        }
        //从当前位置开始向下有多少个连续的1
        int[][] down = new int[M + 1][M + 1];
        for (int i = M - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                down[i][j] = matrix[i][j] == 1 ? down[i + 1][j] + 1 : 0;
            }
        }
        int res = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                //本次循环过程中能达到的最大边长
                int maxLength = Math.min(M - i, M - j);
                for (int square = maxLength; square > 0; square--) {
                    if (right[i][j] >= square && down[i][j] >= square &&
                            down[i][j + square - 1] >= square &&
                            right[i + square - 1][j] >= square) {
                        res = Math.max(res, square);
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1, 1, 1}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 1}, {0, 1, 1, 1, 1}, {0, 1, 0, 1, 1}};
        System.out.println(getMaxSquare(matrix));
    }


}
