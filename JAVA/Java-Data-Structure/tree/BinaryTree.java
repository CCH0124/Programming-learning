package tree;

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode [name=" + name + ", no=" + no + "]";
    }
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }
    public void preOrder() {
        System.out.println(this); // 父節點
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
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

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }

    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return resNode;

    }
}

class BinaryTreeDemo {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    public void delNode(int no){
        if (root != null) {
            if(root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("is empty");
        } 
    }
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("Is empty");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("Is empty");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("Is empty");
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

public class BinaryTree {
    public static void main(String[] args) {
        BinaryTreeDemo binaryTree = new BinaryTreeDemo();
        HeroNode root = new HeroNode(1, "Itachi");
        HeroNode node2 = new HeroNode(2, "Naruto");
        HeroNode node3 = new HeroNode(3, "Kevin");
        HeroNode node4 = new HeroNode(4, "Tom");
        HeroNode node5 = new HeroNode(5, "Jack");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        // System.out.println("前序");
        // binaryTree.preOrder();
        // System.out.println("中序");
        // binaryTree.infixOrder();
        // System.out.println("後序");
        // binaryTree.postOrder();

        // HeroNode res = binaryTree.preOrderSearch(5);
        // if (res != null)
        //     System.out.println(res.getNo() + "--->" + res.getName());
        // else
        //     System.out.println("not found");

        // HeroNode res1 = binaryTree.infixOrderSearch(2);
        // if (res != null)
        //     System.out.println(res1.getNo() + "--->" + res1.getName());
        // else
        //     System.out.println("not found");
        // HeroNode res2 = binaryTree.postOrderSearch(3);
        // if (res != null)
        //     System.out.println(res2.getNo() + "--->" + res2.getName());
        // else
        //     System.out.println("not found");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        binaryTree.preOrder();
        
    }
}