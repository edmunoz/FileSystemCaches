package filesystemcaches;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * @reference http://codeidol.com/java/javagenerics/Maps/Implementing-Map/
 * @author Esteban Muñoz <edmunoz@espol.edu.ec>
 * @param <K> Key
 * @param <V> Value
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int cacheSize;
    private long missRate = 0;
    private long hitRate = 0;
    
    /**
     * Constructor
     * @param cacheSize Tamaño de la cache(Numero de entidades)
     */
    public LRUCache(int cacheSize) {
        //int initialCapacity = 16   La capacidad inicial
        //float loadFactor = 0.75f   El factor de carga
        //boolean accessOrder = true Modo de ordenamiento
        super(cacheSize, 0.75f,false);
        this.cacheSize = cacheSize;
    }
    
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return this.size() > this.cacheSize;
    }

    @Override
    public V put(K key, V value) {
        V result = null; 
        int currentSize = this.size();
        if(currentSize < this.cacheSize){
            if(containsValue(value)){
                remove(getKeyByValue(this, value));
                super.put(key, value);
                this.hitRate++;
            }
            else{ 
                result = super.put(key, value);
                this.missRate++;
            }
        }
        else{
            if(containsValue(value)){
                Object x = getKeyByValue(this, value);
                remove(x);
                super.put(key, value);
                this.hitRate++;
            }
            else{
                Object m = keySet().toArray()[0];
                remove(m);
                super.put(key, value);
                this.missRate++;
            }
        }
        return result; //To change body of generated methods, choose Tools | Templates.
    }


    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public long getHitRate() {
        return hitRate;
    }

    public long getMissRate() {
        return missRate;
    }    

}