package unsort;

/**
 * @author: renjie
 * @description: zigZag形式打印矩阵
 * @date: 2022/6/27 18:14
 */
public class PrintZigZag {

    public static void process(int[][] matrix) {
        int ur = 0;
        int uc = 0;
        int dr = 0;
        int dc = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean isDown = false;
        while (ur <= endR) {
            print(matrix, ur, uc, dr, dc, isDown);
            ur = uc == endC ? ur + 1 : ur;
            uc = uc == endC ? uc : uc + 1;
            dc = dr == endR ? dc + 1 : dc;
            dr = dr == endR ? dr : dr + 1;
            isDown = !isDown;
        }

    }

    public static void print(int[][] matrix, int ur, int uc, int dr, int dc, boolean isDown) {
        if (isDown) {
            while (ur <= dr) {
                System.out.println(matrix[ur++][uc--]);
            }
        } else {
            while (dr >= ur) {
                System.out.println(matrix[dr--][dc++]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] array = {{1, 2}, {2, 3}};
        process(array);

    }
}
