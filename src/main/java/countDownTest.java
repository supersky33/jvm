import java.util.concurrent.CountDownLatch;

public class countDownTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        Demo demo = new Demo(countDownLatch);


        for (int i = 0; i < 10; i++) {
            new Thread(demo, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(EnumCountry.for_EnumCountry(2).getCountry());
        System.out.println(EnumCountry.THREE.getCountry());

    }

    static class Demo implements Runnable {

        public volatile CountDownLatch countDownLatch;

        public Demo(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName());
        }
    }

    public enum EnumCountry {
        ONE(1,"齐国"),TWO(2,"楚国"),THREE(3, "秦国");

        private int code;
        private String country;

        public int getCode() {
            return this.code;
        }

        public String getCountry() {
            return this.country;
        }

        EnumCountry(int code, String country) {
            this.code = code;
            this.country = country;
        }

        public static EnumCountry for_EnumCountry (int index) {
            EnumCountry[] values = EnumCountry.values();
            for(EnumCountry e : values) {
                if (e.getCode() == index) {
                    return e;
                }
            }
            return null;

        }
    }
}
