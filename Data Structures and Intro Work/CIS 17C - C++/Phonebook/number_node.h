
#ifndef __NUMBER_NODE_H
#define __NUMBER_NODE_H

class NumberNode 
{
  friend class AreaNode;
private:
  int prefix;
  int suffix;
  NumberNode* next;

// Do not modify anything above this line
//------------------------------------------------------------------------

// DO NOT make the phonebook class a friend class of this class

// Add additional functions/variables here:
public:
    NumberNode(int p, int s) {
        prefix = p;
        suffix = s;
        next = nullptr;
    }

    NumberNode(int p, int s, NumberNode* n) {
        prefix = p;
        suffix = s;
        next = n;
    }

    int getPrefix() { return this->prefix; }
    int getSuffix() { return this->suffix; }
    NumberNode* getNext() { return this->next; }

};

#endif
