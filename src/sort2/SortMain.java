package sort2;

public class SortMain {
    // initiator, memory assigner
    public static void mergeSort(int[] arr) {
        int[] helper = new int[arr.length];
        mergeSort(arr, helper, 0,arr.length - 1);
    }
    // problem divider
    public static void mergeSort(int[] arr, int[] helper, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(arr, helper, low, middle);
            mergeSort(arr, helper, middle + 1, high);
            merge(arr, helper, low, middle, high);
        }

    }

    public static void merge(int[] arr, int[] helper, int low, int middle, int high) {
        int helperLeft = low; // index of helper
        int helperRight = middle + 1; // index of helper
        int current = low; // index of arr

        for (int i = low; i <= high; i++) {
            helper[i] = arr[i];
        }

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
        // helperLeft != remainder 일 때 남은 왼쪽 배열의 처리: arr[]의 current 뒤쪽에 갖다 붙인다.
        int remainder = middle - helperLeft;
        for (int i = 0; i <= remainder; i++) {
            arr[current + i] = helper[helperLeft + i];
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivotIndex = (left + right) / 2;
        int pivot = arr[pivotIndex];
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = (a+b)/2;
        System.out.println(c);

        // mergeSort
        int[] arr1 = new int[] {5,2,1,7,8,9};
        mergeSort(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + "  ");
        }
        System.out.println("");
        arr1 = new int[] {5,2,1,7,8,9};
        quickSort(arr1, 0, arr1.length - 1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + "  ");
        }


    }
}
