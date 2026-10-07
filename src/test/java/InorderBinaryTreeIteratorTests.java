import es.urjc.grafo.EDA.trees.binaryTrees.InorderBinaryTreeIterator;
import es.urjc.grafo.EDA.trees.binaryTrees.LinkedBinaryTree;
import es.urjc.grafo.EDA.utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InorderBinaryTreeIteratorTests {
    
    public InorderBinaryTreeIteratorTests() {
    }

    /**
     * Test of hasNext method, of class InorderBinaryTreeIterator.
     */
    @Test
    public void testIterator() {
        System.out.println("testIterator");
        LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
        Position<String> a = t.addRoot("A");
        Position<String> b = t.insertLeft(a,"B");
        Position<String> c = t.insertRight(a,"C");
        Position<String> d = t.insertRight(b,"D");
        Position<String> e = t.insertRight(c,"E");
        Position<String> f = t.insertLeft(d,"F");
        Position<String> g = t.insertRight(d,"G");
        Position<String> h = t.insertLeft(e,"H");
        Position<String> i = t.insertRight(e,"I");
        t.insertRight(h,"J");

        StringBuilder salida = new StringBuilder();
        InorderBinaryTreeIterator<String> it = new InorderBinaryTreeIterator<>(t);
        while (it.hasNext()) {
            salida.append(it.next().getElement());
        }
        Assertions.assertEquals("BFDGACHJEI", salida.toString());
    }
}
