package CargoTrain;

import Util.Cargo;

import java.util.ArrayDeque;
import java.util.Queue;

public class Train {

  private int carCapacity;
  private int length;
  private Carriage head;
  private Carriage tail;
  private int carriageCount;

  /*

    This is a Train class.

    @param length      ... length of the train.
    @param carCapacity ... capacity of all carriages.

    Constructs a train object with parameters and adds all carriages to train.

   */

  public Train(int length,int carCapacity){

    this.length = length;
    this.carCapacity = carCapacity;
    carriageCount = 0;

    addAllCarriage(carCapacity);

  }

  //Add all carriages to train
  private void addAllCarriage(int carCapacity){

    while (this.length != carriageCount){

      insertCarriage(carCapacity);

    }

  }

  //Add a carriage to tail of train and updates the head and tail carriage.
  private void insertCarriage(int carCapacity){

    Carriage newCarriage = new Carriage(carCapacity);

    if(this.head == null){

      this.head = newCarriage;
      this.tail = newCarriage;
    }else {

      if(this.head.getNext() == null){

        newCarriage.setPrev(this.head);
        this.head.setNext(newCarriage);
        this.tail = newCarriage;
      }else {

        newCarriage.setPrev(this.tail);
        this.tail.setNext(newCarriage);
        this.tail = newCarriage;
      }
    }

    this.carriageCount++;


  }

  //Remove a carriage from the tail of the train and updates the head and tail carriage.
  private void removeCarriage(){

    if(this.tail== null){

      return;
    }else {

      if(this.head == this.tail){

        this.head = null;
        this.tail = null;

      }else {

        //Carriage current = tail.getPrev();

        this.tail = tail.getPrev();
        tail.setNext(null); //????

      }
    }

    carriageCount--;


  }

  //@return count of carriages of train.
  public int size(){

    return carriageCount;
  }

  /*If cargo queue is empty,remove all carriages are not necessary,
    otherwise calls another load method.
  */
  public void load(Queue<Cargo> cargos){

    if(cargos.isEmpty()){

      Carriage remove = tail;

      while (remove != null){

        if(remove.isEmpty()){

          removeCarriage();
        }

        remove = remove.getPrev();
      }


    }else if(head == null) {

        insertCarriage(carCapacity);
        load(cargos,head);

    }
    else {
      load(cargos, head);
    }

  }

  /*
    Loads all cargoes one by one from cargo queue to current carriage one by one
    until current carriage is full recursively.
   */
  private void load(Queue<Cargo> cargos,Carriage current) {

    Queue<Cargo> temp = new ArrayDeque<>(cargos);

    int currentSize = 0;

    while (!temp.isEmpty()) {

      Cargo cargo = temp.peek();
      int size = cargo.getSize();

      if(currentSize + size <= current.getEmptySlot()) {

        currentSize += size;
        cargos.remove(cargo);
        current.push(cargo);

      }

      temp.remove();

    }

    while (!cargos.isEmpty()){


      if(current == tail){

          insertCarriage(carCapacity);
          load(cargos,current.getNext());

      }else {

          load(cargos,current.getNext());
      }

    }

    //Removing empty carriages if train has empty carriages.
    Carriage remove = tail;

    while (remove != null){

      if(remove.isEmpty()){

        removeCarriage();
      }

      remove = remove.getPrev();
    }


  }

  /*
    Unloads to cargoes in train to cargo queue parameters until train is completely empty.
   */
  public void unload(Queue<Cargo> cargos){


    Carriage current = head;

    while (current != null){

      while (!current.isEmpty()){

          Cargo cargo = current.pop();

          cargos.add(cargo);

      }

      current = current.getNext();
    }


  }

  public Carriage getHead() {
    return head;
  }

  @Override
  public String toString() {
    return "CarriageList [head=" + head + "]";
  }
}
