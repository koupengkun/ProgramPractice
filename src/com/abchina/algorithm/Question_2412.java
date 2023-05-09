package com.abchina.algorithm;

/**
 * 2412. 完成所有交易的初始最少钱数
 * 给你一个下标从 0 开始的二维整数数组 transactions，其中transactions[i] = [costi, cashbacki] 。
 * 数组描述了若干笔交易。其中每笔交易必须以 某种顺序 恰好完成一次。在任意一个时刻，你有一定数目的钱 money ，为了完成交易 i ，money >= costi 这个条件必须为真。执行交易后，你的钱数 money 变成 money - costi + cashbacki 。
 * 请你返回 任意一种 交易顺序下，你都能完成所有交易的最少钱数 money 是多少。

 * 示例 1：
 * 输入：transactions = [[2,1],[5,0],[4,2]]
 * 输出：10
 * 解释：
 * 刚开始 money = 10 ，交易可以以任意顺序进行。
 * 可以证明如果 money < 10 ，那么某些交易无法进行。
 * 示例 2：
 * 输入：transactions = [[3,0],[0,3]]
 * 输出：3
 * 解释：
 * - 如果交易执行的顺序是 [[3,0],[0,3]] ，完成所有交易需要的最少钱数是 3 。
 * - 如果交易执行的顺序是 [[0,3],[3,0]] ，完成所有交易需要的最少钱数是 0 。
 * 所以，刚开始钱数为 3 ，任意顺序下交易都可以全部完成。

 * 提示：
 * 1 <= transactions.length <= 105
 * transactions[i].length == 2
 * 0 <= costi, cashbacki <= 109
 */

/**
 * 思路：
 * 问题建模：每笔交易有一个支出和收入，现在要处理很多交易（执行顺序随机），需要准备多大的初始金额可以完成所有交易。
 * 1.考虑会出现的最坏情况，一直亏钱，找到所有支出大于收入情况，记录总亏钱数：
 * Totallose =sum(cost – back)
 * 2.分情况讨论：
 * cost < back的交易，money = totallose + cost ;
 * cost > back的交易，money = totallose-( cost – back) + cost = totallose + back
 * 遍历所有交易，找到最大的money
 */
public class Question_2412 {
    public long minimumMoney(int[][] transactions) {
        long totalLose,money;
        //遍历二维数组，获取totalLose
        totalLose = 0L;
        money = 0L;
        for(int[] transaction : transactions){
            if(transaction[0] > transaction[1]){
                totalLose += transaction[0] - transaction[1];
            }
        }
        //再次遍历，取最大值
        for (int[] transaction : transactions){
            long tempMoney;
            if(transaction[0] < transaction[1]){
                tempMoney = totalLose + transaction[0];
            }else {
                tempMoney = totalLose + transaction[1];
            }
            money = Math.max(tempMoney, money);
        }
        return money;
    }

    public static void main(String[] args){
        int [][] transactions1 = new int[][]{{2,1},{5,0},{4,2}};
        int [][] transactions2 = new int[][]{{3,0},{0,3}};
        Question_2412 object = new Question_2412();
        System.out.println(object.minimumMoney(transactions1));
        System.out.println(object.minimumMoney(transactions2));
    }
}
