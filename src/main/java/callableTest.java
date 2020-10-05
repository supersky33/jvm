import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class callableTest {

    public static void main(String[] args) throws Exception {

        FutureTask<Integer> futureTask = new FutureTask<>(new CallableDemo());

        new Thread(futureTask, "A").start();

        while (!futureTask.isDone()) {   // 自旋锁

        }

        System.out.println(futureTask.get() + "");

    }
    static class CallableDemo implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("coming in callable");
            return 1024;
        }
    }

}
