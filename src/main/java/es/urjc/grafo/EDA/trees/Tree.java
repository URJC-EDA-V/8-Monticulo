package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;


public interface Tree<E> extends Iterable<Position<E>> {

    /**
     * Returns whether the tree is empty.
     *
     * @return true if empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Replaces the element at a node.
     *
     * @param p
     * @param e
     * @return
     */
    E replace(Position<E> p, E e);

    /**
     * Returns the number of elements that are contained within the tree.
     *
     * @return the size of the tree.
     */
    int size();

    /**
     * Returns the root of the tree.
     *
     * @return
     */
    Position<E> root();

    /**
     * Returns the parent of a given node.
     *
     * @param v
     * @return
     */
    Position<E> parent(Position<E> v);

    /**
     * Returns an iterable collection of the children of a given node.
     *
     * @param v
     * @return
     */
    Iterable<? extends Position<E>> children(Position<E> v);

    /**
     * Returns whether a given node is internal.
     *
     * @param v
     * @return
     */
    boolean isInternal(Position<E> v);

    /**
     * Returns whether a given node is external.
     *
     * @param v
     * @return
     */
    boolean isLeaf(Position<E> v);

    /**
     * Returns whether a given node is the root of the tree.
     *
     * @param v
     * @return
     */
    boolean isRoot(Position<E> v);
}