import java.util.Scanner;


public class Main {
    static int n;
    static int mbyte;
    static int A[];
    static int C[];
//앱 비활성화의 최소의 비용
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        mbyte = sc.nextInt();
        A = new int[n];
        C = new int[n];
        for(int i=0; i<n; i++){
            A[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++){
            C[i] = sc.nextInt();
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            min = Math.min(recur(C[i], mbyte-A[i], i+1), min);
        }
        System.out.println(min);
    }

    private static int recur(int weight, int remain, int start) {
        if(remain <=0) return weight;
        int result= Integer.MAX_VALUE; // INF
        for(int i=start; i<n; i++){
            result = Math.min(recur(weight+C[i], remain-A[i], i+1), result);
        }
        return result;
    }

}
