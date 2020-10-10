package multithread;

import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    private ReentrantLock mutex;
    private ReentrantLock[] forks;

    public DiningPhilosophers() {
        mutex = new ReentrantLock();
        forks = new ReentrantLock[5];
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int right = philosopher;
        int left = (philosopher + 1) % 5;

        mutex.lock();
        forks[right].lock();
        forks[left].lock();

        pickLeftFork.run();
        pickRightFork.run();

        mutex.unlock();

        eat.run();
        putLeftFork.run();
        putLeftFork.run();

        forks[right].unlock();
        forks[left].unlock();
    }

}
