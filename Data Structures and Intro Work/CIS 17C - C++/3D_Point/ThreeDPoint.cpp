#include "ThreeDPoint.h"


ThreeDPoint::ThreeDPoint() {
	z = 0;
	//MyPoint constructor handles x and y
}


ThreeDPoint::ThreeDPoint(double newX, double newY, double newZ) {
	x = newX;
	y = newY;
	z = newZ;
}


double ThreeDPoint::distance(MyPoint& p2) const {
	ThreeDPoint* ptrP = static_cast<ThreeDPoint*>(&p2);
	return sqrt(pow(ptrP->getZ() - z, 2) + pow(ptrP->getX() - x, 2) + pow(ptrP->getY() - y, 2));
}