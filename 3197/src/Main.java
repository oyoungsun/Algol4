import javax.management.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class node {
    int x;
    int y;
    node(int x, int y){
        this.x=x;
        this.y=y;
    }
}
public class Main
{
    static int n;
    static int m;
    static int map [][];
    static int visit [][];

    static int [][] swan = new int [2][2];
    static int dir[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    static Queue<node> water = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];


        int cnt=0;
        for(int i=0; i<n; i++){
            String temp = br.readLine();
            for(int j=0; j<m; j++){
                if(temp.charAt(j)=='.'){
                    map[i][j]=0;
                    water.add(new node(i,j));
                }
                else if(temp.charAt(j)=='L'){
                    map[i][j]=0;
                    swan[cnt][0]=i;
                    swan[cnt][1]=j;
                    cnt++;
                }
                else if(temp.charAt(j)=='X'){
                    map[i][j]=1;
                }
            }
        }//입력완료
        int day=0;
        while(!canMeet()){
            watering();
            day++;
        }
        System.out.println(day);
    }

    private static void watering() {
        Queue<node> temp = new LinkedList<>();
        while(!water.isEmpty()){
            node now = water.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];
                if (nx == n || ny == m || nx < 0 || ny < 0) continue;
                if (map[nx][ny] == 1) {
                    //얼음인 경우
                    map[nx][ny] = 0;//녹인다.
                    temp.add(new node(nx, ny));
                }
            }
        }
        water = temp; //temp가 새로운 물 포인트가 된다.

    }

    private static boolean canMeet() {
        //start = swan[0], end = swan[1]
        Queue<node> q = new LinkedList<>();
        q.add(new node(swan[0][0],swan[0][1]));
        boolean visit[][] = new boolean[n][m];
        visit[swan[0][0]][swan[0][1]] = true;
        while(!q.isEmpty()) {
            node now = q.poll();
            if(now.x==swan[1][0] && now.y==swan[1][1]) return true;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];
                if (nx == n || ny == m || nx < 0 || ny < 0) continue;
                if(visit[nx][ny]) continue;
                if (map[nx][ny] != 0) continue;
                //물인 경우에 이동 가능
                visit[nx][ny] = true;
                q.add(new node(nx, ny));
            }
        }
        return false;
    }
}
