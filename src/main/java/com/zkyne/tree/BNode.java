package com.zkyne.tree;

/**
 * @ClassName: BNode
 * @Description: 二叉搜索树
 * @Author: zhangkun01
 * @Date: 2018/4/8 10:52
 */
public class BNode {
    private int key;
    private double data;
    private BNode parent;
    private BNode leftChild;
    private BNode rightChild;

    public BNode() {
    }

    public BNode(int key, double data) {
        this.key = key;
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public BNode getParent() {
        return parent;
    }

    public void setParent(BNode parent) {
        this.parent = parent;
    }

    public BNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BNode leftChild) {
        this.leftChild = leftChild;
    }

    public BNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BNode rightChild) {
        this.rightChild = rightChild;
    }
}
