import java.util.*;

public class Homework17 {

	/* Given an array of ints, is it possible to choose a
	 * group of some of the ints, such that the group sums
	 * to the given target? This is a classic backtracking
	 * recursion problem. Once you understand the recursive
	 * backtracking strategy in this problem, you can use
	 * the same pattern for many problems to search a space
	 * of choices. Rather than looking at the whole array,
	 * our convention is to consider the part of the array
	 * starting at index start and continuing to the end of
	 * the array. The caller can specify the whole array
	 * simply by passing start as 0. No loops are needed.
	 * The recursive calls progress down the array.
	 *
	 * problem1(0, [2, 4, 8], 10) → true
	 * problem1(0, [2, 4, 8], 14) → true
	 * problem1(0, [2, 4, 8], 9) → false
	 */


	public static boolean problem1(int start, int[] nums, int target) {

		//keep moving index forward

		if(start == nums.length){ //if you reach the end of the array
			return false;
		}

		else if(nums[start] == target){ //if it sums to the target
			return true;
		}

		else if(problem1(start + 1, nums, target - nums[start])){ //include the number
			return true;
		}

		else if(problem1(start + 1, nums, target)){ //don't include the number
			return true;
		}

		else{
			return false;
		}
	}


	/* Given an array of ints, is it possible to choose a
	 * group of some of the ints, beginning at the start
	 * index, such that the group sums to the given target?
	 * However, with the additional constraint that all 6's
	 * must be chosen. (No loops needed.)
	 *
	 * problem2(0, [5, 6, 2], 8) → true
	 * problem2(0, [5, 6, 2], 9) → false
	 * problem2(0, [5, 6, 2], 7) → false
	 */

	 public static boolean problem2(int start, int[] nums, int target) {
		 int sum = 0;
		 for(int i = start; i < nums.length; i++){
			 if(nums[i] == 6){
				 sum += 6;
			 }
		 }

		 return problem2Help(start, nums, target - sum);

		}

		public static boolean problem2Help(int start, int[] nums, int target){
			if(start == nums.length){
 			 return false;
 		 	}

 		 else if(nums[start] == target){
  			 return true;
 		 	}

 		 else if(problem2(start + 1, nums, target - nums[start])){ //include it
 			 return true;
 		 	}

 		 else if(nums[start] == 6 || problem2(start + 1, nums, target)){ //don't include it
 			 return false;
 		 	}

 		 else{
 			 return false;
 		 	}
		}


	/* Given an array of ints, is it possible to divide the
	 * ints into two groups, so that the sums of the two
	 * groups are the same. Every int must be in one group
	 * or the other. Write a recursive helper method that
	 * takes whatever arguments you like, and make the
	 * initial call to your recursive helper from
	 * problem3(). (No loops needed.)
	 *
	 * problem3([2, 2]) → true
	 * problem3([2, 3]) → false
	 * problem3([5, 2, 3]) → true
	 */

	public static boolean problem3(int[] nums) {
		int sum = 0;
		for(int i : nums){
			sum += i;
		}
		int group = sum/2;
		return problem3(0, nums, group);
	}


	public static boolean problem3(int start, int[] nums, int target) {
		if(start == nums.length){ //if you reach the end of the array
			return false;
		}

		else if(nums[start] == target){ //if it sums to the target
			return true;
		}

		else if(problem3(start + 1, nums, target - nums[start])){ //include the number
			return true;
		}

		else if(problem3(start + 1, nums, target)){ //don't include the number
			return true;
		}

		else{
			return false;
		}
	}


	/* Given an array of ints, is it possible to choose
	 * a group of some of the ints, such that the group
	 * sums to the given target, with this additional
	 * constraint: if there are numbers in the array
	 * that are adjacent and the identical value, they
	 * must either all be chosen, or none of them chosen.
	 * For example, with the array {1, 2, 2, 2, 5, 2},
	 * either all three 2's in the middle must be chosen
	 * or not, all as a group. (one loop can be used to
	 * find the extent of the identical values).
	 *
	 * problem4(0, [2, 4, 8], 10) → true
	 * problem4(0, [1, 2, 4, 8, 1], 14) → true
	 * problem4(0, [2, 4, 4, 8], 14) → false
	 */

	public static boolean problem4(int start, int[] nums, int target) {
		int sum = 0;
		ArrayList<Integer> newlist = new ArrayList<Integer>();
		int i = 1;
		while(i < nums.length){
			if(nums[i] == nums[i - 1]){
					sum += nums[i];
				}
			else{
				newlist.add(nums[i]);
			}
			i++
		}
		newlist.add(sum);

		int[] newArr = new int[newlist.size()];
		for(int i = 0; i < newlist.size(); i++){
				newArr[i] = newlist.get(i);
		}
		return problem4Help(start, newArr, target);
	}


	public static boolean problem4Help(int start, int[] nums, int target){
		if(start == nums.length){
		 return false;
		}

	 else if(nums[start] == target){
			 return true;
		}

	 else if(problem4Help(start + 1, nums, target - nums[start])){ //include it
		 return true;
		}

	 else if(problem4Help(start + 1, nums, target)){ //don't include it
		 return false;
		}

	 else{
		 return false;
		}
	}

	/* Given an array of ints, is it possible to divide
	 * the ints into two groups, so that the sum of one
	 * group is a multiple of 10, and the sum of the
	 * other group is odd. Every int must be in one
	 * group or the other. Write a recursive helper
	 * method that takes whatever arguments you like,
	 * and make the initial call to your recursive
	 * helper from problem5(). (No loops needed.)
	 *
	 * problem5([5, 5, 5]) → true
	 * problem5([5, 5, 6]) → false
	 * problem5([5, 5, 6, 1]) → true
	 */

	public static boolean problem5(int[] nums) {
		int sum = 0;
		for(int number : nums){
			sum += number;
		}
		int newTarget = sum % 10;
		if(newTarget % 2 == 0){
			return false;
		}
		else{
			return problem5(0, nums, newTarget);
		}
	}

	public static boolean problem5(int start, int[] nums, int target) {
		if(start == nums.length){ //if you reach the end of the array
			return false;
		}

		else if(nums[start] == target){ //if it sums to the target
			return true;
		}

		else if(problem5(start + 1, nums, target - nums[start])){ //include the number
			return true;
		}

		else if(problem5(start + 1, nums, target)){ //don't include the number
			return true;
		}

		else{
			return false;
		}
	}

	public static void main(String[] args) {
		boolean passed = true;

		int[] arr1 = {2, 4, 8};
		if (!problem1(0, arr1, 8)) {
			System.out.println("Fail 1.1");
			passed = false;
		} else if (problem1(1, arr1, 2)) {
			System.out.println("Fail 1.2");
			passed = false;
		}

		int[] arr2 = {1, 6, 2, 6, 4};
		if (!problem2(0, arr2, 13)) {
			System.out.println("Fail 2.1");
			passed = false;
		} else if (problem2(0, arr2, 4)) {
			System.out.println("Fail 2.2");
			passed = false;
		}

		int[] arr3 = {1, 2, 3, 10, 10, 1, 1};
		if (!problem3(arr3)) {
			System.out.println("Fail 3");
			passed = false;
		}

		int[] arr4 = {8, 2, 2, 1};
		if (!problem4(0, arr4, 9)) {
			System.out.println("Fail 4.1");
			passed = false;
		} else if (problem4(0, arr4, 11)) {
			System.out.println("Fail 4.2");
			passed = false;
		}

		int[] arr5 = {6, 5, 5, 1, 10};
		if (!problem5(arr5)) {
			System.out.println("Fail 5");
			passed = false;
		}

		if (passed) {
			System.out.println("All Pass");
		} else {
			System.out.println("Fail");
		}

	}

}
