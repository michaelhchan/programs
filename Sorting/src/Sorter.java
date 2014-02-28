public class Sorter
{

	private static void swap(int i, int j, int[]nums)
	{
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	

	/**
	 * Sorts the given array of integers using the Quick Sort algorithm
	 * @param integers	array of integer numbers to sort
	 */
	public static void quickSort(int[] integers)
	{
		if (integers != null && 0 < integers.length)
		{
			quickSort(0, integers.length - 1, integers);
		}
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
	
	public static void heapify(int i, int[] nums)
	{
		if (nums != null && 0 < nums.length)
		{
			int size = nums.length;
			int l = (2 * i) + 1;	// get left child index
			int r = (2 * i) + 2;	// get right child index
			int largest = 0;
			
			if (l <= size && nums[i] < nums[l])
			{
				largest = l;
			}
			else
			{
				largest = i;
			}
			
			if (r <= size && nums[largest] < nums[r])
			{
				largest = r;
			}
			
			if (largest != i)
			{
				swap(i, largest, nums);
				heapify(largest, nums);
			}
		}
	}
	
	public static void buildHeap(int[] nums)
	{
		if (nums != null && 0 < nums.length)
		{
			for (int i = (nums.length/2); i > 0; i--)
			{
				heapify(i, nums);
			}
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
		printArray(nums);

		if (fn.equals("-quickSort"))
		{
			quickSort(nums);
		}
		else if (fn.equals("-bubbleSort"))
		{
			bubbleSort(nums);
		}
		else if (fn.equals("-insertionSort"))
		{
			insertionSort(nums);
		}
		else if (fn.equals("-buildHeap"))
		{
			buildHeap(nums);
		}

		// print after
		printArray(nums);
	}
}
