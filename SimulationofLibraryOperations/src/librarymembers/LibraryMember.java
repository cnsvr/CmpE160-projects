package librarymembers;

import books.Book;
import books.Printed;

import java.util.ArrayList;


public abstract class LibraryMember  {

  private static final int MAX_NUM_ID = (int)Math.pow(10,6);

  private int id;
  private int maxNumberOfBooks;
  private int timeLimit;
  private int currentBookNumber = 0;
  private boolean isAccessToHandWritten;
  public ArrayList<Book> historyOfBooks;
  public ArrayList<Printed> hasBooks;




  /*
     Constructs a object libraryMember with its id and initializes the historyOfBooks arraylist.
     @param id .... id of the member(Student or Academician)

   */

  LibraryMember(int id){

    if(id>MAX_NUM_ID){

      // throw new IllegalArgumentException("id is bigger than : " + MAX_NUM_ID);
      return;
    }

    this.id = id;
    historyOfBooks = new ArrayList<>();
    hasBooks = new ArrayList<>();


  }

  abstract ArrayList<Book> getHistory();


  /*
    @return id of member.
   */
  public int getId() {
    return id;
  }

  /*
  @return maximum number of books which a member can have
   */
  public int getMaxNumberOfBooks() {
    return maxNumberOfBooks;
  }

  /*
   Sets maximum number of books which a member can have
   */
  public void setMaxNumberOfBooks(int maxNumberOfBooks) {
    this.maxNumberOfBooks = maxNumberOfBooks;
  }

  /*
  @return maximum time limit of any books which a member can have
   */
  public int getTimeLimit() {
    return timeLimit;
  }

  /*
   Sets maximum time limit of any books which a member can have
   */
  public void setTimeLimit(int timeLimit) {
    this.timeLimit = timeLimit;
  }

  /*
  @return current number of books which a member has
   */

  public int getCurrentBookNumber() {
    return currentBookNumber;
  }

  /*
  Sets current number of books which a member has
   */
  public void setCurrentBookNumber(int currentBookNumber) {
    this.currentBookNumber = currentBookNumber;
  }

  /*
    @return value of isAccessToHandWritten of any member.
   */

  public boolean isAccessToHandWritten() {
    return isAccessToHandWritten;
  }
  /*
  Sets value of isAccessToHandWritten of any member.
   */

  public void setAccessToHandWritten(boolean accessToHandWritten) {
    isAccessToHandWritten = accessToHandWritten;
  }
}
