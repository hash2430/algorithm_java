import java.util.HashSet;
import java.util.Iterator;

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
    public static void main(String[] args) {
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
    }
}
