package filesystemcaches;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

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
        //time java -cp build/classes/ filesystemcaches.FileSystemCaches 5
        //time java -cp build/classes/ filesystemcaches.FileSystemCaches file algorit cacheSize
        String file = args[0].toString
        ();
        String algorit = args[1].toString();
        String cacheSize = Integer.parseInt(args[2].toString());
        switch(algorit) {
            case "FIFO":
                System.out.println("FIFO");
                break;
            case "OPTIMUS":
                System.out.println("OPTIMUS");
                break;                
            case "LRU": 
                System.out.println("LRU");
                LRUCache<Integer,String> cache = new LRUCache<>(cacheSize);        
                LRU(cache,"/home/esteban/Descargas/libro.txt");
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
        //System.out.println(cache);
        System.out.println("Hits : "+ cache.getHitRate());
        System.out.println("Misses :"+cache.getMissRate());    
    }


}