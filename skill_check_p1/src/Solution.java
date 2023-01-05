import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String[] solution(String[] files) {
        String[][] parse = split(files);
        sort(parse);
        String[] answer = merge(parse);
        return answer;
    }

    private String[] merge(String[][] parse) {
        String[] temp = new String[parse.length];
        for (int i = 0; i < parse.length; i++) {
            temp[i] = parse[i][2];
        }
        return temp;
    }

    private String[][] split(String[] files) {
        String[][] parse = new String[files.length][3];
        for (int i = 0; i < files.length; i++) {
            parse[i][1] = getNumber(files[i]);
            String temp[] = files[i].split(parse[i][1]);
            parse[i][0] = temp[0];
            parse[i][2] = files[i];
        }
        return parse;
    }
    private String getNumber(String file) {

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(file);

        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public String[][] sort(String[][] files) {

        Arrays.sort(files, Comparator.comparing((String[] o) -> Integer.parseInt(o[1])));
        Arrays.sort(files, Comparator.comparing((String[] o) -> o[0].toLowerCase()));

        return files;
    }

    public static void main(String[] args) {

//        String [][] files = {
//                {"img","12",".png"},
//                {"img","10",".png"},
//                {"img","02",".png"},
//                {"img","1",".PNG"},
//                {"IMG","01",".GIF"},
//                {"img","2",".JPG"}
//        };
//        String [][] files = {
//                {"F-","5", "Freedom Fighter"},
//                {"B-","50", "Superfortress"},
//                {"A-","10", "Thunderbolt II"},
//                {"F-","14" ,"Tomcat"}
//        };
        String files[] = {
              "imG2.png", "img2"
        };
        Solution s = new Solution();
        files = s.solution(files);
        for(int i=0; i<files.length; i++){
            System.out.println(files[i]);
        }

    }
}