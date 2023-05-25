/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author CFast
 */
public class PuzzleGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       long startTime = System.currentTimeMillis();
        int [] [] InitialGame = new int [3][3];
        
        System.out.println("Enter Your Board");
        
        Scanner x = new Scanner(System.in);
        String STR = x.nextLine();
        String[] parts = STR.split(",");
        int q=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                InitialGame[i][j]=Integer.parseInt(parts[q]);
                q++;
            }
        }

        System.out.println("Choose Algorithm  BFS-->1  DFS-->2  A* first hierc-->3   A* second hierc-->4");
        String number = x.nextLine();
        if(Integer.parseInt(number)==1){
            GameState Try = new GameState(InitialGame,0);
            BFS bfs = new BFS ();
            bfs.CreateBFS(Try);
            bfs.printvisited();
            bfs.path();
            }
        if(Integer.parseInt(number)==2){
          GameState Try = new GameState(InitialGame,0);
          DFS dfs = new DFS ();
          dfs.CreateDFS(Try);
          dfs.printvisited();
          dfs.path();
        }
        if(Integer.parseInt(number)==3){
            GameState Try = new GameState(InitialGame,1);
            AStar a = new AStar();
            a.CreateAStar(Try);
            a.printvisited();
            a.path();
        }
        if(Integer.parseInt(number)==4){
            GameState Try = new GameState(InitialGame,2);
            AStar a = new AStar();
            a.CreateAStar(Try);
            a.printvisited();
            a.path();
        }
          
       long endTime = System.currentTimeMillis();
       long timeElapsed = endTime - startTime;
       
       System.out.println("Execution time in milliseconds: " + timeElapsed);
        
      
    }
        
    
}
