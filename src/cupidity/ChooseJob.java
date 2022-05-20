package cupidity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ChooseJob {

    public static class Job {
        public int hard;
        public int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static int[] bestJob(Job[] jobs, int[] abilitys) {
        //先对job数组排序
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.hard == o2.hard ? o2.money - o1.money : o1.hard - o2.hard;
            }
        });
        //将有效的job放入有序表中
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        Job captain = jobs[0];
        treeMap.put(captain.hard, captain.money);
        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i].hard != captain.hard && jobs[i].money > captain.money) {
                captain = jobs[i];
                treeMap.put(captain.hard, captain.money);
            }
        }
        //根据能力匹配工作
        int[] result = new int[abilitys.length];
        for (int j = 0; j < abilitys.length; j++) {

            Integer hard = treeMap.floorKey(abilitys[j]);
            result[j] = hard == null ? 0 : treeMap.get(hard);
        }
        return result;

    }

    public static void main(String[] args) {
        Job job1 = new Job(2,1);
        Job job2 = new Job(2,3);
        Job job3 = new Job(3,3);
        Job job4 = new Job(3,5);
        Job job5 = new Job(4,6);
        Job[] jobs = {job1,job2,job3,job4,job5};
        int[] ability = {1,2,3,4,5};
        int[] result = ChooseJob.bestJob(jobs,ability);
        System.out.println(result);

    }
}
