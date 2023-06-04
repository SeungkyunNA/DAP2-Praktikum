
public class HashFunction<K> implements SimpleHashFunction<K>{

    final double alpha = 0.19930121; // Ein Realler Zahl zwischen (0 1)

    public int getHash(K key, int m) {
        
        /* Multiplikationsmethode , wenn m >= 31 ist. */
        int s = Math.abs(key.hashCode());
        int sAlpha_Aufrundung = (int) (s*alpha);
        double sAlpha = s*alpha - sAlpha_Aufrundung;
        return (int) (m * sAlpha);
        
        
    }

}
