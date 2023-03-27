import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> min = new PriorityQueue<>((o1, o2) -> o1 - o2);
        PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for(int i=0; i<n; i++){
            int node = Integer.parseInt(br.readLine());
            if(min.size()==max.size()) max.offer(node);
            else min.offer(node);

            if(!min.isEmpty() && !max.isEmpty()) {
                if (min.peek() < max.peek()) {
                    int temp = min.poll();
                    min.offer(max.poll());
                    max.offer(temp);
                }
            }

            sb.append(max.peek()).append("\n");
        }
        System.out.println(sb);
    }

}
