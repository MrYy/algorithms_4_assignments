package firstweek.assignment;

/**
 * Created by ge on 2016/6/2.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.File;

public class Percolation {
    //init N-by-N grid,all sites blocked
    private boolean isOpen[];
    private boolean topOpen[];
    private int N;
    private WeightedQuickUnionUF uf;
    public Percolation(int N) {
        //use index 1->N;
        if(N<=0) throw new IllegalArgumentException();
        isOpen = new boolean[N*N];
        topOpen = new boolean[N];
        uf = new WeightedQuickUnionUF(N*N);
        this.N = N;
    }

    //open a (i,j) site ,if it is blocked
    public void open(int i, int j) {
        //transform 1->N to 0->N-1
        i--;j--;
        int x,y;
        x=i; y = j - 1;
        if(y>=0&&isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        x= i ; y = j + 1;
        if(y<=(N-1)&&isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        x = i -1 ; y = j;
        if(x>=0&&isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        x = i+1;y = j;
        if(x<=(N-1)&&isOpen[calculate(x,y)]) uf.union(calculate(i,j),calculate(x,y));
        isOpen[calculate(i,j)] = true;
        if(i==0) topOpen[j] = true;
    }

    private int calculate(int i, int j) {
        return i*N + j ;
    }
    //is  a (i,j) site open?
    public boolean isOpen(int i, int j) {
        i--;
        j--;
        return isOpen[i * N + j];
    }

    //is (i,j) site full?
    public boolean isFull(int i, int j) {
        i--; j--;
        if(!isOpen(++i,++j)) return false;
        i--; j--;
        if(i==0)
            if(topOpen[j])
                return true;
        for (int index=0;index<N;index++) {
            if(topOpen[index]) return uf.connected(index, calculate(i, j));
//            System.out.print(index+" ");
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
//        System.out.println("percolation");
//        File file = new File("input10.txt");
//        System.out.print(file.isFile());
    }

}
