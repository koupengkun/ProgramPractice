package com.abchina.algorithm.stock_sell;
/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */

/**
 * 算法设计：动态规划
 * 问题3升级版，买卖次数不确定；
 * 确定状态：买卖次数与状态的关系，2*k既多一次买卖会新增两个状态
 * 确定初始值：第一天的所有状态，以及每天第一次买入的状态
 * 状态转移：偶数为买入，奇数为卖出，买入-prices[i]，买入+prices[i]
 * 确定输出值：最后的卖出状态，为最大的收益。
 */
public class Question_188 {
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        if(length < 2 || k < 1){
            return 0;
        }
        //k和状态的关系2k,定义状态矩阵
        int[][] dp = new int[length][2*k];
        //初始化
        for(int i = 0 ; i < 2*k ; i++){
            if(i%2 == 0){
                dp[0][i] = -prices[0];
            }else {
                dp[0][i] = 0;
            }
        }
        //状态转移
        for(int i = 1 ; i < length ; i++){
            dp[i][0] = Math.max(dp[i-1][0],-prices[i]);
            for(int j = 1 ; j < 2*k ; j++){
                if(j%2 == 0){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1] - prices[i]);
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1] + prices[i]);
                }
            }
        }
        return dp[length-1][2*k-1];
    }
    public static void main(String[] args){
        Question_188 object = new Question_188();
        int res = object.maxProfit(2,new int[]{3,2,6,5,0,3});
        System.out.println(res);
    }
}
