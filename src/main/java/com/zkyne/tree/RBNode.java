package com.zkyne.tree;

/**
 * @ClassName: RBNode
 * @Description: 红黑树
 * @Author: zhangkun01
 * @Date: 2018/4/8 16:01
 */
public class RBNode<T extends Comparable<T>> {
    private T key; //关键字(键值)
    private boolean color; //颜色
    private RBNode<T> parent; //父节点
    private RBNode<T> left; //左子节点
    private RBNode<T> right; //右子节点

    public RBNode() {
    }

    public RBNode(boolean color, T key) {
        this.color = color;
        this.key = key;
    }

    public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
        this.color = color;
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public RBNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBNode<T> left) {
        this.left = left;
    }

    public RBNode<T> getRight() {
        return right;
    }

    public void setRight(RBNode<T> right) {
        this.right = right;
    }

    public RBNode<T> getParent() {
        return parent;
    }

    public void setParent(RBNode<T> parent) {
        this.parent = parent;
    }
}
