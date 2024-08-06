//==========================================
/*
	name: Howard Nguyen
	assignment: Module 13
*/
//==========================================

#include "hash.h"

int main() {
	Hash table;

	table.add("joe", "5");
	table.add("dave", "4");
	table.add("jane", "3");
	table.add("Hello", "2");
	table.add("There", "1");
	table.add("kbmd", "0");
	
	table.printTable();

	std::cout << "\n\n\n";
	int index = table.indexOf("There");
	table.printIndexItems(index);
	table.remove("There");
	std::cout << "\nBucket at index " << index << " after removal:\n";
	table.printIndexItems(index);

	std::string key = "dave";
	std::cout << "\n\n\nBefore Mutating:" << std::endl
		<< "key: " << key << std::endl
		<< "value: " << table.get(key) << std::endl;
	table.set(key, "10");
	std::cout << "\nAfter Mutating:" << std::endl
		<< "key: " << key << std::endl
		<< "value: " << table.get(key) << std::endl;

	return 0;
}