package sorting;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**  A class that implements SortingInterface. Has various methods
 *   to sort a list of elements. */
public class SortingImplementation  implements SortingInterface {

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using insertion sort
     * @param array array of Comparable-s
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed if true, the list should be sorted in descending order
     */
    @Override
    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        Comparable curr;
        int j;
        if (reversed == false) {
            for (int i = lowindex; i <= highindex; i++) {
                curr = array[i];
                j = i - 1;
                while (j >= lowindex && curr.compareTo(array[j]) < 0) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = curr;
            }
        }else {
            for (int i = highindex - 1; i >= lowindex; i--){
                curr = array[i];
                j = i + 1;
                while (j <= highindex && curr.compareTo(array[j]) < 0){
                    array [j - 1] = array[j];
                    j++;
                }
                array[j - 1] = curr;
            }
        }
    }

    /**
     * Sorts a given array of 2^k elements using iterative
     * (non-recursive) merge sort.
     * @param array array to sort
     */
    @Override
   public void iterativeMergeSort(Comparable[] array) {

        Comparable[] temp = new Comparable[array.length];

        for(int i = 1; i < array.length; i *= 2){
            for(int j = 0; j < array.length; j += i * 2){
                int low, middle, high;

                low = j;
                middle = i + j - 1;
                high = j + i * 2 - 1;
                merge(array, temp, low, middle, high);
            }
        }
    }
    public static void merge(Comparable[] arr, Comparable[] temp, int low, int mid, int high){
        int k = low;
        int i = low;
        int j = mid + 1;
        while (k <= high) {
            if (i > mid) {// ran out of elements in the i sublist
                temp[k] = arr[j];
                k++;
                j++;
            } else if (j > high) {// ran out of elements in the j sublist
                temp[k] = arr[i];
                k++;
                i++;
            } else if (arr[i].compareTo(arr[j]) < 0) { // place arr[i] in temp, move i
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k] = arr[j]; // place arr[j] in temp, move j
                k++;
                j++;
            }
        }
        // copy the result from temp back to arr
        for (k = low; k <= high; k++)
            arr[k] = temp[k];
    }

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using the randomizedQuickSort
     * @param array array to sort
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     */

    @Override
    public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex) {
        int pivot; // index of the pivot
        if (lowindex < highindex) {
            pivot = partition(array, lowindex, highindex);
            randomizedQuickSort(array, lowindex, pivot - 1);
            randomizedQuickSort(array, pivot + 1, highindex);
        }
    }
    static int partition(Comparable arr[], int low, int high) {

        Random gen = new Random();
        int one = low + gen.nextInt(high - low);
        int two = low + gen.nextInt(high - low);
        int three = low + gen.nextInt(high - low);

        int mid = findMedian( arr, one, two, three);



        // swap pivot in the last position (high)
        Comparable pivot  = arr[mid];
        arr[mid] = arr[high];
        arr[high] = pivot;

        int i = low;
        int j = high - 1;
        while (i <= j) {
            while (i < high && arr[i].compareTo(pivot) < 0) {
                i++;
            }

            while (j >= low && arr[j].compareTo(pivot) >= 0 ) {
                j--;
            }

            if (i < j) {
                // swap values at indices i and j
                int tmp = (int) arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }

        }

        // swap pivot back to index i
        int tmp = (int) arr[i];
        arr[i] = pivot;
        arr[high] = tmp;
        return i;
    }
    static int findMedian(Comparable arr[], int one, int two, int three){ //Method to find the median of the 3 array elements for quicksort
        Comparable a = arr[one];
        Comparable b = arr[two];
        Comparable c = arr[three];
        if (a .compareTo(b) < 0 && b.compareTo(c) < 0 || c.compareTo(b) < 0 && b.compareTo(a) < 0){
            return two;
        }
        else if(b.compareTo(a) < 0 && a.compareTo(c) < 0 || c.compareTo(a) < 0 && a.compareTo(b)< 0){
            return one;
        }else{
            return three;
        }
    }



    /**
     * Sorts a given sublist using hybrid sort, where the list is sorted
     * using randomized quick sort; when sublists get small (size=10 ?),
     * they are sorted using insertion sort.
     * @param array array of Comparable-s to sort
     * @param lowindex the beginning index of the sublist
     * @param highindex the end index of the sublist
     */
    @Override
    public void hybridSort(Comparable[] array, int lowindex, int highindex) {
        if ( highindex - lowindex > 10){
            int pivot; // index of the pivot
            if (lowindex < highindex) {
                pivot = partition(array, lowindex, highindex);
                hybridSort(array, lowindex, pivot - 1);
                hybridSort(array, pivot + 1, highindex);
            }
        }else {
            insertionSort(array, lowindex, highindex, false);
        }
    }

    /**
     * Sorts a sub-array of records using bucket sort.
     * @param array array of records
     * @param lowindex the beginning index of the sublist to sort
     * @param highindex the end index of the sublist to sort
     * @param reversed if true, sort in descending (decreasing) order, otherwise ascending
     */
    @Override
    public void bucketSort(Elem[] array, int lowindex, int highindex, boolean reversed) {

        int numBuckets = ((highindex-lowindex) + 1) / 2;
        LinkedList<Elem>[] buckets = new LinkedList[numBuckets];
        int maxVal = array[0].key();

        for (int i = 0; i < buckets.length; i++){
            buckets[i] = new LinkedList<Elem>();
        }

        for (int i = 1; i < array.length; i++){
            if (array[i].key() > maxVal){
                maxVal = array[i].key();
            }
        }

        int bucketSize = (int) Math.ceil((double) (maxVal + 1)/numBuckets);

        if(reversed == false) {
            for (int i = lowindex; i <= highindex; i++) {
                int bucketPlace = array[i].key() / bucketSize;
                ListIterator<Elem> it = buckets[bucketPlace].listIterator();
                if (!it.hasNext()) {
                    it.add(array[i]);
                } else {
                    boolean added = false;
                    while (it.hasNext()) {
                        if (it.next().key() > array[i].key()) {
                            it.previous();
                            it.add(array[i]);
                            added = true;
                            break;
                        }
                    }
                    if (!added) {
                        it.add(array[i]);
                    }
                }
            }
            int index = lowindex;

            for (int i = 0; i < buckets.length; i++){
                Iterator<Elem> it = buckets[i].listIterator();
                while (it.hasNext()){
                    array[index] = it.next();
                    index++;
                }
            }
        }else{ //sorts the list in descending order
            for (int i = lowindex; i <= highindex; i++){
                int bucketPlace = array[i].key() / bucketSize;
                ListIterator<Elem> it = buckets[bucketPlace].listIterator();
                if (!it.hasNext()){
                    it.add(array[i]);
                }else {
                    boolean added = false;
                    while (it.hasNext()) {
                        if (it.next().key() < array[i].key()) {
                            it.previous();
                            it.add(array[i]);
                            added = true;
                            break;
                        }
                    }
                    if (!added){
                        it.add(array[i]);
                    }
                }
            }
            int index = lowindex;
            for (int j = buckets.length - 1; j >= 0; j--){
                Iterator<Elem> newIt = buckets[j].listIterator();
                while (newIt.hasNext()){
                    array[index] = newIt.next();
                    index++;
                }

            }
        }
    }


    /**
     * Implements external sort method
     * @param inputFile The file that contains the input list
     * @param outputFile The file where to output the sorted list
     * @param k number of elements that fit into memory at once
     * @param m number of chunks
     */
    public void externalSort(String inputFile, String outputFile, int k, int m) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            int totalCount = 0; //keeps total count of elements to go in to final list (N)

            for (int i = 0; i < m; i++){ //writes to temp files
                int count = 0;
                Integer[] array = new Integer[k];
                String fileName = "temp" + i + ".txt";
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                for (int j = 0; j < k; j++){
                    String elem = br.readLine();
                    if (elem != null){
                        array[j] = parseInt(elem);
                        count++;
                        totalCount++;
                    }else{
                        break;
                    }
                }
                randomizedQuickSort(array,0, count - 1);
                for (int j = 0; j < count; j++) {
                    bw.write(array[j].toString());
                    bw.newLine();
                }
                bw.close();
            }

            BufferedReader[] readers = new BufferedReader[m];
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            Integer[] values = new Integer[m];
            for( int i = 0; i < m; i++){
                readers[i]  = new BufferedReader(new FileReader("temp" + i + ".txt"));

                values[i] = parseInt(readers[i].readLine()); //stores first element of each file
            }



            for(int i = 0; i < totalCount; i++) {//compares elements from files and adds them to sorted array
                int smallIndex = 0;
                for (int j = 1; j < m; j++) {
                    if(values[j] == null){
                        continue;
                    }
                    if (values[smallIndex] == null || values[j] < values[smallIndex]) {
                        smallIndex = j;
                    }
                }


                writer.write(values[smallIndex].toString());
                writer.newLine();

                String nextValue = readers[smallIndex].readLine();
                if (nextValue != null) {
                    values[smallIndex] = parseInt(nextValue);
                }else{
                    values[smallIndex] = null;
                }
            }
            writer.close();

        }catch(IOException e){
            System.out.println("Failed: " + e.getMessage());
        }






    }
}