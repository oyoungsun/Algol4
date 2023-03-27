import java.util.Scanner;

public class Main {
    static int n;
//dp 를 생각해보면?
    static long [] dp = new long[1500000+1];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = (int) (sc.nextLong() % 1500000);


            dp[0]=0;
            dp[1]=1;
            for (int i = 2; i < 1500000; i++) {
                dp[i]=(dp[i-1]+dp[i-2] ) % 1000000 ;
            }
        System.out.println(dp[n]);
    }
}
