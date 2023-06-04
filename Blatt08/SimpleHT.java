import java.util.ArrayList;

public class SimpleHT{

    public int size;
    public ArrayList<ArrayList<Pair<Integer,Integer>>> data;

    public SimpleHT(int capacity) {
        /*  Fehlerbehandlung, Kapazität muss großer als 1 sein, 
            k = 0 oder negative Zahl macht kein Sinn             */
        if (capacity < 2) {
            throw new IllegalArgumentException("Hash-Capacity must be > 1");
        }

        /* Init für Data und Kapazität  */
        data = new ArrayList<ArrayList<Pair<Integer,Integer>>>();
        this.size = capacity;
        for (int i = 0 ; i < size ; i++) {
            ArrayList<Pair<Integer,Integer>> p = new ArrayList<Pair<Integer,Integer>>();
            data.add(p);
        }

    }
    
    /* Einfache Hashfunktion. es wird für negative Zahl auch ein "Positive modulo" ergeben. */
    public int addressOf(Integer key) {
        return ((key % size) + size) % size;
    }

    /* ___________________Hash-Methode-Implement___________________ */

    /* SimpleHT : Insert. */
    public void insert(Integer key, Integer value) {
        Pair<Integer,Integer> newPair = new Pair<Integer,Integer>();
        newPair.key = key;
        newPair.value = value;

        ArrayList<Pair<Integer,Integer>> list = data.get(addressOf(key));

        boolean keyAleadyExist = false; // Ist KEY schon vorhanden? 
        for (Pair<Integer,Integer> p : list) {
            if (p.key.equals(key)) {
                p.value = value; // Überschreiben
                keyAleadyExist = true;
            }
        }

        if (!keyAleadyExist) { // KEY gibt es in DATA nicht. fügen wir ein neues Paar.
            list.add(newPair);
        }
        

    }
    /* SimpleHT : Get */
    public Integer get(Integer key) {
        ArrayList<Pair<Integer,Integer>> temp = data.get(addressOf(key));
        
        if (temp.size() == 0) { // Falls kein Element in List exsistiert
            return null;
        }

        for (Pair<Integer,Integer> p : temp) {
            if (p.key.equals(key)) { // Suche in verkettete List das Ziel-Pair
                return p.value;
            }
        }
  
        return null; // Es gibt kein Paar für gegebenes KEY
    }

    /* SimpleHT : Remove */
    public boolean remove(Integer key) {

        ArrayList<Pair<Integer,Integer>> list = data.get(addressOf(key));

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).key.equals(key)) { // Suche in verkettete List das Ziel-Pair
                list.remove(i);
                return true; 
            }
        }
        return false; // Es gibt kein Paar für gegebenes KEY
    }
}