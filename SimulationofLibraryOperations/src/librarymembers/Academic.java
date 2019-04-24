package librarymembers;

import books.Book;
import java.util.ArrayList;


public class Academic extends LibraryMember {

  /*

  Constructs a academic object with its id.
  @param id  ... id of academician.

   */

  public Academic(int id){
    super(id);

  }

  /*
  @return history of the books that are either read or borrowed by a academician member.
   */
  @Override
  public ArrayList<Book> getHistory(){

    return historyOfBooks;

  }
}
