#ifndef THREE_D_POINT_H
#define THREE_D_POINT_H
#include "MyPoint.h"

class ThreeDPoint : public MyPoint {
private:
	double z;

public:
	ThreeDPoint();
	ThreeDPoint(double newX, double newY, double newZ);

	double getZ() const { return z; }
	double distance(MyPoint& p2) const;

};

#endif