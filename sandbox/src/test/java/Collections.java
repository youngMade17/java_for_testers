import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class Collections {
    @Test
    void setTests() {
        var set = Set.copyOf(List.of("a", "b", "c", "a"));
    }
}

