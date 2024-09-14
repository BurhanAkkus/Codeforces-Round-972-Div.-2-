import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main implements Runnable {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    String[] strings = new String[1003];
    int[][] stringScores = new int[1003][5];
    int[][] sonHarfler = new int[1003][5];
    int[][] dp = new int[1003][5];
    char[] harfler = "narek".toCharArray();

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

    int binarySearch(Long[] list, int eleman,int length) {
        int l = 0;
        int r = length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (list[m] > eleman) {
                r = m;
            } else {
                l = m;
            }
        }
        if(list[l] <= eleman){
            return l + 1;
        }
        return l;
    }
    void solve() throws IOException {
        int t = readInt();
        for (int problem = 0; problem < t; problem++){
            int n = readInt();
            int m = readInt();
            for(int i = 0; i < n; i++){
                strings[i] = readString();
                for(int baslangicHarfi = 0; baslangicHarfi < 5; baslangicHarfi++){
                    char aradigimHarf = harfler[baslangicHarfi];
                    int aradigimIndex = baslangicHarfi;
                    int toplamHarfler = 0;
                    for(int j = 0; j < strings[i].length(); j++){
                        char current = strings[i].charAt(j);
                        for(int harfCounter = 0; harfCounter < 5; harfCounter++){
                            if(harfler[harfCounter] == current){
                                toplamHarfler++;break;
                            }
                        }
                    }
                    int scoreNarek = 0;
                    for(int j = 0; j < strings[i].length(); j++){
                        char current = strings[i].charAt(j);
                        if(aradigimHarf == current){
                            aradigimIndex++;
                            if(aradigimIndex == 5){
                                scoreNarek +=5;
                                aradigimIndex = 0;
                            }
                            aradigimHarf = harfler[aradigimIndex];
                        }

                    }
                    stringScores[i][baslangicHarfi] = scoreNarek - toplamHarfler + scoreNarek;
                    sonHarfler[i][baslangicHarfi] = aradigimIndex;
                }
            }

            // initialize DP
            for(int i = 0; i < harfler.length; i++){
                dp[n][i] = 0;
                stringScores[n][i] = 0;
            }
            for(int i = n - 1; i >= 0; i--){
                for(int dp_harf = 0; dp_harf < harfler.length; dp_harf++) {
                    dp[i][dp_harf] = Math.max(
                            stringScores[i][dp_harf] + dp[i + 1][sonHarfler[i][dp_harf]],
                            dp[i + 1][dp_harf]);
                }
            }
            /*int max = 0;
            for(int index = 0; index < 5; index++){
                if(dp[0][index] > max){
                    max = dp[0][index];
                }
            }*/
            out.println(dp[0][0]);


        }
    }
}