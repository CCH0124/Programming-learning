package avl;

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

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 樹節點高度
     * 
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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
        if ((rightHeight() - leftHeight()) > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        if ((leftHeight() - rightHeight()) > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }

    private void leftRotate() {
        Node node = new Node(value); // 當前根節點值，新節點
        node.left = left;
        node.right = right.left;
        value = right.value;
        right = right.right;
        left = node;
    }

    private void rightRotate() {
        Node node = new Node(value); // 當前根節點值，新節點
        node.right = right;
        node.left = left.right;
        value = left.value;
        left = left.left;
        right = node;
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

class AVL {
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

    /**
     * @return the root
     */
    public Node getRoot() {
        return root;
    }
}

public class AVLTree {
    public static void main(String[] args) {
        // int[] arr = { 4, 3, 6, 5, 7, 8}; // 左旋測試
        int[] arr = { 10, 12, 8, 9, 7, 6 }; // 右旋測試
        AVL avl = new AVL();
        for (int i : arr) {
            avl.add(new Node(i));
        }
        avl.infixOrder();
        System.out.println("Tree Height: " + avl.getRoot().height());
        System.out.println("Tree Left Height: " + avl.getRoot().leftHeight());
        System.out.println("Tree Right Height: " + avl.getRoot().rightHeight());
        System.out.println("Root : " + avl.getRoot());
        System.out.println("Root - Left : " + avl.getRoot().left);
        System.out.println("Root - Right: " + avl.getRoot().right);
    }
}