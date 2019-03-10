package DailyProblem;

public class Prob2 {
	public static void main(String[] args) {
		int[] nums = { 3, 2, 1, 3, 4, -1 };
		int N = nums.length;
		// We will mark the presence of an integer i by making nums[i] = i
		// Duplicates and negative numbers are ignored
		// The answer has to lie between 1 and N + 1
		
		for (int i = 0; i < N; i++) {
			while (nums[i] != i + 1 && nums[i] > 0 && nums[i] < N && nums[i] != nums[nums[i] - 1]) {
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (nums[i] != i + 1) {
				System.out.println(i + 1);
				return;
			}
		}
		// If not found then the answer is N + 1
		System.out.println(N + 1);
	}
}
