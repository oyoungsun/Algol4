import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class node {
    int x;
    int y;
    public node(int x, int y){
        this.x=x;
        this.y=y;
    }
}
public class Main {
    static int map[][];
    static int w;
    static int h;
    static int dir[][] = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
    static int graph[][];
    static LinkedList<node> dust;
    static int count;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        w = sc.nextInt();
        h = sc.nextInt();
        while (w!=0 && h!=0) {
            count = Integer.MAX_VALUE;
            map = new int[h][w];
            int nx = 0;
            int ny = 0;
            dust = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                char temp[] = sc.next().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (temp[j] == '.') {
                        map[i][j] = 0;
                    } else if (temp[j] == 'o') {
                        map[i][j] = 0;
                        nx = i;
                        ny = j;
                        dust.add(0, new node(i, j)); //0번쨰에 넣어준다
                    } else if (temp[j] == '*') {
                        map[i][j] = 1;
                        dust.add(new node(i, j));
                    } else if (temp[j] == 'x') {
                        map[i][j] = 2;
                    }
                }
            } //입력
            graph = new int[dust.size()][dust.size()];

            for (int i = 0; i < dust.size(); i++) {
                for (int j = i+1; j < dust.size(); j++) {
                    int dist = bfs(dust.get(i), dust.get(j)); //로봇청소기 현재 위치
                    graph[i][j] = graph[j][i]= dist;
                    if (i==0 && dist == -1) {
                        count = -1;
                        break;
                    }
                }
                if(count==-1) break;
            }
            if(count!=-1){
                boolean [] visit = new boolean[dust.size()];
                find(0,0,0, visit);
            }
            System.out.println(count);
            w = sc.nextInt();
            h = sc.nextInt();
        }
    }

    private static void find(int idx, int cnt, int sum, boolean[] visit) {
        if(cnt == dust.size()-1){
            count = Math.min(count, sum);
            return;
        }
        for(int i=1; i<dust.size(); i++){
            if(visit[i]) continue;
            visit[i]=true;
            find(i, cnt+1, sum+graph[idx][i], visit);
            visit[i]=false;
        }
    }

    private static int bfs(node srt, node end) {
        int dist[][] = new int[h][w];
        Queue<node> q = new LinkedList<>();
        q.add(new node(srt.x,srt.y));

        while(!q.isEmpty()){
            node now = q.poll();
            if(now.x==end.x&&now.y==end.y) {
                return dist[now.x][now.y];
            }
            for(int i=0; i<4; i++) {
                int nextX = now.x+dir[i][0];
                int nextY = now.y+dir[i][1];
                if(nextX==h||nextY==w||nextX<0||nextY<0) continue;
                if (dist[nextX][nextY] != 0) continue;
                if (map[nextX][nextY] == 2) continue;
                dist[nextX][nextY] = dist[now.x][now.y] + 1;
                q.add(new node(nextX, nextY));
            }
        }
        return -1;
    }
}

