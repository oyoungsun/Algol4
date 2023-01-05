
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    //10 1 10 2 1  F S G U D
    //최대높이, 출발, 도착, 업단위, 다운 단위
    static int min = Integer.MAX_VALUE;
    static int visit[];
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int F = sc.nextInt();
        int S = sc.nextInt();
        int G = sc.nextInt();
        int U = sc.nextInt();
        int D = sc.nextInt();
        visit = new int [F+1];
        bfs(F, S, G, U, D);


    }

    private static void bfs(int f, int s, int g, int u, int d) {
        int cnt=0;
        Queue<Integer> q = new LinkedList();
        q.add(s);
        visit[s]=1;
        while(!q.isEmpty()){
            int now = q.poll();
            if(now==g){
                System.out.println(visit[now]-1);
                return
            }
            if(now+u<=f){
                if(visit[now+u]==0) {
                    visit[now+u] = visit[now]+1;
                    q.add(now + u);
                }
            }
            if(now-d>0){
                if(visit[now-d]==0) {
                    visit[now - d] = visit[now] + 1;
                    q.add(now - d);
                }
            }

        }
        System.out.println("use the stairs");
    }
}
