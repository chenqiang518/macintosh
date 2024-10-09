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
        int index = m + n -1;
        // 遍历两个数组
        while (p1 >= 0 && p2 >= 0) {
            nums1[index--] = nums1[p1] <= nums2[p2] ? nums2[p2--] : nums1[p1--];
        }
        // 如果是nums2更长的话，把没遍历到的元素拼到最前面(nums1更长的话不用管)
        while (p2 >= 0) {
            nums1[index--] = nums2[p2--];
        }
    }

    /** 思路
     * 标签：哈希映射
     * 这道题本身如果通过暴力遍历的话也是很容易解决的，时间复杂度在 O(n^2)
     * 由于哈希查找的时间复杂度为 O(1)，所以可以利用哈希容器 map 降低时间复杂度
     * 遍历数组 nums，i 为当前下标，每个值都判断map中是否存在 target-nums[i] 的 key 值
     * 如果存在则找到了两个值，如果不存在则将当前的 (nums[i],i) 存入 map 中，继续遍历直到找到为止
     * 如果最终都没有结果则抛出异常
     * 时间复杂度：O(n)
     *
     */
    @Override
    public int[] twoSum(int[] nums, int target) {

        int length = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < length-1; i++) {
            if (map.containsKey(target-nums[i])){
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
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
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for (String str: strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key,list);
        }
        return new ArrayList<List<String>>(map.values());

    }
}
