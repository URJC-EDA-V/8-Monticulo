import es.urjc.grafo.EDA.Heap;
import es.urjc.grafo.EDA.MaximumComparator;
import es.urjc.grafo.EDA.MinimumComparator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class HeapTest {

    public HeapTest() {
    }

    @Test
    public void testBehavior(){
        Random rand = new Random();
        Heap<Integer> heap = new Heap<>(new MaximumComparator<>());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new MaximumComparator<>());
        for (int i = 0; i < 1000; i++) {
            int nextInt = rand.nextInt(100);
            heap.add(nextInt);
            priorityQueue.add(nextInt);
            assertEquals(priorityQueue.size(), heap.size());
        }

        while (!heap.isEmpty()) {
            assertEquals(priorityQueue.peek(), heap.peak());
            assertEquals(priorityQueue.remove(), heap.remove());
            assertEquals(priorityQueue.size(), heap.size());
        }
    }

    /**
     * Test of add method, of class Heap.
     */
    @Test
    public void testAdd() {
        System.out.println("add");

        Heap<Integer> instance = new Heap<>();
        assertTrue(instance.isEmpty());
        assertTrue(instance.add(3));
        assertFalse(instance.isEmpty());
        assertEquals(1, instance.size());
        Comparator<? super Integer> comparator = instance.comparator();
        assertInstanceOf(MaximumComparator.class, comparator);
        assertTrue(instance.add(3));
        assertEquals(2, instance.size());
    }

    /**
     * Test of comparator method, of class Heap.
     */
    @Test
    public void testComparator() {
        System.out.println("comparator");
        Heap<Integer> instance = new Heap<>(new MinimumComparator<>());
        assertTrue(instance.isEmpty());
        Comparator<? super Integer> comparator = instance.comparator();
        assertInstanceOf(MinimumComparator.class, comparator);
    }

    /**
     * Test of iterator method, of class Heap.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Heap<Integer> instance = new Heap<>(new MinimumComparator<>());
        assertTrue(instance.isEmpty());
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        instance.add(5);
        instance.add(6);
        instance.add(7);
        List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Iterator<Integer> iterator = instance.iterator();
        int cont = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            Integer get = asList.get(cont);
            assertEquals(get, next);
            cont++;
        }
    }

    /**
     * Test of iterator method, of class Heap, with a different comparator.
     */
    @Test
    public void testIteratorReversed() {
        Heap<Integer> instance = new Heap<>(new MaximumComparator<>());
        assertTrue(instance.isEmpty());
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        instance.add(5);
        instance.add(6);
        instance.add(7);
        List<Integer> asList = Arrays.asList(7, 6, 5, 4, 3, 2, 1);
        Iterator<Integer> iterator = instance.iterator();
        int cont = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            Integer get = asList.get(cont);
            assertEquals(get, next);
            cont++;
        }
    }

    /**
     * Test of remove method, of class Heap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Heap<Integer> instance = new Heap<>(new MaximumComparator<>());
        assertTrue(instance.isEmpty());
        assertTrue(instance.add(3));
        assertFalse(instance.isEmpty());
        instance.remove();
        assertTrue(instance.isEmpty());
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        instance.add(5);
        instance.add(6);
        instance.add(7);
        assertEquals(7, instance.size());
        assertEquals(7, instance.remove());
        assertEquals(6, instance.size());
    }

    /**
     * Test of peak method, of class Heap.
     */
    @Test
    public void testPeak() {
        System.out.println("peak");
        Heap<Integer> instance = new Heap<>(new MaximumComparator<>());
        instance.add(1);
        assertEquals(1, (int) instance.peak());
        instance.add(2);
        assertEquals(2, (int) instance.peak());
        instance.add(3);
        assertEquals(3, (int) instance.peak());
        instance.add(4);
        assertEquals(4, (int) instance.peak());
        instance.add(5);
        assertEquals(5, (int) instance.peak());
        instance.add(6);
        assertEquals(6, (int) instance.peak());
        instance.add(7);
        assertEquals(7, (int) instance.peak());
        instance = new Heap<>(new MinimumComparator<>());
        instance.add(7);
        assertEquals(7, (int) instance.peak());
        instance.add(6);
        assertEquals(6, (int) instance.peak());
        instance.add(5);
        assertEquals(5, (int) instance.peak());
        instance.add(4);
        assertEquals(4, (int) instance.peak());
        instance.add(3);
        assertEquals(3, (int) instance.peak());
        instance.add(2);
        assertEquals(2, (int) instance.peak());
        instance.add(1);
        assertEquals(1, (int) instance.peak());
    }

    /**
     * Test of isEmpty method, of class Heap.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Heap<Integer> instance = new Heap<>();
        assertTrue(instance.isEmpty());
        instance.add(3);
        instance.add(4);
        assertFalse(instance.isEmpty());
        instance.remove();
        instance.remove();
        assertTrue(instance.isEmpty());
    }

    /**
     * Test of size method, of class Heap.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Heap<Integer> instance = new Heap<>(new MinimumComparator<Integer>());
        assertEquals(0, instance.size());
        instance.add(7);
        assertEquals(1, instance.size());
        instance.add(6);
        assertEquals(2, instance.size());
        instance.add(5);
        assertEquals(3, instance.size());
        instance.add(4);
        assertEquals(4, instance.size());
        instance.add(3);
        assertEquals(5, instance.size());
        instance.add(2);
        assertEquals(6, instance.size());
        instance.add(1);
        assertEquals(7, instance.size());
    }

    /**
     * Test of clear method, of class Heap.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Heap<Integer> instance = new Heap<>(new MinimumComparator<Integer>());
        assertEquals(0, instance.size());
        instance.add(7);
        assertEquals(1, instance.size());
        instance.add(6);
        assertEquals(2, instance.size());
        instance.add(5);
        assertEquals(3, instance.size());
        instance.add(4);
        assertEquals(4, instance.size());
        instance.add(3);
        assertEquals(5, instance.size());
        instance.add(2);
        assertEquals(6, instance.size());
        instance.add(1);
        assertEquals(7, instance.size());
        instance.clear();
        assertTrue(instance.isEmpty());
    }

}
