import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;
    private class Node{
        Item item;
        Node next;
        Node prev;
    }

    public Deque(){
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException("Illegal Input");
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = item;
        if (oldFirst != null) {
            oldFirst.prev = first;
        } else {
            last = first;
        }
        size ++;
    }

    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException("Illegal Input");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (oldLast != null) {
            oldLast.next = last;
        } else {
            first = last;
        }
        size ++;
    }

    public Item removeFirst(){
        if (size == 0) throw new NoSuchElementException("List is empty");
        Item item = first.item;
        first = first.next;
        size --;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return item;
    }

    public Item removeLast(){
        if (size == 0) throw new NoSuchElementException("List is empty");
        Item item = last.item;
        last = last.prev;
        size --;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {return current != null;}

        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException("No next Element");
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove operation is not supported");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);

        deque.addLast(-1);
        deque.addLast(-2);
        deque.addLast(-3);

        StdOut.println(deque.size());

        while (!deque.isEmpty()){
            StdOut.println(deque.removeFirst());
            StdOut.println(deque.removeLast());
        }
    }
}
