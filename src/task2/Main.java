package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс для запуска программы.
 *
 * Программа на входе (из файла или с консоли) получает множество чисел, записанных через запятую, и определяет возможно ли
 * разбить множество на два подмножества, в которых суммы чисел равны.
 * Если множество нельзя разбить, то выводится соответсвующее сообщение.
 * Также программа проверяет возможность получить число 100 сложением элементов из множества. Если такая возможность есть,
 * то будет выведено сообщение yes. В противном случае будет выведено no.
 * Программа может брать данные из файла или с консоли. После запуска будет задан вопрос об источнике ввода.
 * Ввести file - для чтения данных из файла.
 * Ввести console или нажать Enter для ввода данных с консоли.
 * В случае выбора file программа запросит полный путь до файла. После ввода полного пути нужно нажать Enter.
 * Числа должны вводиться через пробел в одну строку. По окончанию ввода (в случае ввода данных из консоли) необходимо
 * нажать Enter.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Integer> array = new ArrayList<Integer>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите способ ввода дынных: \n" +
                "file - ввод данных из файла. \n" +
                "console (или нажмите Enter) - ввод данных с консоли.");
        String choice = reader.readLine();
        String line;
        if (choice.equals("file")) {
            System.out.println("Введите полный путь до файла: ");
            String filePath = reader.readLine();
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            line = file.readLine();
        } else {
            System.out.println("Введите входные числа через пробел и нажмите Enter: ");
            line = reader.readLine();
        }

        String[] tmpArray = line.split(" ");
        for (String str : tmpArray) {
            int digit = Integer.parseInt(str);
            array.add(digit);
        }

        Table table = new Table(array);
        table.create();

        Table table100 = new Table(array, 100);
        table100.create();

        Partitionator partitionator = new Partitionator(table);
        Partitionator partitionator100 = new Partitionator(table100);

        partitionator.start();
        partitionator100.start();

        partitionator.join();

        ArrayList<Integer> leftSide = partitionator.getPartition();

        Collections.sort(leftSide);

        ArrayList<Integer> rightSide = new ArrayList(array);

        /*RemoveAll не подойдет, т.к. в правой части будут удалены все символы, совпадающие с левой частью.
        * Ex: 30 15 5 45 5. Должно быть - 5 45 : 5 15 30
        * Будет при использование rightSide.removeAll(leftSide) - Res = 30 15 5 : 45.
        */
        for (Integer ls : leftSide) {
            rightSide.remove(ls);
        }

        if (partitionator.isPartitionable()) {
            System.out.println(leftSide.toString().substring(1, leftSide.toString().length() - 1).replaceAll(",", "") + " - "
                    + rightSide.toString().substring(1, rightSide.toString().length() - 1).replaceAll(",", ""));
        } else {
            System.out.println("С данным набором предметов выставить весы в равновесии нельзя! ");
        }

        partitionator100.join();

        if (partitionator100.isPartitionable()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }


    }
}
