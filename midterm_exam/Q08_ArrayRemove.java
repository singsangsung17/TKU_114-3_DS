package midterm_exam;

public class Q08_ArrayRemove {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] newArr = removeElement(arr, 3);
        
        for (int num : newArr) {
            System.out.print(num + " ");
        }
    }

    public static int[] removeElement(int[] arr, int element) {
        int count = 0;
        
        for (int num : arr) {
            if (num != element) {
                count++;
            }
        }
        
        int[] newArr = new int[count];
        int index = 0;
        
        for (int num : arr) {
            if (num != element) {
                newArr[index++] = num;
            }
        }
        
        return newArr;
    }
}
