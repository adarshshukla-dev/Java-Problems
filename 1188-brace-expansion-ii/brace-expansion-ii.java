import java.util.*;

class Solution {
    private int i = 0;

    public List<String> braceExpansionII(String expression) {
        i = 0;
        Set<String> set = parseExpr(expression);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private Set<String> parseExpr(String s) {
        Set<String> res = new HashSet<>();
        List<Set<String>> group = new ArrayList<>();

        while (i < s.length() && s.charAt(i) != '}') {
            if (s.charAt(i) == ',') {
                res.addAll(concatGroup(group));
                group.clear();
                i++;
            } else {
                Set<String> cur = new HashSet<>();
                if (s.charAt(i) == '{') {
                    i++;
                    cur = parseExpr(s);
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (i < s.length() && Character.isLowerCase(s.charAt(i))) {
                        sb.append(s.charAt(i));
                        i++;
                    }
                    cur.add(sb.toString());
                }
                group.add(cur);
            }
        }

        res.addAll(concatGroup(group));
        return res;
    }

    private Set<String> concatGroup(List<Set<String>> group) {
        if (group.isEmpty()) return new HashSet<>();
        Set<String> res = new HashSet<>();
        res.add("");

        for (Set<String> set : group) {
            Set<String> next = new HashSet<>();
            for (String a : res) {
                for (String b : set) {
                    next.add(a + b);
                }
            }
            res = next;
        }

        return res;
    }
}