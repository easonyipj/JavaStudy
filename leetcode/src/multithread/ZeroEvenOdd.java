package multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 假设有这么一个类：
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 *
 * 输入：n = 5
 * 输出："0102030405"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZeroEvenOdd {

    private static int n;
    private static Semaphore semaphoreZero = new Semaphore(1);
    private static Semaphore semaphoreEven = new Semaphore(0);
    private static Semaphore semaphoreOdd = new Semaphore(0);

    private static CountDownLatch countDownLatchZero = new CountDownLatch(0);
    private static CountDownLatch countDownLatchEven = new CountDownLatch(1);
    private static CountDownLatch countDownLatchOdd = new CountDownLatch(1);

    private volatile static int state = 0;

    private void bySemaphore() {
        Thread printZero = new Thread(() -> {
            for(int i = 1; i <= n; i++) {
                try {
                    semaphoreZero.acquire();
                    System.out.println(0);
                    if(i % 2 == 0) {
                        semaphoreOdd.release();
                    }else {
                        semaphoreEven.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread printEven = new Thread(() -> {
            for(int i = 1; i <= n; i += 2) {
                try {
                    semaphoreEven.acquire();
                    System.out.println(i);
                    semaphoreZero.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread printOdd = new Thread(() -> {
            for(int i = 2; i <= n; i += 2) {
                try {
                    semaphoreOdd.acquire();
                    System.out.println(i);
                    semaphoreZero.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        printOdd.start();
        printEven.start();
        printZero.start();
    }

    private void byvolitle() {
        Thread printZero = new Thread(() -> {
            for(int i = 1; i <= n;) {
                while (state == 0) {
                    System.out.println(0);
                    if(i % 2 == 0) {
                        state = 2;
                    }else {
                        state = 1;
                    }
                    i++;
                }
            }
        });

        Thread printEven = new Thread(() -> {
            for(int i = 1; i <= n;) {
                while (state == 1) {
                    System.out.println(i);
                    state = 0;
                    i += 2;
                }
            }
        });

        Thread printOdd = new Thread(() -> {
            for(int i = 2; i <= n;) {
                while (state == 2) {
                    System.out.println(i);
                    state = 0;
                    i += 2;
                }
            }
        });

        printOdd.start();
        printEven.start();
        printZero.start();
    }

    private void byCountDownLatch() {
        Thread printZero = new Thread(() -> {
            for(int i = 1; i <= n; i++) {
                try {
                    countDownLatchZero.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(0);
                if(i % 2 == 0) {
                    countDownLatchOdd.countDown();
                }else {
                    countDownLatchEven.countDown();
                }
                countDownLatchZero = new CountDownLatch(1);
            }
        });

        Thread printEven = new Thread(() -> {
            for(int i = 1; i <= n; i += 2) {
                try {
                    countDownLatchEven.await();
                    System.out.println(i);
                    countDownLatchEven = new CountDownLatch(1);
                    countDownLatchZero.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread printOdd = new Thread(() -> {
            for(int i = 2; i <= n; i += 2) {
                try {
                    countDownLatchOdd.await();
                    System.out.println(i);
                    countDownLatchOdd = new CountDownLatch(1);
                    countDownLatchZero.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        printOdd.start();
        printEven.start();
        printZero.start();
    }

    public static void main(String[] args) throws InterruptedException {
        n = 5;
        new ZeroEvenOdd().byCountDownLatch();
        Thread.sleep(1000L);

    }
}
