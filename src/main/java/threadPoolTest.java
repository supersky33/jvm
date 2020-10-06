import java.util.concurrent.*;

public class threadPoolTest {

    public static void main(String[] args) {

        //ExecutorService pool = Executors.newFixedThreadPool(5);

        Executors.newSingleThreadExecutor();

        Executors.newCachedThreadPool();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++) {
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName() + "-办理业务");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();

    }

}
