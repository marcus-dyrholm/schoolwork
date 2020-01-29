import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolSolved { //With race condition :)
    public static long total = 0;

    public static void main(String[] args){

        ReentrantLock lock = new ReentrantLock();

        ExecutorService exec = Executors.newCachedThreadPool();

        exec.submit(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i=0;i<100;i++)
                        System.out.println("thread1 " + total++);
                }
                finally {
                    lock.unlock();
                }
            }

        });

        exec.submit(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i=0;i<100;i++)
                        System.out.println("thread2 " + total++);
                }
                finally {
                    lock.unlock();
                }
            }
        });
        exec.shutdown();
    }
}