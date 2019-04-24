package library;

import books.Book;
import books.Handwritten;
import books.Printed;
import librarymembers.Academic;
import librarymembers.LibraryMember;
import librarymembers.Student;

import java.util.Scanner;


public class Library {

  private static final int MAX_SIZE = (int)Math.pow(10,6);
  private static int bookID = 0;
  private static int memberID = 0;
  private static int indexOfBook = 0;
  private static int indexOfMembers = 0;
  private static int totalFee = 0;
  private Scanner readInput;
  private Book[] books;
  private LibraryMember[] libraryMembers;



  /*
     Constructor initialize books array and libraryMembers array.

     @param Scanner console  ... to read the remaining parts of input line.
   */

  public Library(Scanner console){

    books = new Book[MAX_SIZE];
    libraryMembers = new LibraryMember[MAX_SIZE];
    readInput = console;


  }



  /*
    Checks the type of the book to be added.
    Assigns an id to the book incrementally.
    Adds the corresponding book object to the books array.
    @exception ... IllegalArgumentException on Input Error.
   */
  public void addBook(){

    String bookType = "";

    if(readInput.hasNext()) {

      bookType = readInput.next().toUpperCase();


      if (!(bookType.equals("P") || bookType.equals("H"))) {

        //throw new IllegalArgumentException("Invalid Book Type!");
        return;

      }

    }

      if(bookType.equals("P")){

        bookID++;

        Book printed = new Printed(bookID);
        System.out.println("Book " + bookID + " is added.");

        books[indexOfBook] = printed;
        indexOfBook++;

      }else {

        bookID++;

        Book handwritten = new Handwritten(bookID);
        System.out.println("Book " +bookID + " is added.");

        books[indexOfBook] = handwritten;
        indexOfBook++;

      }


    }

  /*
      Checks the type of the member whether it is A as Academician or S as Student.
      Assigns an id to the member incrementally.
      Sets the values of the corresponding member with methods of LibraryMember class.
      Adds the corresponding member object to libraryMember array.
   */

  public void addMember(){

    if(readInput.hasNext()){

      String memberType = readInput.next().toUpperCase();

      if(!(memberType.equals("A") || memberType.equals("S"))){

        //throw new IllegalArgumentException("Invalid Member Type!");
        return;
      }

      if(memberType.equals("A")){

        memberID++;

        LibraryMember academic = new Academic(memberID);
        academic.setAccessToHandWritten(true);
        academic.setMaxNumberOfBooks(20);
        academic.setTimeLimit(50);
        System.out.println("Library Member " + memberID + " is added. Academic" );

        libraryMembers[indexOfMembers] = academic;
        indexOfMembers++;

      }else {

        memberID++;

        LibraryMember student= new Student(memberID);
        student.setAccessToHandWritten(false);
        student.setMaxNumberOfBooks(10);
        student.setTimeLimit(20);
        System.out.println("Library Member " + memberID + " is added. Student");

        libraryMembers[indexOfMembers] = student;
        indexOfMembers++;

      }

    }

  }

  /*
      Finds the necessary book and member with their id's and assign them to borrowedBook and borrowerMember.
      Checks the corresponding book whether was taken or not and corresponding member whether exceeded the book limit or not.
      Adds the corresponding book to ArrayList historyBook.

      @param tick   ... This the time unit of the project, which is stored in the LibrarySimulator​.

   */

  public void borrowBook(int tick){

    int Id_Of_Book = Id_Of_Books(readInput);
    int Id_Of_Library_Member = Id_Of_Library_Member(readInput);

    //Checks for validity ob book and member.
    if(Id_Of_Book == 0 || Id_Of_Library_Member == 0){
      return;
    }

      Book borrowedBook;
      LibraryMember borrowerMember = null;

      for(LibraryMember libraryMember : libraryMembers){

        if (libraryMember.getId()== Id_Of_Library_Member) {

          borrowerMember = libraryMember;
          break;
        }
      }





      for (int i = 0; i<indexOfBook;i++) {

        if (books[i].getBookID() == Id_Of_Book) {

          borrowedBook = books[i];

          //Books can be borrowed only if book is not taken and is printed book.
          if(!borrowedBook.isTaken() && borrowedBook.getBookType().equals("P")){

            //Any member can borrow books only if he/she don't exceed the book limit.
            if(borrowerMember.getCurrentBookNumber()<borrowerMember.getMaxNumberOfBooks() && !isPassedBook(borrowerMember,tick)) {
              borrowedBook.borrowBook(borrowerMember, tick);
              borrowerMember.historyOfBooks.add(borrowedBook);
              borrowerMember.hasBooks.add((Printed)borrowedBook);
            }else if(isPassedBook(borrowerMember,tick)) {

              System.out.println("You have book is passed deadline.");
            }else {

              System.out.println("Member " + borrowerMember.getId() + " has " + borrowerMember.getCurrentBookNumber()+ " books"+ "" +
                      " He/She can't borrow any books due to limit of borrowing books.  ");
            }


          }else if(borrowedBook.isTaken()){

            System.out.println("This book is already taken by someone else.");
          }else {

            System.out.println("This book is Handwritten and you can't borrow it,but reads in library!");
          }

          break;

        }

      }


    }

    /*
        Finds the necessary book and member with their id's and assign them to returnedBook and returnerMember.
        Checks the corresponding book whether is taken or not,corresponding member whether has the same book or not,
        and if deadline of book is passed or not.
        If deadline of book is passed , fee is paid by member is added to totalFee.

        @param tick  ...    This the time unit of the project, which is stored in the LibrarySimulator​.

     */
  public void returnBook(int tick) {

    int Id_Of_Book = Id_Of_Books(readInput);
    int Id_Of_Library_Member = Id_Of_Library_Member(readInput);


    //Checks for validity ob book and member.
    if(Id_Of_Book == 0 || Id_Of_Library_Member == 0){
      return;
    }

      Handwritten returnedHandWritten;
      Printed returnedPrinted;
      LibraryMember returnerMember = null;

    for(LibraryMember libraryMember : libraryMembers){

      if(libraryMember.getId() == Id_Of_Library_Member){

        returnerMember = libraryMember;
        break;

      }
    }

      for (Book book : books) {

        if (book.getBookID() == Id_Of_Book && book.getBookType().equals("P")) {

          returnedPrinted = (Printed)book;

          if(returnedPrinted.isTaken() && (returnedPrinted.getWhoHas() == returnerMember)){


            //If deadline of book is passed,member should pay fee for beinga able to borrow any books again.
            if(tick > returnedPrinted.getDeadLine()){

              totalFee += tick - returnedPrinted.getDeadLine();
              System.out.println("Number " + returnerMember.getId() + " paid fee about " +(tick -returnedPrinted.getDeadLine()));
            }

            returnedPrinted.returnBook(returnerMember);
            returnerMember.hasBooks.remove(returnedPrinted);



          }else if(!returnedPrinted.isTaken()){

            System.out.println("You can't return the book that isn't taken yet!");
          }
          else if(!(returnedPrinted.getWhoHas() == returnerMember)) {

            System.out.println("This book is taken by someone else," +
                    " you can't return this book which somebody else has.");
          }

          break;

          // For handwritten books, there is no limit for deadline.
        }else if(book.getBookID() == Id_Of_Book && book.getBookType().equals("H")){

          returnedHandWritten = (Handwritten)book;

          if(returnedHandWritten.isTaken() && (returnedHandWritten.getWhoHas() == returnerMember)){

            returnedHandWritten.returnBook(returnerMember);

          }else if(!returnedHandWritten.isTaken()){

            System.out.println("You can't return the book that isn't taken yet!");
          }
          else if(!(returnedHandWritten.getWhoHas() == returnerMember)) {

            System.out.println("This book is taken by someone else," +
                    " you can't return this book which somebody else has.");
          }


          break;
        }
      }






    }

    /*
     Finds the necessary book and member with their id's and assign them to extendBook and extendMember.
     Checks deadline of the corresponding book whether is missed or not and can be extended or not.

     @param tick    ... This the time unit of the project, which is stored in the LibrarySimulator​.
     */


    public void extendBook(int tick){

    int Id_Of_Book = Id_Of_Books(readInput);
    int Id_Of_Library_Member = Id_Of_Library_Member(readInput);


      //Checks for validity ob book and member.
      if(Id_Of_Book == 0 || Id_Of_Library_Member == 0){
        return;
      }

    Printed extendBook;
    LibraryMember extendMember = null;


      for(LibraryMember libraryMember : libraryMembers){

        if(libraryMember.getId() == Id_Of_Library_Member){

          extendMember = libraryMember;
          break;

        }
      }



      for (Book book : books) {

        if (book.getBookID() == Id_Of_Book && book.getBookType().equals("P")) {

          extendBook = (Printed)book;

           /*
      If the deadline is not missed yet and the deadline of the borrowed book is not extended before,
      this method doubles the time limit permitted to the ​LibraryMember member​.
      Also,this condition checks the book whether was taken or not and member who took this is same with member
      who wants to extend deadline of this book.
       */
          if(tick <= extendBook.getDeadLine() && !extendBook.isExtended() && extendBook.isTaken()
                  && extendBook.getWhoHas() == extendMember){

            extendBook.extend(extendMember,tick);


          }else if (tick > extendBook.getDeadLine()){

            System.out.println("Deadline of book is passed,so you have to return the book and pay the fee,please!");

          }else if(!extendBook.isTaken()){

            System.out.println("This book isn't taken yet,you can't extend deadline of book that you didn't take yet.");


          }else if(!(extendBook.getWhoHas() == extendMember)) {

            System.out.println("This book is taken by someone else,you can't extend deadline of this book which somebody else has.");

          }else
          {

            System.out.println("Deadline for this  book is extended once.");
          }

          break;


        }else if(book.getBookID() == Id_Of_Book && book.getBookType().equals("H")){

          System.out.println("This book can't be borrowed and whose deadline can't be extended." +
                  "It can be read with no limit of deadline in library..");

          return;
        }
      }


  }

  /*
    Finds the necessary book and member with their id's and assign them to readBookInLibrary and readerMemberInLibrary.
    Checks corresponding book's type and corresponding member whether has accessibility to corresponding book or not.
    Only Academician can access Handwritten books.
    Adds the corresponding book to ArrayList historyBook.

   */

  public void readInLibrary(){

    int Id_Of_Book = Id_Of_Books(readInput);
    int Id_Of_Library_Member = Id_Of_Library_Member(readInput);

    //Checks for validity ob book and member.
    if(Id_Of_Book == 0 || Id_Of_Library_Member == 0){
      return;
    }

    Book readBookInLibrary;
    LibraryMember readerMemberInLibrary = null;

    for(LibraryMember libraryMember : libraryMembers){

      if(libraryMember.getId() == Id_Of_Library_Member){

        readerMemberInLibrary = libraryMember;
        break;

      }
    }


    for (Book book : books) {

      if (book.getBookID() == Id_Of_Book) {

        readBookInLibrary = book;

        //If book is Printed,anybody can read them.
        if(readBookInLibrary.getBookType().equals("P") && !readBookInLibrary.isTaken()){

          readBookInLibrary.readBook(readerMemberInLibrary);
          readerMemberInLibrary.historyOfBooks.add(readBookInLibrary);

          //If book is Handwritten,academicians only  can read them.
        }else if(readerMemberInLibrary.isAccessToHandWritten() && !readBookInLibrary.isTaken()) {

          readBookInLibrary.readBook(readerMemberInLibrary);
          readerMemberInLibrary.historyOfBooks.add(readBookInLibrary);


        }else if(readBookInLibrary.isTaken()) {

          System.out.println("This book was already taken!");

        }else {

          System.out.println("Only Academicians can access HandWritten books,Sorry for that!");
        }

        break;

      }
    }



  }


  /* @return true if corresponding book is in library records,otherwise false.
     @param id ....  id of book
  */
  private boolean isValidBook(int id){

    for(int i = 0; i<indexOfBook;i++){

      if(books[i].getBookID() == id){

        return true;
      }
    }

    return false;


  }

  /* @return true if corresponding member is registered in library,otherwise false.
     @param id ....  id of member
  */
  private boolean isValidMember(int id){

    for(int i = 0; i<indexOfMembers;i++){

      if(libraryMembers[i].getId()== id){

        return true;
      }
    }

    return false;


  }

  /*
     @return  id of the book in the remaining parts of input line.
     @param readInput     ... reads input file.
   */
  private int Id_Of_Books(Scanner readInput){

    int Id_Of_Books = 0;

    if(readInput.hasNext()) {

      Id_Of_Books = readInput.nextInt();

      if (isValidBook(Id_Of_Books)) {

        return Id_Of_Books;
      } else {

        System.out.println("There is no book whose id is " + Id_Of_Books);
        Id_Of_Books = 0;
      }

    }

    return Id_Of_Books;
  }

  /*
     @return  id of the book in the remaining parts of input line.
     @param readInput     ... reads input file.
   */
  private int Id_Of_Library_Member(Scanner readInput){

    int Id_Of_LibraryMember = 0;

    if(readInput.hasNext()) {

      Id_Of_LibraryMember = readInput.nextInt();

      if (isValidMember(Id_Of_LibraryMember)) {

        return Id_Of_LibraryMember;
      } else {

        System.out.println("There is no member whose id is " + Id_Of_LibraryMember);
        Id_Of_LibraryMember = 0 ;
      }


    }

    return Id_Of_LibraryMember;
  }

  public boolean isPassedBook(LibraryMember member,int tick){

    for(Printed book : member.hasBooks){

      if(tick > book.getDeadLine()){

        return true;

      }
    }
    return false;

  }

  /*
   @return the total fee.
   */
  public int getTotalFee(){
    return totalFee;
  }
}
