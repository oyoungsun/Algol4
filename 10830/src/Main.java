import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static StringBuilder sb = new StringBuilder();
    static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = (Integer.parseInt(st.nextToken())) % 1000;
                }
        }
        int [][] result = pow(map, B);

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] pow(int [][] matrix, long ex) { //0(N)
        if(ex==1L) return matrix;

        int [][] ret = pow(matrix, ex/2);

        ret = matmul(ret, ret);

        if(ex % 2 == 1L){
            ret = matmul(ret, map);
        }

        return ret;

    }

    public static int[][] matmul(int[][] o1, int[][] o2) {

        int[][] ret = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= 1000;	// 행렬 원소 연산이 끝나면 MOD로 나머지연산
                }
            }
        }
        return ret;
    }
}
