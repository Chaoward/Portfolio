
#ifndef __PHONE_BOOK_H
#define __PHONE_BOOK_H

#include "area_node.h"

using namespace std;

class PhoneBook 
{

private:
  AreaNode* head;

public:
  void insertPhoneNumber ( int, int, int );
  void removePhoneNumber ( int, int, int );
  bool search ( int, int, int );
  void print ( );
  int numNumbers ( );
  int numAreaCodeNumbers ( int );
  int numAreaCodeAndPrefixNumbers ( int, int );
  void readFromFile ( string );
  void split ( int, int, int );

// Do not modify anything above this line
//------------------------------------------------------------------------

// Add additional functions/variables here:

  PhoneBook() {
      head = nullptr;
  }

  AreaNode* getArea(int area);
  

};

#endif
