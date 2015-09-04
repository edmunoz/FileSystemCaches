package filesystemcaches;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @reference http://codeidol.com/java/javagenerics/Maps/Implementing-Map/
 * @author Esteban Muñoz <edmunoz@espol.edu.ec>
 * @param <K> Key
 * @param <V> Value
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int cacheSize;
    
    /**
     * Constructor
     * @param cacheSize Tamaño de la cache(Numero de entidades)
     */
    public LRUCache(int cacheSize) {
        //int initialCapacity = 16   La capacidad inicial
        //float loadFactor = 0.75f   El factor de carga
        //boolean accessOrder = true Modo de ordenamiento
        super(cacheSize, 0.75f,true);
        this.cacheSize = cacheSize;
    }
    
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return this.size() > this.cacheSize;
    }

}