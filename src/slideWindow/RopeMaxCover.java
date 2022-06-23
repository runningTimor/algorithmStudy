package slideWindow;/** * @author renjie * @createTime 2022/6/2323:01 * @description 绳子最大覆盖点数 */public class RopeMaxCover {    public static int getMaxCover(int[] array, int ropeLength) {        if (array == null || array.length == 0 || ropeLength <= 0) {            return 0;        }        int windowLeft = 0;        int windowRight = -1;        int maxCover = 0;        for (int index = 0; index < array.length; index++) {            windowRight++;            while (array[windowRight] - array[windowLeft] > ropeLength) {                windowLeft++;            }            maxCover = Math.max(maxCover, windowRight - windowLeft + 1);        }        return maxCover;    }    public static void main(String[] args) {        int[] array = {1,3,5,7,10,15,16,17,18,19,20,23};        int length = 7;        System.out.println(getMaxCover(array,length));    }}