package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Greedy {
    public static void main(String[] args) {
        Map<String, HashSet<String>> hashmap = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("台北");
        hashSet1.add("宜蘭");
        hashSet1.add("基隆");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("新北市");
        hashSet2.add("台北");
        hashSet2.add("林口");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("新竹");
        hashSet3.add("宜蘭");
        hashSet3.add("屏東");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("宜蘭");
        hashSet4.add("林口");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("屏東");
        hashSet5.add("台東");

        hashmap.put("K1", hashSet1);
        hashmap.put("K2", hashSet2);
        hashmap.put("K3", hashSet3);
        hashmap.put("K4", hashSet4);
        hashmap.put("K5", hashSet5);

        HashSet<String> allArea = new HashSet<>();
        allArea.add("台北");
        allArea.add("宜蘭");
        allArea.add("基隆");
        allArea.add("新北市");
        allArea.add("林口");
        allArea.add("新竹");
        allArea.add("屏東");
        allArea.add("台東");

        ArrayList<String> select = new ArrayList<>();
        HashSet<String> temp = new HashSet<>();
        String maxKey;
        while (allArea.size() != 0) {
            maxKey = null;
            for(String key : hashmap.keySet()){
                temp.clear();
                HashSet<String> area = hashmap.get(key);
                temp.addAll(area);
                temp.retainAll(allArea); // 求交集
                if(temp.size() > 0 && (maxKey == null || temp.size() > hashmap.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if(maxKey != null){
                select.add(maxKey);
                allArea.removeAll(hashmap.get(maxKey));
            }
        }

        System.out.println(select);

    }
}