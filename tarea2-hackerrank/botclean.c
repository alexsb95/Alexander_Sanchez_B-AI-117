#include<stdio.h>

void move ( int toY, int toX, int fromY, int fromX, char board[5][5]) {
        if(toX < fromX){
            for (int i = fromX; i > toX; i-- ){
                 printf("LEFT\n");
            }   
        }else{
             for (int i = fromX; i < toX; i++ ){
              printf ("RIGHT\n");
            }
        }

        if(toY < fromY){
            for (int j = fromY; j > toY; j-- ){
                 printf("UP\n");
            }   
        }else{
             for (int j = fromY; j < toY; j++ ){
              printf ("DOWN\n");
            }
        }
    
    
    printf("CLEAN\n");
        
}
void next_move(int posr, int posc, char board[5][5]) {
    int i;
    int j;
    int foundFlag = 0;
    for(i = 0; i < 5; i++){
        for(j = 0; j < 5; j++){
             if(board[j][i] == 'd'){
                board[j][i] = '-';
                foundFlag = 1;
                break;
            }
        }

        if(foundFlag == 1)
            break;

    }
    if(foundFlag == 1){
        move(j, i, posr, posc, board);
        next_move(j, i, board);

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
