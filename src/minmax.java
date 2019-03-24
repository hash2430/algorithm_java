import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class minmax {

    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {
        // 정렬한다
        sort(arr);
        int min = 0;
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min += arr[i];
        }
        for (int i = 1; i < arr.length; i++) {
            max += arr[i];
        }

        System.out.println(min+" "+max);
    }

    static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j-1] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        miniMaxSum(new int[]{0,0,3,4,5});


    }
}
