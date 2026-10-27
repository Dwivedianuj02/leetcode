
class Solution {
    public int myAtoi(String s) {

        int i = 0;
        int n = s.length();
        int sign = 1;
        int result = 0;

        // Step 1: Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Check sign
        if (i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < n && s.charAt(i) == '+') {
            sign = 1;
            i++;
        }

        // Step 3: Read digits and build the number
        while (i < n && s.charAt(i) >= '0'
                      && s.charAt(i) <= '9') {

            int digit = s.charAt(i) - '0';

            // Step 4: Check overflow before calculation
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && digit > 7)) {

                return sign == 1
                    ? Integer.MAX_VALUE
                    : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}
