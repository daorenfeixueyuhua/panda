package com.panda.acm.贪心;

import java.util.Scanner;


// L：长度
// W：宽度
// Nx：距离左端位置
// Nw：浇灌半径

// Nw<W/2 的喷头不需要开启；
// 问题Sij中选取Nw最大的进行开启，其中必须满足Nx+((Nw^2-W^2/4)/2)^1/2]>i 并且 Nx-((Nw^2-W^2/4)/2)^1/2<j
// 其中Nw的 Nx-((Nw^2-W^2/4)/2)^1/2 -i + j - Nx+((Nw^2-W^2/4)/2)^1/2] 最大。
// 首先开启Nk, Nk的Nw最大的喷头
// 之后分割为：S[0, Nx-((Nw^2-W^2/4)/2)^1/2 -i + ]和S[Nx+((Nw^2-W^2/4)/2)^1/2],L]的子问题；
// 归并表达式
// N = {0,1,...,n}
// F(i,j):
//  // sort N'
//


public class ExC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
