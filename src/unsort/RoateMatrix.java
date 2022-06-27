package unsort;

/**
 * @author: renjie
 * @description: 将矩阵旋转90度
 * @date: 2022/6/27 17:20
 */
public class RoateMatrix {

    public static void process(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int ur = 0;
        int uc = 0;
        int dr = matrix.length - 1;
        int dc = matrix[0].length - 1;
        while (ur < dr) {
            roate(matrix, ur++, uc++, dr--, dc--);
        }

    }

    public static void roate(int[][] matrix, int ur, int uc, int dr, int dc) {
        int temp = 0;
        for (int index = ur; index < dr - ur; index++) {
            temp = matrix[ur][uc + index];
            matrix[ur][uc + index] = matrix[dr - index][uc];
            matrix[dr - index][uc] = matrix[dr][dc - index];
            matrix[dr][dc - index] = matrix[ur + index][dc];
            matrix[ur + index][dc] = temp;
        }
    }


    public static void main(String[] args) {

    }
}
