class Solution {

    int[][] mbti = new int[4][2];
    public String solution(String[] survey, int[] choices) {
        for(int i=0; i<survey.length; i++){
            int index = findType(survey[i]);
            if(index!=-1) {
                choice(survey[i], choices[i], index);
            }
        }
        String answer = print();
        return answer;
    }

    private String print() {
        StringBuilder sb = new StringBuilder();
        if(mbti[0][0]>=mbti[0][1]){
            sb.append("R");
        }else sb.append("T");
        if(mbti[1][0]>=mbti[1][1]){
            sb.append("C");
        }else sb.append("F");
        if(mbti[2][0]>=mbti[2][1]){
            sb.append("J");
        }else sb.append("M");
        if(mbti[3][0]>=mbti[3][1]){
            sb.append("A");
        }else sb.append("N");
        return sb.toString();
    }

    private int findType(String s) {
        if(s.contains("R")){
            return 0;
        }else if(s.contains("C")){
            return 1;
        }
        else if(s.contains("J")){
            return 2;
        }
        else if(s.contains("A")){
            return 3;
        }
        return -1;
    }

    private void choice(String s, int choice, int index) {
        char[] type = s.toCharArray();
        int dir;
        if(type[0]<type[1])dir=0; //정방향 AN
        else dir=1; //반대 NA
        if(choice == 4){
            return;
        }
        else if(choice<4){ //비동의일때 s첫번째에 +1 정방향이면 0에 +1
            mbti[index][dir] += 4 - choice;
        }
        else if(choice>4){ // 동의일때 두번째에 +1 정방향이면 1에 +1
            mbti[index][Math.abs(1-dir)] += choice - 4;
        }
        return;
    }

}
class Main{
    public static void main(String[] args) {
        Solution s = new Solution();
        String [] temp = {"AN", "CF", "MJ", "RT", "NA"};
        int [] choices = {5, 3, 2, 7, 5};
        s.solution(temp, choices);
    }
}