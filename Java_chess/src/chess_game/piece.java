/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess_game;

import java.util.LinkedList;

/**
 *
 * @author ondra
 */
public class piece {
    int origx, origy;
    int pozx, pozy;
    int x,y;
    String typ;
    boolean iswhite;
    LinkedList<piece> p;
    boolean prvnitah;

    public piece(int pozx, int pozy, String typ, boolean iswhite, LinkedList<piece> p) {
        this.pozx = pozx;
        this.pozy = pozy;
        this.x=pozx*64;
        this.y=pozy*64;
        this.typ= typ;
        this.iswhite = iswhite;
        this.p=p;
        this.prvnitah=true;
        p.add(this);
    }
    public void vrat(int pozx, int pozy){
         this.pozx=pozx;
        this.pozy=pozy;
        x=pozx*64;
        y=pozy*64;
    }
    public int pohyb(int pozx, int pozy){
        int x=1;
        boolean projde;
        boolean projde2;
        if(typ.equalsIgnoreCase("pawn")){
          projde = pawn(pozx, pozy);
          if(projde){
              if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
            x = Chess_game.getpiece(pozx*64, pozy*64).seber();
        }      
                polozeni(pozx, pozy);
                prvnitah = false;
                if(x!=1){
                    return x;
                }
                return 1;
            }else{
                polozeni(origx, origy);
                return 0;
            }
          }
                
        if(typ.equalsIgnoreCase("king")){
            projde=king(pozx, pozy);
            if(projde){
              if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
            x = Chess_game.getpiece(pozx*64, pozy*64).seber();
        }
                polozeni(pozx, pozy);
                prvnitah = false;
                if(x!=1){
                    return x;
                }
                return 1;
            }else{
                polozeni(origx, origy);
                return 0;
            }
        }
        if(typ.equalsIgnoreCase("knight")){
            projde = knight(pozx, pozy);
            if(projde){
              if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
            x = Chess_game.getpiece(pozx*64, pozy*64).seber();
        }      
                polozeni(pozx, pozy);
                prvnitah = false;
                if(x!=1){
                    return x;
                }
                return 1;
            }else{
                polozeni(origx, origy);
                return 0;
            }
        }
        if(typ.equalsIgnoreCase("rook")){
            projde=rook(pozx, pozy);
            if(projde){
              if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
            x = Chess_game.getpiece(pozx*64, pozy*64).seber();
        }      
                polozeni(pozx, pozy);
                prvnitah = false;
                if(x!=1){
                    return x;
                }
                return 1;
            }else{
                polozeni(origx, origy);
                return 0;
            }
        }
        if(typ.equalsIgnoreCase("bishop")){
             projde=bishop(pozx, pozy);
            if(projde){
              if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
            x = Chess_game.getpiece(pozx*64, pozy*64).seber();
        }      
                polozeni(pozx, pozy);
                prvnitah = false;
                if(x!=1){
                    return x;
                }
                return 1;
            }else{
                polozeni(origx, origy);
                return 0;
            }
        }
        if(typ.equalsIgnoreCase("queen")){
            projde=bishop(pozx, pozy);
            projde2=rook(pozx, pozy);
            if(projde || projde2){
                if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
           x = Chess_game.getpiece(pozx*64, pozy*64).seber();
        }
                polozeni(pozx, pozy);
                prvnitah = false;
                if(x!=1){
                    return x;
                }
                return 1;
            }else{
                polozeni(origx, origy);
                return 0;
            }
        }
       /* if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
            Chess_game.getpiece(pozx*64, pozy*64).seber();
        } */
      /*  this.pozx=pozx;
        this.pozy=pozy;
        x=pozx*64;
        y=pozy*64;*/
      return 0;
    }
    
    public int seber(){
        if(this.typ.equalsIgnoreCase("king")){
            if(this.iswhite){
                p.remove(this); 
                return 2;
            }else{
               p.remove(this);  
               return 3;
            }
        }
        p.remove(this);  
        return 1;
    }
    public void smaz(){
        p.remove(this);
    }
    public boolean pawn(int pozx, int pozy){
        int pom1 =1 ;
        int pom2=2;
        if(iswhite){
            pom1=-1;
            pom2=-2;
        }
         if(prvnitah){
                if(origx == pozx){
                    if(origy+pom1 == pozy || (origy+pom2 == pozy && Chess_game.getpiece(pozx*64, (origy+pom1)*64)==null)){
                        if(Chess_game.getpiece(pozx*64, pozy*64)==null){
                        return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else if((origx+1 ==pozx || origx-1 == pozx)&&(origy+pom1 == pozy)){
                    if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
                     return true;
                }else{
                      return false; 
                    }       
                }else{
                    return false;
                }
            }else{
                if(origx == pozx){
                    if(origy+pom1 == pozy){
                        if(Chess_game.getpiece(pozx*64, pozy*64)==null){
                            if(iswhite){
                                if(pozy==0){
                                    this.typ="queen";
                                }
                            }else if(pozy==7){
                                this.typ ="queen";
                            }
                        return true;
                        }else{
                            return false;
                        }
                        }else{
                       return false;
                    }
                }else if((origx+1 ==pozx || origx-1 == pozx)&&(origy+pom1 == pozy)){
                    if(Chess_game.getpiece(pozx*64, pozy*64)!=null){
                     if(iswhite){
                                if(pozy==0){
                                    this.typ="queen";
                                }
                            }else if(pozy==7){
                                this.typ ="queen";
                            }
                     return true;
                }else{
                      return false; 
                    }
                }else{
                    return false;
                }
        }
        
    }
    
    public boolean king(int pozx, int pozy){
        if((pozx == origx && pozy ==origy+1) || (pozx == origx && pozy == origy-1) || (pozx == origx-1 && pozy == origy)|| (pozx ==origx-1 && pozy == origy -1)||(pozx==origx-1 && pozy==origy+1)||(pozx == origx+1 && pozy == origy)||(pozx ==origx+1 && pozy == origy -1)||(pozx == origx+1 && pozy == origy+1)){
           
           return true;
           
        }else if(prvnitah){
            if(iswhite){
                if(pozx==origx-2 && pozy==7){
                    if(Chess_game.getpiece(0*64, 7*64)!=null && Chess_game.getpiece(0*64, 7*64).iswhite && Chess_game.getpiece(0*64, 7*64).prvnitah && Chess_game.getpiece(0*64, 7*64).typ.equalsIgnoreCase("rook")){
                        for(int pom=origx-1; pom>0; pom--){
                            if(Chess_game.getpiece(pom*64, 7*64)!=null){
                                return false;
                            }
                        }
                         rosada(0,7, 3, 7);
                         return true;
                    }
                }else if(pozx==origx+2 && pozy==7){
                    if(Chess_game.getpiece(7*64, 7*64)!=null && Chess_game.getpiece(7*64, 7*64).iswhite && Chess_game.getpiece(7*64, 7*64).prvnitah && Chess_game.getpiece(7*64, 7*64).typ.equalsIgnoreCase("rook")){
                        for(int pom=origx+1;pom<7;pom++){
                            if(Chess_game.getpiece(pom*64, 7*64)!=null){
                                return false;
                        }
                    }
                        rosada(7,7,5,7);
                        return true;
                }
                }
            }else{
                 if(pozx==origx-2 && pozy==0){
                     if(Chess_game.getpiece(0*64, 0*64)!=null && !Chess_game.getpiece(0*64, 0*64).iswhite && Chess_game.getpiece(0*64, 0*64).prvnitah && Chess_game.getpiece(0*64, 0*64).typ.equalsIgnoreCase("rook")){
                         for(int pom=origx-1;pom>0;pom++){
                             if(Chess_game.getpiece(pom*64, 0*64)!=null){
                                 return false;
                             }
                             rosada(0,0,3,0);
                             return true;
                         }
                     }
                 }else if(pozx==origx+2 && pozy==0){
                     if(Chess_game.getpiece(7*64, 0*64)!=null && !Chess_game.getpiece(7*64, 0*64).iswhite && Chess_game.getpiece(7*64, 0*64).prvnitah && Chess_game.getpiece(7*64, 0*64).typ.equalsIgnoreCase("rook")){
                         for(int pom=origx+1; pom<7;pom++){
                             if(Chess_game.getpiece(pom*64, 0*64)!=null){
                                 return false;
                             }
                             rosada(7,0,5,0);
                             return true;
                         }
                     }
                 }
                
            }
            
            
        }
        return false;
    }
    
    public boolean knight(int pozx, int pozy){
        return (((origx==pozx-2)||(origx == pozx+2)) && ((origy == pozy+1)||(origy == pozy-1)))||(((origy==pozy-2)||(origy==pozy+2))&&((origx==pozx-1)||(origx==pozx+1)));
    }
    
    public boolean rook(int pozx, int pozy){
        if(pozx != origx && pozy!=origy){
            return false;
        }
        if(pozx == origx){
            if(pozy>origy){
            for(int pom=origy+1; pom<pozy; pom++){
                if(Chess_game.getpiece(pozx*64, pom*64)!=null){
                return false;
            }
            }
            }else{
                for(int pom=origy-1; pom>pozy; pom--){
                if(Chess_game.getpiece(pozx*64, pom*64)!=null){
                return false;
            }}}
        }else if(pozy == origy){
            if(pozx>origx){
            for(int pom=origx+1; pom<pozx; pom++){
                if(Chess_game.getpiece(pom*64, pozy*64)!=null){
                return false;
            }
            }
            }else{
                for(int pom=origx-1; pom>pozx; pom--){
                if(Chess_game.getpiece(pom*64, pozy*64)!=null){
                return false;
            }}}
        }
        return true;
    }
    
    public boolean bishop(int pozx, int pozy){
        int test1 = pozx-origx;
        int test2 = pozy-origy;
        int pom1;
        int pom2;
       if(test1 != test2 && test1 != -test2){
         return false;  
       }
        if(pozx>origx && pozy>origy){
            pom2=origy+1;
            for(pom1=origx+1; pom1<pozx; pom1++){
                 if(Chess_game.getpiece(pom1*64, pom2*64)!=null){
                     return false;
                 }
                 pom2++;
            }
        }else if(pozx>origx && pozy<origy){
            pom2=origy-1;
            for(pom1=origx+1;pom1<pozx;pom1++){
              if(Chess_game.getpiece(pom1*64, pom2*64)!=null){
                     return false;
                 }
              pom2--;
            }
        }else if(pozx<origx && pozy>origy){
            pom2=origy+1;
            for(pom1=origx-1;pom1>pozx;pom1--){
              if(Chess_game.getpiece(pom1*64, pom2*64)!=null){
                     return false;
                 }
              pom2++;
            }
        }else if(pozx<origx && pozy<origy){
            pom2=origy-1;
            for(pom1=origx-1;pom1>pozx;pom1--){
              if(Chess_game.getpiece(pom1*64, pom2*64)!=null){
                     return false;
                 }
              pom2--;
            }
            
        }else{
            return false;
        }
        
        return true;
    }
     
        public void polozeni(int x, int y){
    this.pozx=x;
    this.pozy=y;
    this.x=x*64;
    this.y=y*64;
}
    public void rosada(int a, int b, int x, int y){
    Chess_game.getpiece(a*64, b*64).polozeni(x, y);
}
    
        //ROZDLENA PRVNI VERZE DETEKCE SACHU
    /*public boolean sach(int x, int y){
        
        this.pozx=x;
        this.pozy=y;
        this.x=x*64;
        this.y=y*64;
        if(this.iswhite){
            for(int souX =0; souX <=7; souX++){
                for(int souY=0; souY<=7; souY++){
                    if(Chess_game.getpiece(souX*64, souY*64) !=null){
                    if(Chess_game.getpiece(souX*64, souY*64).iswhite && Chess_game.getpiece(souX*64, souY*64).typ.equalsIgnoreCase("king")){
                       if(sachtest(souX, souY, true)){
                           polozeni(this.origx, this.origy);
                        return true;
                    } 
                    }
                }}
            }
        }else{
            for(int souX =0; souX <=7; souX++){
                for(int souY=0; souY<=7; souY++){
                    if(!Chess_game.getpiece(souX*64, souY*64).iswhite && Chess_game.getpiece(souX*64, souY*64).typ.equalsIgnoreCase("king")){
                       if(sachtest(souX, souY, false)){
                        polozeni(this.origx, this.origy);
                        return true;
                    } 
                    }
                }
            }
        }
        polozeni(this.origx, this.origy);
        return false;
    }    
    
    public boolean sachtest(int x, int y, boolean white){
        return testpawn(x, y, white);
    }
    
    public boolean testpawn(int x, int y, boolean white){
        if(white){
            if(!Chess_game.getpiece((x-1)*64, (y-1)*64).iswhite && Chess_game.getpiece((x-1)*64, (1-y)*64).typ.equalsIgnoreCase("pawn")){
                return true;
            }else if(!Chess_game.getpiece((x+1)*64, (y-1)*64).iswhite && Chess_game.getpiece((x+1)*64, (1-y)*64).typ.equalsIgnoreCase("pawn")){
                return true;
            }
        }
        return false;
    }
    */

}
