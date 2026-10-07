import es.urjc.grafo.EDA.trees.binaryTrees.LinkedBinaryTree;
import es.urjc.grafo.EDA.utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedBinaryTreeTests {

    private static String collectInorder(LinkedBinaryTree<String> tree) {
        StringBuilder inorderBuilder = new StringBuilder();
        for (Position<String> position : tree) {
            inorderBuilder.append(position.getElement());
        }
        return inorderBuilder.toString();
    }

    /**
     * Test of isEmpty method, of class LinkedBinaryTree.
     */
    @Test
    public void testIsEmpty() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Assertions.assertTrue(tree.isEmpty());
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertFalse(tree.isEmpty());
    }

    /**
     * Test of isInternal method, of class LinkedBinaryTree.
     */
    @Test
    public void testIsInternal() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertTrue(tree.isInternal(starNode));
        Assertions.assertFalse(tree.isInternal(fiveNode));
    }

    /**
     * Test of isLeaf method, of class LinkedBinaryTree.
     */
    @Test
    public void testIsLeaf() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        Assertions.assertFalse(tree.isLeaf(starNode));
        Assertions.assertTrue(tree.isLeaf(fiveNode));
    }

    /**
     * Test of isRoot method, of class LinkedBinaryTree.
     */
    @Test
    public void testIsRoot() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertTrue(tree.isRoot(plusRoot));
        Assertions.assertFalse(tree.isRoot(starNode));
    }

    /**
     * Test of hasLeft method, of class LinkedBinaryTree.
     */
    @Test
    public void testHasLeft() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Assertions.assertTrue(tree.hasLeft(plusRoot));
        Assertions.assertFalse(tree.hasLeft(starNode));
    }

    /**
     * Test of hasRight method, of class LinkedBinaryTree.
     */
    @Test
    public void testHasRight() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Assertions.assertTrue(tree.hasRight(plusRoot));
        Assertions.assertFalse(tree.hasRight(starNode));
    }

    /**
     * Test of root method, of class LinkedBinaryTree.
     */
    @Test
    public void testRoot() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.root(), plusRoot);
    }

    /**
     * Test of left method, of class LinkedBinaryTree.
     */
    @Test
    public void testLeft() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.left(plusRoot), twoNode);
    }

    /**
     * Test of left method, of class LinkedBinaryTree.
     */
    @Test
    public void testLeftException() {
        Assertions.assertThrows(RuntimeException.class, this::auxTestLeftException);
    }

    private void auxTestLeftException() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertRight(starNode, "5");
        tree.left(starNode);
    }

    /**
     * Test of right method, of class LinkedBinaryTree.
     */
    @Test
    public void testRight() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.right(plusRoot), starNode);
    }

    /**
     * Test of right method, of class LinkedBinaryTree.
     */
    @Test
    public void testRightException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
            Position<String> plusRoot = tree.addRoot("+");
            tree.insertLeft(plusRoot, "2");
            Position<String> starNode = tree.insertRight(plusRoot, "*");
            // starNode has no right child, so right(starNode) must throw
            tree.right(starNode);
        });
    }

    /**
     * Test of parent method, of class LinkedBinaryTree.
     */
    @Test
    public void testParent() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.parent(twoNode), plusRoot);
    }

    /**
     * Test of parent method, of class LinkedBinaryTree.
     */
    @Test
    public void testParentException() {
        Assertions.assertThrows(RuntimeException.class, this::auxTestParentException);
    }

    private void auxTestParentException() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        tree.parent(plusRoot);
    }

    /**
     * Test of children method, of class LinkedBinaryTree.
     */
    @Test
    public void testChildren() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        List<Position<String>> expectedChildren = new ArrayList<>();
        expectedChildren.add(twoNode);
        expectedChildren.add(starNode);
        Iterator<Position<String>> expectedIter = expectedChildren.iterator();
        for (Position<String> child : tree.children(plusRoot)) {
            Position<String> nextExpected = expectedIter.next();
            Assertions.assertEquals(child, nextExpected);
        }
    }

    /**
     * Test of iterator method, of class LinkedBinaryTree.
     */
    @Test
    public void testIterator() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        tree.insertRight(plusRoot, "3");
        StringBuilder inorder = new StringBuilder();
        for (Position<String> position : tree) {
            inorder.append(position.getElement());
        }
        Assertions.assertEquals("2+3", inorder.toString());
    }

    /**
     * Test of replace method, of class LinkedBinaryTree.
     */
    @Test
    public void testReplace() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        tree.replace(starNode, "+");
        Assertions.assertEquals("+", starNode.getElement());
    }

    /**
     * Test of sibling method, of class LinkedBinaryTree.
     */
    @Test
    public void testSibling() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertEquals(tree.sibling(twoNode), starNode);
    }

    /**
     * Test of sibling method, of class LinkedBinaryTree.
     */
    @Test
    public void testSiblingException() {
        Assertions.assertThrows(RuntimeException.class, this::auxTestSiblingException);
    }

    private void auxTestSiblingException() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        tree.sibling(plusRoot);
    }

    /**
     * Test of addRoot method, of class LinkedBinaryTree.
     */
    @Test
    public void testAddRoot() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Assertions.assertEquals(tree.root(), plusRoot);
    }

    /**
     * Test of insertLeft method, of class LinkedBinaryTree.
     */
    @Test
    public void testInsertLeft() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Assertions.assertEquals(tree.left(plusRoot), twoNode);
    }

    /**
     * Test of insertRight method, of class LinkedBinaryTree.
     */
    @Test
    public void testInsertRight() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Assertions.assertEquals(tree.right(plusRoot), starNode);
    }

    /**
     * Test of remove method, of class LinkedBinaryTree.
     */
    @Test
    public void testRemove() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        tree.remove(twoNode);
        Assertions.assertFalse(tree.hasLeft(plusRoot));
    }

    /**
     * Test of remove method, of class LinkedBinaryTree.
     */
    @Test
    public void testRemoveException() {
        Assertions.assertThrows(RuntimeException.class, this::auxTestRemoveException);
    }

    private void auxTestRemoveException() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        tree.remove(starNode);
    }

    /**
     * Test of attach method, of class LinkedBinaryTree.
     */
    @Test
    public void testAttach() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        LinkedBinaryTree<String> leftSubtreeTree = new LinkedBinaryTree<>();
        leftSubtreeTree.addRoot("*");
        leftSubtreeTree.insertLeft(leftSubtreeTree.root(), "3");
        leftSubtreeTree.insertRight(leftSubtreeTree.root(), "9");
        LinkedBinaryTree<String> rightSubtreeTree = new LinkedBinaryTree<>();
        rightSubtreeTree.addRoot("-");
        rightSubtreeTree.insertLeft(rightSubtreeTree.root(), "1");
        rightSubtreeTree.insertRight(rightSubtreeTree.root(), "7");
        tree.attachLeft(fiveNode, leftSubtreeTree);
        tree.attachRight(fiveNode, rightSubtreeTree);
        Assertions.assertEquals("*", tree.left(fiveNode).getElement());
        Assertions.assertEquals("-", tree.right(fiveNode).getElement());
    }

    /**
     * Test of attach method to a non-empty node, of class LinkedBinaryTree.
     */
    @Test
    public void testAttachException1() {
        Assertions.assertThrows(RuntimeException.class, this::auxTestAttachException1);
    }

    private void auxTestAttachException1() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        LinkedBinaryTree<String> leftSubtreeTree = new LinkedBinaryTree<>();
        leftSubtreeTree.addRoot("*");
        leftSubtreeTree.insertLeft(leftSubtreeTree.root(), "3");
        leftSubtreeTree.insertRight(leftSubtreeTree.root(), "9");
        LinkedBinaryTree<String> rightSubtreeTree = new LinkedBinaryTree<>();
        rightSubtreeTree.addRoot("-");
        rightSubtreeTree.insertLeft(rightSubtreeTree.root(), "1");
        rightSubtreeTree.insertRight(rightSubtreeTree.root(), "7");
        tree.attachLeft(starNode, leftSubtreeTree);
        tree.attachRight(starNode, rightSubtreeTree);
    }

    /**
     * Test of attach method, of class LinkedBinaryTree.
     */
    @Test
    public void testAttachException2() {
        Assertions.assertThrows(RuntimeException.class, this::auxTestAttachException2);
    }

    private void auxTestAttachException2() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertLeft(starNode, "5");

        LinkedBinaryTree<String> leftSubtreeTree = new LinkedBinaryTree<>();
        leftSubtreeTree.addRoot("*");
        leftSubtreeTree.insertLeft(leftSubtreeTree.root(), "3");
        leftSubtreeTree.insertRight(leftSubtreeTree.root(), "9");
        tree.attachLeft(starNode, leftSubtreeTree);
    }

    /**
     * Test of swapElements method, of class LinkedBinaryTree.
     */
    @Test
    public void testSwapElements() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> leftNode = tree.insertLeft(plusRoot, "2");
        Position<String> rightNode = tree.insertRight(plusRoot, "3");
        tree.swapElements(leftNode, rightNode);
        StringBuilder inorder = new StringBuilder();
        for (Position<String> position : tree) {
            inorder.append(position.getElement());
        }
        Assertions.assertEquals("3+2", inorder.toString());
    }

    /**
     * Test of subTree method, of class LinkedBinaryTree.
     */
    @Test
    public void testSubTree() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        Position<String> threeNode = tree.insertLeft(starNode, "3");
        Position<String> fiveNode = tree.insertRight(starNode, "5");
        LinkedBinaryTree<String> extractedSubtree = tree.subTree(starNode);
        Assertions.assertEquals(extractedSubtree.root(), starNode);
        Assertions.assertEquals(extractedSubtree.left(extractedSubtree.root()), threeNode);
        Assertions.assertEquals(extractedSubtree.right(extractedSubtree.root()), fiveNode);
    }

    // Enhanced tests added
    @Test
    public void testRootEmptyException() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Assertions.assertThrows(RuntimeException.class, tree::root);
    }

    @Test
    public void testAddRootException() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        tree.addRoot("root");
        Assertions.assertThrows(RuntimeException.class, () -> tree.addRoot("another"));
    }

    @Test
    public void testReplaceReturnsOldValue() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        String oldValue = tree.replace(starNode, "/");
        Assertions.assertEquals("*", oldValue);
        Assertions.assertEquals("/", starNode.getElement());
        Assertions.assertEquals("2+/", collectInorder(tree));
    }

    @Test
    public void testSiblingNoSiblingNonRootException() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        Position<String> twoNode = tree.insertLeft(plusRoot, "2");
        // no right sibling for twoNode
        Assertions.assertThrows(RuntimeException.class, () -> tree.sibling(twoNode));
    }

    @Test
    public void testInsertLeftRightExistingChildExceptions() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "L");
        tree.insertRight(plusRoot, "R");
        Assertions.assertThrows(RuntimeException.class, () -> tree.insertLeft(plusRoot, "L2"));
        Assertions.assertThrows(RuntimeException.class, () -> tree.insertRight(plusRoot, "R2"));
    }

    @Test
    public void testRemoveSingleChildPromotesChild() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> rootNode = tree.addRoot("root");
        Position<String> aNode = tree.insertLeft(rootNode, "A");
        Position<String> cNode = tree.insertLeft(aNode, "C");
        int sizeBefore = tree.size();
        tree.remove(aNode);
        Assertions.assertEquals(sizeBefore - 1, tree.size());
        Assertions.assertEquals(cNode, tree.left(rootNode));
        Assertions.assertEquals(rootNode, tree.parent(cNode));
    }

    @Test
    public void testSubTreeSizesAndDetach() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        int totalSize = tree.size(); // 5
        LinkedBinaryTree<String> extractedSubtree = tree.subTree(starNode);
        Assertions.assertEquals(3, extractedSubtree.size());
        Assertions.assertEquals(totalSize - 3, tree.size());
        Assertions.assertFalse(tree.hasRight(plusRoot));
        // subtree root should be starNode
        Assertions.assertTrue(extractedSubtree.isRoot(extractedSubtree.root()));
        Assertions.assertEquals("2+", collectInorder(tree));
        Assertions.assertEquals("3*5", collectInorder(extractedSubtree));
    }

    @Test
    public void testIteratorInorderDeep() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        Position<String> plusRoot = tree.addRoot("+");
        tree.insertLeft(plusRoot, "2");
        Position<String> starNode = tree.insertRight(plusRoot, "*");
        tree.insertLeft(starNode, "3");
        tree.insertRight(starNode, "5");
        Assertions.assertEquals("2+3*5", collectInorder(tree));
    }

    @Test
    public void testInvalidPositionCheck() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        tree.addRoot("root");
        Position<String> fakePosition = new Position<String>() {
            @Override
            public String getElement() {
                return "X";
            }
        };
        Assertions.assertThrows(RuntimeException.class, () -> tree.hasLeft(fakePosition));
        Assertions.assertThrows(RuntimeException.class, () -> tree.parent(fakePosition));
    }
}