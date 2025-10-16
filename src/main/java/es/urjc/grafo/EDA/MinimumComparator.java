package es.urjc.grafo.EDA;

import java.util.Comparator;

public class MinimumComparator<T> implements Comparator<T> {

    @Override
    public int compare(T firstObject, T secondObject) {
        if (firstObject instanceof Comparable<?> && secondObject instanceof Comparable<?>) {
            Comparable<T> firstComparable = (Comparable<T>) firstObject;
            return firstComparable.compareTo(secondObject);
        } else {
            throw new IllegalArgumentException("Elements must be Comparable");
        }
    }

}
