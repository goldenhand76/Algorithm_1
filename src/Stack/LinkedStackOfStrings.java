public class LinkedStackOfStrings {

    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void push(String item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;

    }
    public static void main(String[] args) {
        LinkedStackOfStrings s = new LinkedStackOfStrings();
        s.push("1");
        s.push("1");
        s.push("1");
        System.out.print(s.pop());
        System.out.print(s.pop());
        System.out.print(s.pop());
    }
}
