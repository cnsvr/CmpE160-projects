package librarymembers;

import books.Book;
import java.util.ArrayList;


public class Student extends LibraryMember {

  /*
  Constructs a student object with its id.
  @param id  ... id of student.

   */

  public Student(int id){

    super(id);

  }
  /*
    @return history of the books that are either read or borrowed by a student member.
   */
  @Override
  public ArrayList<Book> getHistory(){

    return historyOfBooks;
  }
}
