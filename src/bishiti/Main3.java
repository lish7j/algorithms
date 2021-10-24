package bishiti;

public class Main3 {
    public static void main(String[] args) {
        Main3 m = new Main3();
        System.out.println(m.canBePalindromicString("abca"));
        System.out.println(m.canBePalindromicString("abcdefgh"));
    }
    public int canBePalindromicString (String str1) {
        // write code here
        if (dfs(str1, 0, str1.length() - 1, 2))
            return 1;
        return 0;
    }

    private boolean dfs(String str1, int st, int ed, int have) {

        while (st < ed && str1.charAt(st) == str1.charAt(ed)) {
            st++;
            ed--;
        }
        if (st >= ed)
            return true;
        if (str1.charAt(st) != str1.charAt(ed)) {
            if (have == 0)
                return false;
            return dfs(str1, st + 2, ed, 0) || dfs(str1, st, ed - 2, 0);
        }
        return true;
    }
}
