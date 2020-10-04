import java.util.concurrent.Semaphore;

public class semaphoreTest {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到资源");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " 释放资源==");
                    semaphore.release();
                }
            }, ""+i).start();
        }

    }
}
