package task1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс осуществляет логику поиска наименьшего расстояния между двумя точками. Используется алгоритм "Разделяй и влавствую"
 * Препарата и Шамоса.
 */
public class ClosestDistant {
    /** Массив экземпляров класса Point, содежащий координаты точек. */
    private ArrayList<Point> pointsArray;

    /**
     * @param array массив экземпляров класса Point, содежащий координаты точек.
     */
    public ClosestDistant(ArrayList<Point> array) {
        this.pointsArray = new ArrayList<Point>(array);
    }

    /**
     * Осуществляет поиск наименьшего расстояние между двумя точками из всего переданного в конструктор массива. Весь массив
     * делится на дву части и в каждой части ищется минимальное значение. Из двух полученных значений выбирается
     * наименьшее. Дальше производится проверка и возможном наименьшем расстоянии между точками из разных частей.
     * @return минимально расстояние между всеми точками плоскости.
     */
    public double closestDistant() {

        double minDistance;
        if ( this.pointsArray.size() <= 3 ) {
            minDistance = findMinDistance(pointsArray);
        } else {
            Collections.sort(pointsArray, new XCoordinateComporator());

            int XDelimeter = pointsArray.get((pointsArray.size() / 2) - 1).getX();

            ArrayList<Point> firstHalf = new ArrayList<Point>();
            ArrayList<Point> secondHalf = new ArrayList<Point>();

            for (Point p : pointsArray) {
                if (p.getX() <= XDelimeter) {
                    firstHalf.add(p);
                } else {
                    secondHalf.add(p);
                }
            }

            double minFirstHalfDistance = findMinDistance(firstHalf);
            double minSecondHalfDistance = findMinDistance(secondHalf);
            minDistance = minFirstHalfDistance < minSecondHalfDistance ? minFirstHalfDistance : minSecondHalfDistance;

            ArrayList<Point> sortedYCoordArray = new ArrayList(pointsArray);
            Collections.sort(sortedYCoordArray, new YCoordinateComporator());

            for (Point p1 : firstHalf) {
                if (p1.getX() > XDelimeter - minDistance) {
                    for (Point p2 : sortedYCoordArray) {
                        if (p2.getY() <= p1.getY() + minDistance && !p1.equals(p2)) {
                            double min = p1.findDistanceToNeighbor(p2);
                            if (min < minDistance) {
                                minDistance = min;
                            }
                        }
                    }
                }
            }

            for (Point p1 : secondHalf) {
                if (p1.getX() < XDelimeter + minDistance) {
                    for (Point p2 : sortedYCoordArray) {
                        if (p2.getY() <= p1.getY() + minDistance && !p1.equals(p2)) {
                            double min = p1.findDistanceToNeighbor(p2);
                            if (min < minDistance) {
                                minDistance = min;
                            }
                        }
                    }
                }
            }
        }
    return minDistance;

    }

    /**
     * метод осуществляет поиск наименьшего расстояние между двумя точками в переданном массиве. Для поиска используется
     * полный перебор всех значений. Является вспомогательным методом для метода  {@link #closestDistant()}
     * @param points массив точек на плоскости
     * @return минимальное расстояние между точками.
     */
    private double findMinDistance(ArrayList<Point> points) {

        double minDistance = points.get(0).findDistanceToNeighbor(points.get(1));
        double distance;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                distance = p1.findDistanceToNeighbor(p2);
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }
}
