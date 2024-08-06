#ifndef HASH_H
#define HASH_H

#include<iostream>
#include<string>

//Using strings as both keys, and values
class Hash {
private:
	static const int capacity = 10;
	struct item {
		std::string key;
		std::string value;
		item* next = nullptr;
	};

	int size;
	item* hashTable[capacity];

	//returns the index for searching the hashTable
	int hash(std::string& key);

public:
	Hash();

	int getSize();
	int indexOf(std::string key);
	bool set(std::string key, std::string val);
	bool add(std::string key, std::string val);
	bool remove(std::string key);
	std::string get(std::string key);
	int numberOfItemsInIndex(int index);
	void printTable();
	void printIndexItems(int index);

};

#endif
