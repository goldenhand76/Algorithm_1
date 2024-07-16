import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {

    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void push(Item item){
        s[N++] = item;
    }

    public Item pop() {
        return s[--N];
    }

    public Iterator<Item> iterator() {return new ReverseArrayIterator();}

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext() {return i > 0;}
        public Item next() {return s[--i];}
    }

    public static void main(String[] args) {

    }
}
