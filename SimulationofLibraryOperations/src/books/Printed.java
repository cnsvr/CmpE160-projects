package books;

import interfaces.Borrow;
import interfaces.ReadInLibrary;
import librarymembers.LibraryMember;

public class Printed extends Book implements ReadInLibrary, Borrow {

  private int deadLine;
  private boolean isExtended;

  /*
      This is a child class of Book.
      It constructs a object Printed with book id.
      @param bookID ... id of the book

 */

  public Printed(int bookID){

    super(bookID,"P");
    this.deadLine = 0;
    this.isExtended = false;

  }

  /*
     Sets the corresponding book to setWhoHas as null,setTaken as false and setDeadLine as 0.
     Updates the value of current number of book of corresponding member.
     @param member   ...  member of library.
   */

  public void returnBook(LibraryMember member){

    setWhoHas(null);
    setTaken(false);
    setDeadLine(0);
    member.setCurrentBookNumber(member.getCurrentBookNumber()-1);
    System.out.println("Number " + member.getId() + " delivered  book " + getBookID() + " to library.");


  }
/*
     Sets the corresponding book to setWhoHas as corresponding member,setTaken as true and
     deadline of book with tick.
     Updates the value of current number of book of corresponding member.

     @param member    ... member of library.
     @param tick      ... This is ​time unit of the project, which is stored in the LibrarySimulator​.
   */

  @Override
  public void borrowBook(LibraryMember member, int tick){

    setDeadLine(tick + member.getTimeLimit());
    setWhoHas(member);
    setTaken(true);
    member.setCurrentBookNumber(member.getCurrentBookNumber()+1);
    System.out.println("Number " + member.getId() + " borrowed  book " + getBookID());


  }

  /*
     Sets the corresponding book to setExtended as true.
     Updates the value of time limit of book of corresponding member.

     @param member    ... member of library.
     @param tick      ... This is ​time unit of the project, which is stored in the LibrarySimulator​.
   */


  @Override
  public void extend(LibraryMember member, int tick){

    System.out.println("Current deadline : " + getDeadLine());
    setDeadLine(this.getDeadLine()+member.getTimeLimit());
    System.out.println("Book " + getBookID() + " is extended to " + getDeadLine());
    setExtended(true);

    System.out.println("Number " + member.getId() + " extended the deadline of   book " + getBookID());


  }

  /*
     Sets the corresponding book to setWhoHas as corresponding member and setTaken as true.
     Updates the value of current number of book of corresponding member.

     @param member    ... member of library.
     @param tick      ... This is ​time unit of the project, which is stored in the LibrarySimulator​.
   */

  @Override
  public void readBook(LibraryMember member){

    setTaken(true);
    setWhoHas(member);
    member.setCurrentBookNumber(member.getCurrentBookNumber()+1);
    System.out.println("Number " + member.getId() + " reads  book " + getBookID() + " in library.");



  }

  /*
    @return deadline of this book.
   */
  public int getDeadLine() {
    return deadLine;
  }

  /*
  Sets deadline of this book.
   */

  private void setDeadLine(int deadLine) {
    this.deadLine = deadLine;
  }
  /*

  @return value of isExtended of this book.
   */

  public boolean isExtended() {
    return isExtended;
  }

  /*
  Sets value of isExtended of this book.
   */
  private void setExtended(boolean extended) {
    isExtended = extended;
  }

}
