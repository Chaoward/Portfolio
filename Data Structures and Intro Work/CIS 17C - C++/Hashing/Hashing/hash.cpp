#ifndef HASH_CPP
#define HASH_CPP

#include "hash.h"


Hash::Hash() {
	this->size = 0;
	for (int i = 0; i < capacity; ++i)
		this->hashTable[i] = nullptr;
}


//Hashes the key and returns index number
int Hash::hash(std::string& key) {
	int num = 0;
	for (char c : key)
		num += int(c);
	return num % capacity;
}



int Hash::getSize() { 
	return this->size; 
}


//return -1 if item does not exist
int Hash::indexOf(std::string key) {
	if (get(key) == "\0")
		return -1;
	return hash(key);
}


// returns true if successfully modified, false if item does not exist
bool Hash::set(std::string key, std::string val) {
	item* curItem = this->hashTable[hash(key)];

	while (curItem->key != key && curItem != nullptr)
		curItem = curItem->next;
	if (curItem->key == key) {
		curItem->value = val;
		return true;
	}
	
	return false;
}



bool Hash::add(std::string key, std::string val) {
	int index = hash(key);

	if (this->hashTable[index] == nullptr) {
		this->hashTable[index] = new item;
		this->hashTable[index]->key = key;
		this->hashTable[index]->value = val;
	}
	else if (this->hashTable[index]->key == key) { //does not add dup keys
		return false;
	}
	else {
		item* curItem = this->hashTable[index];
		while (curItem->next != nullptr) {
			curItem = curItem->next;
			if (curItem->key == key) return false; //dup check
		}
		curItem->next = new item;
		curItem = curItem->next;
		curItem->key = key;
		curItem->value = val;
	}

	this->size++;
	return true;
}




bool Hash::remove(std::string key) {
	int index = hash(key);
	item* curItem = this->hashTable[index];
	item* rootItem = nullptr;

	while (curItem != nullptr) {
		if (curItem->key == key) {
			if (rootItem != nullptr)
				rootItem->next = curItem->next != nullptr ? curItem->next : nullptr;
			delete curItem;
			this->size--;
			return true;
		}
		rootItem = curItem;
		curItem = curItem->next;
	}

	return false;
}




std::string Hash::get(std::string key) {
	item* curItem = this->hashTable[hash(key)];
	
	while (curItem->key != key && curItem != nullptr)
		curItem = curItem->next;
	
	return curItem == nullptr ? NULL : curItem->value;  //return null if item is not found
}




int Hash::numberOfItemsInIndex(int index) {
	if (index >= this->capacity || index < 0) return -1; //Out of Index check
	int count = 0;
	item* curItem = this->hashTable[index];

	while (curItem != nullptr) {
		count++;
		curItem = curItem->next;
	}

	return count;
}


//prints first item in a bucket and item count for each bucket
void Hash::printTable() {
	int count;

	std::cout << "//========== HashTable ==========\\\\" << std::endl;
	for (int i = 0; i < this->capacity; ++i) {
		count = numberOfItemsInIndex(i);
		std::cout << "Index: " << i << std::endl
			<< "Key: " << (this->hashTable[i] == nullptr ? "empty" : this->hashTable[i]->key) << std::endl
			<< "Value: " << (this->hashTable[i] == nullptr ? "empty" : this->hashTable[i]->value) << std::endl
			<< "# of items in index: " << count << std::endl
			<< "//===============================\\\\" << std::endl;
	}
}


//prints all items in one bucket
void Hash::printIndexItems(int index) {
	if (index >= this->capacity || index < 0)  //Out of Index check
		throw std::out_of_range("Out of Index");
	item* curItem = this->hashTable[index];

	if (curItem == nullptr)
		std::cout << "Empty at index " << index << std::endl;
	else {
		std::cout << "//===== Items at Index " << index << " =====\\\\" << std::endl;
		while (curItem != nullptr) {
			std::cout << "Key: " << curItem->key << std::endl
				<< "Value: " << curItem->value << std::endl
				<< "//============================\\\\" << std::endl;
			curItem = curItem->next;
		}
	}
}


#endif
