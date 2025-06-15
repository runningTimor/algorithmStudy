package tree;

/**
 * 线段树
 */
public class SegmentTreeCode {

    public static class SegmentTree {

        private int MaxN;
        private int[] array;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] arrayOrigin) {
            MaxN = arrayOrigin.length + 1;
            array = new int[MaxN];
            for (int i = 1; i < MaxN; i++) {
                array[i] = arrayOrigin[i - 1];
            }
            sum = new int[MaxN << 2];
            lazy = new int[MaxN << 2];
            change = new int[MaxN << 2];
            update = new boolean[MaxN << 2];

        }

        public void initial(int left, int right, int rt) {
            if (left == right) {
                sum[rt] = array[left];
                return;
            }
            int middle = (left + right) >> 1;
            initial(left, middle, rt << 1);
            initial(middle + 1, right, rt << 1 | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        public void add(int L, int R, int C, int left, int right, int rt) {
            // 任务范围覆盖
            if (L <= left && R >= right) {
                sum[rt] += (right - left + 1) * C;
                lazy[rt] += C;
                return;
            }

            int middle = (left + right) >> 1;
            pushdown(rt, middle - left + 1, right - middle);
            if (L <= middle) {
                add(L, R, C, left, middle, rt << 1);
            }
            if (R >= middle + 1) {
                add(L, R, C, middle + 1, right, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public void update(int L, int R, int C, int left, int right, int rt) {
            // 任务范围覆盖
            if (L <= left && R >= right) {
                sum[rt] = (right - left + 1) * C;
                lazy[rt] = 0;
                change[rt] = C;
                update[rt] = true;
                return;
            }

            int middle = (left + right) >> 1;
            pushdown(rt, middle - left + 1, right - middle);

            if (L <= middle) {
                update(L, R, C, left, middle, rt << 1);
            }
            if (R >= middle + 1) {
                update(L, R, C, middle + 1, right, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public long query(int L, int R, int left, int right, int rt) {
            // 任务范围覆盖
            if (L <= left && R >= right) {
                return sum[rt];
            }

            int middle = (left + right) >> 1;
            pushdown(rt, middle - left + 1, right - middle);
            int sum = 0;

            if (L <= middle) {
                sum += query(L, R, left, middle, rt << 1);
            }
            if (R >= middle + 1) {
                sum += query(L, R, middle + 1, right, rt << 1 | 1);
            }
            return sum;
        }


        private void pushdown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                sum[rt << 1] = change[rt] * ln;
                change[rt << 1] = change[rt];

                update[rt << 1 | 1] = true;
                sum[rt << 1 | 1] = change[rt] * rn;
                change[rt << 1 | 1] = change[rt];

                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;

                update[rt] = false;
                change[rt] = 0;
            }

            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += ln * lazy[rt];

                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += rn * lazy[rt];
                lazy[rt] = 0;
            }
        }

    }

    public static class Right {

        private int[] array;

        public Right(int[] sourceArray) {
            array = new int[sourceArray.length + 1];
            for (int i = 1; i <= sourceArray.length; i++) {
                array[i] = sourceArray[i - 1];
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                array[i] += C;
            }
        }


        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                array[i] = C;
            }
        }

        public long query(int L, int R) {
            long sum = 0;
            for (int i = L; i <= R; i++) {
                sum += array[i];
            }
            return sum;
        }

    }

    public static int[] generateArray(int length, int max) {
//        int size = length;
        int size = (int) (Math.random() * length) + 1;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            int num = (int) (Math.random() * max) - (int) (Math.random() * max);
            array[i] = num;
        }
        return array;
    }


    public static void main(String[] args) {
        int length = 3;
        int max = 10;

        for (int testTmes = 0; testTmes < 1000; testTmes++) {
            int[] randomArray = generateArray(length, max);
            SegmentTree segmentTree = new SegmentTree(randomArray);
            int S = 1;
            int N = randomArray.length;
            segmentTree.initial(S, N, 1);
            Right right = new Right(randomArray);

            for (int changeTimes = 0; changeTimes <= 2; changeTimes++) {

                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) + 1;

                if (Math.random() < 0.5) {
                    segmentTree.add(L, R, C, S, N, 1);
                    right.add(L, R, C);
                } else {
                    segmentTree.update(L, R, C, S, N, 1);
                    right.update(L, R, C);
                }
            }
            for (int queryTimes = 0; queryTimes <= 1000; queryTimes++) {

                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long query1 = segmentTree.query(L, R, S, N, 1);
                long query2 = right.query(L, R);
                if (query1 != query2) {
                    System.out.println(randomArray.toString());
                }


            }


        }


    }
}
