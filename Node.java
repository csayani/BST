/**
  * Node.java
  * Node class used as in the construction of a BST
  * Defines some key vars and getters/setters
  *
 */

public class Node {
  //the word that the Node is associated with
  public String Ngram;
  //amount of times it appears in the file
  public int count;
  //left child is word that is before the parent
  public Node leftChild;
  //right child is the word that is after the parent alphabetically
  public Node rightChild;

  //constructor method, inits Ngram to whats passed in and count
  //to 1
  public Node(String startNgram){
    this.Ngram = startNgram;
    this.count = 1;
  }

  /**
    * @param none
    * @return the Ngram
  */
  public String getNgram(){
    return this.Ngram;
  }

  /**
    * @param none
    * @return int freq of word in file
  */
  public int getCount(){
    return this.count;
  }

  /**
    * @param none
    * @return Node the left child
    * returns the leftChild
  */
  public Node getLeftChild(){
    return this.leftChild;
  }

  /**
    * @param none
    * @return the rightChild
    * returns the rightChild
  */
  public Node getRightChild(){
    return this.rightChild;
  }

  /**
    * sets the count of node +1
    * @param none
  */
  public void addCount(){
    this.count = this.count + 1;
  }

  /**
    * sets the leftChild of the node
    * @param none
  */
  public void setLeftChild(Node N){
    this.leftChild = N;
  }

  /**
    * sets the rightChild of the Node
    * @param none
  */
  public void setRightChild(Node N){
    this.rightChild = N;
  }

  /**
    * defines print for Node
    * @param none
  */
  public String toString(){
    return this.Ngram +": "+Integer.toString(this.count)+"\n";
  }

  /**
    * sets the count of node to a specified value
    * @param i the val you want to set it to
  */
  public void setCount(int i){
    this.count = i;
  }
}
