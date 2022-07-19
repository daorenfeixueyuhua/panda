package com.panda.acm.uva;

import java.util.Scanner;

public class UVA200 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ch = new int[107];
        String next = scanner.next();
        StringBuilder stringBuilder = new StringBuilder();
        while (!"#".equals(next)) {
            stringBuilder.append(next);
            next = scanner.next();
        }

    }
}
