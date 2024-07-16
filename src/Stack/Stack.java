import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator() {return new ListIterator();}
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() {return current != null;}
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("("));
            else if(s.equals("+")) ops.push(s);
            else if(s.equals("*")) ops.push(s);
            else if(s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
