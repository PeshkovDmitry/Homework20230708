import java.util.Comparator;
import java.util.List;

public class TelephoneBookComparator implements Comparator<List<String>> {
    @Override
    public int compare(List<String> o1, List<String> o2) {
        return o2.size() - o1.size();
    }
}
