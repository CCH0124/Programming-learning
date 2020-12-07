package binarysearchtree;

class Node {
    int value;
    Node left;
    Node right;

    /**
     * @param value
     */
    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 刪除值是否存在
     * 
     * @param value
     * @return
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 取得父節點
     * 
     * @param value
     * @return
     */
    public Node getParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.getParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.getParent(value);
            } else {
                return null;
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

}

class BinarySearchTree {
    private Node root;

    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            throw new RuntimeException("not found value");
        }

    }

    public Node getParent(int value) {
        if (root != null) {
            return root.getParent(value);
        }
        return null;
    }

    /**
     * 
     * @param node 當做二叉樹的根節點
     * @return 最小節點值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        /**
         * 該樹是以左小右大排列
         */
        while (target.left != null) {
            target = target.left;
        }

        del(target.value);
        return target.value;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void del(int value) {
        if (root == null) {
            throw new RuntimeException("root is null");
        }
        if (root.left == null && root.right == null && root.value == value) {
            root = null;
            return;
        }
        Node targetNode = search(value);
        Node parent = getParent(value);

        if (targetNode.left == null && targetNode.right == null) { // 葉子節點刪除
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            int minValue = delRightTreeMin(targetNode.right);
            targetNode.value = minValue;

        } else { // 刪除只有一顆子樹節點
            if (targetNode.left != null) {
                if (parent != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else {
                if (parent != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            throw new RuntimeException("empty");
        }
    }
}

public class BST {
    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i : arr) {
            binarySearchTree.add(new Node(i));
        }

        binarySearchTree.infixOrder(); // 1 2 3 5 7 9 10 12
        // System.out.println("葉子節點刪除測試");
        // binarySearchTree.del(2);
        // binarySearchTree.del(5);
        // binarySearchTree.del(9);
        // binarySearchTree.del(12);
        // binarySearchTree.del(1);
        // System.out.println("刪除只有一顆子樹節點");
        // binarySearchTree.del(1);
        // System.out.println("刪除只有2顆子樹節點");
        // binarySearchTree.del(3);
        // System.out.println("刪除所有節點");
        // System.out.println("葉子節點刪除測試");
        binarySearchTree.del(2);
        binarySearchTree.del(5);
        binarySearchTree.del(9);
        binarySearchTree.del(12);
        binarySearchTree.del(7);
        binarySearchTree.del(3);
        binarySearchTree.del(1);
        binarySearchTree.del(10);
        binarySearchTree.infixOrder();
    }
}