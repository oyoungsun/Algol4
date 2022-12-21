class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(String str : s.split(" ")){
            int temp = Integer.parseInt(str);
            min = Math.min(min, temp);
            max = Math.max(max, temp);
        }
        sb.append(min); sb.append(" "); sb.append(max);
        String answer = sb.toString();

        return answer;
    }
}

class Main{
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution("1 2 3 4");
    }
}