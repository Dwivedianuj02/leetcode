import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans;

        String[] map = {
            "", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"
        };

        solve(digits, 0, "", ans, map);
        return ans;
    }

    void solve(String digits, int i, String curr,
               List<String> ans, String[] map) {
        if (i == digits.length()) {
            ans.add(curr);
            return;
        }

        String letters = map[digits.charAt(i) - '0'];

        for (char c : letters.toCharArray()) {
            solve(digits, i + 1, curr + c, ans, map);
        }
    }
}