package Main;

import java.util.Collection;
import java.util.List;

public interface Calculation {
    double getMean();

    String getModus();

    String getMedian();

    default List<String> sortAsNumbers(Collection<String> collection) {
        return collection
                .stream()
                .map(Integer::valueOf)
                .sorted()
                .map(String::valueOf)
                .toList();
    }
}
