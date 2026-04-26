import java.util.Arrays;

public class Experiment {

    private Sorter sorter = new Sorter();
    private Searcher searcher = new Searcher();

    public long measureSortTime(int[] arr, String type) {
        int[] copy = Arrays.copyOf(arr, arr.length);

        long start = System.nanoTime();

        if (type.equals("basic")) {
            sorter.basicSort(copy);
        } else {
            sorter.advancedSort(copy);
        }

        long end = System.nanoTime();
        return end - start;
    }

    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();

        searcher.search(arr, target);

        long end = System.nanoTime();
        return end - start;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};

        for (int size : sizes) {
            System.out.println("\nArray size: " + size);

            int[] randomArr = sorter.generateRandomArray(size);
            int[] sortedArr = sorter.generateSortedArray(size);

            // Sorting tests
            System.out.println("Random Array:");
            System.out.println("Insertion Sort: " + measureSortTime(randomArr, "basic"));
            System.out.println("Merge Sort: " + measureSortTime(randomArr, "advanced"));

            System.out.println("Sorted Array:");
            System.out.println("Insertion Sort: " + measureSortTime(sortedArr, "basic"));
            System.out.println("Merge Sort: " + measureSortTime(sortedArr, "advanced"));

            // Searching test (on sorted array!)
            int target = sortedArr[size / 2];
            System.out.println("Binary Search Time: " + measureSearchTime(sortedArr, target));
        }
    }
}