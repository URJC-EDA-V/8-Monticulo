import es.urjc.grafo.EDA.Heap;
import es.urjc.grafo.EDA.MaximumComparator;
import es.urjc.grafo.EDA.MinimumComparator;
import es.urjc.grafo.EDA.lists.LinkedPositionalList;
import es.urjc.grafo.EDA.utils.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.random.RandomGenerator;

public class CollectionsHeapTest {

    private static final int n = 100000;
    private static final RandomGenerator randomGenerator = new Random();
    private static final List<Integer> numbers = new ArrayList<>(n);

    private static void addToSortedList(int numberToAdd, List<Integer> sortedList) {
        int pos = 0;
        while (pos < sortedList.size() && sortedList.get(pos) < numberToAdd) {
            pos++;
        }
        sortedList.add(pos, numberToAdd);
    }

    private static void addToSortedLinkedPositionalList(int numberToAdd, LinkedPositionalList<Integer> sortedList) {
        if (sortedList.isEmpty()) {
            sortedList.addFirst(numberToAdd);
        } else {
            for (Position<Integer> position : sortedList.positions()) {
                if (position.getElement() <= numberToAdd) {
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
        PriorityQueue<Integer> javaPriorityQueue = new PriorityQueue<>(new MaximumComparator<>());
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n / 2; i++) {
            javaPriorityQueue.add(numbers.get(i));
        }

        for (int i = n / 2; i < n; i++) {
            javaPriorityQueue.add(numbers.get(i));
            javaPriorityQueue.remove();
        }

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
            unsortedList.sort(new MinimumComparator<>());
            unsortedList.removeLast();
        }

        unsortedList.sort(new MinimumComparator<>());
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
//        System.out.println("Time of LinkedPositionalList: Skipped.");
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
        Heap<Integer> monticulo = new Heap<>(new MaximumComparator<>());
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
