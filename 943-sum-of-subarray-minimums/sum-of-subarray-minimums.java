class Solution {
    public int sumSubarrayMins(int[] arr) {

        int n = arr.length;
        long sum = 0;
        int mod = 1000000007;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {

            while (!stack.isEmpty() &&
                   (i == n || arr[stack.peek()] >= arr[i])) {

                int mid = stack.pop();

                int left = stack.isEmpty() ? -1 : stack.peek();

                int right = i;

                long count = (long)(mid - left) * (right - mid);

                sum = (sum + count * arr[mid]) % mod;
            }

            stack.push(i);
        }

        return (int)sum;
    }
}