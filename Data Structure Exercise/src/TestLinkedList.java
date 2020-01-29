import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestLinkedList {
    public static void main(String[] args) {

        LinkedList<Object> linkedList = new LinkedList<>();

            linkedList.add(0, "blue");
            linkedList.add(1, "green");
            linkedList.add(2, "yellow");
            linkedList.add(3, "white");

            linkedList.add(1,"red");

        ListIterator<Object> listIterator = linkedList.listIterator();
        while (listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
        System.out.println();

        listIterator = linkedList.listIterator(linkedList.size());
        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }

        System.out.println();

        linkedList.addFirst(linkedList.getLast());
        linkedList.removeLast();

        listIterator = linkedList.listIterator();
        while (listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
        System.out.println();

        listIterator = linkedList.listIterator(linkedList.size());
        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }
    }
}
