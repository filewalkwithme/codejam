import java.util.*;
import java.io.*;
public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    int t = in.nextInt(); 
    for (int w = 1; w <= t; w++) {
        int elems = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();

        // populate arr
        for (int i = 0; i < elems; i++) {
            arr.add(in.nextInt());
        }

        int ans = 0;

        // brute force
        for (int i = 0; i < elems-1; i++) {
            int smallest = arr.get(i);
            int smallestPos = i;
            for (int j = i+1; j < elems; j++) {
                if (arr.get(j)<smallest) {
                    smallest = arr.get(j);
                    smallestPos = j;
                }
            }

            if (smallestPos == i) {
                ans++;
            } else {
                List<Integer> arrHead = new ArrayList<>(arr.subList(0,i));
                List<Integer> arrMid = new ArrayList<>(arr.subList(i, smallestPos+1));
                List<Integer> arrTail = new ArrayList<>(arr.subList(smallestPos+1, arr.size()));

                Collections.reverse(arrMid);

                arr.clear();
                arr.addAll(arrHead);
                arr.addAll(arrMid);
                arr.addAll(arrTail);
                ans = ans + (smallestPos - i + 1);
            }
        }

        System.out.println("Case #" + w + ": " + ans);
    }
  }
}