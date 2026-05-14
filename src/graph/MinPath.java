package graph;

import java.util.*;

public class MinPath {
    public List<List<String>> minPath(String start, String to, List<String> pathList) {
        pathList.add(start);
        Map<String, List<String>> nextMap = getNextMap(pathList);
        Map<String, Integer> distanceMap = getDistanceMap(start, nextMap);
        LinkedList<String> prePathList = new LinkedList<>();
        List<List<String>> ans = new ArrayList<>();
        getShortPath(start, to, nextMap, distanceMap, prePathList, ans);
        return ans;
    }

    private void getShortPath(String current, String to, Map<String, List<String>> nextMap,
                              Map<String, Integer> distanceMap, LinkedList<String> prePathList, List<List<String>> ans) {
        prePathList.add(current);
        if (current.equals(to)) {
            ans.add(new ArrayList<>(prePathList));
        } else {
            List<String> nextList = nextMap.get(current);
            for (String nextStr : nextList) {
                if (distanceMap.get(nextStr) != distanceMap.get(current) + 1) {
                    continue;
                }
                getShortPath(nextStr, to, nextMap, distanceMap, prePathList, ans);
            }
        }

        prePathList.pollLast();
    }

    private Map<String, Integer> getDistanceMap(String start, Map<String, List<String>> nextMap) {
        Map<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);
        Set<String> set = new HashSet<>();
        set.add(start);

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            List<String> nextList = nextMap.get(cur);
            for (String nextStr : nextList) {
                if (set.contains(nextStr)) {
                    continue;
                }
                distanceMap.put(nextStr, distanceMap.get(cur) + 1);
                queue.add(nextStr);
                set.add(nextStr);
            }
        }
        return distanceMap;
    }

    private Map<String, List<String>> getNextMap(List<String> pathList) {
        Map<String, List<String>> nextMap = new HashMap<>();
        Set<String> set = new HashSet<>(pathList);
        for (String cur : pathList) {
            List<String> nextList = getNextList(set, cur);
            nextMap.put(cur, nextList);
        }
        return nextMap;
    }

    private List<String> getNextList(Set<String> set, String cur) {
        char[] chars = cur.toCharArray();
        List<String> nextList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (chars[i] == c) {
                    continue;
                }
                char temp = chars[i];
                chars[i] = c;
                String nextString = String.valueOf(chars);
                if (set.contains(nextString)) {
                    nextList.add(nextString);
                }
                chars[i] = temp;
            }
        }
        return nextList;
    }
}
