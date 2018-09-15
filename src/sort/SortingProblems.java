package sort;

public class SortingProblems {
    /*
     * Q1) 정렬된 배열 A, B
     * A의 끝에는 B를 전부 넣을 수 있을 만큼 충분한 여유 공간이 있다.
     * A,B를 정렬되게 병합하는 메서드를 작성하라
     * A) sort.MergeSort 응용
     */
    public static int[] q1(int[] arr1, int[] arr2) {
        /* copy arr1 and arr2 */
        int helperLeft = 0;
        int helperRight = 0;
        int current = 0;
        int[] arr = new int[arr1.length + arr2.length];
        /* merge */
        while (helperLeft < arr1.length && helperRight < arr2.length && current < arr1.length + arr2.length) {
            if (arr1[helperLeft] <= arr2[helperRight]) {
                arr[current] = arr1[helperLeft];
                helperLeft++;
            } else {
                arr[current] = arr2[helperRight];
                helperRight++;
            }
            current++;
        }
        /* copy the remainder */
        if (helperLeft < arr1.length) {
            int remainder = arr1.length - helperLeft;
            for (int i = 0; i < remainder; i++) {
                arr[current + i] = arr1[helperLeft + i];
            }
        } else if (helperRight < arr2.length) {
            int remainder = arr2.length - helperRight;
            for (int i = 0; i< remainder; i++) {
                arr[current + i] = arr2[helperRight + i];
            }
        }
        return arr;
    }
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }
    /*
     * Q2) 문자열을 input으로 받아 서로 인접한 문자열 순서로 정렬하는 메서드
     */
    public static void q2() {
        // 문제가 무슨 말인지 모르겠다
    }

    /*
     * Q3) 입력: n개의 정수로 구성된 오름차순 정렬 상태의 배열을 임의의 횟수만큼 회전시켜 얻은 배열
     *     이 배열에서 특정한 원소를 찾는코드
     */
    public static int q3(int[] arr, int x) {
        // sort: find i s.t. arr[i] >= arr[i + 1]
        int index = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr [i+1]) {
                index = i;
                break;
            }
        }
        int helperRight = index + 1;
        int helperLeft = 0;

        if (arr[arr.length - 1] < x) {
            // find from helperLeft to helperRight - 1
            for (int i = 0; i < helperRight; i++) {
                if (arr[i] == x) {
                    return i;
                }
            }
        } else {
            // find from helperRight to arr.length - 1
            for (int i = helperRight; i < arr.length; i++) {
                if (arr[i] == x) {
                    return i;
                }
            }
        }
        return -1;
    }
    /*
     * Q4) getIndex() of positive int x from aligned list without "length" method
     * return any index if x occurs more than once
     */
    public static int q4(int[] arr, int x) {
        int i = 0;
        while (arr[i] >= 0) {
            if (arr[i] == x) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /*
     * Q6) 큰 파일 정렬: 메모리 카피가 일어나지 않는 것이 관건이다. => bubble sort
     */
    public static void q6(int[] arr) {
        int tmp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                tmp = arr[i];
                arr[i] = arr [i + 1];
                arr[i + 1] = tmp;
            }
        }
    }

    /*
     * Q7) 음이 아닌 정수 40억개.
     * 이 파일에 없는 정수를 생성하는 알고리즘을 작성하라
     * 단 메모리는 1GB만 사용할 수 있다.
     */
    public static void q7() {

    }

    /*
     * Q8) 1부터 N(<= 32,000)까지의 숫자로 이루어진 배열 with 중복된 숫자
     * unknown N
     * 사용가능한 메모리 4KB
     * 중복된 원소를 모두 출력하려면 어떻게?
     * A) 2D Binary search
     * Median이 어디 있는지 아니까 거기를 기준 Index로 삼자.
     */
    public static void q8() {

    }

    /*
     * Q9) 정렬된 행렬 탐색
     * 각 행과 열이 오름차순으로 정렬된 MXN 행렬
     * 특정한 원소를 찾는 메소드를 구하라
     */
    public static int[] q9(int[][] arr, int x) {
        int len1 = arr.length;
        int len2 = arr[0].length;
        int med = 0;
        int shortLen = 0;
        if (len1 <= len2) {
            med = len1 / 2;
            shortLen = len1;
        } else {
            med = len2 / 2;
            shortLen = len2;
        }

        int diag[] = new int[shortLen];
        for (int i = 0; i < shortLen; i++) {
            int j = shortLen -1 - i;
            diag[i] = arr[i][j];
        }
        BubbleSort.bubbleSort(diag);

        if (diag[0] > x) {
            // search from upper triangle
            for (int i =0; i < shortLen; i++) {
                for (int j = 0; j < shortLen - i; j++) {
                    if (arr[i][j] == x) {
                        return new int[] {i, j};
                    }
                }
            }
        } else if (diag[0] <= x && x <= diag[shortLen - 1]) {
            for (int i = 0; i < shortLen; i++) {
                if (arr[i][i] == x) {
                    return new int[] {i, i};
                }
            }
            return new int[] {-1, -1};
        } else {
            // search from lower triangle
            for (int i = 1; i < len1; i++) {
                for (int j = len2 - 1; j > shortLen -1 - i; j--) {
                    if (arr[i][j] == x) {
                        return new int[] {i, j};
                    }
                }
            }
        }
        return  new int[] {-1, -1};
    }

    public static void main(String[] args) {
        // Q1
        int[] arr1 = q1(new int[] {1,5,7,10,11,12,13}, new int[] {2,4,6});
        print(arr1);

        // Q3
        int[] arr3 = new int[] {5,7,8,9,1,3,4};
        int ret = q3(arr3, 0);
        System.out.println("\nq3: " + new Integer(ret));

        // Q4
        int[] arr4 = new int[] {1,2,3,4,7,8,10};
        int q4 = q4(arr4, 8);
        System.out.println("q4: " + new Integer(q4));

        // Q6 => give up
        int[] arr6 = new int[] {1424,45,68,4344,0};
        q6(arr6);
        print(arr6);
        int a = (1+2)/2;
        System.out.println("a:"+a);

        // Q9
        int[][] arr9 = new int[][] {{1,3,4},{4,6,7},{5,9,10},{7,10,11}};
        int[] q9 = q9(arr9, 6);
        print(q9);

    }
}
