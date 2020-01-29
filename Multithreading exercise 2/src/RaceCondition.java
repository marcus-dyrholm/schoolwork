public class RaceCondition {
    public static class Count{
        private long total=0;

        public long getTotal(){
            return total;
        }
        public void add(){
            this.total++;
        }
    }
    public static Count total = new Count();

    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    synchronized (total) {
                        total.add();
                    }
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    synchronized (total) {
                        total.add();
                    }
                }
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}

        System.out.println(total.getTotal());
    }
}