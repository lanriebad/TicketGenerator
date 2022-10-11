import java.util.*;

public class GeneratorUtil {
    public static int getRand(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

   public static int getNumberOfElementsInSet(List<List<Integer>> set) {
        int count = 0;
        for (List<Integer> li : set)
            count += li.size();
        return count;
    }
}
