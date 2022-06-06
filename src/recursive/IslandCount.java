package recursive;

/**
 * @author renjie
 * @description 岛的数量统计
 * @date 2022/6/6 23:25
 */
public class IslandCount {

    public static int getCount(int[][] island) {
        int row = island.length;
        int cow = island[0].length;
        int islandCount = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cow; j++) {
                if (island[i][j] == 1) {
                    islandCount++;
                    infect(island, i, j);
                }
            }
        }
        return islandCount;

    }

    public static void infect(int[][] island, int i, int j) {
        int row = island.length;
        int cow = island[0].length;
        if (i < 0 || i >= row || j < 0 || j >= cow || island[i][j] != 1) {
            return;
        }
        island[i][j]=2;
        infect(island, i + 1, j);
        infect(island, i - 1, j);
        infect(island, i, j + 1);
        infect(island, i, j - 1);
    }

    public static void main(String[] args) {
        int[][] island =
            {{1, 1, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 1, 0},
            {1, 1, 0, 0, 0, 1, 1}};
        System.out.println(getCount(island));
    }

}
