package unsort;

/**
 * @author: renjie
 * @description: TODO
 * @date: 2022/7/24 20:45
 */
public class SearchInSortMatrix {

    public static boolean IsExists(int[][] matrix, int searchNum) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        int row = 0;
        int cow = N - 1;
        while (row < M - 1 && cow > 0) {

            if (matrix[row][cow] == searchNum) {
                return true;
            } else if (matrix[row][cow] > searchNum) {
                cow--;
            } else {
                row++;
            }

        }
        return false;
    }

    public static void main(String[] args) {

        int[][] matrix = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 12},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(IsExists(matrix, 5));
    }
}
