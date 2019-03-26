package practice_0326;

class ch7_technical_issues {
    // how many pairs of integers of difference k are in the arr
    public static int q1(int[] arr, int k) {
        // quick sort
        quickSort(arr, 0, arr.length - 1);

        // binary search a + k for each a in arr
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            int answer = binarySearch(arr, i+1, arr.length - 1, arr[i] + k);
            if (answer != -1) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void quickSort(int[] arr, int start, int end) {
        int pivot = partition(arr, start, end);
        if (pivot - 1 > start) {
            quickSort(arr, start, pivot-1);
        }
        if (end > pivot) {
            quickSort(arr, pivot, end);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = (start + end) / 2;

        while (start <= end) {
            while (arr[pivot] > arr[start]) start++;
            while (arr[pivot] < arr[end]) end--;
            if (start <= end) {
                swap(arr, start, end);
                start++;
                end --;
            }
        }
        return start;
    }

    public static void swap(int[] arr,int n, int m) {
        int tmp = arr[n];
        arr[n] = arr[m];
        arr[m] = tmp;
    }

    public static int binarySearch(int[] arr, int start, int end, int target) {
        int index = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 7, 5, 9, 2, 12, 3};
        int answer = q1(arr, 2);
        System.out.println(answer);
    }
}