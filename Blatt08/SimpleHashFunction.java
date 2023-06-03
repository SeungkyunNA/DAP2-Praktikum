public interface SimpleHashFunction<K> {
    public int getHash(K key, int m);
}
