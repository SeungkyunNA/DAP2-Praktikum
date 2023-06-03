import java.util.ArrayList;

public class SimpleHT{

    public int size;
    public ArrayList<ArrayList<Pair<Integer,Integer>>> data;

    public SimpleHT(int capacity) {
        if (capacity < 2) {
            throw new IllegalArgumentException("Hash-Capacity must be > 1");
        }
        data = new ArrayList<ArrayList<Pair<Integer,Integer>>>();
        this.size = capacity;
        for (int i = 0 ; i < size ; i++) {
            ArrayList<Pair<Integer,Integer>> p = new ArrayList<Pair<Integer,Integer>>();
            data.add(p);
        }

    }
    
    public int addressOf(Integer key) {
        return ((key % size) + size) % size;
    }
    public void insert(Integer key, Integer value) {
        Pair<Integer,Integer> newPair = new Pair<Integer,Integer>();
        newPair.key = key;
        newPair.value = value;

        ArrayList<Pair<Integer,Integer>> list = data.get(addressOf(key));

        boolean flag = false; // Ist KEY schon vorhanden? 
        for (Pair<Integer,Integer> p : list) {
            if (p.key.equals(key)) {
                p.value = value; // Überschreiben
                flag = true;
            }
        }

        if (!flag) { // KEY gibt es in DATA nicht. fügen wir ein neues Paar.
            list.add(newPair);
        }
        

    }
    public Integer get(Integer key) {
        ArrayList<Pair<Integer,Integer>> temp = data.get(addressOf(key));
        if (temp.size() == 0) {
            return null;
        }

        for (Pair<Integer,Integer> p : temp) {
            if (p.key.equals(key)) {
               
                return p.value;
            }
        }
  
        return null;
    }
    public boolean remove(Integer key) {

        ArrayList<Pair<Integer,Integer>> list = data.get(addressOf(key));

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).key.equals(key)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }
}