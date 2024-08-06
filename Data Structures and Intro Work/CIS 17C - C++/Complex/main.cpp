#include<iostream>
#include "Complex.h"
using namespace std;


int main() {
	Complex c1, c2;

	cin >> c1;
	cin >> c2;

	cout << c1 << " + " << c2 << " = " << *&(c1 + c2) << endl;
	cout << c1 << " - " << c2 << " = " << *&(c1 - c2) << endl;
	cout << c1 << " * " << c2 << " = " << *&(c1 * c2) << endl;
	cout << c1 << " / " << c2 << " = " << *&(c1 / c2) << endl;
	cout << "|" << c1 << "|" << " = " << c1.abs() << endl;
	cout << "|" << c2 << "|" << " = " << c2.abs() << endl;

	return 0;
}