// Вариант 7.
//  Создать линейный однонаправленный список из целых чисел.
//  Вставить в список число 0 перед каждым числом от 2 до 7.
//  Определить сумму чисел больших 7.
//  Удалить из списка элементы, которые больше 5.

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var numbers = new ArrayList<Integer>();

        numbers.ensureCapacity(10);
        for(int i = 1; i < 10; ++i)
            numbers.add(i);
        System.out.println("Исходный список:\n" + numbers);

        {
            var currentIter = numbers.listIterator(numbers.size());
            while (currentIter.hasPrevious()) {
                var number = currentIter.previous();
                if (number >= 2 && number <= 7) {
                    currentIter.add(0);
                    currentIter.previous();
                }
            }
            System.out.println("\nВставить в список число 0 перед каждым числом от 2 до 7:\n" + numbers);
        }

        {
            var ref = new Object() {
                int summ = 0;
            };
            numbers.forEach((number) -> {
                if (number > 7) ref.summ += number;
            });
            System.out.println("\nОпределить сумму чисел больших 7:\n" + ref.summ);
        }
        
        numbers.removeIf(number -> number > 5);
        System.out.println("\nУдалить из списка элементы, которые больше 5:\n" + numbers);
    }
}
