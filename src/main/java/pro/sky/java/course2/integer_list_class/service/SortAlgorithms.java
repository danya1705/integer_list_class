package pro.sky.java.course2.integer_list_class.service;

import java.util.Arrays;
import java.util.Random;

public class SortAlgorithms {

    public static void main(String[] args) {
        
        Integer[] arr = generateArray(100000);
        Integer[] arr2 = Arrays.copyOf(arr,arr.length);
        Integer[] arr3 = Arrays.copyOf(arr,arr.length);

        long start = System.currentTimeMillis();
        sortBubble(arr);
        System.out.println("sortBubble: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println("sortSelection: " + (System.currentTimeMillis() - start));
        
        start = System.currentTimeMillis();
        sortInsertion(arr3);
        System.out.println("sortInsertion: " + (System.currentTimeMillis() - start));
        
        
    }

    public static Integer[] generateArray(int size) {

        Integer[] arr = new Integer[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }

        return arr;
    }

    public static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }


}
