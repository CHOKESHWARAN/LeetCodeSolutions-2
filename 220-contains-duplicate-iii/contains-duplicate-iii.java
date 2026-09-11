class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (indexDiff <= 0 || valueDiff < 0) return false;
        
        Map<Long, Long> buckets = new HashMap<>();
        long w = (long) valueDiff + 1;
        
        for (int i = 0; i < nums.length; i++) {
            long val = (long) nums[i];
            long bucketId = getBucketId(val, w);
            
            if (buckets.containsKey(bucketId)) {
                return true;
            }
            if (buckets.containsKey(bucketId - 1) && Math.abs(val - buckets.get(bucketId - 1)) <= valueDiff) {
                return true;
            }
            if (buckets.containsKey(bucketId + 1) && Math.abs(val - buckets.get(bucketId + 1)) <= valueDiff) {
                return true;
            }
            
            buckets.put(bucketId, val);
            
            if (i >= indexDiff) {
                buckets.remove(getBucketId((long) nums[i - indexDiff], w));
            }
        }
        
        return false;
    }
    
    private long getBucketId(long val, long w) {
        return val < 0 ? (val + 1) / w - 1 : val / w;
    }
}