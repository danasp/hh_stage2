package task2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс для хранения булевой таблицы необходимой для определения возможности разбить входное множество чисел на два поднабора
 * с одинаковой суммой чисел в каждом подмножестве.
 * При необходимости определить возможность выборки чисел, сумма которых равна определенному числу, то это число нужно
 * передать в конструктор в качестве второго параметра.
 * Экземпляр класса необходимо передать в экземпляр класса Partitionator
 * @see Partitionator
 */
public class Table
{
    /**
     * Переданное множество.
     */
    private int[] array;
    /**
     * Сумма всех элементов преданного множества.
     */
    private int sum = 0;

    /**
     * Построенная таблица.
     */
    private boolean[][] table;

    /**
     *
     * @param array множество чисел, которое необходимо разбить на 2 подмножества.
     */
    public Table(ArrayList<Integer> array) {
        Collections.sort(array);
        this.array = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            this.array[i] = array.get(i).intValue();
            sum += array.get(i);
        }
        sum = sum/2;

    }

    /**
     *
     * @param array множество чисел, которое необходимо разбить на 2 подмножества.
     * @param sum максимальное число, которое необходимо получить комбинацией элекентов входного множества
     */
    public Table(ArrayList<Integer> array, int sum) {
        this.array = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            this.array[i] = array.get(i).intValue();
        }
        this.sum = sum;
    }

    /**
     * Необходимо вызвать у экземпляра класса для построения таблицы.
     */
    public void create() {
        createTable();
    }

    /**
     * Получить таблицу
     * @return таблица для расчета подмножества.
     */
    public boolean[][] getTable() {
        return table;
    }

    /**
     * Поллучить переланное множество. Данное множество передается в конструкторе при создании экземпляра.
     * @return переданное множество.
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Получить сумму всех элементов множества.
     * @return сумма всех элементов множетсва.
     */
    public int getSum() {
        return sum;
    }

    /**
     * Метод для построения таблицы.
     * Таблица строится по принципу, описанному в <a href="http://algorithms.tutorialhorizon.com/dynamic-programming-subset-sum-problem/">статье</>
     * @see <a href="http://algorithms.tutorialhorizon.com/dynamic-programming-subset-sum-problem/">Dynamic Programming — Subset Sum Problem</>
     */
    private void createTable() {
        table = new boolean[array.length][sum+1];
        int i = 0;
        for (int j = 0; j <= sum; j++) {
            if (j == 0 || array[i] == j) {
                table[i][j] = true;
            } else {
                table[i][j] = false;
            }
//            System.out.print(table[i][j] + " ");
        }

//        System.out.println();

        for (i = 1; i < array.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j == 0 || array[i] == j) {
                    table[i][j] = true;
                } else if (array[i] > j) {
                    table[i][j] = table[i-1][j];
                } else if (array[i] == j) {
                    table[i][j] = true;
                } else {
                    table[i][j] = table[i-1][j-array[i]];
                }
//                System.out.print(table[i][j] + " ");
            }
//            System.out.println();
        }
    }
}
