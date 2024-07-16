import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>{

    private Node first, last;
    private int size;
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public RandomizedQueue(){
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException("Illegal Input");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (!isEmpty()) {
            oldlast.next = last;
        }else first = last;
        size++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int rand = StdRandom.uniformInt(size);
        Node current = first;
        for (int i = 0; i < rand; i++) {
            current = current.next;
        }

        Item item = current.item;
        if (current.prev != null) current.prev.next = current.next;
        else first = current.next;

        if (current.next != null) current.next.prev = current.prev;
        else last = current.prev;

        size--;
        if (isEmpty()){
            last = null;
            first = null;
        }
        return item;
    }

    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int rand = StdRandom.uniformInt(size);
        Node current = first;
        for (int i = 0; i < rand; i++) {
            current = current.next;
        }
        return current.item;
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
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
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        System.out.println("Size after enqueuing: " + rq.size());
        System.out.println("Sample: " + rq.sample());
        System.out.println("Dequeue: " + rq.dequeue());
        System.out.println("Size after dequeuing: " + rq.size());
    }
}
