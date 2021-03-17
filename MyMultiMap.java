package Collections;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int c = 0;
        for(HashMap.Entry<K, List<V>> pair : map.entrySet()){
            c += pair.getValue().size();
        }
        return c;
    }

    @Override
    public V put(K key, V value) {
        if(map.get(key) == null){
            List<V> list = new ArrayList();
            list.add(value);
            map.put(key, list);
            return null;
        }
        else{
            List<V> list = map.get(key);
            V v = list.get(list.size()-1);
            if(list.size() < repeatCount){
                list.add(value);
            }
            else if(list.size() == repeatCount){
                list.remove(0);
                list.add(value);
            }
            map.put(key, list);
            return v;
        }
    }

    @Override
    public V remove(Object key) {
        if(map.get(key) == null){
            return null;
        }
        else{
            List<V> list = map.get(key);
            V v = list.get(0);
            list.remove(0);
            if(list.size() == 0){
                map.remove(key);
            }
            else{
                map.put((K) key, list);
            }
            return v;
        }
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList();
        for(List<V> l : map.values()){
            list.addAll(l);
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        if(map.get(key) == null){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        int c = 0;
        for(List<V> l : map.values()){
            for(V v : l){
                if(v.equals(value)){
                    c++;
                }
            }
        }
        if(c == 0){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}
