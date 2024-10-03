package com.vernon.reaper.solution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeTest {

    public Merge merge;

    @BeforeEach
    void setUp() {
        merge = new Merge();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void merge() {
        int[] nums1={1,2,3,0,0,0},nums2={2,5,6};
        int m=3,n=3;
        merge.merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }
}