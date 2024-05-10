import java.util.*;

public class OptimalPageReplacement {
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
        
        int pageFaults = performOptimal(pageReference, numFrames);
        System.out.println("Number of Page Faults: " + pageFaults);

        int pageHits = numPages - pageFaults;
        System.out.println("Number of Page Hits: "+ pageHits);
        
        scanner.close();
    }
    
    public static int performOptimal(int[] pageReference, int numFrames) {
        HashSet<Integer> frameSet = new HashSet<>();
        int pageFaults = 0;
        
        for (int i = 0; i < pageReference.length; i++) {
            if (!frameSet.contains(pageReference[i])) {
                pageFaults++;
                if (frameSet.size() < numFrames) {
                    frameSet.add(pageReference[i]);
                } else {
                    int farthestIndex = i;
                    int pageToRemove = -1;
                    for (Integer page : frameSet) {
                        int j;
                        for (j = i + 1; j < pageReference.length; j++) {
                            if (pageReference[j] == page) {
                                break;
                            }
                        }
                        if (j == pageReference.length) {
                            pageToRemove = page;
                            break;
                        }
                        if (j > farthestIndex) {
                            farthestIndex = j;
                            pageToRemove = page;
                        }
                    }
                    frameSet.remove(pageToRemove);
                    frameSet.add(pageReference[i]);
                }
            }
        }
        
        return pageFaults;
    }
}
