package unsort;

/**
 * 求子矩阵最大累加和
 */
public class SubMatrixMaxSum {

    public static int maxSum(int[][] matrix) {
        if (matrix==null){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;

        //起始第i行
        for (int i = 0; i < m; i++) {
            //第i行到第j行
            int[] compressArray = new int[n];
            for (int j = i; j < m; j++) {
                //求出i-j行中的最大子矩阵累加和
                //压缩数组
                int current = 0;
                for (int k = 0; k < n; k++) {
                    compressArray[k] += matrix[j][k];
                    current += compressArray[k];
                    max = Math.max(max, current);
                    if (current < 0) {
                        current = 0;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {{-5, 3, 6, 4},
                {-7, 9, -5, 3},
                {-10, 1, -200, 4}};

        int max = maxSum(matrix);
        System.out.println(max);

    }
}
