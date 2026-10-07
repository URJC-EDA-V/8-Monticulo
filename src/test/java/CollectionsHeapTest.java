import es.urjc.grafo.EDA.Heap;
import es.urjc.grafo.EDA.MaximumComparator;
import es.urjc.grafo.EDA.MinimumComparator;
import es.urjc.grafo.EDA.lists.LinkedPositionalList;
import es.urjc.grafo.EDA.utils.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.random.RandomGenerator;

public class CollectionsHeapTest {

    private static final int n = 100_000;
    private static final RandomGenerator randomGenerator = new Random();
    private static final List<Integer> numbers = new ArrayList<>(n);
    private static final Comparator<Integer> comparator = new MinimumComparator<>();

    private static void addToSortedList(int numberToAdd, List<Integer> sortedList) {
        int pos = 0;
        while (pos < sortedList.size() && comparator.compare(sortedList.get(pos), numberToAdd) < 0) {
            pos++;
        }
        sortedList.add(pos, numberToAdd);
    }

    private static void addToSortedLinkedPositionalList(int numberToAdd, LinkedPositionalList<Integer> sortedList) {
        if (sortedList.isEmpty()) {
            sortedList.addFirst(numberToAdd);
        } else {
            for (Position<Integer> position : sortedList.positions()) {
                if (comparator.compare(position.getElement(), numberToAdd) > 0) {
                    sortedList.addBefore(position, numberToAdd);
                    return;
                }
            }
            sortedList.addLast(numberToAdd);
        }
    }

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < n; i++) {
            numbers.add(randomGenerator.nextInt(n));
        }
    }

    @Test
    public void testPerformancePriorityQueue() {
        PriorityQueue<Integer> javaPriorityQueue = new PriorityQueue<>(comparator);
        long startTime = System.currentTimeMillis();

        // Add n/2 random numbers
        for (int i = 0; i < n / 2; i++) {
            javaPriorityQueue.add(numbers.get(i));
        }

        // Add n/2 random numbers and perform n/2 removal operations
        for (int i = n / 2; i < n; i++) {
            javaPriorityQueue.add(numbers.get(i));
            javaPriorityQueue.remove();
        }

        // Remove the rest of numbers
        while (!javaPriorityQueue.isEmpty()) {
            javaPriorityQueue.remove();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time of PriorityQueue: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testPerformanceUnsortedList() {
        List<Integer> unsortedList = new ArrayList<>(n);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n / 2; i++) {
            unsortedList.add(numbers.get(i));
        }

        for (int i = n / 2; i < n; i++) {
            unsortedList.add(numbers.get(i));
            unsortedList.sort(comparator);
            unsortedList.removeLast();
        }

        unsortedList.sort(comparator);
        while (!unsortedList.isEmpty()) {
            unsortedList.removeLast();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time of UnsortedList: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testPerformanceSortedList() {
        List<Integer> sortedList = new ArrayList<>(n);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n / 2; i++) {
            addToSortedList(numbers.get(i), sortedList);
        }

        for (int i = n / 2; i < n; i++) {
            addToSortedList(numbers.get(i), sortedList);
            sortedList.removeLast();
        }

        while (!sortedList.isEmpty()) {
            sortedList.removeLast();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time of SortedList: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testPerformanceLinkedPositionalList() {
        LinkedPositionalList<Integer> sortedPositionalList = new LinkedPositionalList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n / 2; i++) {
            addToSortedLinkedPositionalList(numbers.get(i), sortedPositionalList);
        }

        for (int i = n / 2; i < n; i++) {
            addToSortedLinkedPositionalList(numbers.get(i), sortedPositionalList);
            sortedPositionalList.remove(sortedPositionalList.first());
        }

        while (!sortedPositionalList.isEmpty()) {
            sortedPositionalList.remove(sortedPositionalList.first());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time of LinkedPositionalList: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testPerformanceMonticulo() {
        Heap<Integer> monticulo = new Heap<>(comparator);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n / 2; i++) {
            monticulo.add(numbers.get(i));
        }

        for (int i = n / 2; i < n; i++) {
            monticulo.add(numbers.get(i));
            monticulo.remove();
        }

        while (!monticulo.isEmpty()) {
            monticulo.remove();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time of Monticulo: " + (endTime - startTime) + " ms");
    }

}
