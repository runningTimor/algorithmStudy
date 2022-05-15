public class SynchronizedTest {

    public static void main(String[] args) {
        VolitileTest test = new VolitileTest();
        test.start();
        for (;;){
            if (test.s!=0){
                System.out.println(test.s);
                System.exit(1);
            }
        }

    }

    static class VolitileTest extends Thread{
        private volatile int s = 0;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s = 1;
        }
    }



}