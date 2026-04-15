import java.util.*;

public class RiskThresholdLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    public static int linearSearch(int[] arr, int target) {
        linearComparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    public static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static int ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }

        return low;
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        int target = 30;

        int lin = linearSearch(risks, target);
        System.out.println("Linear: " + target + " → " + (lin == -1 ? "not found" : lin) +
                " (" + linearComparisons + " comparisons)");

        int bin = binarySearch(risks, target);
        System.out.println("Binary: " + target + " → " + (bin == -1 ? "not found" : bin) +
                " (" + binaryComparisons + " comparisons)");

        int f = floor(risks, target);
        int c = ceiling(risks, target);
        int pos = insertionPoint(risks, target);

        System.out.println("Floor: " + f);
        System.out.println("Ceiling: " + c);
        System.out.println("Insertion Index: " + pos);
    }
}