package javapractice.n09;

import java.util.LinkedList;

public class BinaryTree {

    //так как в задании было сказано "описать" (означает ли это, что можно было не реализовывать?..) бинарное дерево и
    //ничего не было сказано про интерфейс его заполнения,
    //был выбран наиболее легкий в реализации способ - публичная структура

    //чтобы не обходить впустую был реализован паттерн visitor..

    public Node root;

    public void depthFirstTraversal(Visitor v) {
        depthRecursiveTraversal(root, v);
    }

    private void depthRecursiveTraversal(Node e, Visitor v) {
        if(e == null) return;
        depthRecursiveTraversal(e.left, v);
        depthRecursiveTraversal(e.right, v);
        v.visit(e.info);
    }

    public void widthFirstTraversal(Visitor v) {
        LinkedList<Node> ll = new LinkedList<Node>();
        ll.add(root);
        Node e;
        while (!ll.isEmpty()) {
            e = ll.poll();
            v.visit(e.info);
            if (e.left != null) ll.add(e.left);
            if (e.right != null) ll.add(e.right);
        }
    }

    public static class Node {
        public int info;

        public Node right;
        public Node left;
    }

}
