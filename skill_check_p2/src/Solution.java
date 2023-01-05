class Solution {
    public int solution(String name) {
        int count =0;
        int length = name.length();
        int index;
        int move = length -1; //모든 커서를 한칸씩 이동해야함.
        for(int i=0; i<name.length(); i++){
            count += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            index = i + 1;
            while(index < length && name.charAt(index) == 'A'){
                index++;
            }
            move = Math.min(move, i * 2 + length - index);
            move = Math.min(move, (length - index) * 2 + i);
        }
        int answer = count + move;
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int count = s.solution("JEROEN");
        System.out.println(count);
    }
}