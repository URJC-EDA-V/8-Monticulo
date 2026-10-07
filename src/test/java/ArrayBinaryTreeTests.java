import es.urjc.grafo.EDA.trees.binaryTrees.ArrayBinaryTree;
import es.urjc.grafo.EDA.utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ArrayBinaryTreeTests {

    public ArrayBinaryTreeTests() {
    }

    /**
     * Test of left method, of class ArrayBinaryTree.
     */
    @Test
    public void testLeft() {
        System.out.println("left");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.left(plusRoot), twoNode);
    }

    /**
     * Test of left method, of class ArrayBinaryTree.
     */
    @Test
    public void testLeftException() {
        Assertions.assertThrows(RuntimeException.class,
                this::auxTestLeftException);
    }

    private void auxTestLeftException() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        tree.left(starNode);
    }

    /**
     * Test of right method, of class ArrayBinaryTree.
     */
    @Test
    public void testRight() {
        System.out.println("right");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.right(plusRoot), starNode);
    }

    /**
     * Test of right method, of class ArrayBinaryTree.
     */
    @Test
    public void testRightException() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Position<String> fiveNode = tree.insertLeft(starNode, "5");
        tree.left(starNode); // mantiene la lógica original
    }

    /**
     * Test of hasLeft method, of class ArrayBinaryTree.
     */
    @Test
    public void testHasLeft() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Assertions.assertTrue(tree.hasLeft(plusRoot));
        Assertions.assertFalse(tree.hasLeft(starNode));
    }

    /**
     * Test of hasRight method, of class ArrayBinaryTree.
     */
    @Test
    public void testHasRight() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Assertions.assertTrue(tree.hasRight(plusRoot));
        Assertions.assertFalse(tree.hasRight(starNode));
    }

    /**
     * Test of isInternal method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsInternal() {
        System.out.println("isInternal");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertTrue(tree.isInternal(starNode));
        Assertions.assertFalse(tree.isInternal(fiveNode));
    }

    /**
     * Test of isLeaf method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsLeaf() {
        System.out.println("isLeaf");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertFalse(tree.isLeaf(starNode));
        Assertions.assertTrue(tree.isLeaf(fiveNode));
    }

    /**
     * Test of isRoot method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsRoot() {
        System.out.println("isRoot");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertTrue(tree.isRoot(plusRoot));
        Assertions.assertFalse(tree.isRoot(starNode));
    }

    /**
     * Test of root method, of class ArrayBinaryTree.
     */
    @Test
    public void testRoot() {
        System.out.println("root");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.root(), plusRoot);
    }

    /**
     * Test of replace method, of class ArrayBinaryTree.
     */
    @Test
    public void testReplace() {
        System.out.println("replace");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        tree.replace(starNode, "+");
        Assertions.assertEquals(starNode.getElement(), "+");
    }

    /**
     * Test of sibling method, of class ArrayBinaryTree.
     */
    @Test
    public void testSibling() {
        System.out.println("sibling");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.sibling(twoNode), starNode);
    }

    /**
     * Test of sibling method, of class ArrayBinaryTree.
     */
    @Test
    public void testSiblingException(){
        Assertions.assertThrows(RuntimeException.class,
                this::auxTestSiblingException);
    }

    private void auxTestSiblingException() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        tree.sibling(plusRoot);
    }

    /**
     * Test of addRoot method, of class ArrayBinaryTree.
     */
    @Test
    public void testAddRoot() {
        System.out.println("addRoot");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Assertions.assertEquals(tree.root(), plusRoot);
    }

    /**
     * Test of insertLeft method, of class ArrayBinaryTree.
     */
    @Test
    public void testInsertLeft() {
        System.out.println("insertLeft");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Assertions.assertEquals(tree.left(plusRoot), twoNode);
    }

    /**
     * Test of insertRight method, of class ArrayBinaryTree.
     */
    @Test
    public void testInsertRight() {
        System.out.println("insertRight");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Assertions.assertEquals(tree.right(plusRoot), starNode);
    }

    /**
     * Test of remove method, of class ArrayBinaryTree.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        tree.remove(twoNode);
        Assertions.assertFalse(tree.hasLeft(plusRoot));
    }

    /**
     * Test of remove method, of class ArrayBinaryTree.
     */
    @Test
    public void testRemoveException() {
        Assertions.assertThrows(RuntimeException.class,
                this::auxTestRemoveException);
    }

    private void auxTestRemoveException() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        tree.removeIfLeaf(starNode);
    }

    /**
     * Test of swap method, of class ArrayBinaryTree.
     */
    @Test
    public void testSwapElements() {
        System.out.println("swap");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> leftNode = tree.insertLeft(plusRoot, "2");
        Position<String> rightNode = tree.insertRight(plusRoot, "3");
        tree.swapElements(leftNode, rightNode);
        StringBuilder inorderBuilder = new StringBuilder();
        for (Position<String> position : tree) {
            inorderBuilder.append(position.getElement());
        }
        Assertions.assertEquals("3+2", inorderBuilder.toString());
    }

    /**
     * Test of isEmpty method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Assertions.assertTrue(tree.isEmpty());
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertFalse(tree.isEmpty());
    }

    /**
     * Test of parent method, of class ArrayBinaryTree.
     */
    @Test
    public void testParent() {
        System.out.println("parent");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.parent(twoNode), plusRoot);
    }

    /**
     * Test of parent method, of class ArrayBinaryTree.
     */
    @Test
    public void testParentException() {
        Assertions.assertThrows(RuntimeException.class,
                this::auxTestParentException);
    }

    private void auxTestParentException() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        tree.parent(plusRoot);
    }

    /**
     * Test of children method, of class ArrayBinaryTree.
     */
    @Test
    public void testChildren() {
        System.out.println("children");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        List<Position<String>> expectedChildren = new ArrayList<>();
        expectedChildren.add(twoNode);
        expectedChildren.add(starNode);
        Iterator<Position<String>> expectedIterator = expectedChildren.iterator();
        for (Position<String> child : tree.children(plusRoot)) {
            Position<String> next = expectedIterator.next();
            Assertions.assertEquals(child, next);
        }
    }

    /**
     * Test of iterator method, of class ArrayBinaryTree.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        tree.insertRight(plusRoot, "3");
        StringBuilder inorderBuilder = new StringBuilder();
        for (Position<String> position : tree) {
            inorderBuilder.append(position.getElement());
        }
        Assertions.assertEquals("2+3", inorderBuilder.toString());
    }

    /**
     * Test of attach method, of class ArrayBinaryTree.
     */
    @Test
    public void testAttach() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        ArrayBinaryTree<String> leftSubtreeTree = new ArrayBinaryTree<>();
        leftSubtreeTree.addRoot("*");
        leftSubtreeTree.insertLeft(leftSubtreeTree.root(), "3");
        leftSubtreeTree.insertRight(leftSubtreeTree.root(), "9");
        ArrayBinaryTree<String> rightSubtreeTree = new ArrayBinaryTree<>();
        rightSubtreeTree.addRoot("-");
        rightSubtreeTree.insertLeft(rightSubtreeTree.root(), "1");
        rightSubtreeTree.insertRight(rightSubtreeTree.root(), "7");
        tree.attachLeft(fiveNode, leftSubtreeTree);
        tree.attachRight(fiveNode, rightSubtreeTree);
        Assertions.assertEquals(tree.left(fiveNode).getElement(), "*");
        Assertions.assertEquals(tree.right(fiveNode).getElement(), "-");
    }

    /**
     * Test of attach method to a non empty node, of class ArrayBinaryTree.
     */
    @Test
    public void testAttachException1() {
        Assertions.assertThrows(RuntimeException.class,
                this::auxTestAttachException1);
    }

    private void auxTestAttachException1() {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        ArrayBinaryTree<String> leftSubtreeTree = new ArrayBinaryTree<>();
        leftSubtreeTree.addRoot("*");
        leftSubtreeTree.insertLeft(leftSubtreeTree.root(), "3");
        leftSubtreeTree.insertRight(leftSubtreeTree.root(), "9");
        ArrayBinaryTree<String> rightSubtreeTree = new ArrayBinaryTree<>();
        rightSubtreeTree.addRoot("-");
        rightSubtreeTree.insertLeft(rightSubtreeTree.root(), "1");
        rightSubtreeTree.insertRight(rightSubtreeTree.root(), "7");
        tree.attachLeft(starNode, leftSubtreeTree);
        tree.attachRight(starNode, rightSubtreeTree);
    }

    /**
     * Test of attach method, of class ArrayBinaryTree.
     */
    @Test
    public void testAttachException2() {
        Assertions.assertThrows(RuntimeException.class,
                this::auxTestAttachException2);
    }

    private void auxTestAttachException2(){
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertLeft(starNode, "5");

        ArrayBinaryTree<String> leftSubtreeTree = new ArrayBinaryTree<>();
        leftSubtreeTree.addRoot("*");
        leftSubtreeTree.insertLeft(leftSubtreeTree.root(), "3");
        leftSubtreeTree.insertRight(leftSubtreeTree.root(), "9");
        tree.attachLeft(starNode, leftSubtreeTree);
    }

    @Test
    public void testArrayResized(){
        int numberOfNodes = 100;
        ArrayBinaryTree<Integer> tree = new ArrayBinaryTree<>(1);
        Position<Integer> currentPosition = tree.addRoot(0);
        Queue<Position<Integer>> queue = new LinkedList<>();
        for (int i = 1; i < numberOfNodes; i++) {
            if (i % 2 == 1) {
                queue.add(tree.insertLeft(currentPosition, i));
            }
            else {
                queue.add(tree.insertRight(currentPosition, i));
                currentPosition = queue.poll();
            }
        }
    }

    /**
     * Test of subTree method, of class ArrayBinaryTree.
     */
    @Test
    public void testSubTree() {
        System.out.println("subTree");
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Position<String> threeNode = tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        ArrayBinaryTree<String> extractedSubtree = (ArrayBinaryTree<String>) tree.subTree(starNode);
        Assertions.assertEquals(extractedSubtree.root().getElement(), starNode.getElement());
        Assertions.assertEquals(extractedSubtree.left(extractedSubtree.root()).getElement(), threeNode.getElement());
        Assertions.assertEquals(extractedSubtree.right(extractedSubtree.root()).getElement(), fiveNode.getElement());
    }

}
