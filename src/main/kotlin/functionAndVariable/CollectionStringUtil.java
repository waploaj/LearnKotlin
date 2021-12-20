package functionAndVariable;

import java.util.List;

public class CollectionStringUtil {
    public static List<String> uppercaseall(List<String> list){
        for (int i = 0; i<list.size(); i++){
            list.set(i, list.get(i).toUpperCase());
        }
        return list;
    }
}
