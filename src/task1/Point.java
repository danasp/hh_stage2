package task1;

/**
 * Используется для хранения координат точки.
 */
public class Point {
    private int x;
    private int y;

    /**
     *
     * @param x координата X.
     * @param y координата Y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получение координаты X.
     * @return координата X.
     */
    public int getX() {
        return x;
    }

    /**
     * Получение координаты Y.
     * @return координата Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Метод для поиска расстояния от текущей точки до соседней точки.
     * @param p2 экземпляр Point точки, до которой ищется расстояние.
     * @return расстояние между текущей точкой и точкой p2.
     */
    public double findDistanceToNeighbor(Point p2) {
        double poweredXCoord = Math.pow((double)(this.getX() - p2.getX()), 2);
        double poweredYCoord = Math.pow((double) (this.getY() - p2.getY()), 2);
        double result = Math.sqrt(poweredXCoord + poweredYCoord);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point)obj;
        return this.getX() == p.getX() && this.getY() == p.getY();
    }
}
