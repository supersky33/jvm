import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadPoolTest {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        Executors.newSingleThreadExecutor();

        Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName() + "-办理业务");
            });
        }

        pool.shutdown();

    }

}
