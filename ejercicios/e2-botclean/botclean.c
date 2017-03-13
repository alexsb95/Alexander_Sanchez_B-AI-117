#include<stdio.h>

void move ( int toY, int toX, int fromY, int fromX, char board[5][5]) {
        if(toX < fromX){
             printf("LEFT\n"); 
        }else if(toX > fromX){
            printf ("RIGHT\n");
        }
        else if(toY < fromY){
            printf("UP\n");
        }else if (toY > fromY){
            printf ("DOWN\n");
        }else{
            printf ("CLEAN\n");
        }
    
    

        
}
void next_move(int posr, int posc, char board[5][5]) {
    int i;
    int j;
    int foundFlag = 0;
    for(j = 0; j < 5; j++){
        for(i = 0; i < 5; i++){
             if(board[j][i] == 'd'){
                foundFlag = 1;
                break;
            }
        }

        if(foundFlag == 1)
            break;

    }
    if(foundFlag == 1){
        move(j, i, posr, posc, board);

    }
    
}

int main(void) {
    int pos[2], i;
    char board[5][5], line[6];
    scanf("%d", &pos[0]);
    scanf("%d", &pos[1]);
    for(i=0; i<5; i++) {
        scanf("%s[^\\n]%*c", line);
        strncpy(board[i], line, 5);
    }
    next_move(pos[0], pos[1], board);
    return 0;
}
