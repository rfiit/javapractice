package javapractice.test;

import javapractice.n09.*;
import org.junit.Test;
import org.junit.*;
import junit.framework.Assert;

public class TestN09 extends Assert{

    BinaryTree bt;

    StringBuilder controlTestString = new StringBuilder();

    Exec executor = new Exec(controlTestString);

    @Before
    public void initTest() {

        controlTestString.setLength(0);

        BinaryTree.Node a;

        bt = new BinaryTree();
        bt.root = new BinaryTree.Node();
        bt.root.info = 1;

        a = new BinaryTree.Node(); a.info = 2;
        bt.root.left = a;
        a = new BinaryTree.Node(); a.info = 3;
        bt.root.right = a;

        a = bt.root.left;

        a.left = new BinaryTree.Node(); a.left.info = 4;
        a.right = new BinaryTree.Node(); a.right.info = 5;

        a = bt.root.right;

        a.left = new BinaryTree.Node(); a.left.info = 6;
        a.right = new BinaryTree.Node(); a.right.info = 7;
    }

    @Test
    public void testWidthFirstTraversal() {
        bt.widthFirstTraversal(executor);
        assertEquals(controlTestString.toString(), "1 2 3 4 5 6 7 ");
    }

    @Test
    public void testDepthFirstTraversal() {
        bt.depthFirstTraversal(executor);
        assertEquals(controlTestString.toString(), "4 5 2 6 7 3 1 ");
    }

    public static class Exec implements Visitor {

        StringBuilder cts;

        @Override
        public void visit(int i) {
            cts.append(i + " ");
        }

        public Exec(StringBuilder s) {
            cts = s;
        }
    }

}
