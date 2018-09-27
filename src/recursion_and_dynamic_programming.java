import java.util.ArrayList;
import java.util.HashSet;

public class recursion_and_dynamic_programming {
    // 진짜 이쁘다...
    public static int q1(int n) {
        int aMax = n/3;
        int bMax;
        int cnt = 0;
        for (int a = aMax; a >= 0; a--) {
            bMax = (n - 3*a)/2;
            for (int b = bMax; b >= 0; b--) {
                cnt++;
            }
        }
        return cnt;
    }
    // 재귀는 더럽다
//    public static HashSet<HashSet<Integer>> q4(HashSet<Integer> set) {
//        HashSet<HashSet<Integer>> subset = new HashSet<>();
//        recursiveSubsetFind(subset, set);
//        HashSet<Integer> zeroSet = new HashSet<Integer>();
//        subset.add(zeroSet);
//        return subset;
//    }
    // java.util.ConcurrentModificationException
    // while에 걸린 iterator가 newSet을 참조하고 있는데 while 안에서 newSet의 수정이 일어나기 때문에 발생한다.
    // Exception의 이름을 보고서 thread 프로그래밍도 안했는데 무슨 일인가, Iterator는 쓰레드를 내부적으로 구현하나 이런 생각했는데
    // Concurrency의 개념이 접근 순서에 의해 다르게 해석될 수 있는 문제 상황을 모두 포함하는 넓은 개념인 것 같다.
//    public static void recursiveSubsetFind(HashSet<HashSet<Integer>> subset, HashSet<Integer> set) {
//        HashSet<Integer> newSet = (HashSet<Integer>) set.clone();
//        Iterator<Integer> iter = newSet.iterator();
//        subset.add(set);
//        while (iter.hasNext()) {
//            newSet.remove(iter.next());
//            if (newSet.size() == 0) break;
//            recursiveSubsetFind(subset, newSet);
//        }
//    }
    // Integer.toBinaryString()으로 하지 않고 boolean array로 해야 되는 이유는 전자가 리턴하는 바이너리의 자리 수는 최소화되기 때문에 이를
    // set.size 길이로 바꾸는 게 귀찮음. 귀찮은 것보다는 까먹을까봐 걱정.
    // 자바, 파이썬에서 ^는 power의 operator가 아님. Math.pow() 사용
    public static HashSet<HashSet<Integer>> q4(HashSet<Integer> set) {
        int size = set.size();
        HashSet<HashSet<Integer>> subset = new HashSet<>();
        Object[] arr = set.toArray();
        size = (int) Math.pow(2, size);
        for (int i = 0; i < size; i++) {
            HashSet<Integer> set1 = new HashSet<>();
            String str = Integer.toBinaryString(i);
            while (str.length() < set.size()) {
                str = "0" + str;
            }
            // str.length == set.size
            for ( int j = 0; j < str.length(); j++) {

                if (str.charAt(j) == '1') {
                    set1.add((Integer)arr[j]);
                }
            }
            subset.add(set1);
        }
        return subset;
    }
    // String.charAt과 binaryString은 order가 반대인 것에 유의
    public static int q5(int a, int b) {
        int aIsPowerOf2 = a&(a-1);
        int bIsPowerOf2 = b&(b-1);
        int n = -1;
        if (aIsPowerOf2 == 0) {
            String strA = Integer.toBinaryString(a);
            for (int i = 0; i < strA.length() ; i--) {
                if (strA.charAt(i) == '1') {
                    n = strA.length() - 1 - i;
                    break;
                }
            }
           return b << n;
        } else if (bIsPowerOf2 == 0) {
            String strB = Integer.toBinaryString(b);
            for (int i =0; i <strB.length(); i++) {
                if (strB.charAt(i) == '1') {
                    n = strB.length() -1 -i;
                    break;
                }
            }
            return a << n;
        } else {
            for (int i = 1; i < b; i++) {
                a += a;
            }
        }
        return a;
    }

    public static ArrayList<String> q7(String str) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> sublist=new ArrayList<>();;
        ArrayList<Character> set = new ArrayList<>();
        if (str.length() == 2) {
            list.add(str);
            char[] charArr = new char[]{str.charAt(0), str.charAt(1)};
            list.add(new String(charArr));
            return list;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            set = new ArrayList<>();
            for (int j = 0; j < str.length(); j++) {
                set.add(str.charAt(j));
            }
            set.remove(i);
            Object[] arr = set.toArray();
            char[] newChar = new char[arr.length];
            for (int j = 0; j < arr.length; j++) {
                newChar[j] = (char)arr[j];
            }
            String newStr = new String(newChar);
            sublist = q7(newStr);
            String prefix = new String(new char[]{c});
            for (int j = 0; j < sublist.size(); j++) {
                sublist.set(j,prefix+sublist.get(j));
            }
        }
        for (int i = 0; i < sublist.size(); i++) {
            list.add(sublist.get(i));
        }
        return list;
    }
    // 하향식 재귀 풀이
    public static int fibonacci1(int a) {
        if (a == 0) return 0;
        if (a == 1) return 1;
        return fibonacci1(a - 1) + fibonacci1(a - 2);
    }
    // 하향식 메모이제이션 풀이
    public static int fibonacci2(int a) {
        int[] arr = new int[a + 1];
        return fibonacci2_(a, arr);
    }

    public static int fibonacci2_(int a, int[] arr) {
        if (a == 0 || a == 1) return a;
        if (arr[a] == 0) {
            arr[a] = fibonacci2_(a - 1, arr) + fibonacci2_(a - 2, arr);
        } else {
            return arr[a];
        }
        return arr[a];
    }

    // 상향식 iterative 동적 프로그래밍 with cache
    public static int fibonacci3(int a) {
        if (a == 0 || a == 1) {
            return a;
        }
        int[] arr = new int[a + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= a; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[a];
    }

    // 상향식 iterative 동적 프로그래밍 without cache
    public static int fibonacci4(int a) {
        if (a == 0 || a == 1) {
            return a;
        }
        int x = 0;
        int y = 1;
        int z = 0;
        for (int i = 0; i < a - 1; i++) {
            z = y + x;
            x = y;
            y = z;
        }
        return z;
    }
    public static void main(String[] args) {
        // fibonacci
        System.out.println("fibonacci:");
        System.out.println(fibonacci1(5));
        System.out.println(fibonacci2(5));
        System.out.println(fibonacci3(5));
        System.out.println(fibonacci4(5));
        // Q1) n = 3a + 2b + c일 때 (a, b, c)의 경우의 수
        int q1 = q1(10);
        System.out.println("Q1: " + q1);
        q1 = q1(3);
        System.out.println(q1);

        // Q4) 모든 부분집합 리턴하기
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(5);
        HashSet<HashSet<Integer>> subsets = q4(set);

        // Q5: * 연산자를 쓰지 않고 곱하기
        int q5 = q5(5,4);
        System.out.println(q5);

        // Q7: 중복없는 순열
        String str = "ABCD";
        ArrayList<String> list = q7(str);
    }
}
