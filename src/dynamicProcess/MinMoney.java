package dynamicProcess;

/**
 * 满足主播晋级需要的最低花费
 */
public class MinMoney {

    /**
     * @param add
     * @param multi
     * @param del     固定花费
     * @param start 开始人气
     * @param end     目标人气
     * @return
     */
    public static int getMinMoney(int add, int multi, int del, int start, int end) {
        return process(add,multi,del,start,end,0,2*end,((end-start)/2)*add);

    }

    /**
     *
     * @param add
     * @param multi
     * @param del   固定花费
     * @param current 当前人气
     * @param aim   目标人气
     * @param preMoney 之前已花费
     * @param limitAim     （超过此人气的解即可忽略）
     * @param limitCost     普通解花费（超过此花费的解即可忽略）
     * @return
     */
    public static int process(int add, int multi, int del, int current, int aim,int preMoney,int limitAim,int limitCost){
        //运算过程中人气值不会超过目标人气2倍
        //找到一个普通解全部由add方法组成，花费比此方法高的尝试即可舍弃
        //运算过程中人气值不会小于0
        if (current>limitAim){
            return Integer.MAX_VALUE;
        }
        if (current<0){
            return Integer.MAX_VALUE;
        }
        if (preMoney>limitCost){
            return Integer.MAX_VALUE;
        }
        if (current==aim){
            return preMoney;
        }
        int pAdd = process(add,multi,del,current+2,aim,preMoney+add,limitAim,limitCost);
        int pMulti = process(add,multi,del,2*current,aim,preMoney+multi,limitAim,limitCost);
        int pDel = process(add,multi,del,current-2,aim,preMoney+del,limitAim,limitCost);
        return Math.min(pAdd, Math.min(pMulti,pDel));
    }

    /**
     * 动态规划版本
     * @return
     */
    public static int dp(int add, int multi, int del, int start, int end){

        //运算过程中人气值不会超过目标人气2倍
        int limitAim= end<<1;
        //找到一个普通解全部由add方法组成，花费比此方法高的尝试即可舍弃
        int limitCost = ((end -start)>>1)*add;

        int[][] dp = new int[limitCost+1][limitAim+1];
        for (int preCost = 0; preCost<=limitCost;preCost++){
            for (int aim = 0 ; aim<=limitCost;aim++){
                if (start==aim){
                    dp[preCost][aim]= preCost;
                }else {
                    dp[preCost][aim]= Integer.MAX_VALUE;
                }
            }
        }

        for (int preCost = 0; preCost<=limitCost;preCost++){
            for (int aim = 0 ; aim<=limitCost;aim++){


            }
        }

        return dp[0][end];
    }


    public static void main(String[] args) {
        System.out.println(dp(2,10,1,0,20));
    }

}
