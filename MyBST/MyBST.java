public class MyBST {
    private class BSTNode {
        Integer val;
        BSTNode left, right;
        public BSTNode(Integer val) {
            this.val = val;
            left = right = null;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }

    BSTNode root;

    public int size() {
        return size(root);
    }
    private int size(BSTNode node) {
        if (node == null) {return 0;}
        return 1 + size(node.left) + size(node.right);        
    }

    public void insert(Integer n) {
        if (root != null) {
            insert(root, n);
        } else {
            root = new BSTNode(n);
        }
    }
    private void insert(BSTNode node, Integer n) {
        if (n <= node.val) {
            if (node.left != null) {
                insert(node.left, n);
            } else {
                node.left = new BSTNode(n);
            }
            
        } else {
            if (node.right != null) {
                insert(node.right, n);
            } else {
                node.right = new BSTNode(n);
            }
        }
        
    }

    public boolean contains(Integer n) {
        return contains(root, n);
    }
    private boolean contains(BSTNode node, Integer n) {
        if (node == null) {return false;}
        if (node.val.equals(n)) {return true;}
        else { 
            return contains(node.left, n) || contains(node.right, n);
        }  
    }

    public Integer getMax() {
        return root != null ? getMax(root) : null;
    }

    private Integer getMax(BSTNode node) {
        return node.right == null ? node.val : getMax(node.right);
    }

    public Integer getMin() {
        return root != null ? getMin(root) : null;
    }

    private Integer getMin(BSTNode node) {
        return node.left == null ? node.val : getMin(node.left);
    }

    public void delete(Integer n) {
        if (contains(n)) {
            delete(root, n, null);
        }
    }

    private void delete(BSTNode node, Integer n, BSTNode temp) {
        if (node == null) {return;}
        if (node.val.equals(n)) {
            if (node.right == null && node.left == null) {
                if (node == root) {
                    root = null;
                }
                else if (temp.left == node) {
                    temp.left = null;
                } else {
                    temp.right = null;
                }

            }
            else if (node.right == null) {
                if (node == root) {
                    root = node.left;
                }
                else if (temp.left == node) {
                    temp.left = node.left;
                } else {
                    temp.right = node.left;
                }
            }
            else if (node.left == null) {
                if (node == root) {
                    root = node.right;
                }
                else if (temp.left == node) {
                    temp.left = node.right;
                } else {
                    temp.right = node.right;
                }
            } else {
                Integer minimum = Math.min(getMin(), n);
                delete(node, minimum, null);
                n = minimum;
            }

        }
        else {
            delete(node.left, n, node);
            delete(node.right, n, node);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(BSTNode node) {
        if (node == null) { return; }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    public void print() {
        print(root, 0);
    }
    
    private void print(BSTNode node, int padding) {
        if (node == null) { return; }
        padding += 6;
        print(node.right, padding);
        System.out.println("");
        for (int i = 6; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println(node.val + "\n");
        print(node.left, padding);
    }
}
