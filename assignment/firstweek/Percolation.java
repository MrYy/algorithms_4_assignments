package assignment.firstweek;

/**
 * Created by ge on 2016/6/2.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //init N-by-N grid,all sites blocked
    boolean isOpen[];
    boolean topOpen[];
    private int N;
    private WeightedQuickUnionUF uf;
    public Percolation(int N) {
        //use index 1->N;
        if(N<=0) throw new IllegalArgumentException();
        isOpen = new boolean[N*N];
        uf = new WeightedQuickUnionUF(N*N);
        this.N = N;
    }

    //open a (i,j) site ,if it is blocked
    public void open(int i, int j) {
        //transform 1->N to 0->N-1
        i--;j--;
        int x,y;
        x=i; y = j - 1;
        if(isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        x= i ; y = j + 1;
        if(isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        x = i -1 ; y = j;
        if(isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        x = i+1;y = j;
        if(isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        isOpen[calculate(i,j)] = true;
        if(i==0) topOpen[j] = true;
    }

    private int calculate(int i, int j) {
        return i*N + j ;
    }
    //is  a (i,j) site open?
    public boolean isOpen(int i, int j) {
        i--; j--;
        return isOpen[i * N + j];
    }

    //is (i,j) site full?
    public boolean isFull(int i, int j) {
        i--; j--;
        if(!isOpen(i,j)) return false;
        if(i==0)
            if(topOpen[j])
                return true;
        for (int index=0;index<N;index++) {
            if(topOpen[index]) return uf.connected(index, calculate(i, j));
        }
        return false;
    }

    //is the system percolated?
    public boolean percolates() {
        for (int j=1;j<=N;j++) {
            if (isFull(N,j)) return true;
        }
        return false;
    }


    public static void main(String[] args) {

    }

}
