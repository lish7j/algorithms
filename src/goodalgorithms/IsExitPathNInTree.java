package goodalgorithms;

import common.TreeNode;

public class IsExitPathNInTree {
    public static void main(String[] args) {

    }

    public boolean isExit(TreeNode root, int n) {
        if (root == null)
            return false;
        return helper(root, n);
    }

    private boolean helper(TreeNode root, int n) {
        if (root.left == null && root.right == null && root.val == n)
            return true;
        boolean fl = false;
        if (root.left != null)
            fl |= helper(root.left, n - root.val);
        if (!fl && root.right != null)
            fl |= helper(root.right, n - root.val);
        return fl;
    }
}
