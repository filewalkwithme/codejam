import java.util.*;
import java.io.*;

public class Solution {
    public HashMap<String, Integer> cache = new HashMap<>();
    public int x;
    public int y;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = in.nextInt();
        for (int w = 1; w <= t; w++) {
            Solution sol = new Solution();
            sol.x = in.nextInt();
            sol.y = in.nextInt();
            String s = in.next();

            int ans = sol.solve(s);

            System.out.println("Case #" + w + ": " + ans);
        }
    }

    public int solve(String s) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        if (s.length() > 1) {

            if (s.charAt(0) == '?') {
                StringBuilder cs = new StringBuilder(s);
                cs.setCharAt(0, 'C');

                StringBuilder js = new StringBuilder(s);
                js.setCharAt(0, 'J');

                int res = Math.min(solve(cs.toString()), solve(js.toString()));
                cache.put(s, res);
                return res;
            }

            if ((s.charAt(0) == 'C' || s.charAt(0) == 'J') && s.charAt(1) == '?') {
                StringBuilder aCs = new StringBuilder(s);
                aCs.setCharAt(1, 'C');

                StringBuilder aJs = new StringBuilder(s);
                aJs.setCharAt(1, 'J');

                int res = Math.min(solve(aCs.toString()), solve(aJs.toString()));
                cache.put(s, res);
                return res;
            }

            if ((s.charAt(0) == 'C' || s.charAt(0) == 'J') && s.charAt(0) == s.charAt(1)) {
                StringBuilder aas = new StringBuilder(s);
                aas.deleteCharAt(0);
                int res = solve(aas.toString());
                cache.put(s, res);
                return res;
            }

            if (s.charAt(0) == 'C' && s.charAt(1) == 'J') {
                StringBuilder Js = new StringBuilder(s);
                Js.deleteCharAt(0);
                int res = this.x + solve(Js.toString());
                cache.put(s, res);
                return res;
            }

            if (s.charAt(0) == 'J' && s.charAt(1) == 'C') {
                StringBuilder Cs = new StringBuilder(s);
                Cs.deleteCharAt(0);
                int res = this.y + solve(Cs.toString());
                cache.put(s, res);
                return res;
            }
        }

        cache.put(s, 0);
        return 0;
    }
}