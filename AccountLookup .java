import java.util.*;

public class AccountLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    public static int linearFirst(String[] arr, String key) {
        linearComparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(key)) return i;
        }
        return -1;
    }

    public static int linearLast(String[] arr, String key) {
        linearComparisons = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            linearComparisons++;
            if (arr[i].equals(key)) return i;
        }
        return -1;
    }

    public static int binarySearch(String[] arr, String key) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            int cmp = arr[mid].compareTo(key);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    public static int countOccurrences(String[] arr, String key) {
        int first = firstOccurrence(arr, key);
        if (first == -1) return 0;
        int last = lastOccurrence(arr, key);
        return last - first + 1;
    }

    public static int firstOccurrence(String[] arr, String key) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = arr[mid].compareTo(key);

            if (cmp == 0) {
                result = mid;
                high = mid - 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static int lastOccurrence(String[] arr, String key) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = arr[mid].compareTo(key);

            if (cmp == 0) {
                result = mid;
                low = mid + 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        int first = linearFirst(logs, "accB");
        int last = linearLast(logs, "accB");

        System.out.println("Linear first accB: index " + first + " (" + linearComparisons + " comparisons)");
        System.out.println("Linear last accB: index " + last);

        Arrays.sort(logs);

        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary accB: index " + index + " (" + binaryComparisons + " comparisons), count=" + count);
    }
}