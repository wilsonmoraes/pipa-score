package br.com.pipa;

public class Testes {
    public static void main(String[] args) {
        try {
            System.out.println(isPossible(10, 20, 30, 4));
        } catch (Exception e) {
            System.out.println("exc");
        }
    }

    public static String isPossible(int a, int b, int c, int d) {
        while (c > a || d > b) {
            if (c > d) {
                c -= d;
                if (c < a) return "No";
            } else {
                d -= c;
                if (d < b) return "No";
            }
        }
        if (a == c && b == d) return "Yes";
        return "No";

    }
}

