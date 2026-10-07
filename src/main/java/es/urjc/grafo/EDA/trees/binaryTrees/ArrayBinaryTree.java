package es.urjc.grafo.EDA.trees.binaryTrees;

import es.urjc.grafo.EDA.utils.Position;

import java.util.*;


public class ArrayBinaryTree<E> implements BinaryTree<E> {

    private static final int CAPACITY = 100;
    private BTPos<E>[] elements;
    private int size = 0;

    public ArrayBinaryTree() {
        this(CAPACITY);
    }

    public ArrayBinaryTree(int capacity) {
        this.elements = new BTPos[capacity];
    }

    private BTPos<E> checkPosition(Position<E> position) {
        if (!(position instanceof BTPos)) {
            throw new RuntimeException("The position is invalid");
        }
        return (BTPos<E>) position;
    }

    @Override
    public Position<E> left(Position<E> position) {
        if (!this.hasLeft(position)) {
            throw new RuntimeException("No left child");
        }
        BTPos<E> node = checkPosition(position);
        return this.elements[2 * node.getIndex() + 1];
    }

    @Override
    public Position<E> right(Position<E> position) {
        if (!this.hasRight(position)) {
            throw new RuntimeException("No right child");
        }
        BTPos<E> node = checkPosition(position);
        return this.elements[2 * node.getIndex() + 2];
    }

    @Override
    public boolean hasLeft(Position<E> position) {
        BTPos<E> node = checkPosition(position);
        return 2 * node.getIndex() + 1 < this.elements.length && this.elements[2 * node.getIndex() + 1] != null;
    }

    @Override
    public boolean hasRight(Position<E> position) {
        BTPos<E> node = checkPosition(position);
        return 2 * node.getIndex() + 2 < this.elements.length && this.elements[2 * node.getIndex() + 2] != null;
    }

    @Override
    public boolean isInternal(Position<E> position) {
        return !this.isLeaf(position);
    }

    @Override
    public boolean isLeaf(Position<E> position) {
        return !this.hasLeft(position) && !this.hasRight(position);
    }

    @Override
    public boolean isRoot(Position<E> position) {
        BTPos<E> node = this.checkPosition(position);
        return node == this.elements[0];
    }

    @Override
    public Position<E> root() {
        if (this.elements[0] == null) {
            throw new NoSuchElementException();
        }
        return this.elements[0];
    }

    @Override
    public E replace(Position<E> position, E element) {
        BTPos<E> node = this.checkPosition(position);
        E replaced = node.getElement();
        node.setElement(element);
        return replaced;
    }

    @Override
    public Position<E> sibling(Position<E> position) {
        BTPos<E> node = this.checkPosition(position);
        if (this.hasLeft(this.parent(position)) && this.left(this.parent(position)) == position) {
            if (this.hasRight(this.parent(position))) {
                return this.right(this.parent(position));
            } else {
                throw new NoSuchElementException();
            }
        } else if (this.hasRight(this.parent(position)) && this.right(this.parent(position)) == position) {
            if (this.hasLeft(this.parent(position))) {
                return this.left(this.parent(position));
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Position<E> addRoot(E element) {
        if (this.elements[0] == null) {
            this.elements[0] = new BTPos<>(element, 0);
            this.size++;
            return this.root();
        }
        throw new RuntimeException("There is already a root");
    }

    @Override
    public Position<E> insertLeft(Position<E> position, E element) {
        BTPos<E> node = this.checkPosition(position);
        if (this.hasLeft(node)) {
            throw new RuntimeException("Cannot insert a left child, there is already a left child");
        }
        BTPos<E> newNode = new BTPos<>(element, 2 * node.getIndex() + 1);
        this.resizeIfNecessary(newNode.getIndex());
        this.elements[2 * node.getIndex() + 1] = newNode;
        this.size++;
        return newNode;
    }

    @Override
    public Position<E> insertRight(Position<E> position, E element) {
        BTPos<E> node = this.checkPosition(position);
        if (this.hasRight(node)) {
            throw new RuntimeException("Cannot insert a right child, there is already a right child");
        }
        BTPos<E> newNode = new BTPos<>(element, 2 * node.getIndex() + 2);
        this.resizeIfNecessary(newNode.getIndex());
        this.elements[2 * node.getIndex() + 2] = newNode;
        this.size++;
        return newNode;
    }

    private void resizeIfNecessary(int index) {
        if (this.elements.length <= index + 1) {
            int newLength = this.elements.length * 2;
            while (newLength < index + 1) {
                newLength *= 2;
            }
            BTPos<E>[] newElements = new BTPos[newLength];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            this.elements = newElements;
        }
    }

    @Override
    public E remove(Position<E> position) {
        return this.cut(position);
    }

    /**
     * Removes only the node in the given position if it has no children.
     * If it has children, it throws an exception.
     *
     * @param position
     * @return
     */
    public E removeIfLeaf(Position<E> position) {
        if (this.hasLeft(position) || this.hasRight(position)) {
            throw new RuntimeException("Has children, so I cannot remove");
        }
        BTPos<E> node = this.checkPosition(position);
        this.elements[node.getIndex()] = null;
        this.size--;
        return node.getElement();
    }

    public E cut(Position<E> position) {
        BTPos<E> node = this.checkPosition(position);

        this.recursiveRemove(node);

        return node.getElement();
    }

    private void recursiveRemove(Position<E> position) {
        if (this.hasLeft(position)) {
            recursiveRemove(this.left(position));
        }
        if (this.hasRight(position)) {
            recursiveRemove(this.right(position));
        }
        this.elements[((BTPos<E>) position).getIndex()] = null;
        this.size--;
    }

    @Override
    public void swapElements(Position<E> position1, Position<E> position2) {
        BTPos<E> node1 = this.checkPosition(position1);
        BTPos<E> node2 = this.checkPosition(position2);
        int index1 = node1.getIndex();
        int index2 = node2.getIndex();
        E elementTemp = node1.getElement();
        this.elements[index1].setElement(node2.getElement());
        this.elements[index2].setElement(elementTemp);
    }

    @Override
    public boolean isEmpty() {
        return this.elements[0] == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Position<E> parent(Position<E> position) {
        BTPos<E> node = this.checkPosition(position);
        if (this.isRoot(node)) {
            throw new RuntimeException("Root has no parent");
        }
        return this.elements[(int) Math.floor((double) (node.getIndex() - 1) / 2)];
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> position) {
        BTPos<E> node = this.checkPosition(position);
        List<Position<E>> children = new ArrayList<>(2);
        if (this.hasLeft(node) || this.hasRight(node)) {
            if (this.hasLeft(node)) {
                children.add(this.left(node));
            }
            if (this.hasRight(node)) {
                children.add(this.right(node));
            }
        }
        return children;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this);
    }

    @Override
    public void attachLeft(Position<E> position, BinaryTree<E> treeToAttach) {
        if (this.hasLeft(position)) {
            throw new RuntimeException("Cannot attach a left child, there is already a left child");
        }
        Position<E> root = this.insertLeft(position, treeToAttach.root().getElement());
        this.attachRecursive(root, treeToAttach, treeToAttach.root());
    }

    private void attachRecursive(Position<E> positionTree, BinaryTree<E> subtree, Position<E> positionSubtree) {
        if (subtree.hasLeft(positionSubtree)) {
            this.insertLeft(positionTree, subtree.left(positionSubtree).getElement());
            this.attachRecursive(this.left(positionTree), subtree, subtree.left(positionSubtree));
        }
        if (subtree.hasRight(positionSubtree)) {
            this.insertRight(positionTree, subtree.right(positionSubtree).getElement());
            this.attachRecursive(this.right(positionTree), subtree, subtree.right(positionSubtree));
        }
    }

    @Override
    public void attachRight(Position<E> position, BinaryTree<E> treeToAttach) {
        if (this.hasRight(position)) {
            throw new RuntimeException("Cannot attach a right child, there is already a right child");
        }
        Position<E> root = this.insertRight(position, treeToAttach.root().getElement());
        this.attachRecursive(root, treeToAttach, treeToAttach.root());
    }

    @Override
    public BinaryTree<E> subTree(Position<E> position) {
        BTPos<E> node = this.checkPosition(position);
        BinaryTree<E> subtree = new ArrayBinaryTree<>();
        subtree.addRoot(node.getElement());
        Queue<BTPos<E>> nodesQueue = new LinkedList<>();
        Queue<BTPos<E>> subTreeNodesQueue = new LinkedList<>();
        subTreeNodesQueue.add((BTPos<E>) subtree.root());
        nodesQueue.add(node);
        while (!nodesQueue.isEmpty()) {
            BTPos<E> currentParent = nodesQueue.poll();
            BTPos<E> currentParentInSubtree = subTreeNodesQueue.poll();
            if (this.hasLeft(currentParent)) {
                BTPos<E> inserted = (BTPos<E>) subtree.insertLeft(currentParentInSubtree, this.left(currentParent).getElement());
                nodesQueue.add((BTPos<E>) this.left(currentParent));
                subTreeNodesQueue.add(inserted);
            }
            if (this.hasRight(currentParent)) {
                BTPos<E> inserted = (BTPos<E>) subtree.insertRight(currentParentInSubtree, this.right(currentParent).getElement());
                nodesQueue.add((BTPos<E>) this.right(currentParent));
                subTreeNodesQueue.add(inserted);
            }
        }

        this.cut(position);

        return subtree;
    }

    private static class BTPos<E> implements Position<E> {

        private E element;
        private int index;

        public BTPos(E element, int index) {
            this.element = element;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }

}
