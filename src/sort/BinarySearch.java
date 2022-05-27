package sort;

//有序数组二分查找
public class BinarySearch {

    public static int searchIndex(int[] array, int searchNum, int startIndex, int EndIndex) {
        int midIndex = startIndex + ((EndIndex - startIndex) >> 1);
        if (array[midIndex] == searchNum) {
            return midIndex;
        }
        if (startIndex == EndIndex) {
            return -1;
        }
        if (array[midIndex] > searchNum) {
            return searchIndex(array, searchNum, startIndex, midIndex - 1);
        } else {
            return searchIndex(array, searchNum, midIndex + 1, EndIndex);
        }
    }

    //在有序数组中查找大于等于searchNum中最小的数字
    public static int searchNearestIndex(int[] array, int searchNum, int startIndex, int EndIndex) {
        int searchIndex = EndIndex;
        while (startIndex <= EndIndex) {
            int midIndex = startIndex + ((EndIndex - startIndex) >> 1);
            if (array[midIndex] >= searchNum) {
                searchIndex = midIndex;
                EndIndex = midIndex - 1;
            } else {
                startIndex = midIndex + 1;
            }
        }
        return searchIndex;
    }

    //array数组无序，相邻的数一定不相等
    public static int findLocalMin(int[] array, int startIndex, int EndIndex) {
        int midIndex = startIndex + ((EndIndex - startIndex) >> 1);
        if (array[midIndex] < array[midIndex + 1] && array[midIndex] < array[midIndex - 1]) {
            return array[midIndex];
        } else {
            if (array[midIndex - 1] < array[midIndex]) {
                return findLocalMin(array, startIndex, midIndex);
            } else {

                return findLocalMin(array, midIndex, EndIndex);
            }
        }


    }

    public static void main(String[] args) {
        int[] array = {4, 3, 2, 1, -1, 10};
//        System.out.println(searchIndex(array, 5, 0, array.length - 1));
//        System.out.println(searchNearestIndex(array, 5, 0, array.length - 1));
        System.out.println(findLocalMin(array, 0, array.length - 1));

    }
}
