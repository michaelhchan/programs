/**
 * This is a class to highlight the different sorting algorthims.
 * 
 * @author michael
 * */
public class Sorter
{
	private static void swap(int i, int j, int[]nums)
	{
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	private static void quickSort(int lo, int hi, int[]nums)
	{
		// elements to consider
		int i = lo;
		int j = hi;
		
		// get a pivot value
		int pivot = nums[lo + (hi - lo)/2];
		
		// separate into two sections
		// perform swapping around pivot
		while (i <= j)
		{
			// if numbers are less than the pivot,
			// keep incrementing the index upward in the left section
			while (nums[i] < pivot)
			{
				i++;
			}
			
			// if numbers are greater than the pivot,
			// keep decrementing the index downward in the right section
			while (pivot < nums[j])
			{
				j--;
			}
			
			// if there are values larger than the pivot in the left section
			// and if there is a value smaller than the pivot in the right section
			// swap around the pivot
			if (i <= j)
			{
				swap(i, j, nums);
				i++;
				j--;
			}
		}
		
		// done swapping
		
		// recurse on left section
		if (lo < j)
		{
			quickSort(lo, j, nums);
		}
		
		// recurse on right section
		if (i < hi)
		{
			quickSort(i, hi, nums);
		}
	}

	/**
	 * Sorts the given array of integers using the Quick Sort algorithm.
	 * Worst case: O(n^2)
	 * Average case: O(nlogn)
	 * Best case: O(n)
	 * @param integers	array of integer numbers to sort
	 */
	public static void quickSort(int[] integers)
	{
		if (integers != null && 0 < integers.length)
		{
			quickSort(0, integers.length - 1, integers);
		}
	}
	
	/**
	 * Sorts the given array of integers using the Bubble Sort algorithm
	 * Worst case: O(n^2)
	 * @param integers	array of integer numbers to sort
	 */
	public static void bubbleSort(int[] nums)
	{
		if (nums != null && 0 < nums.length)
		{
			int n = nums.length;
			for (int i = n - 1; 1 <= i; i--)
			{
				for (int j = 0; j < i - 1; j++)
				{
					if (nums[j] > nums[j + 1])
					{
						swap(j, j + 1, nums);
					}
				}
			}
		}
	}
	
	/**
	 * Sorts the given array of integers using the Insertion Sort algorithm
	 * Worst case: O(n^2)
	 * @param integers	array of integer numbers to sort
	 */
	public static void insertionSort(int[] nums)
	{
		if (nums != null && 0 < nums.length)
		{
			int n = nums.length;
			for (int i = 1; i < n - 1; i++)
			{
				int j = i;
				while (j > 0 && nums[j] < nums[j - 1])
				{
					swap(j, j - 1, nums);
					j--;
				}
			}
		}
	}
	
	private static void maxHeapify(int i, int max, int[] nums)
	{
		int l = (2 * i) + 1;	// get left child index
		int r = (2 * i) + 2;	// get right child index
		int largest = 0;
		
		// check left child if larger than current node
		if (l <= max && nums[i] < nums[l])
		{
			largest = l;
		}
		else
		{
			largest = i;
		}
		
		// check right child if largest
		if (r <= max && nums[largest] < nums[r])
		{
			largest = r;
		}
		
		// if we found a largest, swap and recurse
		if (largest != i)
		{
			swap(i, largest, nums);
			maxHeapify(largest, max, nums);
		}
	}
	
	private static void buildHeap(int max, int[] nums)
	{
		for (int i = (max/2); 0 <= i; i--)
		{
			maxHeapify(i, max, nums);
		}
	}
	
	/**
	 * Builds a heap out of the input array
	 * @param integers	array of integer numbers to sort
	 */
	public static void buildHeap(int[] nums)
	{
		if (nums != null && 0 < nums.length)
		{
			buildHeap(nums.length - 1, nums);
		}
	}
	
	/**
	 * Sorts the given array of integers using the Heap Sort algorithm
	 * Average case: O(nlogn)
	 * @param integers	array of integer numbers to sort
	 */
	public static void heapSort(int[] nums)
	{
		// build a heap
		buildHeap(nums);
		
		for (int i = nums.length - 1; 0 < i; i--)
		{
			// swap the top of heap with end of array
			swap(0, i, nums);

			// re-build the heap
			maxHeapify(0, i - 1, nums);
		}
	}
	
	private static void merge(int lo, int mid, int hi, int nums[], int tmp[])
	{
		// Copy both parts into the temporary array
		for (int i = lo; i <= hi; i++)
		{
			tmp[i] = nums[i];
		}

		int i = lo;
		int j = mid + 1;
		int k = lo;

		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= mid && j <= hi)
		{
			if (tmp[i] <= tmp[j])
			{
				nums[k] = tmp[i];
				i++;
			}
			else
			{
				nums[k] = tmp[j];
				j++;
			}
			k++;
		}
		
		// Copy the rest of the left side of the array into the target array
		while (i <= mid)
		{
			nums[k] = tmp[i];
			k++;
			i++;
		}
	}
	
	private static void mergeSort(int lo, int hi, int[] nums, int[]tmp)
	{
	    // check if low is smaller then high, if not then the array is sorted
	    if (lo < hi)
	    {
	      // Get the index of the element which is in the middle
	      int mid = lo + (hi - lo) / 2;
	      
	      // Sort the left side of the array
	      mergeSort(lo, mid, nums, tmp);
	      
	      // Sort the right side of the array
	      mergeSort(mid + 1, hi, nums, tmp);
	      
	      // Combine them both
	      merge(lo, mid, hi, nums, tmp);
	    }
	}
	
	/**
	 * Sorts the given array of integers using the Merge Sort algorithm
	 * Average case: O(nlogn)
	 * @param integers	array of integer numbers to sort
	 */
	public static void mergeSort(int[] nums)
	{
		if (nums != null && 0 < nums.length)
		{
			// allocate a temporary array
			int[] tmp = new int[nums.length];
			
			// perform merge sort
			mergeSort(0, nums.length - 1, nums, tmp);
		}
	}

	/**
	 * Sorts the given array of integers using the Selection Sort algorithm
	 * Analysis: O(n^2)
	 * @param integers	array of integer numbers to sort
	 */
	public static void selectionSort(int[] nums)
	{
		// loop through all cells
		for (int i = 0; nums != null && i < nums.length; i++)
		{
			// loop through all cells to find the minimum
			int minIdx = i;
			for (int j = i; j < nums.length; j++)
			{
				// if we found a lower value, note the index
				if (nums[j] < nums[minIdx])
				{
					minIdx = j;
				}
			}
			
			// swap lowest cell with the minimum value found
			swap(i, minIdx, nums);
		}
	}
	
	private static void printArray(int[] nums)
	{
		for (int i = 0; i < nums.length; i++)
		{
			if (0 < i) System.out.print(", ");
			System.out.print(nums[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		String fn = args[0];
		int[] nums = new int[args.length - 1];
		for (int i = 1; i < args.length; i++)
		{
			nums[i - 1] = Integer.parseInt(args[i]);
		}

		
		// print before
		System.out.println(fn);
		printArray(nums);

		if (fn.equals("quickSort"))
		{
			quickSort(nums);
		}
		else if (fn.equals("bubbleSort"))
		{
			bubbleSort(nums);
		}
		else if (fn.equals("insertionSort"))
		{
			insertionSort(nums);
		}
		else if (fn.equals("buildHeap"))
		{
			buildHeap(nums);
		}
		else if (fn.equals("heapSort"))
		{
			heapSort(nums);
		}
		else if (fn.equals("mergeSort"))
		{
			mergeSort(nums);
		}
		else if (fn.equals("selectionSort"))
		{
			selectionSort(nums);
		}

		// print after
		printArray(nums);
	}
}
