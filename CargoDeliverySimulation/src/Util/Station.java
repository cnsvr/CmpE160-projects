package Util;

import CargoTrain.Carriage;
import CargoTrain.Train;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;


public class Station {

  private int id;
  private Queue<Cargo> cargoqueue;
  private static PrintStream printStream;

  /*
  This is Station class.

  @param  id    ... id of the station.

  Constructs a station object and initializes the cargoqueue and printStream.

   */

  public Station(int id)throws FileNotFoundException {
    this.id = id;
    cargoqueue = new ArrayDeque<>();
    printStream = new PrintStream("output.txt");
  }


  /*
  Handles the events from the train’s arrival to the leaving of the train.This method also takes care of all the output operations.
  In this method train unloads into ​cargoQueue and prints the cargos that have reached their destination without changing the order in the cargoQueue​.
  Then it loads rest of the queue into the train also without changing the order. Lastly prints the length of the train.
   */
  public void process(Train train) {

    //Firstly,all cargoes from train unloaded to cargoqueue.
    //If station is 0,cargoes will load to train only.


    // First station is the loading station,no unloading event.
    if (this.id == 0) {

      train.load(cargoqueue);

      printStream.println(this.id + " " + train.size());

    } else {

      //Else , after from station 1 to other stations, firstly unloaded and loaded ant train goes until last stop.

      //If there are cargoes whose loading and target station is same, they will not load.Only will print out.
      for (Cargo cargo : cargoqueue) {

        if (cargo.getLoadingStation().getId() == this.getId() && cargo.getTargetStation().getId() == this.getId()) {

          printStream.println(cargo.getId() + " " + cargo.getLoadingStation().getId() + " " + cargo.getTargetStation().getId() + " " + cargo.getSize());
        }

      }


      //To print out of those which will stay in corresponding station.
      Carriage current = train.getHead();

      while (current != null) {

        Stack<Cargo> temp = new Stack<>();

        temp.addAll(current.getCargos());

        while (!temp.isEmpty()) {

          Cargo cargo = temp.pop();

          int id = cargo.getTargetStation().getId();

          if (id == this.getId()) {
            printStream.println(cargo.getId() + " " + cargo.getLoadingStation().getId() + " " + cargo.getTargetStation().getId() + " " + cargo.getSize());

          }
        }

        current = current.getNext();
      }


      train.unload(cargoqueue);



      Queue<Cargo> toLoad = new ArrayDeque<>();

      for (Cargo cargo : cargoqueue) {


        if (cargo.getTargetStation().getId() > this.getId()) {

          toLoad.add(cargo);

        }
      }

      //to updates cargoes queue of the corresponding station.
      this.removeCargo(toLoad);

      train.load(toLoad);


      printStream.println(this.id + " " + train.size());


    }


    }

  //Adds cargoes to cargo queue of the corresponding station.
  public void addCargo(Cargo cargo){

    cargoqueue.add(cargo);
  }

  //Removes cargoes from cargo queue of the corresponding station.
  private void removeCargo(Queue<Cargo> cargoes){

    for (Cargo cargo : cargoes) {

      cargoqueue.remove(cargo);

    }
  }

  //@return id of the station.
  public int getId(){
    return id;
  }


  public Queue<Cargo> getCargoqueue() {
    return cargoqueue;
  }


public static void setPrintStream(PrintStream printStream) {
	Station.printStream = printStream;
}
  
  
}
