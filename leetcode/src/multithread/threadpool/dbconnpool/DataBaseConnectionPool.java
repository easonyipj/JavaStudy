package multithread.threadpool.dbconnpool;

import java.sql.Connection;
import java.util.LinkedList;

@SuppressWarnings("WeakerAccess")
public class DataBaseConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public DataBaseConnectionPool(int initialSize) {
        if(initialSize > 0) {
            for(int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if(connection != null) {
            synchronized (pool) {
                // 连接释放后需要通过，让其他消费者能够感知到连接池中已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    // mills内无法获取到连接，将会返回null
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool) {
            if(mills <= 0) {
                while(pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while(pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

}
