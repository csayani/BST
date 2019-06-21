/**
  * NgramCounter.java
  * contains the main method and constructs the tree
  * also sets up contingent traversal scenarios based on command line
  * args
  * by Chait Sayani
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;

public class NgramCounter{

  public static void main(String[] args) {
    NgramCountMap n = new NgramCountMap();
    n.initLists(args[3]);

    if (args[2].equals("stopwordsY")){
      n.setStopTrue();
      n.initStopWordList();
    }else{
      n.setStopFalse();
    }

    if (args[0].equals("alphabetical")){
      if (args[1].equals("unigrams")){
        for (String s:n.unigramList){
          n.putAlphabet(s);
        }
        n.traverseInOrder(n.getRoot());
        n.alphaSort.remove(0);
        n.printer(n.alphaSort);
      } else if (args[1].equals("bigrams")){
        for (String s:n.bigramList){
          n.putAlphabet(s);
        }
        n.traverseInOrder(n.getRoot());
        n.alphaSort.remove(0);
        n.printer(n.alphaSort);
      }else if (args[1].equals("trigrams")){
        for (String s:n.trigramList){
          n.putAlphabet(s);
        }
        n.traverseInOrder(n.getRoot());
        n.alphaSort.remove(0);
        n.printer(n.alphaSort);
      }
    } else if (args[0].equals("byCount")){
      if (args[1].equals("unigrams")){
        for (String s:n.unigramList){
          n.putAlphabet(s);
        }
        n.traverseInOrder(n.getRoot());
        ArrayList<Node> list = n.getNgramCountsByCount();
        n.printer(list);
      } else if (args[1].equals("bigrams")){
        for (String s:n.bigramList){
          n.putAlphabet(s);
        }
        n.traverseInOrder(n.getRoot());
        ArrayList<Node> list = n.getNgramCountsByCount();
        n.printer(list);
      }else if (args[1].equals("trigrams")){
        for (String s:n.trigramList){
          n.putAlphabet(s);
        }
        n.traverseInOrder(n.getRoot());
        ArrayList<Node> list = n.getNgramCountsByCount();
        n.printer(list);
      }

    }
  }
}
