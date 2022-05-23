package tree;

//预处理
public class PreProcess {

    public static int process(int[] array) {
        int length = array.length;
        int[] leftZero = new int[length + 1];
        int[] rightOne = new int[length + 1];
        int minCost = length;
        for (int i = 1; i <= length; i++) {
            leftZero[i] = array[i - 1] == 0 ? leftZero[i - 1] + 1 : leftZero[i - 1];
        }
        for (int i = length-1; i >= 0; i--) {
            rightOne[i] = array[i] == 1 ? rightOne[i + 1] + 1 : rightOne[i + 1];
        }

        for (int index=0;index<=length;index++){
            minCost = Math.min(minCost,leftZero[index]+rightOne[index]);
        }

        return minCost;
    }

    public static void main(String[] args) {
        int[] array = {0,0,1};
        System.out.println(process(array));

    }

}
