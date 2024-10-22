package com.vernon.reaper.service;

import com.vernon.reaper.service.impl.SolutionImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class SolutionTest {

    public Solution solution;

    @BeforeEach
    void setUp() {
        solution = new SolutionImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void merge() {
        int[] nums1={1,2,3,0,0,0},nums2={2,5,6};
        int m=3,n=3;
        solution.merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }

    @Test
    void twoSum() {
        int[] nums1={2,7,11,15};
        int target = 9;
        int[] index = solution.twoSum(nums1, target);
        System.out.println(Arrays.toString(index));
    }

    @Test
    void groupAnagrams() {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = solution.groupAnagrams(strs);
        System.out.println(lists);
    }

    // 使用Java 8生成随机字母数字字符串
    @Test
    public void generatingRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }

    @Test
    void moveZeroes() {
        int[] nums = {0,1,0,3,12};
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    void maxArea() {
    }
    @Test
    void trap() {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        solution.trap(height);
    }
}