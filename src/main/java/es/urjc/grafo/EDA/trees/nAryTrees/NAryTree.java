package es.urjc.grafo.EDA.trees.nAryTrees;

import es.urjc.grafo.EDA.trees.Tree;
import es.urjc.grafo.EDA.utils.Position;

public interface NAryTree<E> extends Tree<E> {

    /**
     * Adds a root node to an empty tree
     *
     * @param e
     * @return
     */
    Position<E> addRoot(E e);

    /**
     * Add a new node whose parent is pointed by a given position.
     *
     * @param element
     * @param p       The position of the parent, e the element stored in the new
     *                created node.
     * @return
     */
    Position<E> add(E element, Position<E> p);

    /**
     * Add a new node whose parent is pointed by a given position, and set the
     * child at the position n if possible.
     *
     * @param element
     * @param p       The position of the parent, e the element stored in the new
     *                created node.
     * @param n
     * @return
     */
    Position<E> add(E element, Position<E> p, final int n);

    /**
     * Swap the elements at two nodes
     *
     * @param p1
     * @param p2
     */
    void swapElements(Position<E> p1, Position<E> p2);

    /**
     * Remove a node and its corresponding subtree rooted at node.
     *
     * @param p The position of the node to be removed.
     */
    void remove(Position<E> p);

    /**
     * Create un new tree from node v of the same type that invoqued class.
     *
     * @param v new root node
     * @return The new tree.
     */
    NAryTree<E> subTree(Position<E> v);


    /**
     * Attach tree t as children of node p if t and "this" are of the same class.
     *
     * @param p - Node in which t will be attached or null if t is attached in the root.
     * @param t - Tree to be attached.
     */
    void attach(Position<E> p, NAryTree<E> t);

}
