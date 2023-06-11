import java.util.ArrayList;

public class GenHT<K, V>{

    public int size;
    public ArrayList<ArrayList<Pair<K,V>>> data;
    public GenHT(int capacity) {
        /*  Fehlerbehandlung, Kapazität muss großer als 1 sein, 
            k = 0 oder negative Zahl macht kein Sinn             */
        if (capacity < 2) {
            capacity=1;
        }

        /* Init für Data und Kapazität  */
        data = new ArrayList<ArrayList<Pair<K,V>>>();
        this.size = 0;
        for (int i = 0 ; i < capacity ; i++) {
            ArrayList<Pair<K,V>> p = new ArrayList<Pair<K,V>>();
            data.add(p);
        }
    }

    public class Pair<K, V>{
        K key;
        V value;
    }

    /* Einfache Hashfunktion. es wird für negative Zahl auch ein "Positive modulo" ergeben. */
    public int addressOf(K key) {
        return ((key.hashCode() % data.size()) + data.size()) % data.size();
    }

    /* ___________________Hash-Methode-Implement___________________ */

    /* GenHT : Insert */
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
            size++;
        }

    }
    /* GenHT : Get */
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

    /* GenHT : Remove */
    public boolean remove(K key) {

        ArrayList<Pair<K,V>> list = data.get(addressOf(key));

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).key.equals(key)) { // Suche in verkettete List das Ziel-Pair
                list.remove(i);
                size--;
                return true;
            }
        }
        return false; // Es gibt kein Paar für gegebenes KEY
    }

    }

    