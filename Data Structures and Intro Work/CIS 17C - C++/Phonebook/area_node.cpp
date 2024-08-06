
#include <iostream>
#include "area_node.h"

using namespace std;

#define NUM_TO_PRINT_PER_LINE 5

//-----------------------------------------------------------------------
// DO NOT MODIFY THIS PRINT FUNCTION
// BIG-O RUN TIME OF THIS FUNCTION: N

void AreaNode::print ( ) 
{
  NumberNode* temp = head;
  for ( int x = 0; x < size ( ) && temp != NULL; x += NUM_TO_PRINT_PER_LINE ) 
  {
    cout << "       " << flush;
    for ( int y = 0; y < NUM_TO_PRINT_PER_LINE && temp != NULL; y ++, temp = temp->next ) 
	{
      cout << temp->prefix << "-" << flush << temp->suffix << ", " << flush;
    }
    cout << endl;
  }
}

//-----------------------------------------------------------------------
// BIG-O RUN TIME OF THIS FUNCTION: N

int AreaNode::size ( ) 
{
    int count = 0;
    NumberNode* curNode = this->head;

    while (curNode != nullptr) {
        curNode = curNode->next;
        count++;
    }

    return count;
}

//-----------------------------------------------------------------------



//===== removeNumber =================================
/*
    Removes the number passed in by prefix and suffix.
    Returns true if successfully removed, false if not found.
*/
//====================================================
void AreaNode::removeNumber(int prefix, int suffix) {
    NumberNode* curNum = this->head;
    NumberNode* rootNum = nullptr;

    while (curNum != nullptr) { //searching number
        if (curNum->prefix + curNum->suffix == prefix + suffix) {
            //removing phone number
            if (rootNum != nullptr)
                rootNum->next = curNum->next;
            else
                this->head = curNum->next;
            delete curNum;
            return;
        }
        rootNum = curNum;
        curNum = curNum->next;
    }

    cout << "Error: Phone number does not exist" << endl; //Number Not Found;
}



//===== insertNumber ===================================
/*
    Adds the passed in number into the linkedList sorted.
    displays an error if number is a duplicate number.

    NOTE: Numbers are sorted when inserted based on the
        the first half of the sample output.txt
    FORMAT: areaCode(low-high), prefix(low-high), suffix(high-low)
*/
//======================================================
void AreaNode::insertNumber(int prefix, int suffix) {
    NumberNode* curNum = this->head;
    NumberNode* rootNum = nullptr;

    while (curNum != nullptr) {
        if (curNum->prefix == prefix) {
            if (curNum->suffix == suffix) { //dup check
                cout << "Error: cannot insert duplicate phone number" << endl;
                return;
            }
            else if (suffix > curNum->suffix) //suffix highest to lowest check
                break;
        }
        else if (prefix < curNum->prefix)  //prefix lowest to highest check
            break;
        rootNum = curNum;
        curNum = curNum->next;
    }

    //inserting the Node
    if (rootNum == nullptr) {        //Case where number is add at the front
        rootNum = new NumberNode(prefix, suffix, curNum);
        this->head = rootNum;
    }
    else                            //Case where number is added in the middle
        rootNum->next = new NumberNode(prefix, suffix, curNum);
}



//===== numNumber ==============================
/*
    Returns a count of the numbers in the linkList.
    Can get the count of numbers with the passed in prefix.

    DEFAULTS: int targetPrefix = -1
*/
//==============================================
int AreaNode::numNumber(int targetPrefix) {
    NumberNode* curNode = this->head;
    int count = 0;

    while (curNode != nullptr) {
        if (targetPrefix < 0) //counts all numbers
            count++;
        else if (curNode->prefix == targetPrefix) //counts the target prefix only
            count++;
        curNode = curNode->next;
    }

    return count;
}



//===== numPrefixNumbers ==========================
/*
    Returns the count of numbers with the passed in prefix.
    An alias for numNumber()'s prefix option.
*/
//=================================================
int AreaNode::numPrefixNumbers(int targetPrefix) {
    return this->numNumber(targetPrefix);
}





//===== search =======================================
/*
    Returns true if the number was found in the list,
    and false other wise. Allows for searching prefix
    only if parameter suffix is left empty.

    DEFAULTS: int suffix = -1
*/
//====================================================
bool AreaNode::search(int prefix, int suffix) {
    NumberNode* curNode = this->head;

    while (curNode != nullptr && curNode->prefix <= prefix) {
        if (curNode->prefix == prefix)
            if (curNode->suffix == suffix || suffix < 0)
                return true;
        curNode = curNode->next;
    }

    return false;
}


//===== isEmpty ==========================
/*
    returns true if list is empty.
*/
//========================================
bool AreaNode::isEmpty() {
    return this->head == nullptr;
}


//===== getNumber =========================
/*
    Returns a ptr to the PhoneNumber in the
    list if found, else returns nullptr. 
    If a prefix is only provided, will return
    first instance of it.

    DEFAULTS: int suffix = -1
*/
//=========================================
NumberNode* AreaNode::getNumber(int prefix, int suffix) {
    NumberNode* curNode = this->head;

    while (curNode != nullptr) {
        if (curNode->prefix == prefix) {
            if (suffix == curNode->suffix || suffix < 0)
                return curNode;
        }
        curNode = curNode->next;
    }
    
    return nullptr;
}
