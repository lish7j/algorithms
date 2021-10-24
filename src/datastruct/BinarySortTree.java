package datastruct;

public class BinarySortTree {
    static final class TreeNode {
        int val;
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode left, right;

        void add(int val) {
            if (this.val > val) {
                if (this.left == null)
                    this.left = new TreeNode(val);
                else
                    this.left.add(val);
            } else {
                if (this.right == null)
                    this.right = new TreeNode(val);
                else
                    this.right.add(val);
            }
        }
    }

    private TreeNode root;

    public BinarySortTree() {}
    public BinarySortTree(int val) {
        root = new TreeNode(val);
    }
    public boolean put(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return true;
        }
        if (root.val == val)
            return false;
        root.add(val);
        return true;
    }


}
