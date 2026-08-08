class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<nums1.length;i++){
            int k=0;
            for(int j=0;j<nums2.length;j++){
                if(nums1[i]==nums2[j])
                    k++;

            }
            if (k != 0 && !ans.contains(nums1[i]))
          
               ans.add(nums1[i]);
        }int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}