#ifndef COMPLEX
#define COMPLEX
#include<iostream>
#include<cmath>
#include<string>

class Complex {
private:
	double a;
	double b;
public:
	Complex() {
		a = 0;
		b = 0;
	}

	Complex(double x, double y) {
		a = x;
		b = y;
	}

	Complex(double x) {
		a = x;
		b = 0;
	}

	Complex add(Complex& c2);
	Complex subtract(Complex& c2);
	Complex multiply(Complex& c2);
	Complex divide(Complex& c2);
	double abs();
	std::string toString();

	double getRealPart() { return a; }
	double getImaginaryPart() { return b; }

	double operator [](int i);
	//basic math
	Complex operator +(Complex& c2);
	Complex operator -(Complex& c2);
	Complex operator *(Complex& c2);
	Complex operator /(Complex& c2);
	//math and assign
	Complex operator +=(Complex& c2);
	Complex operator -=(Complex& c2);
	Complex operator *=(Complex& c2);
	Complex operator /=(Complex& c2);
	//unary
	void operator +();
	Complex operator -();
	//inc/dec
	Complex operator ++();
	Complex operator ++(int dummy);
	Complex operator --();
	Complex operator --(int dummy);
	//conditionals
	bool operator <(Complex& c2);
	bool operator >(Complex& c2);
	bool operator <=(Complex& c2);
	bool operator >=(Complex& c2);
	bool operator !=(Complex& c2);
	bool operator ==(Complex& c2);
	//iostream
	friend std::istream& operator >>(std::istream& in, Complex& complex) {
		std::cout << "Enter the a value for the complex number: ";
		in >> complex.a;
		std::cout << "Enter the b value for the complex number: ";
		in >> complex.b;
		return in;
	}

	friend std::ostream& operator <<(std::ostream& out, Complex& complex) {
		if (complex.a != 0 && complex.b != 0) {
			out << "(" << (complex.a == 0 ? 0 : complex.a) << " + " << (complex.b == 0 ? 0 : complex.b) << "i)";
		}
		else {
			out << 0;
		}

		return out;
	}
};

#endif  
