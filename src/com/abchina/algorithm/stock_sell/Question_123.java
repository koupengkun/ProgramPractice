package com.abchina.algorithm.stock_sell;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 */
/**
 *算法设计：使用动态规划
 *
 * 确定状态：每天结束有5种状态（未操作、买入股票1、买入股票2、卖出股票1、卖出股票2）；状态记录当日持有最大现金数量
 * 状态转移：
 *  买入股票1：Max{b1，-prices[i]} (第一次买入价格，有最低替换)
 * 	卖出股票1：Max{s1,b1+prices[i]}（第一次买入+当前价格，最高替换）
 * 	买入股票2：Max{b2,s1-prices[i]}（第一次卖出-当前价格，有最大收益替换）
 * 	卖出股票2：Max{s2,b2+prices[i]}（第二次买入+当前价格，有最大替换）
 * 确定初始值：
 *  买入股票1：-prices[0]；买入股票2：当天买入再卖出，再买入-prices[0]；
 *  卖出股票1和卖出股票2：0；
 * 确定输出值：
 *  卖出股票1和卖出股票2的最大值；
 */
public class Question_123 {
    //标准动态规划写法
    public int maxProfit(int[] prices) {
        if(prices.length < 2){
            return 0;
        }
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for(int i = 1 ; i < prices.length ; i++){
            dp[i][0] = Math.max(dp[i-1][0],-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]-prices[i]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]+prices[i]);
        }
        return dp[prices.length-1][3];
    }
    //参数转移写法，更直观
    public int maxProfitSimple(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
