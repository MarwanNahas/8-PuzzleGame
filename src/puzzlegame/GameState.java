/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author CFast
 */
class GameState {
    Board b = new Board();
    GameState parent;
    int depth;
    int function;
    int hierch;
    
    public GameState(int[][] bb,int h) {
        this.b.DoubleArray = bb;
        this.parent=null;
        this.depth=0;
        this.function=0;
        this.hierch=h;
    }
    
    
    
    public boolean CompareInitialToGoal(GameState Board,int[][] GoalBoard){
        int check=1;
        for(int i =0;i<3;i++){
            if(check==0){
                break;
            }
            for(int j =0;j<3;j++){
                if (Board.b.DoubleArray[i][j]!=GoalBoard[i][j]){
                     check=0;
                     break;
                }
            }
        }
        if(check==0){
           return false;  
        }
        else{
            return true;
        } 
    }
    

    public GameState[] TryReachGoal(GameState Board){
        int zerox=0;
        int zeroy=0;
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                if (Board.b.DoubleArray[i][j]==0){
                    zerox=j;
                    zeroy=i;
                }
            }
        }
       
       int [] go;
       go=WhereZeroCanGo(zerox, zeroy);
       GameState[] Children = new GameState[4];

       if (go[0]==1){
               GameState bup=new GameState(Move(Board,zerox,zeroy,zerox,zeroy-1).b.DoubleArray,Board.hierch);
               bup.function=Board.function+1+heuristicsforall(bup.b.DoubleArray,bup.hierch);
               bup.parent=Board;     
               bup.depth=Board.depth+1;
               Children[0]=(bup);   
       }
       if (go[1]==1){
          GameState bdown = new GameState(Move(Board,zerox,zeroy,zerox,zeroy+1).b.DoubleArray,Board.hierch);
          bdown.function=Board.function+1+heuristicsforall(bdown.b.DoubleArray,bdown.hierch);
          bdown.parent=Board;
          bdown.depth=Board.depth+1;
          Children[1]=(bdown); 
       }
      if (go[2]==1){
           GameState bleft = new GameState(Move(Board,zerox,zeroy,zerox-1,zeroy).b.DoubleArray,Board.hierch);
            bleft.function=Board.function+1+heuristicsforall(bleft.b.DoubleArray,bleft.hierch);
            bleft.parent=Board;
            bleft.depth=Board.depth+1;
           Children[2]=bleft;
       }
      if (go[3]==1){ 
           GameState bright= new GameState(Move(Board,zerox,zeroy,zerox+1,zeroy).b.DoubleArray,Board.hierch);
           bright.function=Board.function+1+heuristicsforall(bright.b.DoubleArray,bright.hierch);
           bright.parent=Board;
           bright.depth=Board.depth+1;
           Children[3]=bright;
       }
      return Children;
    }
    
    public int[] WhereZeroCanGo (int zerox,int zeroy){
        int[] go;
        go = new int []{0,0,0,0};
        //UP
        if(zeroy!=0){
            go[0]=1;
        }
        //Down
        if(zeroy!=2){
            go[1]=1;
        }
        //Left
        if(zerox!=0){
            go[2]=1;
        }
        //Right
        if(zerox!=2){
            go[3]=1;
        }
        
        return go;
    }
    
    public GameState Move (GameState Board2,int zerox,int zeroy,int goalx,int goaly){
        int temp;
        int[][] temparray = new int[3][3];
        for (int i = 0 ; i < 3;i++){
            for(int j=0;j<3;j++){
                temparray[i][j]=Board2.b.DoubleArray[i][j];
            }
        }
        GameState board1=new GameState(temparray,Board2.hierch);
        temp=board1.b.DoubleArray[zeroy][zerox];
        board1.b.DoubleArray[zeroy][zerox]=board1.b.DoubleArray[goaly][goalx];
        board1.b.DoubleArray[goaly][goalx]=temp;
        return board1;
    }
    public boolean Compare2GameStates(Board Board1,Board Board2){
        int check=1;
        outerloop:
        for(int i =0;i<3;i++){
            
            for(int j =0;j<3;j++){
                if (Board1.DoubleArray[i][j]!=Board2.DoubleArray[i][j]){
                     check=0;
                     break outerloop;
                }
            }
        }
        if(check==0){
            return false;
        }
        else{
            return true;
        }    
    }
    
    public int heuristicsforeachelement(int x,int i,int j,int h){
       
       int element=x;
       int goaly;
       int goalx;
       int heuristics;
       
       if(element<3){
           
           goaly = 0;
           goalx = element;
           
       }
       else if(element<6){
           
           goaly = 1;
           goalx = element-3;
       }
       else{
           goaly = 2;
           goalx = element-6;
           }
        if(h==1){
        heuristics=abs(goaly-i)+abs(goalx-j);
        }
        else{
         heuristics=(int) sqrt(pow(goaly-i,2)+pow(goalx-j,2));
        }
       return  heuristics;
   }
   
   public int heuristicsforall(int [][]board,int h){
       
       int i;
       int j;
       int total=0;
       for(i=0;i<3;i++){
           for(j=0;j<3;j++){
               total=total+heuristicsforeachelement(board[i][j],i,j,h); 
           }
       }
       return total;
   }
} 

