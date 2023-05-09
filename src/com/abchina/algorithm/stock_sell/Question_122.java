package com.abchina.algorithm.stock_sell;

/**
 * 122. 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。

 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 *      总利润为 4 + 3 = 7 。
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *      总利润为 4 。
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
 */

/**
 * 建模：一组股票价格，可以买卖多次，但同时只能持有一个，获取最大收益（根据价格收益的最大值）；
 * 算法设计：涉及到动态规划，动态计算收益；
 *  定义状态：记录所有变化后的最大收益，每天可以决定是持有股票或是持有现金，定义状态数组；
 *      dp[i][j]表示第i天，状态j时的持有的最大现金数；（状态：0表示持有现金、1表示持有股票）；
 *  状态转移：持有现金说明把上次的股票卖出，持有股票说明把上次的现金买入股票，需要关注状态从现金变到股票、股票变为现金的过程，并更新状态；
 *      开始持有现金，中间可以持有股票或现金，最后状态为持有现金；最后一天，不用买入了，只关注持有现金情况即可；
 *  确定初始值：开始什么都不做dp[0][0]=0，持有股票dp[0][1]=-prices[i]；
 *  确定输出值：dp[length-1][0]
 */
public class Question_122 {
    //动态规划 Dynamic Programming
    public int maxProfit(int[] prices) {
        if(prices.length < 2){
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1 ; i < prices.length ; i++){
            //今天想获取现金，取上次现金和持股卖出的最大值
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //今天想买股票，取上次持股和当次买入股票的最大值
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }
    //回溯backTracking
    /**
     * 每天有两种情况，买入和卖出
     * 通过回溯遍历所有结果
     */
    private int res;
    public int maxProfitBT(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        this.res = 0;
        dfs(prices, 0, len, 0, res);
        return this.res;
    }

    /**
     * @param prices 股价数组
     * @param index  当前是第几天，从 0 开始
     * @param status 0 表示不持有股票，1表示持有股票，
     * @param profit 当前收益
     */
    private void dfs(int[] prices, int index, int len, int status, int profit) {

        if (index == len) {
            this.res = Math.max(this.res, profit);
            return;
        }

        dfs(prices, index + 1, len, status, profit);

        if (status == 0) {
            // 可以尝试转向 1
            dfs(prices, index + 1, len, 1, profit - prices[index]);

        } else {
            // 此时 status == 1，可以尝试转向 0
            dfs(prices, index + 1, len, 0, profit + prices[index]);
        }
    }

    /**
     * 贪心算法
     * 这道题 「贪心」 的地方在于，对于 「今天的股价 - 昨天的股价」，得到的结果有 3 种可能：① 正数，② 0. ③ 负数。
     * 贪心算法的决策是： 只加正数 。
     */
    public int maxProfitGreed(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }

}
