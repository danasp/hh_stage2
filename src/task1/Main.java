package task1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Класс для запуска программы.
 *
 * Программа на входе (из файла или с консоли) получает координаты все точек плоскости и выводит в консоль минимальное
 * расстояние между двуя точками.
 * Программа может брать данные из файла или с консоли. После запуска будет задан вопрос об источнике ввода.
 * Ввести file - для чтения данных из файла.
 * Ввести console или нажать Enter для ввода данных с консоли.
 * В случае выбора file программа запросит полный путь до файла. После ввода полного пути нужно нажать Enter.
 * Координата точки должна вводиться через пробел в одну строку. Координата следующей точки должна вводиться с новой строки.
 * По окончанию ввода (в случае ввода данных из консоли) необходимо дважды нажать Enter.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Point> pointsArray = new ArrayList<Point>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите способ ввода дынных: \n" +
                            "file - ввод данных из файла. \n" +
                            "console (или нажмите Enter) - ввод данных с консоли.");
        String choice = reader.readLine();


        if (choice.equals("file")) {
            System.out.println("Введите полный путь до файла: ");
            String filePath = reader.readLine();
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            String input;
            while ((input = file.readLine()) != null) {

                String[] tmp = input.split(" ");
                int x = Integer.parseInt(tmp[0]);
                int y = Integer.parseInt(tmp[1]);
                pointsArray.add(new Point(x,y));
            }
            file.close();
        }
        else {
            System.out.println("Введите координаты точек: ");
            while (true) {
                String input = reader.readLine();
                if (input.isEmpty()) {
                    break;
                } else {
                    String[] tmp = input.split(" ");
                    int x = Integer.parseInt(tmp[0]);
                    int y = Integer.parseInt(tmp[1]);
                    pointsArray.add(new Point(x, y));
                }
            }
        }
        reader.close();

        ClosestDistant closestDistant = new ClosestDistant(pointsArray);
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(df.format(closestDistant.closestDistant()));

    }
}
