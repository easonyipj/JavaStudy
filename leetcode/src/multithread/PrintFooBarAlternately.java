package multithread;

import java.util.concurrent.CountDownLatch;

/**
 * Suppose you are given the following code:
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * The same instance of FooBar will be passed to two different threads.
 * Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "foobar"
 * Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
 * Example 2:
 *
 * Input: n = 2
 * Output: "foobarfoobar"
 * Explanation: "foobar" is being output 2 times.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintFooBarAlternately {

    private static final Object lock = new Object();
    private static boolean flag = false;
    private static int N = 2;

    public static void printByWaitNotify() {

        Thread printfoo = new Thread(() -> {
            synchronized (lock) {
                try {
                    while (!flag) {
                        lock.wait();
                    }

                    for (int i = 0; i < N; i++) {
                        System.out.print("foo");
                        lock.notifyAll();
                        lock.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread printbar = new Thread(() -> {
            synchronized (lock) {
                flag = true;
                lock.notifyAll();

                try {
                    for (int i = 0; i < N; i++) {
                        lock.wait();
                        System.out.print("bar");
                        lock.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        printbar.start();
        printfoo.start();
    }


}
