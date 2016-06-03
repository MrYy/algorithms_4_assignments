package firstweek.jobview.question1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by ge on 2016/6/3.
 * firstweek job interview . Q1 and Q2
 */
public class SocalNetwork {
    private static Scanner scanner;
    private int[] socalLink;
    private int[] size;
    private int[] max;
    private int N;
    private int count;
    public SocalNetwork(int N) {
        socalLink = new int[N];
        this.N = N;
        for (int i = 0 ;i<N;i++) {
            socalLink[i] = i;
        }
        size = new int[N];
        for (int i =0; i<N;i++) {
            size[i] = 1;
        }
        max = new int[N];
        for (int i =0;i<N;i++) {
            max[i] = i;
        }
    }
    public boolean union(int p , int q) {
        int i = find(p);
        int j = find(q);
        int sum = 0 ;
        if (i == j) return false;

        if (size[i]<size[j]) {
            socalLink[i] = j;
            if (max[i]>max[j]) max[j] = max[i];
            sum = size[j] += size[i];
        }
        else
        {
            socalLink[j] = i ;
            if (max[j]>max[i]) max[i] = max[j];
            sum = size[i] += size[j];
        }
        if(sum == N) return true;
        return false;
    }
    public boolean connected(int p ,int q) {
        return find(p)==find(q);
    }

    public int find(int p) {
        while (socalLink[p]!=p) p = socalLink[p];
        return p;
    }
    //question two
    public int findMax(int p) {
        while (socalLink[p]!=p) p = socalLink[p];
        return max[p] + 1 ;
    }
    public static void main(String[] args) {
        File file = new File("friends");
        if (file.exists()) {
            try {
                scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
                //N students
                int N = scanner.nextInt();
                SocalNetwork socal = new SocalNetwork(N);
                int count = 0;
                while (scanner.hasNext()) {
                    int p = scanner.nextInt() - 1 ;
                    int q = scanner.nextInt() - 1;
                    count ++;
                    if(socal.union(p,q)){
                        System.out.println("all connect at times:"+count);
                        System.out.println("find max:"+ socal.findMax(4));
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
