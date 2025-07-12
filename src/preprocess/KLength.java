package preprocess;

public class KLength {

    /**
     * 子数组和小于K的最大长度
     */
    public static int maxLength(int[] array, int k){

        if (array == null || array.length == 0) {
            return 0;
        }

        int N = array.length;
        int[] minSum = new int[N];
        int[] minSumEnd = new int[N];
        minSum[N - 1] = array[N - 1];
        minSumEnd[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {
                minSum[i] = array[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = array[i];
                minSumEnd[i] = i;
            }
        }

        int sum = 0;
        int end = 0;
        int ans = 0;

        for (int i = 0; i < N; i++) {
            while (end < N && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            ans = Math.max(ans, end - i);

            if (end > i) {

                sum -= array[i];
            } else {
                end = end + 1;
            }

        }

        return ans;
    }


    public static void main(String[] args) {
        int[] array = {-5, 2, 3, 3, 3};
        int k = 0;
        System.out.println(maxLength(array, k));
    }
}
