import java.util.*;

/*
    Реализуйте структуру телефонной книги с помощью HashMap.
    Программа также должна учитывать, что во входной структуре будут
    повторяющиеся имена с разными телефонами, их необходимо считать,
    как одного человека с разными телефонами.
    Вывод должен быть отсортирован по убыванию числа телефонов.
 */

public class Main {
    public static void main(String[] args) {
        TelephoneBook book = new TelephoneBook();
        book.add("Иванов Иван", "123");
        book.add("Иванов Андрей", "456");
        book.add("Иванов Иван", "789");
        book.add("Сидорова Анна", "321");
        book.add("Сидорова Анна", "213");
        book.add("Сидорова Анна", "89578");
//        System.out.println(book);
//        book.deleteByName("Сидорова Анна");
//        System.out.println(book);
//        System.out.println(book.getPhoneByName("Иванов Иван"));
//        System.out.println(book.getNameByPhone("456"));
//        book.deleteOnlyPhone("123");
        System.out.println(book);

    }
}