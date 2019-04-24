package books;

import interfaces.ReadInLibrary;
import librarymembers.LibraryMember;

/**
 * Created by macair on Mar/22/2019
 */
public class Handwritten extends Book implements ReadInLibrary {

  public Handwritten(int bookID){

    /*

      This is a child class of Book.
      It constructs a object Handwritten with book id.
      @param bookID ... id of the book

     */

    super(bookID,"H");



  }

  /*
     Sets the corresponding book to setWhoHas as null and setTaken as false.
     Updates the value of current number of book of corresponding member.
     @param member   ...  member of library.
   */

  @Override
  public void returnBook(LibraryMember member){

    setTaken(false);
    setWhoHas(null);
    member.setCurrentBookNumber(member.getCurrentBookNumber()-1);
    //System.out.println("Handwritten returnBook is called.");
    System.out.println("Number " + member.getId() + " delivered  book " + getBookID() + "to library.");


  }

  /*
     Sets the corresponding book to setWhoHas as corresponding member and setTaken as true
     Updates the value of current number of book of corresponding member.

     @param member    ... member of library.

   */


  @Override
  public void readBook(LibraryMember member){

    setTaken(true);
    setWhoHas(member);
    member.setCurrentBookNumber(member.getCurrentBookNumber()+1);
    //System.out.println("Handwritten readBook is called");
    System.out.println("Number " + member.getId() + " reads  book " + getBookID() + " in library.");



  }




}
