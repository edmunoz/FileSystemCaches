/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filesystemcaches;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;


/**
 * 
 * @author Esteban Muñoz <edmunoz@espol.edu.ec>
 */
public class FIFOCache<K,V> extends LinkedHashMap<K,V>{
    private final int cacheSize;
    private long missRate = 0;
    private long hitRate = 0;
    /**
     * Constructor
     * @param cacheSize Tamaño de la cache(Numero de entidades)
     */
    public FIFOCache(int cacheSize) {
        super(cacheSize,0.75f,false);
        this.cacheSize = cacheSize;
    }
    
    /**
     * preguntamos si hay datos
     * Si no hay agregamos el dato y miss++
     * Si hay 
     * 
     */
    @Override
    public V put(K key, V value) {
        V result = null; 
        if(this.size() < this.cacheSize){//Validamos si la cache llego a su limite
            if(containsValue(value)){//Preguntamos si el valor esta en la tabla
                this.hitRate++;//Aumentamos el hit rate
            }
            else{//Si no se encuentra en la tabla
                result = super.put(key, value);//Agregamos el valor
                this.missRate++;//Aumentamos el miss rate
            }
        }
        else{//Cuando la cache llego a su tamaño total
            if(containsValue(value)){//Si el valor esta en la tabla
                this.hitRate++;//Aumentamos el hit rate
            }
            else{
                Object m = keySet().toArray()[0];//Obtenemos el primer valor en la tabla
                remove(m);//Lo removemos
                result = super.put(key, value);//Agregamos el nuevo valor al final
                this.missRate++;//Aumentamos el miss ratr
            }
        }
        return result; 
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
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
