package sort;

public class MergeSort {
    public void mergeSort(int[] arr) {
        int[] helper = new int[arr.length];
        mergeSort(arr, helper, 0, arr.length - 1);
    }
    public void mergeSort(int[] arr, int[] helper, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(arr, helper, low, middle);
            mergeSort(arr, helper, middle + 1, high);
            merge(arr, helper, low, middle, high);
        }
    }
    public void merge(int[] arr, int[] helper, int low, int middle, int high) {
        /* 절반짜리 두 배열을 helper에 복사한다. */
        for (int i = low; i <= high; i++) {
            helper[i] = arr[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /* helper 배열 순회. 왼쪽 절반과 오른쪽 절반을 비교하여 작은 원소를 원래 배열의 current 위치에 복사해 넣는다. */
        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                arr[current] = helper[helperLeft];
                helperLeft++;
            } else {
                arr[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        /* 왼쪽 절반에 남아있는 원소들을 원래의 배열로 복사해 넣는다 */
        int remainder = middle - helperLeft;
        for (int i = helperLeft; i <= remainder; i++) {
            arr[current + i] = helper[helperLeft + i];
        }
    }
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,5,23,1,1,200,4,1};
        MergeSort ms = new MergeSort();
        ms.mergeSort(arr);
        ms.print(arr);
    }
}
