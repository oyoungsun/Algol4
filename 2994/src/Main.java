import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int money[] = new int[n];
        int dp[] = new int[100001]; //동전의 값
        int max = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, 0);
        for(int i=0; i<n; i++){
            money[i] = sc.nextInt();
            dp[money[i]] = 1;
        }

        for(int i=1; i<=k; i++){
            if(dp[i]==0) {
                dp[i] = max;
            }
            for(int j : money){
                if(i-j<0) continue;
                dp[i] = Math.min(dp[i], dp[i-j] + 1);
            }

        }
        if(dp[k]==max) System.out.println(-1);
        else System.out.println(dp[k]);
    }
}
