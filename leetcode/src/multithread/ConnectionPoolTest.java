package multithread;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 6-18
 */
public class ConnectionPoolTest {
    private static DataBaseConnectionPool pool  = new DataBaseConnectionPool(10);
    // 保证10个ConnectionRunner同时开始run()
    private static CountDownLatch start = new CountDownLatch(1);
    // main线程等待所有ConnectionRunner执行完后再继续执行
    private static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // 线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection:  " + got);
        System.out.println("not got connection " + notGot);
    }

    static class ConnetionRunner implements Runnable {
        int           count;
        AtomicInteger got;
        AtomicInteger notGot;

        private ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            while (count > 0) {
                try {
                    // 从线程池获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的次数got和未获取的次数notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
