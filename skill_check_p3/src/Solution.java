import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

//초당 최대 처리량 1초당 처리한 개수
//처리시간 == 03:10:33.020 -0.011 +0.001= 2016-09-15 03:10:33.010
//"2016-09-15 01:00:04.001 - 2.0s  +0.001 = 2016-09-15 01:00:02.002",
class Solution {

    public int solution(String[] lines) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

        int[] counts = new int[lines.length];
        int max = 0;

        for(int i=0; i<lines.length; i++) {
            // 이전 로그의 완료 시점
            String[] pre = lines[i].split(" ");
            Date preEndDate = format.parse(pre[1]);
            long preEnd = preEndDate.getTime();

            // 해당 로그 보다 늦게 종료되는 로그 체크
            for (int j = i ; j < lines.length; j++) {
                // 다음 로그의 시작시점
                String[] next = lines[j].split(" ");
                Date nEnd = format.parse(next[1]);
                double sec = Double.parseDouble(next[2].substring(0, next[2].length()-1)); // 처리 시간

                long nStart = (long) (nEnd.getTime() - sec*1000 + 1);

                if(preEnd + 1000 > nStart) { //시작지점이 더 빠르면
                    counts[i] += 1;
                    max = Math.max(max, counts[i]);
                }
            }
        }
        return max;
    }

}