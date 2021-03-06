#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Child.h"
#include "General.h"

/**************************************************/
/*             Read a Child from a file           */
/**************************************************/
void readChild(FILE* fp, Child* pChild) {
	//Child ID
	fscanf(fp, "%d", &pChild->id);
	fscanf(fp, "%d", &pChild->age);
}

/**************************************************/
/*            show a Child				           */
/**************************************************/
void showChild(const Child* pChild) {
	printf("\nID:%d  ", pChild->id);
	printf("Age:%d  ", pChild->age);
}

/**************************************************/
void getChildFromUser(Child* pChild, int id)
/**************************************************/
/**************************************************/
{
	pChild->id = id;

	puts("\nAge:\t");
	scanf("%d", &pChild->age);
}

/**************************************************/
/*Write a Child to the open file				*/
/**************************************************/
void writeChild(FILE* fp, const Child* pChild) {
	fprintf(fp, "%d %d\n", pChild->id, pChild->age);
}

int findChildById(Child** pChildList, int count, int id) {
	Child childToFind = { id, 0 };
	Child* pChild = &childToFind;
	Child** result;
	qsort(pChildList, count, sizeof(Child*), compareChildrenById);
	result = (Child**) bsearch(&pChild, pChildList, count, sizeof(Child*),
			compareChildrenById);
	if (result) {
		return result - pChildList;
	}
	return -1;
}

void birthday(Child* pChild) {
	pChild->age++;
}

//void	releaseChild(Child* pChild)
//{
//	//nothing to release
//}

void writeChildrenArrBinary(FILE* fp, Child** arr, int size) {
	int i;
	for (i = 0; i < size; i++) {
		writeChildBinary(fp, arr[i]);
	}

}

void writeChildBinary(FILE* fp, Child* pChild) {
	Byte res[2];
	res[0] = (pChild->id) & 0xFF;
	res[1] = (pChild->age << 5) | ((pChild->id >> 8) & 0x1F);
	fwrite(&res[0], sizeof(Byte), 1, fp);
	fwrite(&res[1], sizeof(Byte), 1, fp);
}

void childFromBinary(FILE* file, Child* res) {
	if (!file) {
		return;
	}
	Byte childBytes[2];
	fread(childBytes, sizeof(Byte), 2, file);
	int age = (childBytes[1] >> 5) & 0x7;
	int id = (childBytes[1] & 0x1F) << 8 | childBytes[0];
	res->age = age;
	res->id = id;
}

void sortChidrenArrById(Child** childrenArr, int size) {
	insertionSort(childrenArr, size, sizeof(Child*), compareChildrenById);
}

int compareChildrenById(const void* cmp1, const void* cmp2) {
	Child* child1 = *(Child**) cmp1;
	Child* child2 = *(Child**) cmp2;
	if (child1->id == child2->id) {
		return 0;
	}
	return child1->id > child2->id ? 1 : -1;
}
