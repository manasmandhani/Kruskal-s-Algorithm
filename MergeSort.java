

/**
*
* Class "MergeSort" implements merge sort.
*
* @version 1.0
*
* @author  Manas Mandhani
*/

public class MergeSort {
	public void mergeSort(Edge[] array) {
		int n = array.length;
		int counter = 0;
		if (n < 2)
			return;
		int middle = n / 2;
		Edge[] left_array = new Edge[middle];
		Edge[] right_array = new Edge[n - middle];
		for (int i = 0; i < middle; i++) {
			left_array[i] = array[i];
		}
		for (int i = middle; i < n; i++) {
			right_array[counter] = array[i];
			counter++;
		}
		mergeSort(left_array);
		mergeSort(right_array);
		Merge(left_array, right_array, array);
	}

	public void Merge(Edge[] left_array, Edge[] right_array, Edge[] array) {
		int left, right, count;
			for (left = 0, right = 0, count = 0; left < left_array.length && right < right_array.length; count++) {
				if (left_array[left].weight < right_array[right].weight) {
					array[count] = left_array[left];
					left++;
				} else {
					array[count] = right_array[right];
					right++;
				}
			}

			for (; left < left_array.length; left++, count++) {
				array[count] = left_array[left];
			}

			for (; right < right_array.length; right++, count++) {
				array[count] = right_array[right];
		}
	}
}
