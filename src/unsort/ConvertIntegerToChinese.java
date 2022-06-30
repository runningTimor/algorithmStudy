package unsort;

/**
 * 将整数中文名称打印
 */
public class ConvertIntegerToChinese {

    public static final String[] name1To9 = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
    public static final int yi = 100000000;

    public static String convert(int num) {
        String ans = "";
        if (num == 0) {
            return "零";
        }
        ans = num<0?"负":"";
        int yiWei = Math.abs(num/yi);
        int rest = Math.abs(num%yi);
        if (yiWei!=0){
            ans += num1To99(yiWei,false)+"亿";
            if (rest < yi / 10) {
                ans += "零";
            }
        }
        ans += numInteger(rest);
        return ans;
    }

    /**
     * 打印 1-9的数字
     *
     * @param num
     * @return
     */
    public static String num1To9(int num) {
        if (num==0){
            return "";
        }
        return name1To9[num - 1];
    }

    /**
     * 打印1-99的数字
     * @param num
     * @param hasBaiWei
     * @return
     */
    public static String num1To99(int num, boolean hasBaiWei) {
        if (num < 10) {
            return num1To9(num);
        }
        String ans = "";
        int k = num / 10;
        if (k==1&&!hasBaiWei) {
            ans += "十";
        }else {
            ans += num1To9(num / 10) + "十";
        }
        int rest = num % 10;
        ans += num1To9(rest);
        return ans;
    }

    /**
     * 打印1-999的数字
     * @param num
     * @return
     */
    public static String num1To999(int num) {
        if (num<100){
            return num1To99(num,false);
        }

        String ans = "";
        ans += num1To9(num / 100) + "佰";
        int rest = num % 100;
        if (rest >= 10) {
            return ans + num1To99(rest,true);
        } else if (rest > 0) {
            return ans + "零" + num1To9(rest);
        }
        return ans;
    }

    /**
     * 打印一万以下的数字
     * @param num
     * @return
     */
    public static String num1To9999(int num) {
        if (num<1000){
            return num1To999(num);
        }

        String ans = "";
        ans += num1To9(num / 1000) + "千";
        int rest = num % 1000;
        if (rest >= 100) {
            ans += num1To999(rest);
        } else if (rest > 0) {
            ans += "零" + num1To99(rest,false);
        }
        return ans;
    }

    /**
     * 打印一亿以下的数字
     * @param num
     * @return
     */
    public static String num1To99999999(int num){
        if (num<10000){
            return num1To9999(num);
        }

        String ans = "";
        ans += num1To9999(num / 10000) + "万";
        int rest = num % 10000;
        if (rest >= 1000) {
            ans += num1To9999(rest);
        } else if (rest > 0) {
            ans += "零" + num1To9999(rest);
        }
        return ans;
    }

    public static String numInteger(int num){
        if (num<yi){
            return num1To99999999(num);
        }
        String ans = "";
        ans+= num1To99(num/yi,false)+"亿";
        int rest = num%yi;
        if (rest>yi/10){
            ans+= num1To99999999(rest);
        }else {
            ans += "零"+num1To99999999(rest);
        }
        return ans;
    }


    //2147483647
    public static void main(String[] args) {
        System.out.println(convert(Integer.MIN_VALUE));
        System.out.println(convert(1000000));


    }

}
