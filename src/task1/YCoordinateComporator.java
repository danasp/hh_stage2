package task1;

import java.util.Comparator;

/**
 * Класс компаратор для сортировки по оси Y.
 */
public class YCoordinateComporator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {

        return p1.getY() < p2.getY() ? -1 : p1.getY() == p2.getY() ? 0 : 1;
    }
}
