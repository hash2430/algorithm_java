package previousExam2019;

import java.util.ArrayList;
import java.util.HashMap;

// 2019 공채 3번문제
public class Solution3 {
    public static int solution(String[][] relation) {
        int answer = 0;
        int stoppingCondition = ((relation[0].length%2)==0)?relation[0].length/2:(relation[0].length + 1) / 2;
        ArrayList<String> keySet = new ArrayList<>();

        int keySetLength =(int)Math.pow(2, relation[0].length);
        for (int i = 0; i < keySetLength; i++) {
            String str = Integer.toBinaryString(i);
            while (str.length() < relation[0].length) {
                str = "0" + str;
            }
            keySet.add(str);
        }

        // ArratList를 바꿨는데 왜 자동적으로 안바뀌어서 리턴값으로 수정하게 해야되는지 모르겠고
        // for문에 keySet size가 걸려있는데 그 안에서 keySet에 대한 수정이 어째서 가능한건지 모르겠다.
        for (int i = 0; i < stoppingCondition; i++) {
            int numTrue = i + 1;
            for (int j = 0; j < keySet.size(); j++) {
                String key = keySet.get(j);
                if (count1s(key) == numTrue) {
                    boolean isCandidate = isCandidate(key, relation);
                    if (isCandidate) {
                        keySet = removeThisKeyAndSuperSet(key, keySet);
                        answer++;
                    }
                }
            }

        }
        return answer;
    }

    public static int count1s(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                cnt++;
            }
        }
        return cnt;
    }

    public static boolean isCandidate(String key, String[][] relation) {
        // key의 true bit번째 column이 concatenate 된 String을 relation의 모든 row에 대해 만들어 그것을 set에 넣을 때 유일하면 true를 리턴한다
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < relation.length; i++) {
            String rowVal = "";
            for (int j = 0; j < key.length(); j++) {
                if (key.charAt(j) == '1') {
                    rowVal += relation[i][j];
                }
            }
            if (map.get(rowVal) == null) {
                map.put(rowVal, "");
            } else {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<String> removeThisKeyAndSuperSet(String key, ArrayList<String> keySet) {
        ArrayList<Integer> trueBitLocation = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == '1') {
                trueBitLocation.add(i);
            }
        }
        ArrayList<Integer> removeIndex = new ArrayList<>();
        for (int i = 0; i < keySet.size(); i++) {
            boolean remove = true;
            for (int j = 0; j < trueBitLocation.size(); j++) {
                int location = trueBitLocation.get(j);
                if (keySet.get(i).charAt(location) != '1') {
                    remove = remove && false;
                }
            }
            if (remove)
                removeIndex.add(i);
        }

        ArrayList<String> newKeyset = new ArrayList<>();
        for (int i = 0; i <keySet.size(); i++) {
            if (!removeIndex.contains(i)) {
                newKeyset.add(keySet.get(i));
            }
        }
        return newKeyset;
    }

    public static void main(String[] args) {
        String [][] relation ={{"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}};
        int answer = solution(relation);
        System.out.println(answer);
    }
}