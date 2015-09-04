package filesystemcaches;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Esteban Muñoz <edmunoz@espol.edu.ec>
 */
public class FileSystemCaches {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //$java cacheSimulator workload.txt LRU 50000
        //time java -cp build/classes/ filesystemcaches.FileSystemCaches file algorit cacheSize
        String file = args[0].toString();
        String algorit = args[1].toString();
        int cacheSize = Integer.parseInt(args[2].toString());
        switch(algorit) {
            case "FIFO":
                System.out.println("Runing FIFO cache simulator .....");
                FIFOCache<Integer,String> cacheFIFO = new FIFOCache<>(cacheSize);        
                FIFO(cacheFIFO,file);                
                break;
            case "OPTIMUS":
                System.out.println("OPTIMUS");
                break;                
            case "LRU": 
                System.out.println("Runing LRU cache simulator .....");
                LRUCache<Integer,String> cacheLRU = new LRUCache<>(cacheSize);        
                LRU(cacheLRU,file);
                break;                
            default:
                throw new AssertionError();
        }
    }


    public static void LRU(LRUCache cache,String fileName){
        BufferedReader br = null;
        int count = 0;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null){
                count++;
                cache.put(count,sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }        
        //System.out.println(cache);
        System.out.println("Hits : "+ cache.getHitRate());
        System.out.println("Misses :"+cache.getMissRate());    
    }

    public static void FIFO(FIFOCache cache,String fileName){
        BufferedReader br = null;        
        int count = 0;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null) {                
                count++;
                System.out.println(count);
                cache.put(count,sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }        
        System.out.println(cache);
        System.out.println("Hits : "+ cache.getHitRate());
        System.out.println("Misses :"+cache.getMissRate());    
    }


}