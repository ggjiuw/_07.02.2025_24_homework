package com.ggjiuw;

public class Main {
    public static void main(String... args) {
        int[][] ar = {
                {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, -1, -1, -1, -1, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        int startX = 2;
        int startY = 1;
        int finishX = 10;
        int finishY = 7;

        WaveAlg w = new WaveAlg(ar);
        Point[] chain = w.findTheWay(startX, startY, finishX, finishY);

        if (chain != null) {
            Point startPoint = new Point(startX, startY);
            Point finishPoint = new Point(finishX, finishY);

            boolean[][] isPath = new boolean[ar.length][ar[0].length];

            isPath[startPoint.getY()][startPoint.getX()] = true;
            isPath[finishPoint.getY()][finishPoint.getX()] = true;

            for (Point p : chain) {
                if (p != null) {
                    isPath[p.getY()][p.getX()] = true;
                }
            }
            printFieldWithPath(ar, isPath, startPoint, finishPoint);
        } else {
            System.out.println("Cannot find path.");
        }
    }

    private static void printFieldWithPath(int[][] field, boolean[][] isPath, Point startPoint, Point finishPoint) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (j == startPoint.getX() && i == startPoint.getY())
                    System.out.print("S");
                else if (j == finishPoint.getX() && i == finishPoint.getY())
                    System.out.print("F");
                else if (field[i][j] == -1)
                    System.out.print("#");
                else if (isPath[i][j])
                    System.out.print(" ");
                else
                    System.out.print("=");
            }
            System.out.println();
        }
    }
}
