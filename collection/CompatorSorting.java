import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompatorSorting {
    
    public static void main(String args[]) {
        List<Integer> values = new ArrayList<>();
        values.add(803);
        values.add(701);
        values.add(602);
        Comparator<Integer> com = new Comparator<Integer>() {
            @override
            	
        }
        Collections.sort(values,com);
        for (int i : values) {
            System.out.println(i);
        }
    }
}