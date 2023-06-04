import java.util.ArrayList;

public class ParamHT<K, V> {
    public int size;
    public ArrayList<ArrayList<Pair<K,V>>> data;
    public SimpleHashFunction<K> hashFunction;

    public ParamHT(int capacity) {
        /*  Fehlerbehandlung, Kapazität muss großer als 1 sein, 
            k = 0 oder negative Zahl macht kein Sinn             */
        if (capacity < 2) {
            throw new IllegalArgumentException("Hash-Capacity must be > 1");
        }

        /* Init für Data und Kapazität  */
        data = new ArrayList<ArrayList<Pair<K,V>>>();
        this.size = capacity;
        for (int i = 0 ; i < size ; i++) {
            ArrayList<Pair<K,V>> p = new ArrayList<Pair<K,V>>();
            data.add(p);
        }
    }


    public ParamHT(int capacity, SimpleHashFunction<K> hashFunction) {
        
        /* Besondere Hashfunktion */
        this.hashFunction = hashFunction;

        /*  Fehlerbehandlung, Kapazität muss großer als 1 sein, 
            k = 0 oder negative Zahl macht kein Sinn             */
        if (capacity < 2) {
            throw new IllegalArgumentException("Hash-Capacity must be > 1");
        }

        /* Init für Data und Kapazität  */
        data = new ArrayList<ArrayList<Pair<K,V>>>();
        this.size = capacity;
        for (int i = 0 ; i < size ; i++) {
            ArrayList<Pair<K,V>> p = new ArrayList<Pair<K,V>>();
            data.add(p);
        }
    }



    public int addressOf(K key) throws IndexOutOfBoundsException{
        /* Ob dies Class mit oder ohne (Externe) Hashfunktion? */

        if (hashFunction == null){ // Ohne (Externe) Hashfunktion
            return ((key.hashCode() % size) + size) % size;
        } 

        int code = hashFunction.getHash(key, size); // Mit (Externe) Hashfunktion
        
        /* Fehlerbehandlung, Falls Hashfunktion äußere Index zurückgegeben */
        if (code >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            return code;
        }
        
    }

    /* ___________________Hash-Methode-Implement___________________ */

    /* ParamHT : Insert */
    public void insert(K key, V value) {

        Pair<K,V> newPair = new Pair<K,V>();
        newPair.key = key;
        newPair.value = value;

        ArrayList<Pair<K,V>> list = data.get(addressOf(key));

        boolean keyAleadyExist = false; // Ist KEY schon vorhanden? 
        for (Pair<K,V> p : list) {
            if (p.key.equals(key)) {
                p.value = value; // Überschreiben
                keyAleadyExist = true;
            }
        }

        if (!keyAleadyExist) { // KEY gibt es in DATA nicht. fügen wir ein neues Paar.
            list.add(newPair);
        }

    }
    /* ParamHT : Get */
    public V get(K key) {
        ArrayList<Pair<K,V>> list = data.get(addressOf(key));
        
        if (list.size() == 0) { // Falls kein Element in List exsistiert
            return null;
        }

        for (Pair<K,V> p : list) {
            if (p.key.equals(key)) { // Suche in verkettete List das Ziel-Pair
                return p.value;
            }
        }

        return null; // Es gibt kein Paar für gegebenes KEY
    }

    /* ParamHT : Remove */
    public boolean remove(K key) {

        ArrayList<Pair<K,V>> list = data.get(addressOf(key));

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).key.equals(key)) { // Suche in verkettete List das Ziel-Pair
                list.remove(i);
                return true;
            }
        }
        return false;  // Es gibt kein Paar für gegebenes KEY
    }
}
