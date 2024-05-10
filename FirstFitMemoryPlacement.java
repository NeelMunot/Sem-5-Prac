import java.util.*;

// class Process {
//     String name;
//     int size;

//     public Process(String name, int size) {
//         this.name = name;
//         this.size = size;
//     }
// }

// class MemoryBlock {
//     String name;
//     int size;
//     boolean allocated;

//     public MemoryBlock(String name, int size) {
//         this.name = name;
//         this.size = size;
//         this.allocated = false;
//     }
// }

// public class FirstFitMemoryPlacement {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter number of memory blocks: ");
//         int numBlocks = scanner.nextInt();

//         List<MemoryBlock> memory = new ArrayList<>();
//         for (int i = 1; i <= numBlocks; i++) {
//             System.out.print("Enter size of memory block " + i + ": ");
//             int size = scanner.nextInt();
//             memory.add(new MemoryBlock("Block " + i, size));
//         }

//         System.out.print("Enter number of processes: ");
//         int numProcesses = scanner.nextInt();

//         List<Process> processes = new ArrayList<>();
//         for (int i = 1; i <= numProcesses; i++) {
//             System.out.print("Enter size of process " + i + ": ");
//             int size = scanner.nextInt();
//             processes.add(new Process("Process " + i, size));
//         }

//         allocateMemory(memory, processes);

//         scanner.close();
//     }

//     public static void allocateMemory(List<MemoryBlock> memory, List<Process> processes) {
//         for (Process process : processes) {
//             boolean allocated = false;
//             for (MemoryBlock block : memory) {
//                 if (!block.allocated && block.size >= process.size) {
//                     block.allocated = true;
//                     System.out.println("Allocated " + process.name + " to " + block.name);
//                     allocated = true;
//                     break;
//                 }
//             }
//             if (!allocated) {
//                 System.out.println("Unable to allocate " + process.name);
//             }
//         }
//     }
// }
// Java implementation of First - Fit algorithm 

// Java implementation of First - Fit algorithm 
class FirstFitMemoryPlacement 
{ 
	// Method to allocate memory to 
	// blocks as per First fit algorithm 
	static void firstFit(int blockSize[], int m, 
						int processSize[], int n) 
	{ 
		// Stores block id of the 
		// block allocated to a process 
		int allocation[] = new int[n]; 
	
		// Initially no block is assigned to any process 
		for (int i = 0; i < allocation.length; i++) 
			allocation[i] = -1; 
	
		// pick each process and find suitable blocks 
		// according to its size ad assign to it 
		for (int i = 0; i < n; i++) 
		{ 
			for (int j = 0; j < m; j++) 
			{ 
				if (blockSize[j] >= processSize[i]) 
				{ 
					// allocate block j to p[i] process 
					allocation[i] = j; 
	
					// Reduce available memory in this block. 
					blockSize[j] -= processSize[i]; 
	
					break; 
				} 
			} 
		} 
	
		System.out.println("\nProcess No.\tProcess Size\tBlock no."); 
		for (int i = 0; i < n; i++) 
		{ 
			System.out.print(" " + (i+1) + "\t\t" + 
							processSize[i] + "\t\t"); 
			if (allocation[i] != -1) 
				System.out.print(allocation[i] + 1); 
			else
				System.out.print("Not Allocated"); 
			System.out.println(); 
		} 
	} 
	
	// Driver Code 
	public static void main(String[] args) 
	{ 
		int blockSize[] = {100, 500, 200, 300, 600}; 
		int processSize[] = {212, 417, 112, 426}; 
		int m = blockSize.length; 
		int n = processSize.length; 
		
		firstFit(blockSize, m, processSize, n); 
	} 
} 
