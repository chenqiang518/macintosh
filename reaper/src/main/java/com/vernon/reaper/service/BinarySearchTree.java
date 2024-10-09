package com.vernon.reaper.service;


public class BinarySearchTree {
    Node tree;

    public void insert(int value) {
        if (tree == null) {
            tree = new Node(value);
            return;
        }
        Node p = tree;
        while (p != null) {
            if (value > p.data) {
                if (p.right == null) {
                    p.right = new Node(value);
                    return;
                }
                p = p.right;
            } else if (value < p.data) {
                if (p.left == null) {
                    p.left = new Node(value);
                    return;
                }
                p = p.left;
            }
        }
    }

    public Node find(int value) {
        Node p = tree;
        while (p != null) {
            if (value > p.data)
                p = p.right;
            else if (value < p.data)
                p = p.left;
            else
                return p;
        }

        return null;

    }

    public void delete(int value) {
        // 要删除的结点
        Node p = tree;
        // 要删除结点的父结点
        Node pp = null;

        while (p != null && p.data != value) {
            if (value > p.data) {
                pp = p;
                p = p.right;
            }
            else if (value < p.data) {
                pp = p;
                p = p.left;
            }
        }
        // 没找到
        if (p == null)
            return;

        if (p.left != null && p.right != null) {
            Node tmpP = p.right;
            Node tmpPP = p;
            while (tmpP.left != null) {
                tmpPP = tmpP;
                tmpP = tmpP.left;
            }
            p.data = tmpP.data;
            p = tmpP;
            pp = tmpPP;
        }

        // 如果 p 结点下面有一个或没有孩子时
        Node child;
        if (p.left != null)
            child = p.left;
        else if (p.right != null)
            child = p.right;
        else
            child = null;

        // 把孩子接到 p 的父结点上 pp

        if (pp == null)
            tree = child;
        else if (pp.right == p)
            pp.right = child;
        else if (pp.left == p)
            pp.left = child;

    }

    public void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;

        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] data = new int[] { 1, 10, 20, 40, 13 };
        for (int i : data) {
            binarySearchTree.insert(i);
        }
        System.out.println(20 == binarySearchTree.find(20).data);
        binarySearchTree.delete(20);
        System.out.println(null == binarySearchTree.find(20));
        // 1 10 40 13
        binarySearchTree.preOrder(binarySearchTree.tree);
        System.out.println("-----------------------");
        // 1 10 13 40
        binarySearchTree.inOrder(binarySearchTree.tree);
        System.out.println("-----------------------");
        // 13 40 10 1
        binarySearchTree.postOrder(binarySearchTree.tree);

    }
}
