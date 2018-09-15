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


    }
}
