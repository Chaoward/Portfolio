
#include <iostream>
#include <string>
#include "phone_book.h"

using namespace std;

//-------------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N + M

void PhoneBook::removePhoneNumber ( int area, int prefix, int suffix ) 
{
    AreaNode* rootArea = nullptr;
    AreaNode* curArea = this->head;

    while (curArea != nullptr) { //searching through areaCode
        if (curArea->areaCode == area) {
            curArea->removeNumber(prefix, suffix);
            if (curArea->size() == 0) {     //deletes areaNode if empty
                 if (rootArea == nullptr)    //rootHead == null means we are deleting the head pointer
                     this->head = curArea->next;
                 else
                     rootArea->next = curArea->next;
                 delete curArea;
            }
            return;
        }
        rootArea = curArea;
        curArea = curArea->next;
    }

    cout << "Error: Phone number does not exist" << endl;
}

//-------------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N + M
void PhoneBook::insertPhoneNumber ( int area, int prefix, int suffix ) 
{
    AreaNode* curArea = this->head;
    AreaNode* rootArea = nullptr;

    while (curArea != nullptr) {
        if (curArea->areaCode == area) {
            curArea->insertNumber(prefix, suffix);
            return;
        }
        else if (area < curArea->areaCode)
            break;
        rootArea = curArea;
        curArea = curArea->next;
    }

    //inserting new AreaCode
    if (rootArea == nullptr) {           //Case where number is add at the front
        rootArea = new AreaNode(area, curArea, new NumberNode(prefix, suffix));
        this->head = rootArea;
    }
    else                                //Case where number is added in the middle or at the end
        rootArea->next = new AreaNode(area, curArea, new NumberNode(prefix, suffix));
}

//-------------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N + M 
bool PhoneBook::search ( int area, int prefix, int suffix ) 
{
    AreaNode* curArea = this->head;

    while (curArea != nullptr) {
        if (curArea->areaCode == area)
            return curArea->search(prefix, suffix);
        curArea = curArea->next;
    }

	return false;
}

//-------------------------------------------------------------------------
// DO NOT MODIFY THIS PRINT FUNCTION
// BIG-O RUN TIME OF THIS FUNCTION: N
void PhoneBook::print ( ) 
{

  if ( head == NULL ) 
  {
    cout << "Phonebook is empty" << endl;
  }
  else 
  {
    for ( AreaNode* temp = head; temp != NULL; temp = temp->next ) 
	{
      cout << "( " << temp->areaCode << " )" << endl;
      temp->print ( );
    }
  }

}

//-------------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N
int PhoneBook::numNumbers ( ) 
{
    AreaNode* curArea = this->head;
    int count = 0;

    while (curArea != nullptr) {
        count += curArea->numNumber();
        curArea = curArea->next;
    }

   return count;
}

//-------------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N + M
int PhoneBook::numAreaCodeNumbers ( int area ) 
{
    return this->numAreaCodeAndPrefixNumbers(area, -1);
}

//-------------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N
int PhoneBook::numAreaCodeAndPrefixNumbers ( int area , int prefix ) 
{
    AreaNode* curArea = this->head;

    while (curArea != nullptr) {
        if (curArea->areaCode == area)
            return curArea->numNumber(prefix);
    }

    return 0;
}

//-------------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N
void PhoneBook::split(int oldAreaCode, int prefix, int newAreaCode)
{
    if (!this->search(oldAreaCode, prefix, -1)) {
        cout << "Error: prefix not found in area code" << endl;
        return;
    }
    if (this->getArea(newAreaCode) != nullptr) {
        cout << "Error: new area code already exists" << endl;
        return;
    }

    NumberNode* curNode = this->getArea(oldAreaCode)->getNumber(prefix);
    NumberNode* nextNode = curNode->getNext();

    while (curNode->getPrefix() == prefix) {
        this->insertPhoneNumber(newAreaCode, curNode->getPrefix(), curNode->getSuffix());
        this->removePhoneNumber(oldAreaCode, curNode->getPrefix(), curNode->getSuffix());
        if (nextNode == nullptr) return;
        curNode = nextNode;
        nextNode = curNode->getNext();
    }
}
//-------------------------------------------------------------------------



//===== getArea ===================================
/*
    Returns the pointer to the AreaNode from the passed
    in area code. Returns nullptr if not found.
*/
//=================================================
AreaNode* PhoneBook::getArea(int area) {
    AreaNode* curNode = this->head;

    while (curNode != nullptr) {
        if (curNode->areaCode == area)
            return curNode;
        curNode = curNode->next;
    }

    return nullptr;
}
