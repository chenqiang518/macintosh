package com.vernon.reaper.service.impl;

import com.vernon.reaper.service.Solution;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SolutionImpl implements Solution {
    @Override
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 双指针，一个判断nums1的元素，另一个判断nums2的元素
        int p1 = m - 1;
        int p2 = n - 1;
        // nums1放置元素的索引，从m+n-1倒着开始放，这样不会影响nums1前面部分的元素
        int index = m + n - 1;
        // 遍历两个数组
        while (p1 >= 0 && p2 >= 0) {
            nums1[index--] = nums1[p1] <= nums2[p2] ? nums2[p2--] : nums1[p1--];
        }
        // 如果是nums2更长的话，把没遍历到的元素拼到最前面(nums1更长的话不用管)
        while (p2 >= 0) {
            nums1[index--] = nums2[p2--];
        }
    }

    /**
     * 思路
     * 标签：哈希映射
     * 这道题本身如果通过暴力遍历的话也是很容易解决的，时间复杂度在 O(n^2)
     * 由于哈希查找的时间复杂度为 O(1)，所以可以利用哈希容器 map 降低时间复杂度
     * 遍历数组 nums，i 为当前下标，每个值都判断map中是否存在 target-nums[i] 的 key 值
     * 如果存在则找到了两个值，如果不存在则将当前的 (nums[i],i) 存入 map 中，继续遍历直到找到为止
     * 如果最终都没有结果则抛出异常
     * 时间复杂度：O(n)
     */
    @Override
    public int[] twoSum(int[] nums, int target) {

        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length - 1; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    @Override
    public List<List<String>> groupAnagrams(String[] strs) {
        /* return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str ->{
                    // 返回 str 排序后的结果。
                    // 按排序后的结果来 groupingBy
                    char[] array = str.toCharArray();
                    Arrays.sort(array);
                    return new String(array);
                })).values());*/
        /* return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> str.chars().sorted()
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString())).values());*/
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());

    }

    @Override
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int max_length = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int cur_length = 1 + left + right;
                if (cur_length > max_length) max_length = cur_length;
                map.put(num, cur_length);
                map.put(num - left, cur_length);
                map.put(num + right, cur_length);
            }
        }
        return max_length;
    }

    /**
     * 思路及解法
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左指针与右指针对应的数交换，同时左指针右移。
     * 注意到以下性质：
     * 左指针左边均为非零数；
     * 右指针左边直到左指针处均为零。
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     */
    @Override
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }

    /**
     * 用一句话概括双指针解法的要点：指针每一次移动，都意味着排除掉了一个柱子。
     * 在一开始，我们考虑相距最远的两个柱子所能容纳睡的面积。水的宽度是两根柱子之间的距离d=8；
     * 水的高度取决于两根柱子之间较短的那个，即左边柱子的高度h=3。水的面积就是3x8=24。
     * <p>
     * 如果选择固定一根柱子，另外一根变化，水的面积会有什么变化吗？稍加思考可得：
     * 当前柱子是最两侧的柱子，水的宽度 d 为最大，其他的组合，水的宽度都比这个小。
     * 左边柱子较短，决定了水的高度为 3。如果移动左边的柱子，新的水面高度不确定，一定不会超过右边的柱子高度 7。
     * 如果移动右边的柱子，新的水面高度一定不会超过左边的柱子高度 3，也就是不会超过现在的水面高度。
     * <p>
     * 由此可见，如果固定左边的柱子，移动右边的柱子，那么水的高度一定不会增加，且宽度一定减少，所以水的面积一定减少。
     * 这个时候，左边的柱子和任意一个其他柱子的组合，其实都可以排除了。也就是我们可以排除掉左边的柱子了。
     * 这个排除掉左边柱子的操作，就是双指针代码里的 i++。i 和 j 两个指针中间的区域都是还未排除掉的区域。
     * 随着不断排除，i 和 j 都会往中间移动。当 i 和 j 相遇，算法就结束了。
     */
    @Override
    public int maxArea(int[] height) {

        int left = 0, right = height.length - 1, ans = 0;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            ans = Math.max(ans, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }

    /**
     * 解题思路：
     * 暴力法搜索为 O(N3) 时间复杂度，可通过双指针动态消去无效解来优化效率。
     * 算法流程：
     * 先将 nums 排序，时间复杂度为 O(NlogN)。
     * 固定 3 个指针中最左（最小）元素的指针 k，双指针 i，j 分设在数组索引 (k,len(nums)) 两端。
     * 双指针 i , j 交替向中间移动，记录对于每个固定指针 k 的所有满足 nums[k] + nums[i] + nums[j] == 0 的 i,j 组合：
     * 当 nums[k] > 0 时直接break跳出：因为 nums[j] >= nums[i] >= nums[k] > 0，即 3 个元素都大于 0 ，在此固定指针 k 之后不可能再找到结果了。
     * 当 k > 0且nums[k] == nums[k - 1]时即跳过此元素nums[k]：因为已经将 nums[k - 1] 的所有组合加入到结果中，本次双指针搜索只会得到重复组合。
     * i，j 分设在数组索引 (k,len(nums)) 两端，当i < j时循环计算s = nums[k] + nums[i] + nums[j]，并按照以下规则执行双指针移动：
     * 当s < 0时，i += 1并跳过所有重复的nums[i]；
     * 当s > 0时，j -= 1并跳过所有重复的nums[j]；
     * 当s == 0时，记录组合[k, i, j]至res，执行i += 1和j -= 1并跳过所有重复的nums[i]和nums[j]，防止记录到重复组合。
     */
    @Override
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int left = k + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[k] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[k], nums[left], nums[right]));
                    right--;
                    do left++;
                    while (left < right && nums[left] == nums[left - 1]);
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }

    @Override
    public int trap(int[] height) {
        // 左&右指针：分别指向左右两边界的列
        int left = 0, right = height.length - 1, ans = 0;
        // 左指针的左边最大高度、右指针的右边最大高度
        int leftMax = height[left], rightMax = height[right];
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if ( leftMax < rightMax) {
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                ans += leftMax - height[left];
                left++;
            }else {
                // 右边同理
                ans += rightMax - height[right];
                right--;
            }

        }
        return ans;
    }

    @Override
    public int lengthOfLongestSubstring(String s) {
        /*
        总体思路:
        遍历字符串，每次以 i 值记录，不回溯 i 值，用flag记录遍历过程找到的重复的字符的位置。
        如果遇到重复字符，i-flag 即为子串长度，此时flag重新定位到子串中重复字符的位置，
        i 继续往后遍历。这里length跟result记录长度。
         */
        int i = 0,flag = 0,length = 0,result = 0;
        while (i < s.length()) {
            //从flag的位置开始算索引值，然后存放在pos中
            int pos = s.indexOf(s.charAt(i),flag);
            //如果pos小于i，等价于发现了重复字符
            if (pos < i) {
                //flag要移动到重复字符的后一位
                flag = pos + 1;
                //最大长度result
                if (length > result) {
                    result = length;
                }
                //如果result最大长度已经大于等于剩余字符的长度了  那么就返回result
                if (result >= s.length() - pos - 1) {
                    return result;
                }
                //i-pos就是长度 但是因为下面length++了  所以减去1
                length = i - pos - 1;
            }
            //长度+1
            length++;
            //字符向后移一个
            i++;
        }
        //最大长度length，必须要满足子串在右侧
        return length;
    }
}
