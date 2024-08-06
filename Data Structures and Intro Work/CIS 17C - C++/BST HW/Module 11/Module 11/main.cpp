#include "BST.cpp"
using namespace std;

int main() {
	BST<int> tree;
	
	for (int i = 0; i < 5; ++i) {
		tree.add(i + 1);
	}
	int num = 4;
	cout << endl << tree.search(num) << endl;

	return 0;
}