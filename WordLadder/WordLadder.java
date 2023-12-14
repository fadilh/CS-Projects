import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordLadder {

    public static boolean oneLetterDifference(String initial, String next){
        int diff = 0;
        if (initial.length() == next.length()) {
            for (int i = 0; i < initial.length(); i++) {
                if (initial.charAt(i) != next.charAt(i)) {
                    diff++;
                }
            }
        }
        
        return diff <= 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> dictionary = new ArrayList<String>();
        ArrayList<String> usedWords = new ArrayList<String>();
        String startWord = "", finalWord = "";
        Scanner givenReader = new Scanner(new File("input.txt"));
        Scanner dictReader = new Scanner(new File("dictionary.txt"));
        while (dictReader.hasNext()) { dictionary.add(dictReader.nextLine().toLowerCase()); }
        dictReader.close();
        
        while (givenReader.hasNextLine()) {
            startWord = givenReader.next();
            finalWord = givenReader.next();
            if (!dictionary.contains(startWord) || startWord.length() != finalWord.length()) { 
                System.out.println("No ladder between " + startWord + " and " + finalWord);
            } else if (startWord.equals(finalWord)) {
                System.out.println("Ladder found >>> [" + startWord + "]");
            } else {
                Queue<Stack<String>> queue = new LinkedList<>();
                Stack<String> s = new Stack<String>();
                boolean ladderFound = false;
                dictionary.remove(startWord);
                usedWords.add(startWord);
                s.push(startWord);
                queue.offer(s);
                while (!queue.isEmpty()) {
                    Stack<String> curr = queue.poll();
                    String prevWord = curr.peek();
                    if (prevWord.equals(finalWord)) {
                        Stack<String> currRev = new Stack<>();
                        Stack<String> currCop = new Stack<>();
                        currRev.addAll(curr);
                        while (!currRev.isEmpty()) {
                            currCop.push(currRev.pop());
                        }
                        String ladder = "[";
                        while (!currCop.isEmpty()) {
                            String temp = currCop.pop();
                            if (currCop.size() == 1) {
                                ladder += temp;
                            } else if (currCop.size() > 1) {
                                ladder += temp + ", ";
                            } else { 
                                ladder += ", " + temp;
                            }
                        }
                        ladder += "]";
                        System.out.println("Ladder Found >>> " + ladder);
                        ladderFound = true;
                        break;
                    } else {
                        for (int i = 0; i < dictionary.size(); i++) {
                            String word = dictionary.get(i);
                            if (word.length() == prevWord.length() && oneLetterDifference(prevWord, word)) {
                                dictionary.remove(word);
                                usedWords.add(word);
                                Stack<String> currCopy = new Stack<>();
                                currCopy.addAll(curr);
                                currCopy.push(word);
                                queue.offer(currCopy);
                            }
                        }
                    }
                }
                if (!ladderFound) {
                    System.out.println("No ladder between " + startWord + " and " + finalWord);
                }
                dictionary.addAll(usedWords);
                usedWords.clear();
            }
            
        }

    }
}