package es.urjc.grafo.EDA.trees.binaryTrees;


import es.urjc.grafo.EDA.utils.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LinkedBinaryTree<E> implements BinaryTree<E> {

    private BTNode<E> root = null;
    private int size = 0;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
    }

    /**
     * Returns whether the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether a node is internal.
     *
     * @param position
     * @return
     */
    @Override
    public boolean isInternal(Position<E> position) {
        checkPosition(position);
        return (hasLeft(position) || hasRight(position));
    }

    /**
     * Returns whether a node is external.
     *
     * @param position
     * @return
     */
    @Override
    public boolean isLeaf(Position<E> position) {
        return !isInternal(position);
    }

    /**
     * Returns whether a node is the root.
     *
     * @param position
     * @return
     */
    @Override
    public boolean isRoot(Position<E> position) {
        checkPosition(position);
        return (position == root());
    }

    /**
     * Returns whether a node has a left child.
     *
     * @param position
     * @return
     */
    @Override
    public boolean hasLeft(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        return (node.getLeft() != null);
    }

    /**
     * Returns whether a node has a right child.
     *
     * @param position
     * @return
     */
    @Override
    public boolean hasRight(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        return (node.getRight() != null);
    }

    /**
     * Returns the root of the tree.
     *
     * @return
     */
    @Override
    public Position<E> root() {
        if (root == null) {
            throw new RuntimeException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the left child of a node.
     *
     * @param position
     * @return
     */
    @Override
    public Position<E> left(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        Position<E> leftPos = node.getLeft();
        if (leftPos == null) {
            throw new RuntimeException("No left child");
        }
        return leftPos;
    }

    /**
     * Returns the right child of a node.
     *
     * @param position
     * @return
     */
    @Override
    public Position<E> right(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        Position<E> rightPos = node.getRight();
        if (rightPos == null) {
            throw new RuntimeException("No right child");
        }
        return rightPos;
    }

    /**
     * Returns the parent of a node.
     *
     * @param position
     */
    @Override
    public Position<E> parent(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new RuntimeException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     *
     * @param position
     */
    @Override
    public Iterable<? extends Position<E>> children(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        List<Position<E>> children = new ArrayList<>(2);
        if (node.getLeft() != null) {
            children.add(node.getLeft());
        }
        if (node.getRight() != null) {
            children.add(node.getRight());
        }
        return Collections.unmodifiableList(children);
    }

    /**
     * Returns an iterator of the elements stored at the nodes.
     */
    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this, root);
    }

    /**
     * Replaces the element at a node.
     *
     * @param position updated position.
     * @param element  element introduced in position position.
     * @return
     */
    @Override
    public E replace(Position<E> position, E element) {
        BTNode<E> node = checkPosition(position);
        E temp = position.getElement();
        node.setElement(element);
        return temp;
    }

    /**
     * Return the sibling of a node
     *
     * @param position
     * @return
     */
    @Override
    public Position<E> sibling(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        BTNode<E> parentPos = node.getParent();
        if (parentPos != null) {
            BTNode<E> sibPos;
            BTNode<E> leftPos = parentPos.getLeft();
            if (leftPos == node) {
                sibPos = parentPos.getRight();
            } else {
                sibPos = parentPos.getLeft();
            }
            if (sibPos != null) {
                return sibPos;
            }
        }
        throw new RuntimeException("No sibling");
    }

    /**
     * Adds a root node to an empty tree
     *
     * @param element
     * @return
     */
    @Override
    public Position<E> addRoot(E element) {
        if (!isEmpty()) {
            throw new RuntimeException("Tree already has a root");
        }
        root = new BTNode<>(element, null, null, null);
        this.size++;
        return root;
    }

    /**
     * Inserts a left child at a given node.
     *
     * @param position
     * @param element
     * @return
     */
    @Override
    public Position<E> insertLeft(Position<E> position, E element) {
        BTNode<E> node = checkPosition(position);
        Position<E> leftPos = node.getLeft();
        if (leftPos != null) {
            throw new RuntimeException("Node already has a left child");
        }
        BTNode<E> newNode = new BTNode<>(element, node, null, null);
        node.setLeft(newNode);
        this.size++;
        return newNode;
    }

    /**
     * Inserts a right child at a given node.
     *
     * @param position
     * @param element
     * @return
     */
    @Override
    public Position<E> insertRight(Position<E> position, E element) {
        BTNode<E> node = checkPosition(position);
        Position<E> rightPos = node.getRight();
        if (rightPos != null) {
            throw new RuntimeException("Node already has a right child");
        }
        BTNode<E> newNode = new BTNode<>(element, node, null, null);
        node.setRight(newNode);
        this.size++;
        return newNode;
    }

    /**
     * Removes a node with zero or one child. No se cortan los subárboles, se conectan.
     * Es decir, solo se elimina la posición position, sin perder los descendientes de position.
     * Si position tiene dos hijos, no se puede eliminar.
     * Si position tiene un solo hijo, este pasa a ser hijo del padre de position.
     * Si position no tiene hijos, simplemente se elimina.
     * Si position es la raíz, la nueva raíz será el hijo de position (si lo tiene).
     *
     * @param position
     * @return
     */
    @Override
    public E remove(Position<E> position) {
        BTNode<E> node = checkPosition(position);
        BTNode<E> leftPos = node.getLeft();
        BTNode<E> rightPos = node.getRight();
        if (leftPos != null && rightPos != null) {
            throw new RuntimeException("Cannot remove node with two children");
        }
        BTNode<E> child; // the only child of v, if any
        // v is a leaf
        if (leftPos != null) {
            child = leftPos;
        } else child = rightPos;
        if (node == root) { // v is the root
            if (child != null) {
                child.setParent(null);
            }
            root = child;
        } else { // v is not the root
            BTNode<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
            if (child != null) {
                child.setParent(parent);
            }
        }
        this.size--;
        return position.getElement();
    }

    /**
     * Swap the elements at two nodes.
     *
     * @param position1
     * @param position2
     */
    @Override
    public void swapElements(Position<E> position1, Position<E> position2) {
        BTNode<E> node1 = checkPosition(position1);
        BTNode<E> node2 = checkPosition(position2);
        E temp = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(temp);
    }


    @Override
    public void attachLeft(Position<E> position, BinaryTree<E> treeToAttach) {
        checkPosition(position);
        if (treeToAttach instanceof LinkedBinaryTree) {
            this.attachLeft(position, (LinkedBinaryTree<E>) treeToAttach);
        } else {
            this.attachLeftGeneral(position, treeToAttach);
        }
    }

    @Override
    public void attachRight(Position<E> position, BinaryTree<E> treeToAttach) {
        checkPosition(position);
        if (treeToAttach instanceof LinkedBinaryTree) {
            this.attachRight(position, (LinkedBinaryTree<E>) treeToAttach);
        } else {
            this.attachRight(position, treeToAttach);
        }
    }

    private void attachLeftGeneral(Position<E> position, BinaryTree<E> treeToAttach) {
        if (this.hasLeft(position)) {
            throw new RuntimeException("Cannot attach a left child, there is already a left child");
        }
        Position<E> root = this.insertLeft(position, treeToAttach.root().getElement());
        this.attachRecursive(root, treeToAttach, treeToAttach.root());
    }

    private void attachRecursive(Position<E> position, BinaryTree<E> subtree, Position<E> positionSubtree) {
        if (subtree.hasLeft(positionSubtree)) {
            this.insertLeft(position, subtree.left(positionSubtree).getElement());
            this.attachRecursive(this.left(position), subtree, subtree.left(positionSubtree));
        }
        if (subtree.hasRight(positionSubtree)) {
            this.insertRight(position, subtree.right(positionSubtree).getElement());
            this.attachRecursive(this.right(position), subtree, subtree.right(positionSubtree));
        }
    }

    private void attachRightGeneral(Position<E> position, BinaryTree<E> treeToAttach) {
        if (this.hasRight(position)) {
            throw new RuntimeException("Cannot attach a right child, there is already a right child");
        }
        Position<E> root = this.insertRight(position, treeToAttach.root().getElement());
        this.attachRecursive(root, treeToAttach, treeToAttach.root());
    }

    /**
     * If v is a good binary tree node, cast to BTPosition, else throw exception
     */
    private BTNode<E> checkPosition(Position<E> position) {
        if (!(position instanceof BTNode)) {
            throw new RuntimeException("The position is invalid");
        }
        return (BTNode<E>) position;
    }

    // Auxiliary methods

    /**
     * Creates a list storing the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     *
     * @param position
     * @param listToStorePositionsInOrder
     */
    protected void preorderPositions(Position<E> position, List<Position<E>> listToStorePositionsInOrder) {
        listToStorePositionsInOrder.add(position);
        if (hasLeft(position)) {
            preorderPositions(left(position), listToStorePositionsInOrder); // recurse on left child
        }
        if (hasRight(position)) {
            preorderPositions(right(position), listToStorePositionsInOrder); // recurse on right child
        }
    }

    /**
     * Creates a list storing the nodes in the subtree of a node, ordered
     * according to the inorder traversal of the subtree.
     *
     * @param position
     * @param listToStorePositionsInOrder
     */
    protected void inorderPositions(Position<E> position, List<Position<E>> listToStorePositionsInOrder) {
        if (hasLeft(position)) {
            inorderPositions(left(position), listToStorePositionsInOrder); // recurse on left child
        }
        listToStorePositionsInOrder.add(position);
        if (hasRight(position)) {
            inorderPositions(right(position), listToStorePositionsInOrder); // recurse on right child
        }
    }

    /**
     * Create a new tree from node position.
     *
     * @param position new root node
     * @return The new tree.
     */
    public LinkedBinaryTree<E> subTree(Position<E> position) {
        BTNode<E> newRoot = checkPosition(position);

        if (newRoot == root) {
            this.root = null;
            this.size = 0;
        } else {
            if (newRoot.parent.left == newRoot) newRoot.parent.left = null;
            else newRoot.parent.right = null;
        }

        newRoot.parent = null;

        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        tree.root = newRoot;
        int count = 0;
        Iterator<Position<E>> iterator = new InorderBinaryTreeIterator<>(this, position);
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        tree.size = count;
        this.size -= count;
        return tree;
    }

    /**
     * Attach tree t as left children of node position
     */
    private void attachLeft(Position<E> position, LinkedBinaryTree<E> treeToAttach) {
        BTNode<E> node = checkPosition(position);

        if (treeToAttach == this) {
            throw new RuntimeException("Cannot attach a tree over himself");
        }

        if (hasLeft(position)) throw new RuntimeException("Cannot attach a tree in a non empty method");

        if (treeToAttach != null && !treeToAttach.isEmpty()) {
            this.size += treeToAttach.size();
            BTNode<E> r = checkPosition(treeToAttach.root());
            node.setLeft(r);
            r.setParent(node);
            treeToAttach.root = null;
        }
    }

    /**
     * Attach tree t as right children of node position
     */
    private void attachRight(Position<E> position, LinkedBinaryTree<E> treeToAttach) {
        BTNode<E> node = checkPosition(position);

        if (treeToAttach == this) throw new RuntimeException("Cannot attach a tree over himself");

        if (hasRight(position)) throw new RuntimeException("Cannot attach a tree in a non empty method");

        if (treeToAttach != null && !treeToAttach.isEmpty()) {
            this.size += treeToAttach.size();
            BTNode<E> r = checkPosition(treeToAttach.root());
            node.setRight(r);
            r.setParent(node);
            treeToAttach.root = null;
        }
    }

    protected static class BTNode<T> implements Position<T> {

        private T element;
        private BTNode<T> left, right, parent;

        /**
         * Main constructor
         *
         * @param element
         * @param parent
         * @param left
         * @param right
         */
        public BTNode(T element, BTNode<T> parent, BTNode<T> left, BTNode<T> right) {
            setElement(element);
            setParent(parent);
            setLeft(left);
            setRight(right);
        }

        /**
         * Returns the element stored at this position
         *
         * @return
         */
        @Override
        public T getElement() {
            return element;
        }

        /**
         * Sets the element stored at this position
         *
         * @param element
         */
        public final void setElement(T element) {
            this.element = element;
        }

        /**
         * Returns the left child of this position
         *
         * @return
         */
        public final BTNode<T> getLeft() {
            return left;
        }

        /**
         * Sets the left child of this position
         *
         * @param node
         */
        public final void setLeft(BTNode<T> node) {
            left = node;
        }

        /**
         * Returns the right child of this position
         *
         * @return
         */
        public final BTNode<T> getRight() {
            return right;
        }

        /**
         * Sets the right child of this position
         *
         * @param node
         */
        public final void setRight(BTNode<T> node) {
            right = node;
        }

        /**
         * Returns the parent of this position
         *
         * @return
         */
        public final BTNode<T> getParent() {
            return parent;
        }

        /**
         * Sets the parent of this position
         *
         * @param parent
         */
        public final void setParent(BTNode<T> parent) {
            this.parent = parent;
        }
    }

}
