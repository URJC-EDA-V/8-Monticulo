package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.lists.LinkedPositionalList;
import es.urjc.grafo.EDA.utils.Pair;
import es.urjc.grafo.EDA.utils.Position;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PosOrderTreeIterator<T> implements Iterator<Position<T>> {

    // Cada posición en la lista contiene un par (nodo, iteradorHijos). El iteradorHijos es un iterador por los hijos del nodo.
    // De esta manera, podemos saber qué hijos ya hemos visitado y cuáles no.
    private final LinkedPositionalList<Pair<Position<T>, Iterator<? extends Position<T>>>> nodesToVisit;
    private final Tree<T> tree;

    public PosOrderTreeIterator(Tree<T> tree) {
        this(tree, tree.root());
    }

    public PosOrderTreeIterator(Tree<T> tree, Position<T> root) {
        this.tree = tree;
        this.nodesToVisit = new LinkedPositionalList<>();
        this.nodesToVisit.addFirst(new Pair<>(root, this.tree.children(root).iterator()));
    }

    @Override
    public boolean hasNext() {
        return (!nodesToVisit.isEmpty());
    }

    @Override
    public Position<T> next() {
        if (this.nodesToVisit.isEmpty()) {
            throw new NoSuchElementException();
        }
        Position<Pair<Position<T>, Iterator<? extends Position<T>>>> currentPos = this.nodesToVisit.first();
        Pair<Position<T>, Iterator<? extends Position<T>>> current = currentPos.getElement();
        // If children not yet visited, add them before current and descend
        if (current.getSecond().hasNext()) {
            Position<T> child = current.getSecond().next();
            Iterator<? extends Position<T>> childIter = this.tree.children(child).iterator();
            Pair<Position<T>, Iterator<? extends Position<T>>> newPair = new Pair<>(child, childIter);
            this.nodesToVisit.addBefore(currentPos, newPair);
            return next(); // Check if the new child has children
        } else {
            // All children already processed, so we can pop and return this node
            this.nodesToVisit.remove(currentPos);
            return current.getFirst();
        }
    }

}
