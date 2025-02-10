package com.ggjiuw;
public class WaveAlg {
    private int[][] field;
    private int x1;
    private int y1;

    public WaveAlg(int[][] field) {
        this.field = field;
    }

    public Point[] findTheWay(int x, int y, int x1, int y1) {
        field[y][x] = 1;
        this.x1 = x1;
        this.y1 = y1;

        boolean wasChanged = true;

        while (wasChanged) {
            wasChanged = false;

            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (isActiveField(j, i)) {
                        if (moveWaveFurther(j, i)) {
                            wasChanged = true;

                            Point[] res = allBackWay();
                            if (res != null)
                                return res;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Point[] allBackWay() {
        Point p = backWayCheck(this.x1, this.y1);

        if (p == null) {
            return null;
        }

        int chainLength = field[p.getY()][p.getX()] - 1;

        Point[] res = new Point[chainLength];
        res[0] = p;

        for (int i = 1; i < chainLength; i++) {
            res[i] = backWayCheck(res[i - 1].getX(), res[i - 1].getY());
        }

        return res;
    }

    public Point backWayCheck(int x, int y) {
        Point p = null;
        int min = 100000;

        if (x > 0) {
            if (field[y][x - 1] > 0 && field[y][x - 1] < min) {
                p = new Point(x - 1, y);
                min = field[y][x - 1];
            }
        }

        if (y > 0) {
            if (field[y - 1][x] > 0 && field[y - 1][x] < min) {
                min = field[y - 1][x];
                p = new Point(x, y - 1);
            }
        }

        if (x < field[y].length - 1) {
            if (field[y][x + 1] > 0 && field[y][x + 1] < min) {
                min = field[y][x + 1];
                p = new Point(x + 1, y);
            }
        }

        if (y < field.length - 1) {
            if (field[y + 1][x] > 0 && field[y + 1][x] < min) {
                min = field[y + 1][x];
                p = new Point(x, y + 1);
            }
        }
        return p;
    }

    public boolean isActiveField(int x, int y) {
        return field[y][x] > 0;
    }

    public boolean moveWaveFurther(int x, int y) {
        boolean res = false;
        if (x > 0) {
            if (field[y][x - 1] == 0) {
                field[y][x - 1] = field[y][x] + 1;
                res = true;
            }
        }

        if (y > 0) {
            if (field[y - 1][x] == 0) {
                field[y - 1][x] = field[y][x] + 1;
                res = true;
            }
        }

        if (x < field[y].length - 1) {
            if (field[y][x + 1] == 0) {
                field[y][x + 1] = field[y][x] + 1;
                res = true;
            }
        }

        if (y < field.length - 1) {
            if (field[y + 1][x] == 0) {
                field[y + 1][x] = field[y][x] + 1;
                res = true;
            }
        }
        return res;
    }
}
