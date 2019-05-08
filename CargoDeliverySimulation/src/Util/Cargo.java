package Util;


public class Cargo {
  private int id;
  private int size;
  private Station loadingStation;
  private Station targetStation;

  /*
    This is a cargo class.
    @param id             ...   id of cargo
    @param size           ...   size of cargo
    @param loadingStation ... loading station of cargo
    @param targetStation  ... target station of cargo

    Constructs a Cargo object and initializes with these parameters.

   */
  public Cargo(int id,int size,Station loadingStation, Station targetStation){

    this.id = id;
    this.size = size;
    this.loadingStation = loadingStation;
    this.targetStation = targetStation;

  }

  //@return id of cargo.
  public int getId() {
    return id;
  }

  //@return loading station of cargo.
  public Station getLoadingStation() {
    return loadingStation;
  }

  //@return target station of cargo.
  public Station getTargetStation() {
    return targetStation;
  }

  //@return size of cargo.
  public int getSize() {
    return size;
  }

  @Override
  public String toString(){

    return "ID: "+  id + " " + "LS: " + loadingStation.getId() + " " + " TS: " + targetStation.getId();

  }

}
