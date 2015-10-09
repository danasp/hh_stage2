package task2;

import java.util.ArrayList;

/**
 * Класс, осуществляющий разбиения множество на два подмножества.
 */
public class Partitionator extends Thread {

    /** Множество, для которого осуществляется разбиение.*/
    private int[] array;
    /** Таблица, при помощи которой осуществляется разделение. Таблица - экземплят класса Table
     * @see Table
     */
    private Table table;
    /** Одно из подмножеств */
    private ArrayList partition;

    /**
     *
     * @param table экземплят класс Table
     * @see Table
     */
    public Partitionator(Table table) {
        this.table = table;
        this.array = table.getArray();
    }

    public void run() {
        findPartition();
    }

    /**
     * Возвращает одно из двух подмножеств.
     * @return одно найденное подмножество.
     */
    public ArrayList<Integer> getPartition() {
        return partition;
    }

    /**
     * Возвращает результат возможности разбиениения одного подмножества на два с равной суммой. Если
     * @return возвращает true если сумма элементов найденного подмножества равна половине суммы всех элементов множества.
     * Иначе false.
     */
    public boolean isPartitionable() {
        int sum = 0;
        for (Integer i : this.getPartition()) {
            sum += i;
        }
        if (sum == table.getSum()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Просматривает экземпляр класса Table и находит искомое подмножество.
     * @see Table
     */
    private void findPartition() {

        partition = new ArrayList<>();

        //Находим точку с первым значением true в последнем столбце.
        int i;
        int j = table.getSum();
        for (i = 0; i < array.length; i++) {
            if (table.getTable()[i][j] == true) {
                break;
            }
        }

        boolean running = true;
        while (running) {
            while (table.getTable()[i-1][j] == true) {
                i--;
                if (i == 0) {
                    if (j != 0 ) {
                        partition.add(array[i]);
                    }
                    running = false;
                    break;
                }
            }
            if (i != 0) {
                try {
                    j = j - array[i];
                    partition.add(array[i]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    running = false;
                }
                if (j == 0) {
                    running = false;
                }
            }
        }

    }
}
