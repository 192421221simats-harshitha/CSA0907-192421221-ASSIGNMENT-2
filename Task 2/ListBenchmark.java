
import java.util.*;

public class ListBenchmark {

    static void testArrayList(int size) {
        ArrayList<String> songs = new ArrayList<>();

        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            songs.add("Song" + i);
        }
        long addTime = System.nanoTime() - start;

        start = System.nanoTime();
        songs.add(0, "NewSong");
        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();
        songs.remove(0);
        long removeTime = System.nanoTime() - start;

        start = System.nanoTime();
        songs.get(size / 2);
        long accessTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (String song : songs) {
            // Iteration
        }
        long iterateTime = System.nanoTime() - start;

        System.out.println("ArrayList:");
        System.out.println("Add at end: " + addTime + " ns");
        System.out.println("Insert at front: " + insertTime + " ns");
        System.out.println("Remove at front: " + removeTime + " ns");
        System.out.println("Access middle: " + accessTime + " ns");
        System.out.println("Iteration: " + iterateTime + " ns");
    }

    static void testLinkedList(int size) {
        LinkedList<String> songs = new LinkedList<>();

        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            songs.add("Song" + i);
        }
        long addTime = System.nanoTime() - start;

        start = System.nanoTime();
        songs.add(0, "NewSong");
        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();
        songs.remove(0);
        long removeTime = System.nanoTime() - start;

        start = System.nanoTime();
        songs.get(size / 2);
        long accessTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (String song : songs) {
            // Iteration
        }
        long iterateTime = System.nanoTime() - start;

        System.out.println("LinkedList:");
        System.out.println("Add at end: " + addTime + " ns");
        System.out.println("Insert at front: " + insertTime + " ns");
        System.out.println("Remove at front: " + removeTime + " ns");
        System.out.println("Access middle: " + accessTime + " ns");
        System.out.println("Iteration: " + iterateTime + " ns");
    }

    static void testArrayDeque(int size) {
        ArrayDeque<String> queue = new ArrayDeque<>();

        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            queue.addLast("Song" + i);
        }
        long addTime = System.nanoTime() - start;

        start = System.nanoTime();
        queue.addFirst("NewSong");
        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();
        queue.removeFirst();
        long removeTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (String song : queue) {
            // Iteration
        }
        long iterateTime = System.nanoTime() - start;

        System.out.println("ArrayDeque:");
        System.out.println("Add at end: " + addTime + " ns");
        System.out.println("Add at front: " + insertTime + " ns");
        System.out.println("Remove at front: " + removeTime + " ns");
        System.out.println("Iteration: " + iterateTime + " ns");
        System.out.println("Direct middle access: Not supported");
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};

        for (int size : sizes) {
            System.out.println("\nPlaylist size: " + size);
            testArrayList(size);
            testLinkedList(size);
            testArrayDeque(size);
        }
    }
}
