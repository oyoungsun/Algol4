import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int cnt, h;
    static char map [][];
    static int visit [][];

    static int dir[][] = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];


        int cnt=0;
        for(int i=0; i<n; i++){
            String temp = br.readLine();
            for(int j=0; j<m; j++){
                if(temp.charAt(j)=='.'){
                    map[i][j]='.';
                }
                else if(temp.charAt(j)=='x'){ //미네랄
                    map[i][j]='x';
                }
            }
        }//입력완료
        cnt = Integer.parseInt(br.readLine());
        h = Integer.parseInt(br.readLine());
}
