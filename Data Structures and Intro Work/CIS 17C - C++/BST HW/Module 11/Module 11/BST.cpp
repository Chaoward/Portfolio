#ifndef BST_CPP
#define BST_CPP
#include "BST.h"

template<typename T>
void BST<T>::add(const T val) {
	add(&val);
}

template<typename T>
void BST<T>::add(const T& val) {
	if (this->size == 0) {
		this->parentRoot.value = val;
	}
	Node<T>* curNode = &this->parentRoot;
	Node<T>* root = nullptr;

	while (curNode != nullptr) {
		std::cout << curNode << std::endl; 
		if (val > curNode->value) {
			root = curNode;
			curNode = curNode->right;
		}
		else if (val < curNode->value) {
			root = curNode;
			curNode = curNode->left;
		}
		else {
			curNode->value = val;
			this->size++;
			return;
		}
	}

	this->size++;
	curNode = new Node<T>;
	curNode->value = val;
	curNode->parent = root;
	if (val > root->value)
		root->right = curNode;
	else
		root->left = curNode;
}



template<typename T>
bool BST<T>::remove(const T& val) {
	Node<T>* curNode = &this->parentRoot;
	bool searching = true;

	while (searching) {
		if (val > curNode->value)
			curNode = curNode->right;
		else if (val < curNode->value)
			curNode = curNode->left;
		else
			searching = false;
	}

	/*while (true) { //WIP
		if ((curNode->left & curNode->right) == nullptr) {
			curNode->parent->left = nullptr;
			delete curNode;
			return true;
		}
		else if () {

		}
	}*/
}



//21.1
template<typename T>
bool BST<T>::search(const T& val) {
	Node<T>* curNode = &this->parentRoot;
	
	while (curNode != nullptr) {
		std::cout << curNode->value << " ";
		if (val > curNode->value)
			curNode = curNode->right;
		else if (val < curNode->value)
			curNode = curNode->left;
		else
			return true;
	}

	return false;
}



//21.3
template<typename T>
int BST<T>::height() {
	return _height(&this->parentRoot);
}

template<typename T>
int BST<T>::_height(const Node<T>* curNode) {
	if (curNode == nullptr)
		return 0;
	int leftHeight = height(curNode->left);
	int rightHeight = height(curNode->right);
	return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
}



//21.4
template<typename T>
void BST<T>::inorder() {
	_inorder(&parentRoot);
}

template<typename T>
void BST<T>::_inorder(const Node<T>* curNode) {
	if (curNode->left == nullptr) {
		std::cout << curNode->value << " ";
	}
	else {
		_inorder(curNode->left);
		std::cout << curNode->value << " ";
	}

	if (curNode->right != nullptr)
		_inorder(curNode->right);
	return;
}


#endif