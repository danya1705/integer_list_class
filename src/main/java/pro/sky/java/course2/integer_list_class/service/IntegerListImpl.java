package pro.sky.java.course2.integer_list_class.service;

import pro.sky.java.course2.integer_list_class.exception.ArrayIsFullException;
import pro.sky.java.course2.integer_list_class.exception.ElementNotFound;
import pro.sky.java.course2.integer_list_class.exception.NullArgumentException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] arr;
    private int size;
    private final boolean extension;
    private boolean sorted;

    public IntegerListImpl(int size, boolean extension) {
        arr = new Integer[size];
        this.size = 0;
        this.extension = extension;
        sorted = false;
    }

    public IntegerListImpl(int size) {
        arr = new Integer[size];
        this.size = 0;
        this.extension = false;
        sorted = false;
    }

    @Override
    public void printList() {
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public Integer add(Integer item) {

        validateInteger(item);
        validateArray();

        arr[size++] = item;
        if (size > 1 && arr[size - 1] < arr[size - 2]) sorted = false;
        return arr[size - 1];

    }

    @Override
    public Integer add(int index, Integer item) {

        validateInteger(item);
        validateIndex(index);
        validateArray();

        for (int i = size; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = item;
        size++;
        if ((index > 0 && arr[index] < arr[index - 1]) || (index < size - 1 && arr[index] > arr[index + 1])) sorted = false;
        return arr[index];
    }

    @Override
    public Integer set(int index, Integer item) {

        validateInteger(item);
        validateIndex(index);

        arr[index] = item;
        if ((index > 0 && arr[index] < arr[index - 1]) || (index < size - 1 && arr[index] > arr[index + 1])) sorted = false;
        return arr[index];
    }

    @Override
    public Integer remove(Integer item) {

        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFound();
        } else {
            for (int i = index; i < size; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
            return item;
        }
    }

    @Override
    public Integer removeByIndex(int index) {

        validateIndex(index);

        Integer item = arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return !(indexOf(item) == -1);
    }

    @Override
    public int indexOf(Integer item) {
        validateInteger(item);
        return binarySearch(item);
    }

    @Override
    public int lastIndexOf(Integer item) {

        validateInteger(item);

        for (int i = size - 1; i > 0; i--) {
            if (arr[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {

        validateIndex(index);

        return arr[index];

    }

    @Override
    public boolean equals(IntegerList otherList) {

        if (otherList == null) throw new NullArgumentException();
        if (size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!arr[i].equals(otherList.get(i))) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    private void extendArray() {
        Integer[] arrTemp = Arrays.copyOf(arr, size);
        arr = Arrays.copyOf(arrTemp, size * 10);
    }

    public void validateInteger(Integer item) {
        if (item == null) throw new NullArgumentException();
    }

    public void validateIndex(int index) {
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
    }

    public void validateArray() {
        if (size >= arr.length && !extension) throw new ArrayIsFullException();
        if (size >= arr.length && extension) extendArray();
    }

    private void swapElements(int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private void sortSelection() {
        for (int i = 0; i < size - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(i, minElementIndex);
        }
        sorted = true;
    }

    private int binarySearch(Integer item) {

        if (!sorted) {
            sortSelection();
        }

        int min = 0;
        int max = size - 1;

        while (min <= max) {

            int mid = (min + max) / 2;

            if (arr[mid].equals(item)) {
                return mid;
            } else if (arr[mid] > item) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    public boolean isSorted() {
        return sorted;
    }
}
