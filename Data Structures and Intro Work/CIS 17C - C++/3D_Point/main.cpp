#include<iostream>
#include "ThreeDPoint.h"
using namespace std;


int main() {
	ThreeDPoint p1;
	ThreeDPoint p2(10, 30, 25.5);

	cout << "Distance between points: " << p1.distance(p2) << endl;

	return 0;
}