package sort;

/*
 * 1. 임의의 인덱스를 정한다.
 * 2. 해당 인덱스의 value보다 작은 값을 왼쪽에, 큰 값을 오른쪽에 줄세운다.
 * 3. 새 인덱스를 찾는다.
 * 4. 2로 돌아가 반복한다.
 */
public class QuickSort {
    public void print(int[] list) {
        for (int i = 0; i < list.length; i++){
            System.out.println(list[i]);
        }
    }


    public void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }
    public int partition(int[]arr, int left, int right) {
        int pivotIndex = (left + right) / 2;
        int pivotValue = arr[pivotIndex];
        while (left <= right) {
            while (arr[left] < pivotValue) {
                left++;
            }
            while (arr[right] > pivotValue) {
                right--;
            }
            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }
    public static void main(String args[]) {
        int[] arr = {10,2,12,400,8,3,1,1};
        QuickSort qs = new QuickSort();
        qs.quickSort(arr, 0, arr.length - 1);
        qs.print(arr);
    }
}

