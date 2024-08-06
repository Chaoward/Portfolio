
#include <iostream>
#include "phone_book.h"

using namespace std;


int main ( ) 
{

  // Every test prints out the number of phone numbers in the book;

  PhoneBook phoneBook;
  
  cout << "Test 1: Operations on an empty book\n";
  cout << "Removing a number from an empty book\n";
  phoneBook.removePhoneNumber ( 909, 345, 1264 );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  cout << "Size of area code ( 909 ) = " << phoneBook.numAreaCodeNumbers ( 909 ) << endl;
  cout << "Number of phone numbers with area code ( 909 ) and prefix 234 = " << phoneBook.numAreaCodeAndPrefixNumbers ( 909, 234 ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  
  cout << "Test 2: Inserting first phone number\n";
  phoneBook.insertPhoneNumber ( 909, 345, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 3: Inserting 3 additional phone numbers to the same area code\n";
  phoneBook.insertPhoneNumber ( 909, 345, 2222 );
  phoneBook.insertPhoneNumber ( 909, 345, 3333 );
  phoneBook.insertPhoneNumber ( 909, 123, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  cout << "Size of area code ( 909 ) = "  << phoneBook.numAreaCodeNumbers ( 909 ) << endl;
  cout << "Number of phone numbers with area code ( 909 ) and prefix 345 = " << phoneBook.numAreaCodeAndPrefixNumbers ( 909, 345 ) << endl;

  system("pause");
  cout << "--------------------------------------------------------\n";

  cout << "Test 4: Inserting duplicate number at tail of phone number list\n";
  phoneBook.insertPhoneNumber ( 909, 345, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";

  cout << "Test 5: Inserting duplicate number in middle of phone number list\n";
  phoneBook.insertPhoneNumber ( 909, 345, 2222 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  
  cout << "Test 6: Inserting duplicate number at head of phone number list\n";
  phoneBook.insertPhoneNumber ( 909, 123, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 7: Inserting 4 more numbers to the same area code\n";
  phoneBook.insertPhoneNumber ( 909, 123, 2222 );
  phoneBook.insertPhoneNumber ( 909, 123, 3333 );
  phoneBook.insertPhoneNumber ( 909, 123, 4444 );
  phoneBook.insertPhoneNumber ( 909, 123, 5555 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 8: Searching for a phone number that does exist\n";
  cout << "Searching for 909-345-1111...." << flush;
  if ( phoneBook.search ( 909, 345, 1111 ) ) 
  {
    cout << "found\n";
  }
  else 
  {
    cout << "not found\n";
  }
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 9: Searching for a phone number that does not exist\n";
  cout << "Searching for 909-345-9876...." << flush;
  if ( phoneBook.search ( 909, 345, 9876 ) ) 
  {
    cout << "found\n";
  }
  else 
  {
    cout << "not found\n";
  }
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 10: Removing from head of phone number list\n";
  phoneBook.removePhoneNumber ( 909, 123, 5555 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 11: Removing from middle of phone number list\n";
  phoneBook.removePhoneNumber ( 909, 123, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 12: Removing from tail of phone number list\n";
  phoneBook.removePhoneNumber ( 909, 345, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 13: Removing a phone number that does not exist\n";
  phoneBook.removePhoneNumber ( 909, 345, 9876 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";

//*************************************************************************
  // tests for more than 1 area code
  cout << "Test 14: Inserting a number into a new area code\n";
  phoneBook.insertPhoneNumber ( 818, 999, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 15: Inserting 3 more numbers into the new area code\n";
  phoneBook.insertPhoneNumber ( 818, 999, 2222 );
  phoneBook.insertPhoneNumber ( 818, 999, 3333 );
  phoneBook.insertPhoneNumber ( 818, 888, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 16: Adding a third area code\n";
  phoneBook.insertPhoneNumber ( 619, 777, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 17: Searching for a phone number in an area code that does not exist \n";
  cout << "Searching for 111-345-1111...." << flush;
  if ( phoneBook.search ( 111, 345, 1111 ) ) 
  {
    cout << "found\n";
  }
  else 
  {
    cout << "not found\n";
  }
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 18: Removing in an area code that does not exist\n";
  phoneBook.removePhoneNumber ( 435, 567, 2345 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 19: Removing the last phone number in an area code at the head of the area code list\n";
  phoneBook.removePhoneNumber ( 619, 777, 1111 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
//************************************************************************
  cout << "Test 20: Inserting additional phone numbers\n";
	phoneBook.insertPhoneNumber ( 868, 678, 5436 );
	phoneBook.insertPhoneNumber ( 868, 678, 5436 );
	phoneBook.insertPhoneNumber ( 868, 678, 5433 );
	phoneBook.insertPhoneNumber ( 868, 678, 5435 );
	phoneBook.insertPhoneNumber ( 868, 678, 5439 );
	phoneBook.insertPhoneNumber ( 868, 678, 5430 );
	phoneBook.insertPhoneNumber ( 753, 123, 2345 );
	phoneBook.insertPhoneNumber ( 753, 123, 1234 );
	phoneBook.insertPhoneNumber ( 753, 123, 4345 );
	phoneBook.insertPhoneNumber ( 753, 123, 2545 );
	phoneBook.insertPhoneNumber ( 753, 123, 2365 );
	phoneBook.insertPhoneNumber ( 753, 123, 9345 );
	phoneBook.insertPhoneNumber ( 753, 674, 3456 );
	phoneBook.insertPhoneNumber ( 868, 678, 5436 );
	phoneBook.insertPhoneNumber ( 868, 678, 5436 );
	phoneBook.insertPhoneNumber ( 868, 678, 5433 );
	phoneBook.insertPhoneNumber ( 868, 678, 5435 );
	phoneBook.insertPhoneNumber ( 868, 678, 5439 );
	phoneBook.insertPhoneNumber ( 868, 678, 5430 );
	phoneBook.insertPhoneNumber ( 753, 123, 2345 );
	phoneBook.insertPhoneNumber ( 753, 123, 1234 );
	phoneBook.insertPhoneNumber ( 753, 123, 4345 );
	phoneBook.insertPhoneNumber ( 753, 123, 2545 );
	phoneBook.insertPhoneNumber ( 753, 123, 2365 );
	phoneBook.insertPhoneNumber ( 753, 123, 9345 );
	phoneBook.insertPhoneNumber ( 753, 674, 3456 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
//*************************************************************************
  // test spliting area codes
  cout << "Test 21: Splitting 123 prefix out of area code 909 to area code 808\n";
  phoneBook.split ( 909, 123, 808 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 22: Splitting nonexisting prefix out of area code 909 to area code 808\n";
  phoneBook.split ( 909, 123, 808 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 23: Splitting 345 prefix out of area code 909 to existing area code\n";
  phoneBook.split ( 909, 345, 808 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  system("pause");
  cout << "--------------------------------------------------------\n";
  cout << "Test 24: Splitting 345 prefix out of area code 909 to 707 (909 is now empty)\n";
  phoneBook.split ( 909, 345, 707 );
  phoneBook.print ( );
  cout << "Total size of book = " << phoneBook.numNumbers ( ) << endl;
  cout << "--------------------------------------------------------\n";

  system("pause");
  return 1;
}
