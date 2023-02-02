import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

//6 6
//2 1
//4 4
//4 0
//4 4
//6 3
//2 3
//4 0
//1 1
//0 2

class point{
    int x;
    int y;
    int dist;
    point(int y, int x, int dist){
        this.y=y;
        this.x=x;
        this.dist = dist;
    }
}
public class Main { //두 직선이 서로 접하지 않는 최소 길이
    static int n, m;
    static int f_dst, s_dst;
    static point map [][];
    static int min= Integer.MAX_VALUE;
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        point p[] = new point[4];
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            p[i] = new point(y, x, 0);
        }
        //A1-A2 이어지는 거 먼저 그리고 b1-b2그리는 방법

        //test

        int ans1= bfs(p[0], p[1], p[2], p[3]);
        //반대로 해보고 min 구하기
        int ans2 = bfs(p[2], p[3], p[0], p[1]);
        if(ans1 == Integer.MAX_VALUE && ans2 == Integer.MAX_VALUE){
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(Math.min(ans1, ans2));
    }
//마지막엔 yx바꾸는수밖에...
    //a1에서 출발 후 a2도착. 다시a2에서 a1으로 가는 경로 만듦.
    // a1으로 다시 되돌아오면 b1출발. b2까지.
    private static int bfs(point a1, point a2, point b1, point b2) {
        //a1-a2 길 구하기
        f_dst=0;
        s_dst=0;
        map = new point[101][101];
        boolean visit[][] = new boolean [101][101];
        Queue<point> q = new LinkedList();
        q.add(a1);
        visit[a1.y][a1.x]=true;
        visit[b1.y][b1.x]=true;
        visit[b2.y][b2.x]=true; //시작점, 끝점 못닿게
        while(!q.isEmpty()){
            point now = q.poll();
            if(now.x==a2.x && now.y==a2.y){
                f_dst=now.dist;
                break;
            }
            for(int i=0; i<4; i++) {
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];
                int dist = now.dist+1;
                if (nx < 0 || ny < 0 || nx > n || ny > m) continue;
                if (visit[ny][nx]) continue;
                q.add(new point(ny,nx, dist));
                visit[ny][nx]=true;
                map[ny][nx]= now;//dir

            }
        }
//a1-a2까지 완료. 경로 찾기
        // visit 초기화
        visit = new boolean[101][101];

        point now = a2;
        visit[now.y][now.x]=true;
        while(true){
            visit[now.y][now.x] = true; //경로 체크하기
            if(now.x==a1.x && now.y==a1.y){
                break;
            }
            now =map[now.y][now.x];
        }

    //    map = new int[101][101];
        q = new LinkedList<>();
        q.add(b1);
        visit[b1.y][b1.x]=true;

        while(!q.isEmpty()){
            now = q.poll();
            if(now.x==b2.x && now.y==b2.y){
                s_dst=now.dist;
                break;
            }
            for(int i=0; i<4; i++) {
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];
                int dist = now.dist+1;
                if (nx < 0 || ny < 0 || nx > n || ny > m) continue;
                if (visit[ny][nx]) continue;
                q.add(new point(ny,nx, dist));
                visit[ny][nx]=true;
                map[ny][nx]= now; //dir

            }
        }
        if(s_dst==0) return Integer.MAX_VALUE; //연결 불가능.
        else return f_dst+s_dst;
    }
}
