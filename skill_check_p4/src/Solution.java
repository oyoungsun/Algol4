import java.util.*;


// 1. 기호 위치 파악
// 2. 기호의 규칙 종류, 시작, 끝 인덱스
// 3. 기호를 제외한 단어를 이어붙이고 공백을 추가한다.
// 4. 이어붙인 문자열을 후행 공백 제거 후 리턴
class Pattern {
    int rule, start, end;
    public Pattern(int rule, int start, int end){
        this.rule = rule;
        this.start = start;
        this.end = end;
    }
}
class Solution {
    LinkedHashMap<Character, List<Integer>> symbol = new LinkedHashMap<>();
    int len;
    int sstr=0;
    char [] temp;
    public String solution(String sentence) {
        len = sentence.length();
        //기호의 위치파악
        findIndex(sentence);

        //기호의 규칙 찾기
        List<Pattern> patterns = findPattern(sentence);
        StringBuilder sb = new StringBuilder();
        int size = patterns.size(); Pattern pattern;
        for(int i=0; i<size; ++i){
            pattern = patterns.get(i);
            int rule = pattern.rule;
            if(rule==-1) return "invalid";
            int start = pattern.start;
            int end = pattern.end;
            if(rule==0){
                if((sstr<=start -1)&& (end+1<(i<size-1 ? patterns.get(i+1).start:len))){
                    start--;
                    end++;
                }
            }
            if (sstr<start) {
                sb.append(getStr(sstr, start-1));
            }
            sb.append(getStr(start, end));
            sstr = end+1;
            }
        if(sstr<len){
            sb.append(getStr(sstr, len-1));
        }
        return sb.toString().trim();
    }

    private String getStr(int s, int e) {
        StringBuilder sb = new StringBuilder();
        for(int i=s; i<=e; ++i){
            char c = temp[i];
            if(c>='A'&&c<='Z') sb.append(c);
        }
        return sb.toString()+" ";
    }

    private List<Pattern> findPattern(String sentence) {
        List<Pattern> patterns = new ArrayList<>();
        int rule = -1, schar, echar, sword = 0, eword = 0;
        int pres=-1, pree=-1, preworde=-1;
        boolean rule1 = true;
        for (List<Integer> pos : symbol.values()) {
            //한 패턴 인덱스 모두 나열되어있음.
            schar = sword = pos.get(0);
            echar = eword = pos.get(pos.size() - 1);
            for (int i = 1; i < pos.size(); i++) { //기호가 2씩 차이남 -> 1
                int distance = pos.get(i) - pos.get(i - 1);
                if (distance < 2) rule = -1; //invalid
                if (distance > 2) { // 규칙1은 아님.
                    rule1 = false;
                    break;
                }
            }
            if (pos.size() >= 3 && !rule1) rule = -1;
            if (pos.size() == 1 || pos.size() >= 3) {
                rule = 1;
                sword--;
                eword++;
                if (sword < 0 || len <= eword) rule = -1;
            }
            if (pos.size() == 2) {
                if (rule1) rule = 0; //둘 다 가능한 경우
                else rule = 2;
            }
            if (pres < schar && echar < pree) {
                if (rule == 2) rule = -1;
                continue;
            }
            if (preworde >= sword) rule = -1;
            patterns.add(new Pattern(rule, sword, eword));
            pres = schar;
            pree = echar;
            preworde = eword;
        }
        return patterns;
    }

    private void findIndex(String sentence) {
        //기호를 찾으면 key에는 소문자, value에 위치를 추가한다.
        temp = sentence.toCharArray();
        for(int i=0; i<sentence.length(); i++){
            char c = temp[i];
            if(isLower(c)){
                if(!symbol.containsKey(c))
                    symbol.put(c, new ArrayList<>());
                symbol.get(c).add(i);
            }
        }
    }

    private boolean isLower(char c) {
        if(c>='a' && c<='z') return true;
        return false;
    }
}