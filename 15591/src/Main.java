import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//후기 :
//bfs를 응용한 문제였지만 쉽지는 않았던 문제.

//시간초과문제떄문에 모든 경우를 구하고 k를 판단하는 것 보다
//처음부터 k를 만족하지 않는 모든 경로를 탐색하지 않는 방향이 되어야한다.


// 언뜻 읽으면 최소 유사도를 구하는 문제 같지만
// 하나의 정점에서 다른 모든 정점에 대해 갈수있는지를 판단하는 문제였음.

//문제 : 두개의 동영상의 유사도
// N-1개의 동영상 쌍 (단방향그래프
//경로는 반드시 하나있음. 단방향 사이클 그래프
//한 점에서 다른점으로 가는  그 경로의 모든 연결들의 USADO 중 최솟값
//소들에게 몇 개의 동영상이 추천될 지, 몇 개의 노드를 거쳐가는가?
class Node{
    int n;
    long dist;
    public Node(int n, long dist){
        this.n = n;
        this.dist = dist;
    }
}
public class Main {
    static LinkedList<Node>[] grape;
    static int n;
    static int q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        grape = new LinkedList[n+1];
        boolean visit[][] = new boolean[n+1][n+1];
        for(int i=1; i<=n; i++){
            grape[i] = new LinkedList();
            grape[i].add(new Node(i, Long.MAX_VALUE));
            visit[i][i]=true;
        }
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            long r = Integer.parseInt(st.nextToken());
            grape[x1].add(new Node(x2, r));
            grape[x2].add(new Node(x1, r));
            visit[x1][x2] = visit[x2][x1]=true;
        }
        //모든 노드 유사도 출력
//        for(int i=1; i<=n; i++){
//            for(int j=1; j<=n; j++){
//                if(!visit[i][j]) {
//                    visit[i][j] = visit[j][i] = true;
//                    long dist = USADO(i, j, k);
//                    if (dist == -1) continue;
//                    grape[i].add(new Node(j, dist));
//                    grape[j].add(new Node(i, dist));
//                }
//            }
//        }

//        for(int i=1; i<=n; i++){
//            for(Node node : grape[i]){
//                System.out.print(node.n+" "+node.dist);
//                System.out.print("   ");
//            }
//            System.out.println();
//        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int result = question(k, x);
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
    //유사도 찾기
    public static int USADO(int i, int k){
        boolean visit[] =new boolean[n+1];
        PriorityQueue<Integer> queue = new PriorityQueue<>(); //now, dist
        queue.offer(i);
        visit[i]=true;
        int count=0;
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(Node node : grape[now]){ //2에서 갈수있는 1, 3, 4
                if(node.dist>=k && !visit[node.n]){
                    queue.offer(node.n);
                    visit[node.n] = true;
                    count++; //ㅜㅜ
                } //1, k==3
                //1->2 1->3 1->4 생각해보면
                // 1에서 queue에 2들어감. visit2, 2에서 3X 4들어감. visit4
                // 그럼 답 나왔음_> 종료
            }
        }
        return count;
    }
    public static int question(int k, int x){
//        for(int i=1; i<=n; i++){
//            if(i==x) continue;
            int count = USADO(x, k);
           // if(result!=-1 && result<=1000000000) count++;
        //}
        return count;
    }
}
