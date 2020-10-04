import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class consumer2 {

    /**
     * 生产者消费者3.0版本，不使用sync lock
     *
     * @param args
     */

    public static void main(String[] args) throws InterruptedException {
        MyResouce myResouce = new MyResouce(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            try {
                myResouce.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"生产线程").start();
        new Thread(()->{
            try {
                myResouce.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费线程").start();

        Thread.sleep(5000L);
        System.out.println("===>end");
        myResouce.stop();
    }

    static class MyResouce {

        private volatile boolean FLAG = true;
        private AtomicInteger atomicInteger = new AtomicInteger();
        private BlockingQueue<String> blockingQueue = null;

        public MyResouce(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
            System.out.println(blockingQueue.getClass().getName());
        }

        public void produce() throws Exception {
            String data = null;
            boolean result;
            while (FLAG) {
                data = atomicInteger.incrementAndGet() + "";
                result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (result) {
                    System.out.println("生产者[成功插入数据]" + Thread.currentThread().getName() + "--" + data);
                }
                else {
                    System.out.println("生产者[失败插入数据]：" + Thread.currentThread().getName() + "--" + data);
                }
                Thread.sleep(1000L);
            }
            System.out.println("====>生产活动结束");
        }

        public void consumer() throws Exception {
            String result = null;
            while (FLAG) {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (null == result || result.equalsIgnoreCase("")) {
                    FLAG = false;
                    System.out.println("消费者：等待超时");
                    return;
                }
                System.out.println("消费者[读取数据]：" + Thread.currentThread().getName() + "--" + result);
            }
        }

        public void stop() {
            this.FLAG = false;
        }

    }

}
