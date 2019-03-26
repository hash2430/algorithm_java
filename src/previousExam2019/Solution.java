package previousExam2019;

import java.util.HashMap;
class Solution {
    public static String[] solution(String[] record) {
        String[] answer = new String[record.length];
        // build map
        HashMap<String, String> map = new HashMap<String, String>();
        String line;
        String[] words;
        // process each line and put to map
        for (int i = 0; i < record.length; i++) {
            line = record[i];
            words = line.split(" ");
            if (words.length == 3)
                map.put(words[1], words[2]);
        }

        // Generate output
        for (int i = 0; i < record.length; i++) {
            String str = "";
            line = record[i];
            words = line.split(" ");
            if ((words[0].compareTo("Enter") == 0)) {
                str += map.get(words[1]);
                str += "님이 들어왔습니다.";
            } else if ((words[0].compareTo("Leave") == 0)){
                str += map.get(words[1]);
                str += "님이 나갔습니다.";
            }
            answer[i] = str;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] input = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] strs = solution(input);
    }
}