import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int array[];
    static boolean dp[][];
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int [] arr = new int[n + 1];
        dp = new boolean[n + 1][n + 1];

        array = new int[n];
        int[][] dp1 = new int[n][n];

        for(int i=1; i<=n; i++){
            arr[i] = sc.nextInt();
        }

        //pelin();
        solve(arr);
        m = sc.nextInt();
        for(int i=0; i<m; i++){

            int s = sc.nextInt() - 1;
            int e = sc.nextInt() - 1;
            //System.out.println(dp[s][e]);
            if(dp[s][e]) System.out.println(1);
            else System.out.println(0);
        }

    }
    public static void solve(int[] arr){
        for(int i = 1; i <= n; i++)
            dp[i][i] = true;

        for(int i = 1; i <= n - 1; i++)
            if(arr[i] == arr[i + 1]) dp[i][i + 1]= true;

        for(int i = 2; i < n; i++){
            for(int j = 1; j <= n - i; j++){
                if(arr[j] == arr[j + i] && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }
    }
    private static void pelin( ) {
//        for(int i=0; i<n; i++) {
//            dp[i][i] = 1;
//        }
//
//        for(int i = 0; i < n - 1; i++)
//            if(array[i] == array[i + 1]) dp[i][i + 1]= 1;
//
//        for(int i = 1; i < n-1; i++){
//            for(int j = 0; j < n - i; j++){
//                if(array[j] == array[j + i] && dp[j + 1][j + i - 1]==1)
//                    dp[j][j + i] = 1;
//            }
//       }

    }
    private static int recur(int srt, int end ) {

        if(srt==end){
            return 1;
        } //홀수
        if((end - srt)==1) {
            if(array[srt]==array[end])
                return 1; //짝수
            else return 0;
        }

        if(array[srt]==array[end]) {
            return recur(srt + 1, end - 1);
        }else {
            return 0;
        }
    }



    private static int searchP(int srt, int end) {
        Stack<Integer> s = new Stack<>();
        int size = end - srt + 1;
        if(size % 2 ==0) {
            for (int i = srt; i < srt+(size / 2); i++) {
                s.add(array[i]);
            }
            for ( int i= srt+(size / 2); i<=end; i++){
                if(s.pop() != array[i]) {
                    return 0;
                }
            }
        }else {
            for (int i = srt; i < srt+size / 2; i++) {
                s.add(array[i]);
            }
            for ( int i= srt+(size / 2)+1; i<=end; i++){
                if(s.pop() != array[i]) {
                    return 0;
                }
            }
        }
        return 1;
    }
}
