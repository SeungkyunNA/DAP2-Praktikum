import java.util.Random;

public class HashFunction<K> implements SimpleHashFunction<K>{
    final int prim = 31;
    final double alpha = 0.19930121;
    Random r = new Random();
    final int a = r.nextInt(prim-1) + 1;
    final int b = r.nextInt(prim-1);
    /* Univeselle Hashfunktion */
    public int getHash(K key, int m) {

        if (m < prim) {
            int h = a*key.hashCode() + b;
            return (((h % prim) + prim) % prim) % m;
        } else {
            int s = Math.abs(key.hashCode());
            int sAlpha_Aufrundung = (int) (s*alpha);
            double sAlpha = s*alpha - sAlpha_Aufrundung;
            return (int) (m * sAlpha);
        }
        
    }

}
