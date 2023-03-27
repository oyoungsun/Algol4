import java.util.Arrays;
import java.util.Scanner;

//  가능성트리 & 그리드
//
public class Main {
    static int [] chap;

    static int dp[][];
    static int s[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            chap = new int[n];
            dp = new int[n][n];
            s = new int[n][n];

            for (int i = 0; i < n; i++) {
                chap[i] = sc.nextInt();
                Arrays.fill(dp[i], -1);
                Arrays.fill(s[i], -1);
                dp[i][i] = 0;

            }

            System.out.println(cost(0, n - 1));
        }
    }

    private static int cost(int st, int end){
        if(dp[st][end] != -1 ) return dp[st][end];

        int max = Integer.MAX_VALUE;
        for(int i=st; i<end; i++){
            max = Math.min(cost(st, i)+cost(i+1, end), max);
        }
        max += sum(st, end);
        dp[st][end] = max;

        return max;
    }

    private static int sum(int start, int end) {
        if(s[start][end]!=-1)
            return s[start][end];

        int sum = 0;

        for (int i = start; i <= end; i++) {
            sum += chap[i];
        }

        s[start][end] = sum;

        return sum;
    }


}
