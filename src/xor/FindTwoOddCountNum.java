package xor;

//找到数组中的两个奇数个的数字（其余数字都存在偶数个数字）
public class FindTwoOddCountNum {

    public static void process(int[] array){
        int xor = 0;
        for (int i=0;i<array.length;i++){
            xor ^= array[i];
        }
        //由异或性质可知，有偶数个的数字异或后为0
        //假设数组中两个奇数个数字分别为a,b。则xor= a^b
        //  取出一个数（二进制）最右边的1(说明a,b在condition位置的数字不同，一个1一个0)
        int condition = xor&(~xor+1);
        int eor = 0;
        for (int i=0;i<array.length;i++){
            if ((condition&array[i])==0){
                eor^=array[i];
            }
        }
        System.out.println(eor);
        System.out.println(xor^eor);
    }


    public static void main(String[] args) {

        int[] array = {1,1,2,33,33,4,4,4};
        process(array);
    }
}

