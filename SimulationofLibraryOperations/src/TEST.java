import books.Handwritten;
import books.Printed;
import interfaces.Borrow;
import interfaces.ReadInLibrary;

/**
 * Created by macair on Apr/03/2019
 */


public class TEST {

  public static void main(String[]args){

    Borrow borrowBook = new Printed(123);
    ReadInLibrary readInLibrary = new Handwritten(123);



  }
}
