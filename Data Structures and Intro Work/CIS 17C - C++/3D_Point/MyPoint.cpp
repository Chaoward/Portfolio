#include "MyPoint.h"

MyPoint::MyPoint() {
	x = 0;
	y = 0;
}


MyPoint::MyPoint(double newX, double newY) {
	x = newX;
	y = newY;
}


double MyPoint::distance(MyPoint& p2) {
	return sqrt( pow(p2.getX() - x, 2) + pow(p2.getY() - y, 2) );
}
