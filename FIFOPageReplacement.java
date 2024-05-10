import java.util.*;

public class FIFOPageReplacement {
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
        
        int[] results = performFIFO(pageReference, numFrames);
        System.out.println("Number of Page Faults: " + results[0]);
        System.out.println("Number of Page Hits: " + results[1]);
        
        scanner.close();
    }
    
    public static int[] performFIFO(int[] pageReference, int numFrames) {
        HashSet<Integer> frameSet = new HashSet<>();
        Queue<Integer> frameQueue = new LinkedList<>();
        int pageFaults = 0;
        int pageHits = 0;
        
        for (int page : pageReference) {
            if (frameSet.contains(page)) {
                pageHits++;
            } else {
                if (frameSet.size() == numFrames) {
                    int removedPage = frameQueue.poll();
                    frameSet.remove(removedPage);
                }
                frameSet.add(page);
                frameQueue.offer(page);
                pageFaults++;
            }
        }
        
        return new int[] {pageFaults, pageHits};
    }
}
