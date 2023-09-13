package software.darkmatter.java10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Java10 {

    public void var() {
        var s = "String from var";
        System.out.println(s);
    }

    public void immutableCollections() {
        var list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        var unmodifiableList = Collections.unmodifiableList(list);

        var set = new HashSet<>(list);
        var unmodifiableSet = Collections.unmodifiableSet(set);

        var map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        var unmodifiabledMap = Collections.unmodifiableMap(map);

        unmodifiableList.forEach(System.out::println);
        unmodifiableSet.forEach(System.out::println);
        unmodifiabledMap.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    public void collectionCopyOf() {
        var list = List.of(1, 2);
        var newUnmodifiableList = List.copyOf(list);

        newUnmodifiableList.forEach(System.out::println);
    }
}
