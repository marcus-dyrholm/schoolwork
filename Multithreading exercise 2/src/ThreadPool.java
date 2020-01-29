import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {
    public static long total = 0;

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.submit(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 100; i++) {
                        //total.add();
                        System.out.println("t1 " + total++);
                    }
                } finally {
                    lock.unlock();
                }
            }
        });


        executor.submit(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 100; i++) {
                        //total.add();
                        System.out.println("t2 " + total++);
                    }
                } finally {
                    lock.unlock();
                }
            }
        });

executor.shutdown();


    }
}