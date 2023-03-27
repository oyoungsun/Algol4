import org.w3c.dom.Node;

import java.util.*;

class node{
    int end;
    int money;
    int time;
    public node(int end, int money, int time){
        this.end = end;
        this.money = money;
        this.time = time;
    }
}
public class Main {

    static int N;
    static int M;
    static int K;
    static int dist[];
    static int visit[];
    static int dp[][];
    static LinkedList<node> srt[];
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        //출발 노드 설정
        //출발 노드 기준으로 각 노드 최소 비용 저장 dist[];
        //방문하지 않는 노드 중 가장 비용이 적은 노드 선택
        //해당 노드를 거쳐서 특정한 노드로 가는 경우를 고려해 최소비용 갱신
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for (int t = 0; t < testcase; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();
            srt = new LinkedList[N + 1];
            dp = new int[N + 1][M + 1];

            for (int i = 1; i <= N; i++) {
                srt[i] = new LinkedList<>();
                Arrays.fill(dp[i], INF);
            }
            for (int i = 0; i < K; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                int money = sc.nextInt();
                int time = sc.nextInt();
                srt[start].add(new node(end, money, time));
            }
            dijkstra(1);
            int min = INF;
            for (int i = 0; i <= M; i++) {
                min = Math.min(min, dp[N][i]); // 뉴욕 도착하는 최소 시간
            }
            if (min == INF) System.out.println("Poor KCM");
            else System.out.println(min);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<node> q = new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return o1.time - o2.time;
            }
        });
        q.add(new node(start, 0, 0));
        dp[start][0] = 0;

        while(!q.isEmpty()) {
            node now = q.poll();
            if (now.end == N) break;
            for (node next : srt[now.end]) {
                int nM = next.money + now.money;
                int nT = next.time + now.time;
                if (nM > M) continue;
                if (dp[next.end][nM] <= nT) continue; //

                for (int j = nM; j<=M; j++){ //해당노드로 특정 노드에 가는 경우
                    if(dp[next.end][j] > nT){
                        dp[next.end][j] = nT; //최소 갱신
                    }
                }
                dp[next.end][nM] = nT;
                q.add(new node(next.end, nM, nT));
            }
        }
    }
}
