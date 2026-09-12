import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long weight;
        List<Integer> selected;

        State(long weight, List<Integer> selected) {
            this.weight = weight;
            this.selected = selected;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] sorted = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sorted[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        Arrays.sort(sorted, (a, b) -> Integer.compare(a.l, b.l));

        int[] nextIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n, target = sorted[i].r;
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (sorted[mid].l > target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            nextIdx[i] = low;
        }

        State[][] memo = new State[n + 1][5];
        State best = dp(sorted, nextIdx, memo, 0, 4);

        int[] result = new int[best.selected.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = best.selected.get(i);
        }
        return result;
    }

    private State dp(Interval[] intervals, int[] nextIdx, State[][] memo, int i, int count) {
        if (i == intervals.length || count == 0) {
            return new State(0, new ArrayList<>());
        }
        if (memo[i][count] != null) {
            return memo[i][count];
        }

        State skip = dp(intervals, nextIdx, memo, i + 1, count);

        Interval curr = intervals[i];
        State nextState = dp(intervals, nextIdx, memo, nextIdx[i], count - 1);

        List<Integer> pickSelected = new ArrayList<>(nextState.selected);
        pickSelected.add(curr.id);
        Collections.sort(pickSelected);
        State pick = new State(curr.weight + nextState.weight, pickSelected);

        State res;
        if (pick.weight > skip.weight) {
            res = pick;
        } else if (pick.weight < skip.weight) {
            res = skip;
        } else {
            res = compareLists(pick.selected, skip.selected) < 0 ? pick : skip;
        }

        memo[i][count] = res;
        return res;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int minLen = Math.min(a.size(), b.size());
        for (int i = 0; i < minLen; i++) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.size(), b.size());
    }
}