package unsort;

/**
 * 找出数组中不存在的数（所有数都介于1到n之间）
 */
public class NotExistNum {

    public static void findNotExistNum(int[] arrays) {
        if (arrays==null||arrays.length==0){
            return ;
        }

        for (int index = 0; index < arrays.length; index++) {
            exchange(arrays[index],arrays);
        }
        for (int index = 0; index < arrays.length; index++) {
            if (arrays[index]!=index+1){
//                System.out.println(index+1);
                System.out.println(arrays[index]);
            }
        }

    }

    public static void exchange(int value,int[] arrays){
        while(arrays[value-1]!=value){
            int temp = arrays[value-1];
            arrays[value-1]=value;
            value =temp;
        }
    }

    public static void main(String[] args) {
        int[] arrays = {1, 3, 4, 2, 2};

        findNotExistNum(arrays);
    }
}
