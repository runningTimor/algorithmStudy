package unsort;

/**
 * 根据二叉树的前序遍历和中序遍历生成后序遍历
 */
public class PreAndInArrayToLastArray {
    private static String[] preArray;
    private static String[] inArray;
    private static String[] lastArray;

    public static void lastArray(int preIndexStart, int preIndexEnd, int inIndexStart, int inIndexEnd,
                                 int lastIndexStart, int lastIndexEnd) {
        int length =lastIndexEnd-lastIndexStart+1;
        if (length==0){
            return;
        }
        lastArray[lastIndexEnd] = preArray[preIndexStart];
        //找到前序遍历数组第一个元素在中序遍历数组中的索引
        int index = 0;
        for (;index<length;index++){
            if (inArray[inIndexStart+index]==preArray[preIndexStart]){
                break;
            }
        }
        //对左子树进行递归
        lastArray(preIndexStart+1,preIndexStart+index,inIndexStart,inIndexStart+index-1,
                lastIndexStart,lastIndexStart+index-1);
        //对右子树进行递归
        lastArray(preIndexStart+index+1,preIndexEnd,inIndexStart+index+1,inIndexEnd,
                lastIndexStart+index,lastIndexEnd-1);

    }

    public static void main(String[] args) {
        preArray = new String[]{"a", "b", "c", "d","e"};
        inArray = new String[]{"c", "b", "d","a", "e"};
        lastArray = new String[preArray.length];
        lastArray(0,4,0,4,0,4);
        System.out.println(lastArray);
    }


}
