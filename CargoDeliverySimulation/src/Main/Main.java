package Main;

 
import Util.Cargo;
import CargoTrain.Train;
import Util.Station;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

  private static Scanner console;
  private static PrintStream printstream;
 

  
  private static  ArrayList<Station> stations;
  private static Train train;

  public static void main(String[] args) throws FileNotFoundException {
	  
	  
	  //InputGenerator input = new InputGenerator();
	  
	  //input.main(args);
	  
	  console = new Scanner(new File(args[0]));
	  printstream = new PrintStream(args[1]);
	  
	  
	  readAndInitialize();
	  execute();
   
  }

  /*
  Reads the inputs and initializes necessary classes and fields,
  and also places cargos to their initial station.
   */
    private static void readAndInitialize() throws FileNotFoundException {

      //Reads the first line and initialize the train with parameters.
      String firstLine = console.nextLine();

      Scanner scanner = new Scanner(firstLine);

      int numberOfCarriages = scanner.nextInt();
      int capacity = scanner.nextInt();
      int numberOfStation = scanner.nextInt();

      train = new Train(numberOfCarriages, capacity);

      scanner.close();

      stations = new ArrayList<>();
      //Initialize stations.
      for (int i = 0; i < numberOfStation; i++) {

        Station station = new Station(i);
        Station.setPrintStream(printstream);
        stations.add(station);
      }


      while (console.hasNextInt()) {

          int cargoNumber = console.nextInt();
          int loading = console.nextInt();
          int target = console.nextInt();
          int cargoSize = console.nextInt();

          Station loadingStation = null;
          Station targetStation = null;

          for (Station station : stations) {

            if (station.getId() == loading) {

              loadingStation = station;
            }

            if (station.getId() == target) {

              targetStation = station;
            }

          }

          Cargo cargo = new Cargo(cargoNumber, cargoSize, loadingStation, targetStation);
          loadingStation.addCargo(cargo);


        }
      
      console.close();

      }

   /*
   Starts the train from the first station,
   it will move the train and perform the necessary operations until train leaves the last station.
    */
   private  static void execute() {

     for (Station station : stations) {

       station.process(train);


     }
   }
   

}
