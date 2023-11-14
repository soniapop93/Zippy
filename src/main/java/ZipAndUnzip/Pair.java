package ZipAndUnzip;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair {
    private String item1;
    private String item2;

    public Pair(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }
}
