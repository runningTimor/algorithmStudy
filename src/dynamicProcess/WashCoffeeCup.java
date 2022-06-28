package dynamicProcess;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: renjie
 * @description: 洗咖啡杯
 * @date: 2022/6/28 16:47
 */
public class WashCoffeeCup {

    public static class CoffeeMachine {
        private int startTime;
        private int machineTime;

        public CoffeeMachine(int startTime, int machineTime) {
            this.startTime = startTime;
            this.machineTime = machineTime;
        }
    }

    public static class CoffeeMachineComparator implements Comparator<CoffeeMachine> {
        @Override
        public int compare(CoffeeMachine o1, CoffeeMachine o2) {
            return (o1.startTime + o1.machineTime) - (o2.startTime + o2.machineTime);
        }
    }

    /**
     * 求所有人喝完咖啡并且洗完咖啡杯的时间
     *
     * @param washTime 洗碗机洗咖啡杯的时间
     * @param dryTime  咖啡杯自然风干的时间
     * @param index    当前来到index的人洗咖啡杯
     * @param washLine 咖啡机在washLine时刻才空闲
     * @return
     */
    public static int process(int[] drinkTime, int washTime, int dryTime, int index, int washLine) {
        if (index == drinkTime.length - 1) {
            return Math.min(Math.max(drinkTime[index], washLine) + washTime, drinkTime[index] + dryTime);
        }
        int washTime1 = Math.max(drinkTime[index], washLine) + washTime;
        int washTime2 = process(drinkTime, washTime, dryTime, index + 1, washTime1);
        int washTotalTime = Math.max(washTime1, washTime2);

        int dryTime1 = drinkTime[index] + dryTime;
        int dryTime2 = process(drinkTime, washTime, dryTime, index + 1, washLine);
        int dryTotalTime = Math.max(dryTime1, dryTime2);

        return Math.min(washTotalTime, dryTotalTime);
    }

    public static int getWashTime(int[] coffeeMachine, int washTime, int dryTime, int count) {
        int[] drinkTime = getDrinkTime(coffeeMachine, count);
        return process(drinkTime, washTime, dryTime, 0, 0);
    }

    public static int[] getDrinkTime(int[] coffeeMachineTime, int count) {
        PriorityQueue<CoffeeMachine> queue = new PriorityQueue<CoffeeMachine>(new CoffeeMachineComparator());
        int[] drinkTime = new int[count];
        for (int machineTime : coffeeMachineTime) {
            queue.add(new CoffeeMachine(0, machineTime));
        }
        for (int index = 0; index < count; index++) {
            CoffeeMachine coffeeMachine = queue.poll();
            coffeeMachine.startTime += coffeeMachine.machineTime;
            drinkTime[index] = coffeeMachine.startTime;
            queue.add(coffeeMachine);
        }
        return drinkTime;
    }

    public static int dp(int[] drinkTime, int washTime, int dryTime, int index, int washLine) {
        int count = drinkTime.length;
        if (washTime >= dryTime) {
            return drinkTime[count - 1] + dryTime;
        }
        int[][] dp = new int[count][drinkTime[count - 1] + count * washTime];
        for (int i = 0; i < dp[0].length; i++) {
            dp[count - 1][i] = Math.min(Math.max(drinkTime[count - 1], i) + washTime, drinkTime[count - 1] + dryTime);
        }
        for (int i = count - 2; i >= 0; i--) {
            int limit = drinkTime[i] + (i + 1) * washTime;
            for (int j = 0; j <= limit; j++) {
                int washTime1 = Math.max(drinkTime[i], j) + washTime;
                int washTime2 = dp[i + 1][washTime1];
                int washTotalTime = Math.max(washTime1, washTime2);

                int dryTime1 = drinkTime[i] + dryTime;
                int dryTime2 = dp[i + 1][j];
                int dryTotalTime = Math.max(dryTime1, dryTime2);

                dp[i][j] = Math.min(washTotalTime, dryTotalTime);
            }
        }

        return dp[0][0];
    }


    public static void main(String[] args) {

    }
}
