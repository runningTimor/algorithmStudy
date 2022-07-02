package unsort;

/**
 * @author: renjie
 * @description: 求数组排序后相邻两数的最大差值(不能使用非基于比较的排序)
 * @date: 2022/7/2 16:42
 */
public class SequenceArrayMaxGap {

    //建立一个平凡解去舍弃部分解(差值最大的一定两个数一定不在同一个桶中)
    public static int getMaxGap(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int length = array.length;
        int max = array[0];
        int min = array[0];
        for (int num : array) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if (min == max) {
            return 0;
        }
        //构建一个长度为length+1的桶数组
        //每个元素代表该位置桶是否有数字进入过桶
        boolean[] hasNum = new boolean[length + 1];
        //每个桶中的最小元素
        int[] mins = new int[length + 1];
        //每个桶中的最大元素
        int[] maxs = new int[length + 1];
        int bit = 0;
        for (int num : array) {
            //计算数组中每个元素应该放在那个桶位
            bit = (num - min) * length / (max - min);
            mins[bit] = hasNum[bit] ? Math.min(mins[bit], num) : num;
            maxs[bit] = hasNum[bit] ? Math.max(maxs[bit], num) : num;
            hasNum[bit] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 1; i <= length; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 6, 7, 9};
        System.out.println(getMaxGap(array));
    }
}
