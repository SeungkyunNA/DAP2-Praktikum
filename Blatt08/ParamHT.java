import java.util.ArrayList;

public class ParamHT<K, V> {
    public int size;
    public ArrayList<ArrayList<Pair<K,V>>> data;
    public SimpleHashFunction<K> hashFunction;

    public ParamHT(int capacity) {

        if (capacity < 2) {
            throw new IllegalArgumentException("Hash-Capacity must be > 1");
        }

        data = new ArrayList<ArrayList<Pair<K,V>>>();
        this.size = capacity;
        for (int i = 0 ; i < size ; i++) {
            ArrayList<Pair<K,V>> p = new ArrayList<Pair<K,V>>();
            data.add(p);
        }
    }


    public ParamHT(int capacity, SimpleHashFunction<K> hashFunction) {
        this.hashFunction = hashFunction;

        if (capacity < 2) {
            throw new IllegalArgumentException("Hash-Capacity must be > 1");
        }

        data = new ArrayList<ArrayList<Pair<K,V>>>();
        this.size = capacity;
        for (int i = 0 ; i < size ; i++) {
            ArrayList<Pair<K,V>> p = new ArrayList<Pair<K,V>>();
            data.add(p);
        }
    }



    public int addressOf(K key) throws IndexOutOfBoundsException{

        if (hashFunction == null){
            return ((key.hashCode() % size) + size) % size;
        } 
        int code = hashFunction.getHash(key, size);
        if (code >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            return code;
        }
        
    }
    public void insert(K key, V value) {

        Pair<K,V> newPair = new Pair<K,V>();
        newPair.key = key;
        newPair.value = value;

        ArrayList<Pair<K,V>> list = data.get(addressOf(key));

        boolean flag = false; // Ist KEY schon vorhanden? 
        for (Pair<K,V> p : list) {
            if (p.key.equals(key)) {
                p.value = value; // Überschreiben
                flag = true;
            }
        }

        if (!flag) { // KEY gibt es in DATA nicht. fügen wir ein neues Paar.
            list.add(newPair);
        }

    }
    public V get(K key) {
        ArrayList<Pair<K,V>> list = data.get(addressOf(key));
        
        if (list.size() == 0) {
            return null;
        }

        for (Pair<K,V> p : list) {
            if (p.key.equals(key)) {
                return p.value;
            }
        }

        return null;
    }
    public boolean remove(K key) {

        ArrayList<Pair<K,V>> list = data.get(addressOf(key));

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).key.equals(key)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }
}
