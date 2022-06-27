package cupidity;

/**
 * @author: renjie
 * @description: 打包机器移动货物的最小轮数
 * @date: 2022/6/27 15:50
 */
public class PackingMachine {

    //array[i]代表每台机器上的货物数量
    public static int getMinTurns(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int sum = 0;
        int length = array.length;
        for (int num : array) {
            sum += num;
        }
        if (sum % length != 0) {
            return -1;
        }
        int leftSum = 0;
        int avg = sum / length;
        int res = 0;
        for (int index = 0; index < length; index++) {
            int leftRest = leftSum - index * avg;
            int rightRest = sum - leftSum - array[index] - (length - index - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                res = Math.max(res, (Math.abs(leftRest) + Math.abs(rightRest)));
            } else {
                res = Math.max(res, (Math.max(Math.abs(leftRest), Math.abs(rightRest))));
            }
            leftSum += array[index];
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
