package sort;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length - i; j++) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void print(int[] list) {
        for (int i = 0; i < list.length; i++){
            System.out.println(list[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {100,4,352,23,0};
        bubbleSort(arr);
        print(arr);
    }
}
