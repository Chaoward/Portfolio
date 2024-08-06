
#ifndef __AREA_NODE_H
#define __AREA_NODE_H

#include "number_node.h"

class AreaNode 
{
  friend class PhoneBook;
private:
  int areaCode;
  AreaNode* next;
  NumberNode* head;

public:
  void print ( );
  int size ( );

// Do not modify anything above this line
//------------------------------------------------------------------------

// Add additional functions/variables here:

  AreaNode(int c) {
      areaCode = c;
      next = nullptr;
      head = nullptr;
  }

  AreaNode(int c, AreaNode* n, NumberNode* h) {
      areaCode = c;
      next = n;
      head = h;
  }
  
  void insertNumber(int prefix, int suffix);
  void removeNumber(int prefix, int suffix);
  int numNumber(int targetPrefix = -1);
  int numPrefixNumbers(int targetPrefix);
  bool search(int prefix, int suffix = -1);
  bool isEmpty();
  NumberNode* getNumber(int prefix, int suffix = -1);

};

#endif
