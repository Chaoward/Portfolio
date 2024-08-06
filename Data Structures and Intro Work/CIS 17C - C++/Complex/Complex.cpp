#include "Complex.h"

Complex Complex::add(Complex& c2) {
	this->a += c2.getRealPart();
	this->b += c2.getImaginaryPart();
	return *this;
}

Complex Complex::subtract(Complex& c2) {
	this->a -= c2.getRealPart();
	this->b -= c2.getImaginaryPart();
	return *this;
}

Complex Complex::multiply(Complex& c2) {
	this->a = (a * c2.getRealPart()) - (b * c2.getImaginaryPart());
	this->b = (a * c2.getRealPart()) + (b * c2.getImaginaryPart());
	return *this;
}

Complex Complex::divide(Complex& c2) {
	double denom = c2.getRealPart() * c2.getRealPart() + c2.getImaginaryPart() * c2.getImaginaryPart();
	this->a = ( (a * c2.getRealPart()) + (b * c2.getImaginaryPart()) ) / denom;
	this->b = ((b * c2.getRealPart()) - (a * c2.getImaginaryPart())) / denom;
	return *this;
}

double Complex::abs() {
	return sqrt(a * a + b * b);
}

std::string Complex::toString() {
	return std::to_string(round(a)) + " + " + std::to_string(round(b)) + "i";
}


//Overloading Operators
double Complex::operator [](int i) {
	return i == 0 ? this->a : this->b;
}

//basic math
Complex Complex::operator +(Complex& c2) {
	Complex newValue(this->a, this->b);
	return newValue.add(c2);
}

Complex Complex::operator -(Complex& c2) {
	Complex newValue(this->a, this->b);
	return newValue.subtract(c2);
}

Complex Complex::operator *(Complex& c2) {
	Complex newValue(this->a, this->b);
	return newValue.multiply(c2);
}

Complex Complex::operator /(Complex& c2) {
	Complex newValue(this->a, this->b);
	return newValue.divide(c2);
}

//math and assign
Complex Complex::operator +=(Complex& c2) {
	return this->add(c2);
}

Complex Complex::operator -=(Complex& c2) {
	return this->subtract(c2);
}

Complex Complex::operator *=(Complex& c2) {
	return this->multiply(c2);
}

Complex Complex::operator /=(Complex& c2) {
	return this->divide(c2);
}

//unary
void Complex::operator +() {

}

Complex Complex::operator -() {
	return Complex(-this->a, -this->b);
}


//inc/dec
Complex Complex::operator ++() {
	this->a++;
	this->b++;
	Complex newValue = *this;
	return newValue;
}

Complex Complex::operator ++(int dummy) {
	Complex temp(this->a, this->b);
	this->a++;
	this->b++;
	return temp;
}

Complex Complex::operator --() {
	this->a--;
	this->b--;
	Complex newValue = *this;
	return newValue;
}

Complex Complex::operator --(int dummy) {
	Complex temp(this->a, this->b);
	this->a--;
	this->b--;
	return temp;
}


//conditionals
bool Complex::operator <(Complex& c2) {
	if (this->abs() < c2.abs())
		return true;
	return false;
}

bool Complex::operator >(Complex& c2) {
	if (this->abs() > c2.abs())
		return true;
	return false;
}

bool Complex::operator <=(Complex& c2) {
	return *this < c2 || *this == c2;
}

bool Complex::operator >=(Complex& c2) {
	return *this > c2 || *this == c2;
}

bool Complex::operator !=(Complex& c2) {
	return !(*this == c2);
}

bool Complex::operator ==(Complex& c2) {
	return this->abs() == c2.abs();
}