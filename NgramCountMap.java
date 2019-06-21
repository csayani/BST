/**
  * NgramCountMap.java
  * A BST that takes in a text file and constructs the BST based on
  * alphabetical words
  * by Chait Sayani
 */

//import key modules
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;


public class NgramCountMap{
  //if true, stopWords are excluded
  public boolean stopWords;
  // List of all stopWords for reference
  public ArrayList<String> stopWordList;
  // list of all the words from the text file
  public ArrayList<ArrayList<String>> wordList;
  //list of all unigrams from WordList
  public ArrayList<String> unigramList;
  //list of all bigrams from WordList
  public ArrayList<String> bigramList;
  //list of all trigrams from WordList
  public ArrayList<String> trigramList;
  //list of all wordNodes with counts
  public ArrayList<Node> countNodeList;
  //alphabetically sorted list
  public ArrayList<Node> alphaSort;
  //list sorted by count
  public ArrayList<Node> countSort;
  //the root of the BST
  public Node root;


  //constructor method
  public NgramCountMap(){
    this.root = null;
    this.wordList = new ArrayList<ArrayList<String>>();
    this.unigramList = new ArrayList<String>();
    this.bigramList = new ArrayList<String>();
    this.trigramList = new ArrayList<String>();
    this.stopWordList = new ArrayList<String>();
    this.alphaSort = new ArrayList<Node>();
    this.countSort = new ArrayList<Node>();
    this.countNodeList = new ArrayList<Node>();
  }

  //returns the root
  public Node getRoot(){
    return this.root;
  }

  //sets stopwords to true
  public void setStopTrue(){
    this.stopWords = true;
  }

  //sets stopwords to false
  public void setStopFalse(){
    this.stopWords = false;
  }

  //Creates a list of stopWords
  public void initStopWordList(){
    File file = new File("stopwords.txt");
    Scanner scanner = null;

    try{
      scanner = new Scanner(file);
      } catch (FileNotFoundException e){
          System.err.println(e);
          System.exit(1);
      }

    while (scanner.hasNext()){
      this.stopWordList.add(scanner.next());
    }
  }


  //reads in the text file and makes uni/bi/trigram lists
  public void initLists(String fileName){
    File file = new File(fileName);
    Scanner scanner = null;

    try{
      scanner = new Scanner(file);
      } catch (FileNotFoundException e){
          System.err.println(e);
          System.exit(1);
      }
    //lets the scanner split by sentence
    scanner.useDelimiter("\\.");

    //creates a string array per sentence and adds it to wordList
    while (scanner.hasNext()){
      String[] curWords = scanner.next().split(" ");
      ArrayList<String> temp = new ArrayList<String>();
      for (int i = 0; i <curWords.length;i++){
        temp.add(curWords[i]);
      }
      this.wordList.add(temp);
    }

    //if stopwords is true, it removes all stopwords from wordList
    if (this.stopWords==true){
      for (int i = 0; i<this.wordList.size();i++){
        for (int a = 0; a<this.wordList.get(i).size();i++){
          if (this.stopWordList.contains(this.wordList.get(i).get(a))){
            this.wordList.get(i).remove(a);
          }
        }
      }
    }

    //turns unigrams from WordList into bigrams by adding the curWord
    //and the next word
    for (int i=0;i<this.wordList.size();i++){
      for (int a=0;a<this.wordList.get(i).size()-1;a++){
        String curBigram = this.wordList.get(i).get(a) +' '+ this.wordList.get(i).get(a+1);
        this.bigramList.add(curBigram);
      }
    }

    //same method is used to make trigrams
    for (int i=0;i<this.wordList.size();i++){
      for (int a = 0; a<this.wordList.get(i).size()-2;a++){
        String curTrigram = this.wordList.get(i).get(a)+' '+ this.wordList.get(i).get(a+1)
          +' '+ this.wordList.get(i).get(a+2);
        this.trigramList.add(curTrigram);
      }
    }

    //makes a unigram list with the same method
    for (int i=0;i<this.wordList.size();i++){
      for (int a = 0; a<this.wordList.get(i).size();a++){
        this.unigramList.add(this.wordList.get(i).get(a));
      }
    }

  }

  //reads in textfiles and makes an arrayList of nodes
  //each node object has the counts associated with it
  //as well as the words
  public void initCountNodeList(ArrayList<String> nGramList){
    //used to see if a word is already read in
    ArrayList<String> wordsAdded = new ArrayList<String>();
    //loops through nGrams in the list
    for (String s:nGramList){
      //if its not added then it adds a node w count 1
      //else iterates to node and adds count
      if (!wordsAdded.contains(s)){
        Node addNode = new Node(s);
        this.countNodeList.add(addNode);
        wordsAdded.add(s);
      }else{
        for (Node n:countNodeList){
          if (n.getNgram().equals(s)){
            n.addCount();
          }
        }
      }
    }
  }


  //constructs a tree sorted by count
  public void putCount(Node n){
    //used to form a trigger loop
    boolean stop = false;

    //if BST empty, sets Node as root
    if (this.root==null){
      this.root = n;
    }

    Node compareNode = this.root;
    Node parent = null;

    //iterates through the list, and adds node as rightChild if
    //its >= to the parent, else makes it a left child
    //exits out of loop when added
    while (!stop){
      parent = compareNode;
      if (n.getCount()==compareNode.getCount()) {
        compareNode = compareNode.getRightChild();
        if(compareNode==null){
          parent.setRightChild(n);
          stop = true;
        }
      }else if (n.getCount()>compareNode.getCount()){
        compareNode = compareNode.getRightChild();
        if (compareNode==null){
          parent.setRightChild(n);
          stop = true;
        }
      } else if (n.getCount()<compareNode.getCount()){
        compareNode = compareNode.getLeftChild();
        if (compareNode==null){
          parent.setLeftChild(n);
          stop = true;
        }
      }
    }

  }

  //constructs a tree by alphabet
  public void putAlphabet(String ngram){
    Node addNode = new Node(ngram);
    boolean stop = false;

    //if tree is empty, makes newNode the root
    if (this.root==null){
      this.root = addNode;
      this.root.setCount(0);
    }

    Node compareNode = this.root;
    Node parent = null;

    //else adds count if the Nodes are the same
    //adds as leftChild if its alphabetically prior
    //adds as a right child otherwise
    while (!stop){
      parent = compareNode;
      if (ngram.equals(compareNode.getNgram())) {
        compareNode.addCount();
        stop = true;
      }else if (ngram.compareTo(compareNode.getNgram())>0){
        compareNode = compareNode.getRightChild();
        if (compareNode==null){
          parent.setRightChild(addNode);
          //System.out.println("rightChild added");
          stop = true;
        }
      } else if (ngram.compareTo(compareNode.getNgram())<0){
        compareNode = compareNode.getLeftChild();
        if (compareNode==null){
          parent.setLeftChild(addNode);
          //System.out.println("leftChild added");
          stop = true;
        }
      }
    }

  }

  //returns a sorted ArrayList from the Traversal
  public ArrayList<Node> getNgramCountsByNgram(){
    this.alphaSort.remove(0);
    return this.alphaSort;
  }

  //traverses the tree in order and adds it to a list
  public void traverseInOrder(Node N){
    if (N==null){
      return;
    }
   traverseInOrder(N.getLeftChild());
   this.alphaSort.add(N);
   traverseInOrder(N.getRightChild());
  }

  //sorts the alphabetically sorted list by count
  public ArrayList<Node> getNgramCountsByCount(){
    for (int i=0;i<this.alphaSort.size()-1;i++){
      int max = i;
      for (int j = i+1;j<this.alphaSort.size();j++){
        if (this.alphaSort.get(j).getCount()>this.alphaSort.get(max).getCount()){
          max = j;
        }
      Node temporary = this.alphaSort.get(max);
      this.alphaSort.set(max,this.alphaSort.get(i));
      this.alphaSort.set(i,temporary);
      }
    }
    return this.alphaSort;
  }

  //defines print for the arrayList
  public void printer(ArrayList<Node> list){
    System.out.println(" ");
    for (Node N:list){
      System.out.print(N);
    }
  }


}
