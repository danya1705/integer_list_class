package pro.sky.java.course2.integer_list_class.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortAlgorithmsTest {

    Integer[] arr = new Integer[7];
    Integer[] arrSorted = new Integer[7];

    @BeforeEach
    void generateArrays() {
    arr = new Integer[] {2, 1, 5, 10, 100, 4, 3};
    arrSorted = new Integer[] {1, 2, 3, 4, 5, 10, 100};
    }

    @Test
    void swapElements() {
        Integer[] expected = new Integer[] {2, 1, 5, 100, 10, 4, 3};
        SortAlgorithms.swapElements(arr,3,4);
        assertThat(arr).isEqualTo(expected);
    }

    @Test
    void sortBubble() {
        SortAlgorithms.sortBubble(arr);
        assertThat(arr).isEqualTo(arrSorted);
    }

    @Test
    void sortSelection() {
        SortAlgorithms.sortSelection(arr);
        assertThat(arr).isEqualTo(arrSorted);
    }

    @Test
    void sortInsertion() {
        SortAlgorithms.sortInsertion(arr);
        assertThat(arr).isEqualTo(arrSorted);
    }
}