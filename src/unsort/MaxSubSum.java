package unsort;

/**
 * 子数组最大累加和
 */
public class MaxSubSum {

    public static int process(int[] array){
        int current = 0;
        int max = Integer.MIN_VALUE;

        for (int a :array){
             current+=a;
             max = Math.max(max,current);
             if (current<0){
                 current =0;
             }
        }

        return max;
    }
}
