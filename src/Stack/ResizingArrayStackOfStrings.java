public class ResizingArrayStackOfStrings {

    private String[] s;
    private int N = 0;
    public ResizingArrayStackOfStrings(int l){
        s = new String[l];
    }

    public void push(String item){
        if (s.length == N) { resize(2 * s.length);}
        s[N++] = item;
    }

    public String pop(){
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }

    public void resize(int l){
        String[] copy = new String[l];
        for (int i = 0; i < N; i++){
            copy[i] = s[i];
        }
        s = copy;
    }

    public static void main(String[] args) {

    }
}
