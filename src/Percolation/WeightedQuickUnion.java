public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int n){
        id = new int[n];
        sz = new int[n];
        for (int i=0; i < n; i++){
            id[i] = i;
            sz[i] = 0;
        }
    }

    public int root(int i){
        while (id[i] != i){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) {return;}
        if (sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(10);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(3, 4);
        System.out.print(uf.connected(1, 4));
    }
}
