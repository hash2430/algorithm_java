import java.util.*;

public class array_and_string {
    // 문자열에 같은 문자가 중복되어 등장하는지 확인하는 알고리즘
    // 나는 128글자의 ascii 문자열을 가정함.
    public static boolean q01(String input) {
        boolean result = false;
        int strLen = input.length();
        if (strLen > 128) {
            return true;
        }
        HashMap<Character, Integer> map = new HashMap();
        int charNum = 0;

        for (int i = 0; i < strLen; i++) {
            if (map.get(input.charAt(i)) == null) {
                charNum = 1;
                map.put(input.charAt(i), new Integer(charNum));
            }

            else
                return true;
        }
        return result;
    }

    // 순서만 바꿔서 a와 b를 동일하게 만들 수 있으면 true, 없으면 false
    // Generic을 쓰면 너무 복잡해진다. 최대한 []를 쓰는 구현으로 해보자.
    // Comparator까지 구현해야한다니 투머치 아닌가! 실전에선 정말 패닉할 듯.
    // 언어상 구현된 정렬을 이용할 수 있으면 문자열 유형 문제를 풀기가 쉬워진다.
    // 근데 또 막상 보니 char[] 쓸려면 sort까지 직접 구현해야되네.
    // Generic이 나은듯.
    public static boolean q02(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        ArrayList<Character> arr1 = new ArrayList<>();
        ArrayList<Character> arr2 = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            arr1.add(new Character(a.charAt(i)));
            arr2.add(new Character(b.charAt(i)));
        }
        Comparator<Character> comparator = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1.compareTo(o2);
            }
        };
        arr1.sort(comparator);
        arr2.sort(comparator);
        for (int i = 0; i < a.length(); i++) {
            if (arr1.get(i).compareTo(arr2.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }
//// Generic 안 쓰고 char[]로 쉽게 가보려고 했는데 여긴 정렬함수도 없다.
    // FIXME: 주의사항!! char to String을 할 때 toString을 쓰면 안되고 String의 생성자에 char[]를 줘야한다.
    // FIXME: toString을 쓸 때는 정말 주의하자.
    public static boolean q02_ver2(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        sort(charA);
        sort(charB);
//        String strA = charA.toString();
//        String strB = charB.toString();
        String strA = new String(charA);
        String strB = new String(charB);
        int compResult = strA.compareTo(strB);
        if (compResult != 0) {
            return false;
        }
        return true;
    }

    public static void sort(char[] a) {
        char tmp = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length - i; j++) {
                if (a[j - 1] > a[j]) {
                    tmp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
    // 공백을 '%20'으로 치환하라.
    // TODO: 자바에서 list로 가변길이 자료형을 만들 수 없고 generic으로만 할 수 있는 것 같다.
    // TODO: 그리고 필요한 새 String의 크기를 구하는 것과 새 String을 실제로 채우는 작업에서 str의 iteration이 2번 반복되는 것이 불가결하다.
    // TODO: 그래서 문제에서 "최종적으로 모든 문자를 담을 수 있을 만큼 충분한 공간이 이미 확보됐다"라고 한 것.
    // TODO: 즉 newStr을 충분히 큰 고정길이의 char[]로 선언하고 시작하라는 뜻이었다.
    public static String q03(String a) {
        char[] str = a.toCharArray();
        char[] newStr = new char[2048];
//        int[] pos = new int[10];
//        int num = 0;
//        for (int i = 0; i < str.length; i++) {
//            if (str[i] == ' ') {
//                pos[num] = i;
//                num++;
//            }
//        }
//        char[] newStr = new char[str.length + 2 * num];
//        for (int i =0; i < pos.length; i++) {
//            pos[i] += 2*i;
//        }
        int occur = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                newStr[i + occur * 2] = '%';
                newStr[i + 1 + occur * 2] = '2';
                newStr[i + 2 + occur * 2] = '0';
                occur += 1;
            } else {
                newStr[i + occur * 2] = str[i];
            }
        }
        return new String(newStr);
    }

    // Q4: 회문의 순열인지 여부
    public static boolean q04(String str) {
        int strLen = str.length();
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> oddMap = new HashMap<>();
        Character character;
        ArrayList<Character> charList = new ArrayList<>();
        char c;
        int n;
        for (int i = 0; i < strLen; i++) {
            c = str.charAt(i);
            if (map.get(c) == null) {
                map.put(new Character(c), 1);
            } else {
                n = map.get(c);
                map.put(new Character(c), n + 1);
            }
        }
        Set<Character> charSet = map.keySet();
        Iterator iter = charSet.iterator();
        while (iter.hasNext()) {
            character = (Character) iter.next();
            if (map.get(character) % 2 == 0) {
                return true;
            } else {
                if (strLen % 2 == 0) {
                    return false;
                } else {
                    charList.add(character);
                }
            }
        }

        if (charList.size() == 1) {
            return true;
        }
        return false;
    }
    // Q5. insert, delete, replace 한 번의 operation만으로 치환 가능한 관계인지.
    // TODO: 로직을 종이에 정성껏 새웠고 자신이 있었고! 그대로 코딩했는데 예상한 결과가 안나오면 로직을 다시볼 게 아니라 디버깅을 해라
    // TODO: 막다른 골목에 다다르면 좌절하거나 새로 시작하지 말고 예제로 돌아가라. 그리고 하던 것 마저해라.
    // TODO: 문자열이 같은 지 알아볼 때 정렬은 하지마라.
    public static boolean q05(String s1, String s2) {
        if (s1.length() < s2.length()) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        } else if (s1.length() == s2.length()) {
            //위치별로 비교해서 다른것이 1개면 true
            int diff = 0;
            for (int i =0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff++;
                }
            }
            if (diff == 1) {
                return true;
            } else {
                return false;
            }
        }

        // s1 > s2
        int unseen = -1;
        if (s1.length() - s2.length() == 1) {
            // unseen character의 index를 알아낸다.
            char[] newStr = new char[s2.length()];
            for(int i = 0; i < s1.length(); i++) {
               int x = -1;
               for (int j =0; j < s2.length(); j++) {
                   if (s1.charAt(i)==s2.charAt(j)) {
                       x=j;
                   }
               }
               if (x==-1) {
                    unseen = i;
                    break;
               }
            }
            if (unseen == -1) {
                return false;
            } else {
                // unseen character를 빼고 새로 구성한 newStr가 s2와 같은지 알아본다.
                for (int i = 0; i < unseen; i++) {
                    newStr[i] = s1.charAt(i);
                }
                for (int i = 0; i < s1.length()-unseen-1; i++) {
                    newStr[unseen+i] = s1.charAt(unseen+1+i);
                }
                if (new String(newStr).compareTo(s2) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Q6: 문자열 압축. 만약 압축된 문자열이 더 길다면 원본 문자열을 리턴하라.

    // TODO: 내가 진짜 왠만하면 자바로 시험보려고 했는데 int와 char를 번갈아 표현하는 list를 만들기가 어렵다.
    // Character.forDigit(int, radix)를 쓸 때 10 이하로만 반복된다고 가정하고 썼다.
    // TODO: 10 이하의 숫자는 48을 더해서 char로 캐스팅하고 10 이상의 숫자는 48을 빼서 char로 캐스팅?
    // Generic으로 변환하는 것은 필요없었는데 generic가면 int to char api가 있을까하여 불필요하게 전환
    public static String q06(String str) {
        char[] chars = str.toCharArray();
        char c;
        int cnt = 1;
        ArrayList<Character> newChar = new ArrayList<>();
        c = chars[0];
        for (int i =1; i < str.length(); i++) {
            if (chars[i] == c) {
                cnt++;
            } else {
                newChar.add(c);
                //newChar.add(new Character((char)(cnt+48)));
                newChar.add(Character.forDigit(cnt, 10));
                c = chars[i];
                cnt = 1;
            }
        }
        newChar.add(c);
        newChar.add(new Character((char)(cnt+48)));
        if (newChar.size() > str.length()) {
            return str;
        }
        char[] newChars = new char[newChar.size()];
        for (int i = 0; i < newChar.size(); i++)
            newChars[i] = newChar.get(i).charValue();
        return new String(newChars);
    }
    //Q6을 string concat operator써서 구현한 버전. 훨씬 간단하다.
    // String append시에 +를 쓰지 말자는 강박을 극복하면 훨신 쉽게 짤 수 있다.
    public static String q06_ver2(String str) {
        char[] chars = str.toCharArray();
        char c;
        int cnt = 1;
        String newStr = "";
        c = chars[0];
        for (int i =1; i < str.length(); i++) {
            if (chars[i] == c) {
                cnt++;
            } else {
                newStr += c;
                newStr += (char)(cnt+48);
                c = chars[i];
                cnt = 1;
            }
        }
        newStr += c;
        newStr += (char)(cnt+48);
        if (newStr.length() > str.length()) {
            return str;
        }
        return newStr;
    }

    // Q7: NxN 행렬에 대해 row와 col을 pivot.
    public static int[][] q07(int[][] pix) {
        int[][] rPix = new int[pix.length][pix[0].length];
        for (int i=0; i<pix.length;i++) {
            for (int j =0; j<pix.length; j++) {
                //rPix[i][j] = pix[pix.length-1-j][i];
                rPix[pix.length-1-j][i]=pix[i][j];
            }
        }

        return rPix;
    }
    public static void printMat(int[][] pixels) {
        for (int i=0;i<pixels.length;i++){
            for (int j = 0; j < pixels[0].length;j++) {
                System.out.print(pixels[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
    // Q8) 0이 포함된 row와 col은 모두 0으로 만들어라
    // TODO: boolean의 초기값은 false이고 int의 초기값은 0인 것을 기억하자.
    public static int[][] q08(int[][] mat) {
        boolean[][] map = new boolean[mat.length][mat[0].length];
        int[][] newMat = new int[mat.length][mat[0].length];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if (mat[i][j]!=0){
                    map[i][j] = true;
                }
            }
        }

        for (int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                newMat[i][j]=mat[i][j]*mult(mat, map, i, j);
            }
        }
        return newMat;
    }
    public static int mult(int[][] mat, boolean[][] map, int r, int c) {
        int mul=1;
        for(int i=0;i<mat.length;i++){
            mul*=bool2int(map[i][c]);
        }
        for(int j=0;j<mat[0].length;j++){
            mul*=bool2int(map[r][j]);
        }
        return mul;
    }

    public static int bool2int(boolean bool) {
        return bool?1:0;
    }


    public static void main(String[] args) {
        //Q1
        boolean q1Result = q01("orange");
        System.out.println("Q1: "+q1Result);

        //Q2
        boolean q2Result = q02_ver2("tocao", "octaa");
        System.out.println("Q2: " + q2Result);

        //Q3
        String str03 = q03("wow right now we are going to paris");
        System.out.println("Q3: " + str03);

        // Q4
        String str04_01 = "hiccih";
        String str04_02 = "hicocih";
        String str04_03 = "hiocih";
        System.out.println("Q4: " + q04(str04_01) + ", " + q04(str04_02) + ", " + q04(str04_03));

        // Q5
        String str0501 = "card";
        String str0502 = "clard";
        String str0503 = "ard";
        String str0504 = "cord";
        String str0505 = "code";
        System.out.println("Q5: " + q05(str0501, str0502) + ", "
                + q05(str0501, str0503) + ", " + q05(str0501, str0504)
                + ", " + q05(str0501, str0505));

        // Q6
        String str0601 = "aaabbcdddd";
        String str0602 = "abcd";
        System.out.println("Q6: " + q06_ver2(str0601) + ", " +
        q06(str0602));

        // Q7
        int[][] pixels = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] rotatedPixels = q07(pixels);
        System.out.println("Q7:");
        printMat(pixels);
        System.out.println("");
        printMat(rotatedPixels);

        // Q8
        int[][] mat = {{0,1},{1,1},{1,1},{1,1}};
        int[][] resultMat = q08(mat);
        System.out.println("Q8:");
        printMat(mat);
        System.out.println();
        printMat(resultMat);

    }
}
