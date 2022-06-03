package unsort;

public class Fabonaqie {

    /**
     * 方阵matrix的N次方
     * @param matrix
     * @param power
     * @return
     */
    public static int[][] matrixPower(int[][] matrix, int power) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }
        int[][] temp = matrix;
        //将power转换成2的N次方的和
        //power的二进制形式，每次取出最右边的1
        for (;power!=0;power>>=1){
            if ((power&1)!=0){
                result = multiMatrix(result,temp);
            }
            temp = multiMatrix(temp,temp);

        }
        return result;
    }


    /**
     * 矩阵A的规模为M*N;矩阵B的规模N*P
     * @param matrixA
     * @param matrixB
     * @return
     */
    public static int[][] multiMatrix(int[][] matrixA, int[][] matrixB) {

        int[][] result = new int[matrixA.length][matrixB[0].length];
        for (int m = 0; m < matrixA.length; m++) {
            for (int n = 0; n < matrixB[0].length; n++) {
                for (int k = 0; k < matrixB.length; k++) {
                    result[m][n] += matrixA[m][k] * matrixB[k][n];
                }
            }
        }

        return result;
    }

    /**
     * 求斐波那契数列第N项值
     * @param n
     * @return
     */
    public static int fabonaqie(int n){
        if (n<1){
            return 0;
        }
        if (n==1||n==2){
            return 1;
        }
        //base矩阵为fabonaqie数列的特征矩阵
        int[][] base = {{1,1},{1,0}};
        int[][] result = matrixPower(base,n-2);
        return result[0][0]+result[1][0];
    }

    public static void main(String[] args) {
        int n = fabonaqie(42);
        System.out.println(n);

    }
}
