import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

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
     * Метод для вывода всего справочника в виде строки
     * @return Строка с информацией из справочника
     */
    public String toString() {
        return map.toString();
    }


}
