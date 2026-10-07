
package es.urjc.grafo.EDA.trees.binaryTrees;


import es.urjc.grafo.EDA.utils.Position;

import java.util.Iterator;
import java.util.Stack;


public class InorderBinaryTreeIterator<T> implements Iterator<Position<T>> {


    private Stack<Position<T>> nodeStack = new Stack<>();
    private final BinaryTree<T> tree;

    public InorderBinaryTreeIterator(BinaryTree <T> tree) {
        this(tree,tree.root());
    }


    public InorderBinaryTreeIterator(BinaryTree <T> tree, Position<T> node) {
        this.tree = tree;
        this.pushLeftInDepth(node);
    }

    @Override
    public boolean hasNext() {
        return (!nodeStack.isEmpty());
    }

    private void pushLeftInDepth(Position<T> node){
        this.nodeStack.push(node);
        while (tree.hasLeft(node)) {
            node = tree.left(node);
            nodeStack.push(node);
        }
    }

    /**
     * This method visits the nodes of a tree by following a breadth-first search
     */
    @Override
    public Position<T> next() {
        Position<T> aux = nodeStack.pop();
        if (tree.hasRight(aux)) {
            pushLeftInDepth(tree.right(aux));
        }
        return aux;
    }
    
}



