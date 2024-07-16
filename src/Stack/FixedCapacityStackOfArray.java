public class FixedCapacityStackOfArray {

    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfArray(int capacity){
        s = new String[capacity];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void push(String item){
        s[N++] = item;
    }

    public String pop(){
        return s[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfArray s = new FixedCapacityStackOfArray(5);
        s.push("1");
        s.push("1");
        s.push("1");
        System.out.print(s.pop());
        System.out.print(s.pop());
        System.out.print(s.pop());
    }
}
