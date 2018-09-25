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

    public static void main(String[] args) {
        // Q1) n = 3a + 2b + c일 때 (a, b, c)의 경우의 수
        int q1 = q1(10);
        System.out.println("Q1: " + q1);
        q1 = q1(3);
        System.out.println(q1);
    }
}
