#include<iostream>
#include<random>
#include<time.h>
using namespace std;

void quickSort(int* array, int SIZE);
void sortPivot(int* array, const int& SIZE);

void display(int* array, const int& SIZE) {
    for (int i = 0; i < SIZE; ++i)
        cout << array[i] << " ";
    cout << endl;
}


int main() {
    const int SIZE = 8;
    int array[SIZE];
    srand(time(0));
    for (int i = 0; i < SIZE; ++i) {
        array[i] = (rand() % 100) + 1;
        cout << array[i] << " ";
    }
    cout << "\n\n";

    quickSort(array, SIZE);

    for (int i = 0; i < SIZE; ++i)
        cout << array[i] << " ";
    cout << endl;

	return 0;
}



void quickSort(int* array, int SIZE) {
    if (SIZE <= 2) {
        if (array[0] > array[1]) {
            int temp = array[0];
            array[0] = array[1];
            array[1] = temp;
        }
        return;
    }
    //sortPivot(array, SIZE);
	//int* pivot = array + (SIZE / 2) - 1;

    //int* low = array;
    //int* high = array + SIZE - 2;
    //swap pivot
    //int temp = *pivot;
    //*pivot = *high;
    //*high = temp;


    //sort 2
    int i = -1;
    for (int j = 0; j < SIZE - 1; ++j) {
        if (array[j] < array[SIZE - 1]) {
            i++;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    int pivot = array[SIZE - 1];
    for (int j = SIZE - 1; j > i; --j)
        array[j] = array[j - 1];
    array[i + 1] = pivot;

    quickSort(array, i + 1);
    quickSort((array + i + 2), SIZE - i + 1);

    //sort
    /*while (high > low) {

        if (*low > *pivot && *high < *pivot) {
            temp = *low;
            *low = *high;
            *high = temp;
        }
        else if (*high > * pivot)
            high--;
        else if (*low < *pivot)
            low++;
    }
    temp = *pivot;
    *pivot = *high;
    *high = temp;*/
}

/*
void sortPivot(int* array, const int& SIZE) {
	if (SIZE == 1)
		return;

	//sorts the first middle and last values
	int* temp[3] = { array, array + (SIZE / 2) - 1, array + SIZE - 1 };
    bool nextPassNeeded = true;
    for (int k = 0; k < 3 && nextPassNeeded; k++) {
        nextPassNeeded = false;
        for (int i = 0; i < 2; i++) {
            if (*temp[i] > *temp[i + 1]) {
                int val = *temp[i];
                *temp[i] = *temp[i + 1];
                *temp[i + 1] = val;
                nextPassNeeded = true;
            }
        }
    }
}
*/
