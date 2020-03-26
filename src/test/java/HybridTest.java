import org.junit.Test;
import sorting.SortingImplementation;

import java.util.Random;

public class HybridTest {


    @Test
    public void testHybrid() {
        SortingImplementation listSorter = new SortingImplementation();
        Comparable[] arr1 = new Comparable[80000];
        Comparable[] arr2 = new Comparable[80000];
        Random r = new Random();


        for (int j = 0; j < arr1.length; j++) {
                arr1[j] = r.nextInt(40);

                arr2[j] = r.nextInt(40);

            }



        long startTime = System.currentTimeMillis();

        listSorter.hybridSort(arr1, 0, arr1.length - 1);

        long endTime = System.currentTimeMillis();
        /*for (int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + ", ");
        }*/
        System.out.println("\nRunning time of Hybrid sort: " + (endTime - startTime) );



        long quickStart = System.currentTimeMillis();

        listSorter.randomizedQuickSort(arr2, 0, arr1.length - 1);

        long quickEnd = System.currentTimeMillis();

        System.out.println("Quick sort runtime in miliseconds: " + (quickEnd - quickStart));
    }
}

