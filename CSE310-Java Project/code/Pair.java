import java.util.Random;

public class Pair {
    private int x;
    private int y;

    public Pair() {
    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair getRandomPair(int[][] tab) {
        Random rnd = new Random();
        int i, j;
        while (true) {
            i = rnd.nextInt(tab.length);
            j = rnd.nextInt(tab.length);
            if (tab[i][j] == 0) break;
        }
        return new Pair(i, j);
    }

    public boolean equals(Pair pair) {
        if ((x == pair.getX()) && (y == pair.getY())) {
            return true;
        } else {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}