package sort;

//快速排序
public class QuickSort {

    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] p = partition(array, left, right);
        //p[0]为等于区域的左边界，p[1]为等于区域的右边界
        quickSort(array, left, p[0] - 1);
        quickSort(array, p[1] + 1, right);
    }

    //在索引从left到right范围内，把小于最后一个位置的数字放在左侧，等于的数字放在中间，大于的放在右侧
    public static int[] partition(int[] array, int left, int right) {
        //从数组随机选择一个数作为划分依据
        int selectIndex = (int) (Math.random() * (right - left)) + left;
        int selectNum = array[selectIndex];
        int smallRight = left;
        int bigLeft = right;
        int index = left;
        while (index <= bigLeft) {
            if (array[index] > selectNum) {
                swap(array, bigLeft--, index);
            } else if (array[index] < selectNum) {
                swap(array, smallRight++, index++);
            } else {
                index++;
            }
        }
        return new int[]{smallRight, bigLeft};
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {

        int[] array = {5, 3, 2, 7, 8, 6, 5, 4, 7, 8, 9};
        quickSort(array, 0, array.length - 1);
        System.out.println();
    }

}
