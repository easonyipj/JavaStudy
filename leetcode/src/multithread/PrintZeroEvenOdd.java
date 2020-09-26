package multithread;

import java.util.concurrent.Semaphore;

/**
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer.
 * Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 *
 *  
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.
 * Example 2:
 *
 * Input: n = 5
 * Output: "0102030405"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintZeroEvenOdd {
    private int n;
    private Semaphore zeroSemphore = new Semaphore(1);
    private Semaphore evenSemphore = new Semaphore(0);
    private Semaphore oddSemphore = new Semaphore(0);

    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero() throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            zeroSemphore.acquire();
            System.out.print(0);
            if(i % 2 == 1) {
                oddSemphore.release();
            } else {
                evenSemphore.release();
            }
        }
    }

    public void even() throws InterruptedException {
        for(int i = 2; i <= n; i += 2) {
            evenSemphore.acquire();
            System.out.print(i);
            zeroSemphore.release();
        }
    }

    public void odd() throws InterruptedException {
        for(int i = 1; i <= n; i += 2) {
            oddSemphore.acquire();
            System.out.print(i);
            zeroSemphore.release();
        }
    }

    public static void main(String[] args) {
        PrintZeroEvenOdd printZeroEvenOdd = new PrintZeroEvenOdd(5);
        Thread threadZero = new Thread(() -> {
            try {
                printZeroEvenOdd.zero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadZero.start();
        Thread threadEven = new Thread(() -> {
            try {
                printZeroEvenOdd.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadEven.start();
        Thread threadOdd = new Thread(() -> {
            try {
                printZeroEvenOdd.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadOdd.start();
    }
}
