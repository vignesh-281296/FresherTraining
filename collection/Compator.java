import java.util.Comparator;

public class Compator implements Comparator<Integer> {
    
    public int compare(int o1, int o2) {
        if (o1 > o2) {
            return 1;   
        }
        return -1;
    } 
}