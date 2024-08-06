#ifndef BST_H
#define BST_H
#include<iostream>

template<typename T>
struct Node {
	Node<T>* parent = nullptr; //21.7
	Node<T>* right = nullptr;
	Node<T>* left = nullptr;
	T value;
};

template<typename T>
class BST {
private:
	Node<T> parentRoot;
	int size;

	int _height(const Node<T>* curNode);
	void _inorder(const Node<T>* curNode);

public:
	BST() {
		size = 0;
	}

	void add(const T val);
	void add(const T& val);
	bool remove(const T val);
	bool remove(const T& val);
	bool search(const T val);
	bool search(const T& val);
	int height();
	void inorder();
};

#endif

