#ifndef NODE_H
#define NODE_H

template<typename T>
class Node {
public:
	T element;
	Node* next;

	Node() {
		next = nullptr;
	}

	Node() {
		this->element = element;
		next = nullptr;
	}
};

#endif
