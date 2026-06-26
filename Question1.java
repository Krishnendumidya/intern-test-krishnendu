
    public static boolean subarraySum(int[] arr, int target) {
        int left = 0;
        int sum = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                return true;
            }
        }

        return false;
    }
