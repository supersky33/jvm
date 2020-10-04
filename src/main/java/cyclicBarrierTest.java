import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class cyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("cyclic barrier end");
        });

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finish");
            }, "Thread " + i).start();
        }

    }
}
