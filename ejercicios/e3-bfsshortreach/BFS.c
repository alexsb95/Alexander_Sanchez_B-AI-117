#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

typedef struct Node Node;
struct Node{
    int state;
    int cantConexions;
    int *nodeConexion;
};

typedef struct Graph Graph;
struct Graph{
    int cantNodes;
    int cantEdges;
    Node *listNode;
};

typedef struct Query Query;
struct Query{
    Graph* listGraph;
    int cantQueries;
};

Query globalQuery;


void readQuery(){
    printf("Hola");
}

void readInput(){
   int queries;
   scanf("%d", &queries);

   printf( "\nYou entered: %d ",queries);
    
    /*Mayor que 0 y menor que 10*/
   if(48 <= queries <= 57){
       globalQuery.cantQueries = queries;
       for(int i = 0; i < queries; i++){
           readQuery();
       }
   }

}

int main() {

    readInput();

    return 0;
}
