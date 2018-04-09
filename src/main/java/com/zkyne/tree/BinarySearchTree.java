package com.zkyne.tree;

/**
 * @ClassName: BinarySearchTree
 * @Description: 二叉查找树
 * @Author: zhangkun01
 * @Date: 2018/4/8 15:37
 */
public class BinarySearchTree {

    private BNode root; //根节点

    public BinarySearchTree() {
        root = null;
    }
    public BinarySearchTree(BNode root) {
        this.root = root;
    }

    public BNode getRoot() {
        return root;
    }

    /**
     * 二叉搜索树查找的时间复杂度为O(logN)
     * @param key find node with given key
     * @return
     */
    public BNode find(int key) {
        BNode current = root;
        while (current.getKey() != key) {
            if (key < current.getKey()) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * 插入节点
     * @param key
     * @param value
     */
    public void insert(int key, double value) {
        BNode newNode = new BNode();
        newNode.setKey(key);
        newNode.setData(value);
        if (root == null) { //if tree is null
            root = newNode;
        } else {
            BNode current = root;
            BNode parent;
            while (true) {
                parent = current;
                if (key < current.getKey()) { //turn left
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(newNode);
                        newNode.setParent(parent);
                        return;
                    }
                } else /*if(key > current.getKey()) */{ //turn right
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(newNode);
                        newNode.setParent(parent);
                        return;
                    }
                }/*else{
                    parent.setData(newNode.getData());
                    return;
                }*/
            }
        }
    }

    /**
     * 遍历二叉树
     * @param traverseType 1 前序 2 中序 3 后序 其他默认中序
     */
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.println("Preorder traversal:");
                preOrder(root);//前向遍历
                break;
            case 2:
                System.out.println("Inorder traversal:");
                inOrder(root);//中向遍历
                break;
            case 3:
                System.out.println("Postorder traversal:");
                postOrder(root);//后向遍历
                break;
            default:
                System.out.println("Inorder traversal:");
                inOrder(root);
                break;
        }
        System.out.println("");
    }

    /**
     * 前序遍历
     * @param localRoot
     */
    private void preOrder(BNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.getData() + " ");
            preOrder(localRoot.getLeftChild());
            preOrder(localRoot.getRightChild());
        }
    }

    /**
     * 中序遍历
     * @param localRoot
     */
    private void inOrder(BNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.getLeftChild());
            System.out.print(localRoot.getData() + " ");
            inOrder(localRoot.getRightChild());
        }
    }

    /**
     * 后序遍历
     * @param localRoot
     */
    private void postOrder(BNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.getLeftChild());
            postOrder(localRoot.getRightChild());
            System.out.print(localRoot.getData() + " ");
        }
    }

    /**
     * 查找最小值
     * 根据二叉搜索树的存储规则，最小值应该是左边那个没有子节点的那个节点
     * @return
     */
    public BNode minNumber() {
        BNode current = root;
        BNode parent = root;
        while (current != null) {
            parent = current;
            current = current.getLeftChild();
        }
        return parent;
    }

    /**
     * 查找最大值
     * 根据二叉搜索树的存储规则，最大值应该是右边那个没有子节点的那个节点
     * @return
     */
    public BNode maxNumber() {
        BNode current = root;
        BNode parent = root;
        while (current != null) {
            parent = current;
            current = current.getRightChild();
        }
        return parent;
    }

    /**
     * 删除节点
     *  删除节点在二叉树中是最复杂的，主要有三种情况：
     *      1. 该节点没有子节点（简单）
     *      2. 该节点有一个子节点（还行）
     *      3. 该节点有两个子节点（复杂）
 *      删除节点的时间复杂度为O(logN)
     * @param key
     * @return
     */
    public boolean delete(int key) {
        BNode current = root;
//      BNode parent = root;
        boolean isLeftChild = true;

        if (current == null) {
            return false;
        }
        //寻找要删除的节点
        while (current.getKey() != key) {
//          parent = current;
            if (key < current.getKey()) {
                isLeftChild = true;
                current = current.getLeftChild();
            } else {
                isLeftChild = false;
                current = current.getRightChild();
            }
            if (current == null) {
                return false;
            }
        }

        //找到了要删除的节点，下面开始删除
        //1. 要删除的节点没有子节点,直接将其父节点的左子节点或者右子节点赋为null即可
        if (current.getLeftChild() == null && current.getRightChild() == null) {
            return deleteNoChild(current, isLeftChild);
        }

        //3. 要删除的节点有两个子节点
        else if (current.getLeftChild() != null && current.getRightChild() != null) {
            return deleteTwoChild(current, isLeftChild);
        }

        //2. 要删除的节点有一个子节点，直接将其砍断，将其子节点与其父节点连起来即可，要考虑特殊情况就是删除根节点，因为根节点没有父节点
        else {
            return deleteOneChild(current, isLeftChild);
        }

    }

    /**
     * 删除没有子节点的节点
     * @param node
     * @param isLeftChild
     * @return
     */
    public boolean deleteNoChild(BNode node, boolean isLeftChild) {
        if (node == root) {
            root = null;
            return true;
        }
        if (isLeftChild) {
            node.getParent().setLeftChild(null);
        } else {
            node.getParent().setRightChild(null);
        }
        return true;
    }

    /**
     * 删除有一个子节点的节点
     * @param node
     * @param isLeftChild
     * @return
     */
    public boolean deleteOneChild(BNode node, boolean isLeftChild) {
        if (node.getLeftChild() == null) {
            if (node == root) {
                root = node.getRightChild();
                node.setParent(null);
                return true;
            }
            if (isLeftChild) {
                node.getParent().setLeftChild(node.getRightChild());
            } else {
                node.getParent().setRightChild(node.getRightChild());
            }
            node.getRightChild().setParent(node.getParent());
        } else {
            if (node == root) {
                root = node.getLeftChild();
                node.setParent(null);
                return true;
            }
            if (isLeftChild) {
                node.getParent().setLeftChild(node.getLeftChild());
            } else {
                node.getParent().setRightChild(node.getLeftChild());
            }
            node.getLeftChild().setParent(node.getParent());
        }
        return true;
    }

    /**
     * 删除有两个子节点的节点
     * @param node
     * @param isLeftChild
     * @return
     */
    public boolean deleteTwoChild(BNode node, boolean isLeftChild) {
        BNode successor = getSuccessor(node);
        if (node == root) {
            // 这里主要是防止在删除根节点的右节点的左节点为空时出错
            if (root.getRightChild().getLeftChild() == null){
                successor.setRightChild(node.getRightChild().getRightChild());
            } else{
                successor.setRightChild(root.getRightChild());
            }
            successor.setLeftChild(root.getLeftChild());
            successor.setParent(null);
            root = successor;
        } else if (isLeftChild) {
            node.getParent().setLeftChild(successor);
        } else {
            node.getParent().setRightChild(successor);
        }
        successor.setLeftChild(node.getLeftChild());//connect successor to node's left child
        return true;
    }

    /**
     * 获取要删除节点的后继节点
     * @param delNode
     * @return
     */
    public BNode getSuccessor(BNode delNode) {
        BNode successor = delNode;
        BNode current = delNode.getRightChild();
        while (current != null) {
            successor = current;
            current = current.getLeftChild();
        }
        if (successor != delNode.getRightChild()) {
            successor.getParent().setLeftChild(successor.getRightChild());
            if (successor.getRightChild() != null) {
                successor.getRightChild().setParent(successor.getParent());//删除后续节点在原来的位置
            }
            successor.setRightChild(delNode.getRightChild());//将后续节点放到正确位置，与右边连上
        }
        return successor;
    }

    public static void main(String[] args) {
        BNode bNode = new BNode(0, 30D);
        BinarySearchTree binarySearchTree = new BinarySearchTree(bNode);
        BNode root = binarySearchTree.getRoot();
        binarySearchTree.postOrder(root);
        System.out.println();
        binarySearchTree.insert(2, 40D);
        root = binarySearchTree.getRoot();
        binarySearchTree.postOrder(root);
        System.out.println();
        binarySearchTree.insert(1, 50D);
        root = binarySearchTree.getRoot();
        binarySearchTree.postOrder(root);
        System.out.println();
        BNode bNode1 = binarySearchTree.find(0);
        System.out.println(bNode1.getKey() + "---" + bNode1.getData());
    }

}
