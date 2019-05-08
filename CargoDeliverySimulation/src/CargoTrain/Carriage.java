package CargoTrain;

import Util.Cargo;

import java.util.Stack;

public class Carriage {

  private int emptySlot;
  private Stack<Cargo> cargos;
  private Carriage prev;
  private Carriage next;

  /*
    This is Carriage class.
    @param capacity ... capacity of the carriage.
    Construct a Carriage class with its own capacity.

   */

  public Carriage(int capacity) {

    this.emptySlot = capacity;
    cargos = new Stack<>();
    prev = null;
    next = null;

  }

  //@return true if carriage has not empty slot,otherwise false;
  public boolean isFull() {

    Stack<Cargo> temp = cargos;

    while (!temp.isEmpty()) {

      emptySlot -= temp.pop().getSize();

    }
      return (emptySlot == 0);
  }

  //Adding the cargo parameter to cargo stack in carriage.
  public void push(Cargo cargo) {

    cargos.push(cargo);

  }

  //@return and remove the cargo from cargo stack in carriage.
  public Cargo pop() {


    return cargos.pop();

  }

  //@return size of empty slot.
  public int getEmptySlot() {
    return emptySlot;
  }

  //@return cargo stack of the carriage.
  public Stack<Cargo> getCargos() {
    return cargos;
  }

  //@return true if carriage is empty,otherwise false.
  public boolean isEmpty(){

    return cargos.isEmpty();
  }

  //@return previous carriage of this carriage.
  public Carriage getPrev() {
    return prev;
  }

  //Set the previous carriage of this carriage.
  public void setPrev(Carriage prev) {
    this.prev = prev;
  }

  //@return next carriage of this carriage.
  public Carriage getNext() {
    return next;
  }

  //Set the next carriage of this carriage.
  public void setNext(Carriage next) {
    this.next = next;
  }

}


