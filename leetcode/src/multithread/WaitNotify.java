package multithread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class WaitNotify {
    private static boolean flag = true;
    private static final Object lock = new Object();
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static void main() {
        try {
            Thread waitThread = new Thread(new Wait(), "waitThread");
            waitThread.start();
            Thread.sleep(10);
            Thread notifyThread = new Thread(new Notify(), "notifyThread");
            notifyThread.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                while(flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. Wait. " + FORMAT.format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread() + " finish the work " + FORMAT.format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock " + FORMAT.format(new Date()));
                flag = false;
                // 调用notify后不是马上释放对象锁，而是在代码块语句执行完后释放对象锁
                lock.notifyAll();
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again " + FORMAT.format(new Date()));
            }
        }
    }
}
