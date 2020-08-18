package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: Xusj
 * @Date: 2020/3/30
 * @Description:
 */
public class SynchronizedTest implements Runnable {

    /**
     * 共享资源
     */
    static int i = 0;

    /**
     * 作用于实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();

        Thread t1 = new Thread(test);
        Thread t2= new Thread(test);
        t1.start();
        t2.start();
        //todo join了解
        t1.join();
        t2.join();

        //todo 线程指定方法启动
        new Thread(test::increase).start();
        System.out.println(i);

        /**
         * todo 线程池如何达到以上情景
         */
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(()-> {
//            singleThreadPool.submit(test);
        });
        singleThreadPool.shutdown();

        //todo 为什么分布式环境下synchronized失效？如何解决这种情况？

    }

}
