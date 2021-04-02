import java.util.*;
import java.io.*;

public class Solution {
    public HashMap<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = in.nextInt();
        for (int w = 1; w <= t; w++) {
            int N = in.nextInt();
            int C = in.nextInt();

            ArrayList<Integer> solution = solve(N, C);
            StringBuilder res = new StringBuilder();
            if (solution==null) {
                res.append("IMPOSSIBLE");
            } else {
                for (Integer x: solution) {
                    res.append(x+" ");
                }
            }

            System.out.println("Case #" + w + ": " + res.toString());
        }
    }

    public static ArrayList<Integer> solve(int N, int C) {
        if (N<2) {
            return null;
        }

        int min = N-1;
        int max = ((N*(N+1))/2)-1;
        if (C < min || C > max) {
            return null;
        }

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (N==2) {
            if (C==1) {
                res.add(1);
                res.add(2);
                return res;
            }
            if (C==2) {
                res.add(2);
                res.add(1);
                return res;
            }
            return null;
        }

        int nTmp = N-1;
        int minTmp = nTmp-1;
        int maxTmp = (((nTmp)*(nTmp+1))/2)-1;
        for (int i=1; i<=N; i++) {
            if (C-i >= minTmp && C-i <= maxTmp) {
                res = solve(N-1, C-i);
                if (res==null) {
                    continue;
                } else {
                    // increment all elements by 1
                    for (int j=0; j<res.size(); j++) {
                        res.set(j, res.get(j)+1);
                    }

                    // append 1 to the left
                    res.add(0, 1);

                    // revert the first "i" items
                    ArrayList<Integer> tmp = new ArrayList<>(res.subList(0, i));
                    Collections.reverse(tmp);
                    tmp.addAll(res.subList(i, res.size()));

                    return tmp;
                }
            }
        }

        return null;
    }
}