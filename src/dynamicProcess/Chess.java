package dynamicProcess;

/**
 * @author renjie
 * @description 象棋中马用指定步数K从原点跳到目标位置的方法数
 * @date 2022/6/11 11:26
 */
public class Chess {

    public static int process(int step, int currentX, int currentY) {
        if (currentX < 0 || currentX > 8 || currentY < 0 || currentY > 9) {
            return 0;
        }
        if (step == 0) {
            return (currentX == 0 && currentY == 0) ? 1 : 0;
        }
        int res = 0;
        res += process(step - 1, currentX + 1, currentY + 2);
        res += process(step - 1, currentX + 2, currentY + 1);
        res += process(step - 1, currentX + 2, currentY - 1);
        res += process(step - 1, currentX + 1, currentY - 2);
        res += process(step - 1, currentX - 1, currentY - 2);
        res += process(step - 1, currentX - 2, currentY - 1);
        res += process(step - 1, currentX - 2, currentY + 1);
        res += process(step - 1, currentX - 1, currentY + 2);
        return res;
    }

    public static int dp(int step, int currentX, int currentY) {
        int[][][] chessBoard = new int[step + 1][9][10];
        chessBoard[0][0][0] = 1;
        for (int level = 1; level <= step; level++) {
            for (int x = 0; x <= 8; x++) {
                for (int y = 0; y <= 9; y++) {
                    chessBoard[level][x][y] += dealLimit(level-1, x + 1, y + 2, chessBoard);
                    chessBoard[level][x][y] += dealLimit(level-1, x + 2, y + 1, chessBoard);
                    chessBoard[level][x][y] += dealLimit(level-1, x + 2, y - 1, chessBoard);
                    chessBoard[level][x][y] += dealLimit(level-1, x + 1, y - 2, chessBoard);
                    chessBoard[level][x][y] += dealLimit(level-1, x - 1, y - 2, chessBoard);
                    chessBoard[level][x][y] += dealLimit(level-1, x - 2, y - 1, chessBoard);
                    chessBoard[level][x][y] += dealLimit(level-1, x - 2, y + 1, chessBoard);
                    chessBoard[level][x][y] += dealLimit(level-1, x - 1, y + 2, chessBoard);
                }
            }
        }
        return chessBoard[step][currentX][currentY];

    }

    public static int dealLimit(int step, int currentX, int currentY, int[][][] chessBoard) {
        if (currentX < 0 || currentX > 8 || currentY < 0 || currentY > 9) {
            return 0;
        }
        return chessBoard[step][currentX][currentY];
    }

    public static void main(String[] args) {
        System.out.print(process(2, 1, 3));
        System.out.print(dp(2, 1, 3));
    }
}
