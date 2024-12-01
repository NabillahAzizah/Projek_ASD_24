import java.util.ArrayList;

public class Sorters {
    // Insertion Sort berdasarkan typoCount
    public static void insertionSortByTypo(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Player key = players.get(i);
            int j = i - 1;
            while (j >= 0 && players.get(j).typoCount > key.typoCount) {
                players.set(j + 1, players.get(j));
                j--;
            }
            players.set(j + 1, key);
        }
    }

    // Merge Sort berdasarkan waktu
    public static void mergeSortByTime(ArrayList<Player> players) {
        if (players.size() <= 1) return;

        int mid = players.size() / 2;
        ArrayList<Player> left = new ArrayList<>(players.subList(0, mid));
        ArrayList<Player> right = new ArrayList<>(players.subList(mid, players.size()));

        mergeSortByTime(left);
        mergeSortByTime(right);

        merge(players, left, right);
    }

    private static void merge(ArrayList<Player> players, ArrayList<Player> left, ArrayList<Player> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).time <= right.get(j).time) {
                players.set(k++, left.get(i++));
            } else {
                players.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            players.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            players.set(k++, right.get(j++));
        }
    }
}
