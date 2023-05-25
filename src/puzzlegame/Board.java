/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

/**
 *
 * @author CFast
 */
public class Board {
    int[][] DoubleArray;

    public Board() {
        this.DoubleArray =new int[3][3];
    }


 
    
    public void printboard(){
        
               for(int i = 0 ; i<3;i++){
                   System.out.print("|");
                   for(int j = 0;j<3;j++){
                       System.out.print(this.DoubleArray[i][j]);     
                   } 
                   System.out.print("|");
                   System.out.println("");
                   
               }
               
               System.out.println("-----");
           }
}

   