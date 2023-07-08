import java.util.*;

public class TelephoneBook {
    private Map<String, List<String>> map = new HashMap<>();

    /**
     * Метод для добавления записи в телефонную книгу
     * @param name Имя абонента
     * @param phone Телефон абонента
     */

    public void add(String name, String phone) {
        if (map.containsKey(name)) {
            map.get(name).add(phone);
        } else {
            List<String> list = new ArrayList<>();
            list.add(phone);
            map.put(name, list);
        }
    }

    /**
     * Метод для получения всех телефонов абонента по имени
     * @param name Имя абонента
     * @return Список телефонов абонента
     */

    public List<String> getPhoneByName(String name) {
        return map.get(name);
    }

    /**
     * Метод для получения имени абонента по номеру телефона
     * @param phone Телефон абонента
     * @return Имя абонента
     */

    public String getNameByPhone(String phone) {
        for (Map.Entry item: map.entrySet()) {
            List<String> list = (List<String>) item.getValue();
            for (String num : list) {
                if (num.equals(phone)) {
                    return (String) item.getKey();
                }
            }
        }
        return null;
    }

    /**
     * Метод для удаления всех номеров абонента из справочника
     * @param name Имя абонента
     */
    public void deleteByName(String name) {
        map.remove(name);
    }

    /**
     * Метод для удаления одного телефона из справочника независимо от имени абонента
     * @param phone
     */
    public void deleteOnlyPhone(String phone) {
        String name = getNameByPhone(phone);
        if (name != null) {
            map.get(name).remove(phone);
            if (map.get(name).isEmpty()) {
                map.remove(name);
            }
        }
    }

    /**
     * Метод для упрощенного вывода всего справочника
     * @return Строка с информацией из справочника
     */
    public String toString() {
        return getTreeMap(new ArrayList<>(map.keySet())).toString();
    }

    /**
     * Метод для вывода данных справочника в виде таблицы
     * @param names Список имен абонентов для вывода данных
     * @return Отформатированная строка
     */
    public String show(List<String> names) {
        String nameTitle = "Абонент";
        String phoneTitle = "Номер";
        int nameFieldLength = 20;
        int phoneFieldLength = 20;
        // Формируем заголовок
        String nameLine =  "─".repeat(nameFieldLength);
        String phoneLine =  "─".repeat(phoneFieldLength);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("┌").append(nameLine).append("┬").append(phoneLine).append("┐\n");
        stringBuilder.append(getTableLine(nameTitle,phoneTitle,nameFieldLength,phoneFieldLength));
        stringBuilder.append("├").append(nameLine).append("┼").append(phoneLine).append("┤\n");
        // Формируем область данных
        Map<List<String>, String> tree = getTreeMap();
        for (Map.Entry item: tree.entrySet()) {
            String name = (String) item.getValue();
            List<String> list = (List<String>) item.getKey();
            stringBuilder.append(getTableLine(name,list.get(0),nameFieldLength,phoneFieldLength));
            for (int i = 1; i < list.size(); i++) {
                stringBuilder.append(getTableLine("",list.get(i),nameFieldLength,phoneFieldLength));
            }
        }
        // Формируем завершающую строку
        stringBuilder.append("└").append(nameLine).append("┴").append(phoneLine).append("┘\n");
        return stringBuilder.toString();
    }

    /**
     * Вспомогательный метод, формирующий одну строку таблицы
     * @param first Содержимое первого столбца
     * @param second Содержимое второго столбца
     * @param firstFieldLength Ширина первого столбца
     * @param secondFieldLength Ширина второго столбца
     * @return Отформатированная строка
     */
    private String getTableLine(String first, String second, int firstFieldLength, int secondFieldLength) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("│");
        stringBuilder.append(first);
        stringBuilder.append(" ".repeat(firstFieldLength - first.length()));
        stringBuilder.append("│");
        stringBuilder.append(second);
        stringBuilder.append(" ".repeat(secondFieldLength - second.length()));
        stringBuilder.append("│\n");
        return stringBuilder.toString();
    }

    /**
     * Вспомогательный метод, формирующий TreeMap для заданного списка имен абонентов
     * @param names Список имен абонентов для вывода данных
     * @return справочник в виде TreeMap
     */
    private TreeMap getTreeMap(List<String> names) {
        TelephoneBookComparator comparator = new TelephoneBookComparator();
        TreeMap<List<String>, String> tree = new TreeMap<>(comparator);
        for (String name : names) {
            tree.put(map.get(name), name);
        }
        return tree;
    }
}
