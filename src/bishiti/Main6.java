package bishiti;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;

public class Main6 {
    public static void main(String[] args) throws IOException {
        Map<Integer, String> name = new HashMap<>();
        name.put(5, "C");
        name.put(10, "B");
        name.put(15, "A");
        name.put(17, "S");
        name.put(22, "SS");
        name.put(33, "SSS");
        name.put(35, "SSSS");
        System.out.println(getLevelByQuotaPolicy(name, 99, 100, "A"));
        System.out.println(getLevelByQuotaPolicy(name, 399, 100, "SSSS"));

    }

    public static boolean isOld(String v1, String v2) {
        String[] v1s = v1.split("\\.");
        String[] v2s = v2.split("\\.");
        int n = Math.max(v1s.length, v2s.length);
        for (int i = 0; i < n; i++) {
            int version1 = i < v1s.length ? Integer.parseInt(v1s[i]) : 0;
            int version2 = i < v2s.length ? Integer.parseInt(v2s[i]) : 0;
            if (version1 > version2)
                return true;
            else if (version1 < version2)
                return false;
        }
        return false;
    }

    public static <T extends Comparable> T[] BubbleSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = arr.length - 1; j >= i; j--) {
                if (arr[j].compareTo(arr[j - 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
        return arr;
    }

    public static String getLevelByQuotaPolicy(Map<Integer, String> priorityNumber2Name,
                                               int cpuUsed, int quotaLimit, String originalLevel) {
        int over = cpuUsed - quotaLimit;
        int newPriority = 0;
        for (Integer priority : priorityNumber2Name.keySet()) {
            if (priorityNumber2Name.get(priority).equals(originalLevel)) {
                newPriority = priority;
                break;
            }
        }
        if (over <= 0) {
            return originalLevel + "(" + newPriority + ")";
        }
        int level = (int) Math.ceil(over * 1.0 / (quotaLimit * 0.5));
        TreeMap<Integer, String> map = new TreeMap<>(priorityNumber2Name);
        for (int i = 0; i < level; i++) {
            if (map.lowerEntry(newPriority) == null) {
                return map.get(newPriority) + "(" + newPriority + ")";
            }
            newPriority = map.lowerKey(newPriority);
        }
        return map.get(newPriority) + "(" + newPriority + ")";
    }
}