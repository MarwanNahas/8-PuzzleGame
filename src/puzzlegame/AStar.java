/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author CFast
 */
public class AStar {
     PriorityQueue<GameState> priorityqueue = new PriorityQueue<>(new Comparator<GameState>() {
            
            @Override
            public int compare(GameState g1, GameState g2) {
                
                return g1.function-g2.function; 
            }
     });
   
     
     
     
     
 
     
     
    ArrayList<GameState> Visited = new ArrayList<GameState>();
// The parent will bee added in main in the object
  //  public void CreateAStar(GameState Root){
     public void CreateAStar(GameState Root){
       
         
         
         
        GameState[] Expanded = new GameState[4];
        priorityqueue.add(Root);
        while(!priorityqueue.isEmpty()){   
            GameState State;
            State=priorityqueue.remove();
            this.Visited.add(State);
            
            int[] [] goalstate;
            goalstate = new int [][] {{0,1,2},{3,4,5},{6,7,8}};
            boolean check;
            check=State.CompareInitialToGoal(State, goalstate);
            if(check==false){
                Expanded=State.TryReachGoal(State);
                GameState GetFromPQ;
                for(int i =0;i<4;i++){  
                   if(Expanded[i]!=null){
                       boolean f = true;
                       for(int j = 0 ; j<this.Visited.size();j++){  
                           f=true;
                             f=State.Compare2GameStates(Expanded[i].b, this.Visited.get(j).b);
                            if(f==true){
                               break;
                           }
                       }
                       if(f==false){ 
                           if(priorityqueue.isEmpty()){
                               priorityqueue.add(Expanded[i]);
                           }
                           else{
                               boolean check2;
                               check2=true;
                            for (GameState item: priorityqueue){
                                check2=true;
                               check2=State.Compare2GameStates(Expanded[i].b,item.b);
                               if(check2==true){
                                   GetFromPQ = new GameState(item.b.DoubleArray,item.hierch);
                                          GetFromPQ.depth=item.depth;
                                          GetFromPQ.parent=item.parent;
                                          GetFromPQ.function=item.function;
                                          priorityqueue.remove(item);
                                          if(GetFromPQ.function>=Expanded[i].function){
                                              priorityqueue.add(Expanded[i]);
                                          }
                                          else{
                                              priorityqueue.add(GetFromPQ);
                                          }
                                   break;
                               }  
                           }
                            if(check2==false){
                                
                                priorityqueue.add(Expanded[i]);
                            }
                            
                           }
                           
                       } 
                    }
                }
            }
            else{
                return;
            }
        }
    }


                public void path(){
                   GameState InitialGame = new GameState(this.Visited.get(0).b.DoubleArray,this.Visited.get(0).hierch);
                   GameState temp = new GameState(this.Visited.get(this.Visited.size()-1).b.DoubleArray,this.Visited.get(this.Visited.size()-1).hierch);
                   temp.parent=this.Visited.get(this.Visited.size()-1).parent;
                   int counter=0;
                    System.out.println("------Print Path--------");
                   while(!temp.b.DoubleArray.equals(InitialGame.b.DoubleArray)){
                       temp.b.printboard();
                       temp=temp.parent;
                       counter++;
                   }
                    InitialGame.b.printboard();
                    System.out.println("-------Path Ended--------");
                    System.out.println("Path Cost is = " + counter);
                    System.out.println("Goal Depth is = " + counter);
                    }
                
                public void printvisited(){
                    System.out.println("------Print Expanded Nodes--------");
                    int i =0;
                    while(i<this.Visited.size()){ 
                        this.Visited.get(i).b.printboard(); 
                        i++;
                    }
                    System.out.println("------Expanded Nodes Ended--------");
                }
}
             

