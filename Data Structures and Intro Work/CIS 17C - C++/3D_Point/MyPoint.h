#ifndef MY_POINT_H
#define MY_POINT_H
#include<math.h>


class MyPoint {
protected:
	double x, y;

public:
	MyPoint();
	MyPoint(double newX, double newY);

	double getX() const { return x; }
	double getY() const { return y; }
	double distance(MyPoint& p2);
};

#endif
