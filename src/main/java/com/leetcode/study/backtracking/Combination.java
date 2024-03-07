package com.leetcode.study.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dreamyao
 * @title 组合
 * @date 2024/3/6 20:38
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class Combination {

    private final List<Integer> path = new ArrayList<>();
    private final List<List<Integer>> res = new ArrayList<>();

    /**
     * LCR 080. 组合
     * 已解答
     * 中等
     * 相关标签
     * 相关企业
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 示例 1:
     *
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * 示例 2:
     *
     * 输入: n = 1, k = 1
     * 输出: [[1]]
     *
     *
     * 提示:
     *
     * 1 <= n <= 20
     * 1 <= k <= n
     *
     *
     * 注意：本题与主站 77 题相同： https://leetcode-cn.com/problems/combinations/
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }

    private void backtracking(int n, int k, int start) {
        // 终止条件
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 至多 k - path.size()
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            // 处理数据
            path.add(i);
            backtracking(n, k, i + 1);
            path.removeLast();
        }
    }

    /**
     * LCR 081. 组合总和
     * 中等
     * 相关标签
     * 相关企业
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     *
     * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
     *
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
     *
     *
     *
     * 示例 1：
     *
     * 输入: candidates = [2,3,6,7], target = 7
     * 输出: [[7],[2,2,3]]
     * 示例 2：
     *
     * 输入: candidates = [2,3,5], target = 8
     * 输出: [[2,2,2,2],[2,3,3],[3,5]]
     * 示例 3：
     *
     * 输入: candidates = [2], target = 1
     * 输出: []
     * 示例 4：
     *
     * 输入: candidates = [1], target = 1
     * 输出: [[1]]
     * 示例 5：
     *
     * 输入: candidates = [1], target = 2
     * 输出: [[1,1]]
     *
     *
     * 提示：
     *
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     *
     *
     * 注意：本题与主站 39 题相同： https://leetcode-cn.com/problems/combination-sum/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combination(candidates, target, 0, 0);
        return res;
    }

    private void combination(int[] candidates, int target, int sum, int start) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] <= target) {
                // 处理数据
                path.add(candidates[i]);
                combination(candidates, target, sum + candidates[i], i);
                path.removeLast();
            }
        }
    }

    /**
     * LCR 082. 组合总和 II
     * 中等
     * 相关标签
     * 相关企业
     * 给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
     *
     *
     *
     * 示例 1:
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出:
     * [
     * [1,2,2],
     * [5]
     * ]
     *
     *
     * 提示:
     *
     * 1 <= candidates.length <= 100
     * 1 <= candidates[i] <= 50
     * 1 <= target <= 30
     *
     *
     * 注意：本题与主站 40 题相同： https://leetcode-cn.com/problems/combination-sum-ii/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combination2(candidates, target, 0, 0);
        return res;
    }

    private void combination2(int[] candidates, int target, int sum, int start) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] <= target) {
                if (i > 0 && candidates[i] == candidates[i - 1] && i > start) {
                    continue;
                }
                // 处理数据
                path.add(candidates[i]);
                combination2(candidates, target, sum + candidates[i], i + 1);
                path.removeLast();
            }
        }
    }
}
