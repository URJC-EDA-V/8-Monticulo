package es.urjc.grafo.EDA;

import java.util.*;


public class Heap<E> implements HeapInterface<E> {

    private static final int CAPACITY = 100;
    private final ArrayList<E> heap;
    private final Comparator<E> comparator;

    public Heap() {
        this(CAPACITY);
    }

    public Heap(Comparator<E> c) {
        this(c, CAPACITY);
    }

    public Heap(int size) {
        this(new MinimumComparator<>(), size);
    }

    public Heap(Comparator<E> comparator, int size) {
        this.heap = new ArrayList<>(size);
        this.comparator = comparator;
    }

    // Returns the index of the parent
    // of the element at ith index.
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Returns the index of the left child.
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Returns the index of the
    // right child.
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private boolean hasLeft(int i) {
        return this.leftChild(i) < this.heap.size();
    }

    private boolean hasRight(int i) {
        return this.rightChild(i) < this.heap.size();
    }

    //siftUp
    //move a node up in the tree, as long as needed; used to restore heap condition after insertion.
    private void siftUp(int i) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //siftDown
    //move a node down in the tree, similar to sift-up; used to restore heap condition after deletion or replacement.
    private void siftDown(int i) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(E e) {
        this.heap.add(e);
        this.siftUp(this.heap.size() - 1);
        return true;
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override
    public Iterator<E> iterator() {
        return new HeapIterator();
    }

    @Override
    public E remove() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void swap(int i, int j) {
        E temp = this.heap.get(i);
        this.heap.set(i, this.heap.get(j));
        this.heap.set(j, temp);
    }

    @Override
    public E peak() {
        return this.heap.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public void clear() {
        this.heap.clear();
    }

    private class HeapIterator implements Iterator<E> {

        // Montículo auxiliar que nos permite recorrer el montículo original sin modificarlo
        // El montículo auxiliar contiene pares (elemento, índice) y se ordena por el elemento
        // El índice se utiliza para poder obtener los hijos del elemento en el montículo
        // original sin tener que ir eliminando elementos del montículo original
        private final Heap<Map.Entry<E, Integer>> auxHeap;
        private final WrappedComparator<Map.Entry<E, Integer>> comparator;

        public HeapIterator() {
            this.comparator = new WrappedComparator<>();
            this.auxHeap = new Heap<>(this.comparator, heap.size());
            this.auxHeap.add(new AbstractMap.SimpleEntry<>(heap.getFirst(), 0));
        }

        @Override
        public boolean hasNext() {
            return this.auxHeap.size() > 0;
        }

        private Map.Entry<E, Integer> getLeft(Map.Entry<E, Integer> item) {
            int indexLeftChild = 2 * item.getValue() + 1;
            if (indexLeftChild < heap.size()) {
                return new AbstractMap.SimpleEntry<>(heap.get(indexLeftChild), indexLeftChild);
            } else return null;
        }

        private Map.Entry<E, Integer> getRight(Map.Entry<E, Integer> item) {
            int indexRightChild = 2 * item.getValue() + 2;
            if (indexRightChild < heap.size()) {
                return new AbstractMap.SimpleEntry<>(heap.get(indexRightChild), indexRightChild);
            } else return null;
        }

        @Override
        public E next() {
            Map.Entry<E, Integer> result = this.auxHeap.remove();
            Map.Entry<E, Integer> left = this.getLeft(result);
            Map.Entry<E, Integer> right = this.getRight(result);
            if (left != null) {
                this.auxHeap.add(left);
            }
            if (right != null) {
                this.auxHeap.add(right);
            }
            return result.getKey();
        }

        // La única utilidad de este comparador es poder utilizar el comparator del heap original
        // para comparar los elementos del heap auxiliar de este iterador, que son pares (elemento, índice)
        // y la comparación se hace solo por el elemento (la clave del par)
        private class WrappedComparator<T extends Map.Entry<E, Integer>> implements Comparator<T> {

            private final Comparator<E> comparator = Heap.this.comparator;

            public int compare(T o1, T o2) {
                return this.comparator.compare(o1.getKey(), o2.getKey());
            }

        }
    }


}
