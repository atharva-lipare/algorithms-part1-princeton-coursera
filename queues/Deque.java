import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first,last;
    private int size;

    private class Node {
        private Item item;
        private Node next,prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("arg is null in addFirst");
        }
        if (size == 0) {
            size++;
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = null;
            last = first;
            return;
        }
        Node sec = first;
        first = new Node();
        first.prev = null;
        first.item = item;
        first.next = sec;
        sec.prev = first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("arg is null in addLast");
        }
        if (size == 0) {
            size++;
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = null;
            last = first;
            return;
        }
        Node secLast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        secLast.next = last;
        last.prev = secLast;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("deque empty in removeFirst");
        }
        if (size == 1) {
            Item temp = first.item;
            first = null;
            last = null;
            size--;
            return temp;

        }
        Item tmpi = first.item;
        first = first.next;
        first.prev = null;
        size--;
        return tmpi;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("deque empty in removeLast");
        }
        if (size == 1) {
            Item temp = first.item;
            first = null;
            last = null;
            size--;
            return temp;
        }
        Item temp = last.item;
        last = last.prev;
        last.next = null;
        size--;
        return temp;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException("remove method called");
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next method called");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deq1 = new Deque<>();
        deq1.addLast(1);
        StdOut.println("first =" + deq1.first.item +" last =" + deq1.last.item);
        deq1.addLast(2);
        StdOut.println("first =" + deq1.first.item +" last =" + deq1.last.item);
        deq1.addLast(3);
        StdOut.println("first =" + deq1.first.item +" last =" + deq1.last.item);
        StdOut.println(deq1.removeFirst());
        StdOut.println(deq1.removeFirst());
        StdOut.println(deq1.removeFirst());
        StdOut.println("first =" + deq1.first.item +" last =" + deq1.last.item);
        StdOut.println(deq1.removeLast());
        StdOut.println("first =" + deq1.first.item +" last =" + deq1.last.item);
        StdOut.println(deq1.isEmpty());
        StdOut.println("size =" + deq1.size);
        deq1.addLast(1);
        StdOut.println("first =" + deq1.first.item +" last =" + deq1.last.item);
        deq1.addLast(2);
        StdOut.println("first =" + deq1.first.item +" last =" + deq1.last.item);
        /* Iterator itr = deq1.iterator();
        StdOut.println(itr.next());
        StdOut.println(itr.next());
        StdOut.println(itr.next()); */
    }

}