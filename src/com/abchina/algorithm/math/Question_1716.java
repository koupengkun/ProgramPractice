package com.abchina.algorithm.math;

/**
 * 1716. 计算力扣银行的钱
 * Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
 * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
 * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。

 * 示例 1：
 * 输入：n = 4
 * 输出：10
 * 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
 * 示例 2：
 * 输入：n = 10
 * 输出：37
 * 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
 * 示例 3：
 * 输入：n = 20
 * 输出：96
 * 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
 */

/**
 * 思路：
 * 简单的数字累加问题，确定数学模型：
 * 前x-1周每周 = sum(7)+7*(x-1) ；第X周 = sum(剩余天数)+剩余天数*（x-1）
 * 程序实现：轮次表示当前周，使用公式计算每周的值然后累加
 */
public class Question_1716 {
    public int totalMoney(int n) {
        int round = 1;
        int money = 0;
        while(n > 0){
            if(n>7){
                money += calcWeek(7,round);
            }else {
                money += calcWeek(n,round);
            }
            n -= 7;
            round ++;
        }
        return money;
    }

    public int calcWeek(int x,int round){
        return x*(x+1)/2 + x*(round-1);
    }

    public static void main(String[] args){
        Question_1716 object = new Question_1716();
        System.out.println("结果："+object.totalMoney(20));
    }
}
