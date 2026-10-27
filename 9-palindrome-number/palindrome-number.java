
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int dev = 0;

        while (x > dev) {
            int digit = x % 10;
            dev = dev * 10 + digit;
            x = x / 10;
        }

        return x == dev || 
        x == dev / 10;
    }
}
