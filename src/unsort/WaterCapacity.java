package unsort;

/**
 * @author: renjie
 * @description: 接雨水
 * @date: 2022/6/28 11:58
 */
public class WaterCapacity {

    //双指针
    public static int getCapacity(int[] array) {
        if (array == null || array.length < 3) {
            return 0;
        }
        int L = 1;
        int R = array.length - 2;
        int leftMax = array[0];
        int rightMax = array[array.length - 1];
        int sum = 0;
        while (L <= R) {
            if (leftMax <= rightMax) {
                sum += leftMax - array[L] >= 0 ? leftMax - array[L] : 0;
                leftMax = Math.max(leftMax, array[L++]);
            } else {
                sum += rightMax - array[R] >= 0 ? rightMax - array[R] : 0;
                rightMax = Math.max(rightMax, array[R--]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] array = {3, 1, 2, 5, 2, 4};
        System.out.println(getCapacity(array));

    }

}
