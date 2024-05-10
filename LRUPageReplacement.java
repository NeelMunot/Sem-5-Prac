import java.util.*;

public class LRUPageReplacement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of frames: ");
        int numFrames = scanner.nextInt();
        
        System.out.print("Enter number of pages: ");
        int numPages = scanner.nextInt();
        
        System.out.print("Enter page reference string: ");
        int[] pageReference = new int[numPages];
        for (int i = 0; i < numPages; i++) {
            pageReference[i] = scanner.nextInt();
        }
        
        int[] results = performLRU(pageReference, numFrames);
        System.out.println("Number of Page Faults: " + results[0]);
        System.out.println("Number of Page Hits: " + results[1]);
        
        scanner.close();
    }
    
    public static int[] performLRU(int[] pageReference, int numFrames) {
        HashSet<Integer> frameSet = new HashSet<>();
        LinkedHashMap<Integer, Integer> frameMap = new LinkedHashMap<>();
        int pageFaults = 0;
        int pageHits = 0;
        
        for (int page : pageReference) {
            if (frameSet.contains(page)) {
                frameMap.remove(page);
                pageHits++;
            } else {
                if (frameSet.size() == numFrames) {
                    int leastRecentlyUsedPage = frameMap.entrySet().iterator().next().getKey();
                    frameMap.remove(leastRecentlyUsedPage);
                    frameSet.remove(leastRecentlyUsedPage);
                }
                pageFaults++;
            }
            frameMap.put(page, 0);
            frameSet.add(page);
            
            // Increment access count for each page
            for (Map.Entry<Integer, Integer> entry : frameMap.entrySet()) {
                frameMap.put(entry.getKey(), entry.getValue() + 1);
            }
        }
        
        return new int[] {pageFaults, pageHits};
    }
}
