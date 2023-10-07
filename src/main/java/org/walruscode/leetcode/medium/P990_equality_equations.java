package org.walruscode.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P990_equality_equations {

    public boolean equationsPossible(String[] equations) {
        int alphabet = 26;

        Set<Integer>[] sets = new Set[alphabet];

        for (int i = 0; i < alphabet; i++) {
            sets[i] = new HashSet<>();
            sets[i].add(i);
        }

        // Merge equalities
        for (String equation: equations) {
            char first = equation.charAt(0);
            char second = equation.charAt(3);

            if (equation.charAt(1) == '=' && first != second) {
                int x = first - 'a';
                int y = second - 'a';

                Set<Integer> aSet1 = sets[x];
                Set<Integer> aSet2 = sets[y];

                if (x < y) {
                    aSet1.addAll(aSet2);
                    sets[y] = aSet1;
                } else {
                    aSet2.addAll(aSet1);
                    sets[x] = aSet2;
                }
            }
        }

        // Check inequalities
        for (String equation: equations) {
            char first = equation.charAt(0);
            char second = equation.charAt(3);

            if (equation.charAt(1) == '!') {
                if (first == second) return false;

                int x = first - 'a';
                int y = second - 'a';

                Set<Integer> aSet1 = sets[x];
                Set<Integer> aSet2 = sets[y];

                if (aSet1.contains(y) || aSet2.contains(x)) return false;
            }
        }

        return true;
    }

    // this is way too complicated
    public boolean equationsPossibleDoesNotWork(String[] equations) {

        Map<Character, Set<Character>> nodes = new HashMap<>();
        Map<Character, Set<Character>> notEqual = new HashMap<>();

        for (String equation: equations) {
            Character first = equation.charAt(0);
            Character second = equation.charAt(3);

            Set<Character> notEquals1 = notEqual.get(first);
            Set<Character> notEquals2 = notEqual.get(second);

            Set<Character> equals1 = nodes.get(first);
            Set<Character> equals2 = nodes.get(second);

            if (equation.charAt(1) == '!') {
                // cheap case
                if (first.equals(second)) return false;

                if (equals1 != null && equals2 != null && equals1.contains(second))
                    return false;

                if (equals1 == null) {
                    Set<Character> temp1 = new HashSet<>();
                    temp1.add(first);
                    nodes.put(first, temp1);
                    equals1 = temp1;
                }

                if (equals2 == null) {
                    Set<Character> temp1 = new HashSet<>();
                    temp1.add(second);
                    nodes.put(second, temp1);
                    equals2 = temp1;
                }

                if (notEquals1 == null) {
                    Set<Character> temp1 = new HashSet<>();
                    for (Character key: equals2) {
                        temp1.add(key);
                    }
                    for (Character key: equals1) {
                        notEqual.put(key, temp1);
                    }
                } else {
                    for (Character key: equals2) {
                        notEquals1.add(key);
                    }
                }

                if (notEquals2 == null) {
                    Set<Character> temp2 = new HashSet<>();
                    for (Character key: equals1) {
                        temp2.add(key);
                    }
                    notEqual.put(second, temp2);
                    for (Character key: equals2) {
                        notEqual.put(key, temp2);
                    }
                } else {
                    for (Character key: equals1) {
                        notEquals2.add(key);
                    }
                }
            } else {

                if (notEquals1 != null && notEquals2 != null && notEquals1.contains(second))
                    return false;

                if (equals1 != null && equals2 != null) {
                    equals1.addAll(equals2);
                    nodes.put(second, equals1);
                }

                if (equals1 == null && equals2 != null) {
                    equals2.add(first);
                    nodes.put(first, equals2);
                }

                if (equals1 != null && equals2 == null) {
                    equals1.add(second);
                    nodes.put(second, equals1);
                }

                if (equals1 == null) {
                    Set<Character> temp1 = new HashSet<>();
                    temp1.add(first);
                    temp1.add(second);
                    nodes.put(first, temp1);
                    nodes.put(second, temp1);
                }

                if (notEquals1 != null && notEquals2 != null) {
                    notEquals1.addAll(notEquals2);
                    notEqual.put(second, notEquals1);
                }

                if (notEquals1 != null && notEquals2 == null) {
                    notEqual.put(second, notEquals1);
                }

                if (notEquals1 == null && notEquals2 != null) {
                    notEqual.put(first, notEquals2);
                }
            }
        }

        return true;
    }
}
