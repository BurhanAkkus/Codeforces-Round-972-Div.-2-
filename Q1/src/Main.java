import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main implements Runnable {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    int[] numbers = new int[52];

    int[] a = new int[300003];
    int[] b = new int[300003];

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * (1L << 20)).start();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            if (System.getProperty("ONLINE_JUDGE") != null) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            }
            Locale.setDefault(Locale.US);
            solve();
            in.close();
            out.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Time = " + (t2 - t1));
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            System.exit(-1);
        }
    }


    //Util Functions
    String readString() throws IOException {
        while (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    long readLong() throws IOException {
        return Long.parseLong(readString());
    }

    double readDouble() throws IOException {
        return Double.parseDouble(readString());
    }


    ArrayList<Integer> readIntArrayList(int length) throws IOException {
        ArrayList array = new ArrayList();
        for(int input = 0; input < length; input++){
            array.add(readInt());
        }
        return array;
    }

    int[] readIntList(int length) throws IOException {
        int[] array = new int[length];
        for(int input = 0; input < length; input++){
            array[input] = readInt();
        }
        return array;
    }

    void readIntList(int[] array,int length) throws IOException {
        for(int input = 0; input < length; input++){
            array[input] = readInt();
        }
    }

    // Helper functions

    int[] bitsRep(int n){
        int[] bits = new int[22];
        int i = 0;
        while(n > 0){
            bits[i++] = n % 2;
            n = n / 2;
        }
        return bits;
    }

    boolean[] bitsBool(int[] intArray){
        boolean[] bits = new boolean[intArray.length];
        for(int i = 0; i < intArray.length; i++)
            bits[i] = intArray[i] > 0;

        return bits;
    }

    int[] arrayTopla(int[] a, int[] b){
        int[] sum = new int[Math.max(a.length,b.length)];
        for(int i = 0; i < sum.length; i++){
            if(i < a.length){
                sum[i] += a[i];
            }
            if(i < b.length){
                sum[i] += b[i];
            }
        }
        return sum;
    }

    int[] arrayCikar(int[] a, int[] b){
        int[] fark = new int[Math.max(a.length,b.length)];
        for(int i = 0; i < fark.length; i++){
            if(i < a.length){
                fark[i] += a[i];
            }
            if(i < b.length){
                fark[i] -= b[i];
            }
        }
        return fark;
    }
    // Helper Class

    int binarySearch(List<Integer> list, int eleman) {
        int l = 0;
        int r = list.size();
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (list.get(m) > eleman) {
                r = m;
            } else {
                l = m;
            }
        }
        if(list.size() <= l){
            return -1;
        }
        if(list.get(l) <= eleman){
            if(list.size() == l + 1)
                return -1;
            else{
                return l + 1;
            }
        }
        return l;
    }

    void solve() throws IOException {
        int t = readInt();
        char[] harfler = new char[5];
        harfler[0] = 'a';
        harfler[1] = 'e';
        harfler[2] = 'i';
        harfler[3] = 'o';
        harfler[4] = 'u';
        for (int problem = 0; problem < t; problem++){
            int n = readInt();
            int repetition = n / 5;
            int kalan = n % 5;
            for(int harf = 0; harf < 5; harf++) {
                for (int i = 0; i < repetition; i++) {
                    out.print(harfler[harf]);
                }
                if(kalan > harf){
                    out.print(harfler[harf]);
                }
            }
            out.println();
        }
    }

}