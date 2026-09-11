class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int total = 0;
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;

            int[] currentCount = new int[10];
            currentCount[d1]++;
            currentCount[d2]++;
            currentCount[d3]++;

            if (currentCount[d1] <= count[d1] &&
                currentCount[d2] <= count[d2] &&
                currentCount[d3] <= count[d3]) {
                total++;
            }
        }

        return total;
    }
}