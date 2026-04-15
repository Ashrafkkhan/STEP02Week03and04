import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ": " + fee + "@" + timestamp;
    }
}

public class TransactionAudit {

    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int swaps = 0, passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("BubbleSort (fees): " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    public static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("InsertionSort (fee+ts): " + list);
    }

    public static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    public static void detectOutliers(List<Transaction> list) {
        System.out.print("High-fee outliers: ");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.print(t + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.println("none");
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        List<Transaction> bubbleList = new ArrayList<>(transactions);
        List<Transaction> insertionList = new ArrayList<>(transactions);

        bubbleSort(bubbleList);
        insertionSort(insertionList);
        detectOutliers(insertionList);
    }
}