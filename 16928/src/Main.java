import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int rope;
    static int snake;
    static int map[]= new int[101];
    static int visit[] = new int [101];
    //  static int dice[] = {1,2,3,4,5,6};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rope = Integer.parseInt(st.nextToken());
        snake = Integer.parseInt(st.nextToken());

        for (int i = 1; i < map.length; i++) {
            map[i] = i;
        }

        for(int i=0; i<rope; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            map[x] = dst;
        }
        for(int i=0; i<snake; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            map[x] = dst;
        }
        bfs(1);
        System.out.println(visit[100]);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visit[start]=0;
        q.add(start);

        while(true){
            int now = q.poll();
            for(int i=1; i<7; i++){
                int next = now+i;
//                if(map[now]!=0 && visit[map[now]]==0) {
//                    q.add(map[now]); //사다리, 뱀 타는 경우
//                    visit[map[now]] = visit[now];
//                }
                if(next>100){
                    continue;
                }
                //  if(next<1 || visit[next]!=0) continue; //이미 방문-> 무시
                if(visit[map[next]]==0) {
                    q.add(map[next]);
                    visit[map[next]] = visit[now] + 1;

                }
                if(map[next]==100) return;

            }
        }

    }
}