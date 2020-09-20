package graph;

import javafx.util.Pair;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) {
            return 0;
        }

        int len = beginWord.length();
        // 队列存放匹配到的单词和层数
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        // Set存放已经匹配过的单词
        Set<String> wordExisted = new HashSet<>();
        // 存放通配符对应的单词
        Map<String, ArrayList<String>> wordMatch = new HashMap<>();

        for(String word : wordList) {
            for(int i = 0 ; i < len; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1, len);
                ArrayList<String> wordMatchedList = wordMatch.getOrDefault(key, new ArrayList<String>());
                wordMatchedList.add(word);
                wordMatch.put(key, wordMatchedList);
            }
        }

        Pair<String, Integer> firstPair = new Pair<String, Integer>(beginWord, 1);
        queue.offer(firstPair);
        wordExisted.add(beginWord);

        Pair<String, Integer> pair;
        while(!queue.isEmpty()) {
            pair = queue.poll();
            String word = pair.getKey();
            int level = pair.getValue();
            for(int i = 0; i < len; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1, len);
                ArrayList<String> wordMatchedList = wordMatch.getOrDefault(key, new ArrayList<String>());
                for(String wordMatched : wordMatchedList) {
                    if(wordMatched.equals(endWord)) {
                        return level + 1;
                    }
                    if(!wordExisted.contains(wordMatched)) {
                        wordExisted.add(wordMatched);
                        queue.offer(new Pair<>(wordMatched, level + 1));
                    }
                }
            }

        }

        return 0;
    }
}
