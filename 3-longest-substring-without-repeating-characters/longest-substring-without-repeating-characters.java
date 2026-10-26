
class Solution {
    public int lengthOfLongestSubstring(String s) {

        int start = 0;
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {

            for (int i = start; i < end; i++) {
                if (s.charAt(i) == s.charAt(end)) {
                    start = i + 1;
                    break;
                }
            }

            int length = end - start + 1;

            if (length > maxLength) {
                maxLength = length;
            }
        }

        return maxLength;
    }
}
