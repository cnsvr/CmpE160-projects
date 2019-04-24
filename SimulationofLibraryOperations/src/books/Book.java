package books;

import librarymembers.LibraryMember;


public abstract class Book {

  private static final int MAX_NUM_ID = (int)Math.pow(10,6);
  private int bookID;
  private String bookType;
  private boolean isTaken;
  private LibraryMember whoHas;

  /*
    This is an abstract class and parent class of Handwritten and Printed classes.

    @param bookID      ...     id of the book and mostly 6-digit.
    @param bookType    ...     type of book ->> H for Handwritten , P for Printed.
    @throws            ...     IllegalArgumentException if bookID or bookType is invalid.

    Constructor takes two parameters and initializes all fields of class with given parameters.

*/

  Book(int bookID,String bookType){

    if(bookID > MAX_NUM_ID && !(bookType.equals("H") || bookType.equals("P"))){

     // throw new IllegalArgumentException("ID of book : " + bookID + "\nType of Book :" + bookType);
      return;
    }

    this.bookID = bookID;
    this.bookType = bookType;
    this.isTaken = false;
    this.whoHas = null;


  }


  public abstract void returnBook(LibraryMember member);

  public  void borrowBook(LibraryMember member,int tick){}

  public  void extend(LibraryMember member,int tick){}

  public abstract void readBook(LibraryMember member);


  /*
  @return id of book.
   */
  public int getBookID() {
    return bookID;
  }

  /*
  @return type of book.
   */
  public String getBookType() {
    return bookType;
  }

  /*
  @return value of isTaken of a book
   */
  public boolean isTaken() {
    return isTaken;
  }
  /*
  Sets value of isTaken of a book.
   */
  void setTaken(boolean taken) {
    isTaken = taken;
  }
  /*
  @return member who has the corresponding book.
 */
  public LibraryMember getWhoHas() {
    return whoHas;
  }
  /*
  Sets member who has the corresponding book.
   */

  void setWhoHas(LibraryMember whoHas) {
    this.whoHas = whoHas;
  }

}
