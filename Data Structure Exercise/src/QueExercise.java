public class QueExercise {
    public static void main(String[] args) {
        GenericQueue<String> queue = new GenericQueue<String>();
        queue.enqueue("Tom");
        queue.enqueue("George");
        queue.enqueue("Peter");
        System.out.println(queue.toString());

    }
}


class GenericQueue<E> extends java.util.LinkedList<E> {


    private static final long serialVersionUID = 1L;

    public void enqueue(E o) {
        this.addLast(o);
    }

    public Object dequeue() {
        return removeFirst();
    }

    public int getSize() {
        return size();
    }

    @Override
    public String toString() {

        return "Queue: " + super.toString();
    }
}

