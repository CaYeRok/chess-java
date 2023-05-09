/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess_game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author ondra havranek
 */
public class Chess_game {
static LinkedList<piece> p=new LinkedList<>();
public static piece vybrany = null; 
public static piece cil=null;
public static int ww=0, bw=0;
public static boolean bilynatahu;

public static void start(){
    new piece(4, 7, "king", true, p);
        new piece(3, 7, "queen", true, p);
        new piece(2, 7, "bishop", true, p);
        new piece(5, 7, "bishop", true, p);
        new piece(1, 7, "knight", true, p);
        new piece(6, 7, "knight", true, p);
        new piece(0, 7, "rook", true, p);
        new piece(7, 7, "rook", true, p);
        new piece(0, 6, "pawn", true, p);
        new piece(1, 6, "pawn", true, p);
        new piece(2, 6, "pawn", true, p);
        new piece(3, 6, "pawn", true, p);
        new piece(4, 6, "pawn", true, p);
        new piece(5, 6, "pawn", true, p);
        new piece(6, 6, "pawn", true, p);
        new piece(7, 6, "pawn", true, p);
        
        new piece(4, 0, "king", false, p);
        new piece(3, 0, "queen", false, p);
        new piece(2, 0, "bishop", false, p);
        new piece(5, 0, "bishop", false, p);
        new piece(1, 0, "knight", false, p);
        new piece(6, 0, "knight", false, p);
        new piece(0, 0, "rook", false, p);
        new piece(7, 0, "rook", false, p);
        new piece(0, 1, "pawn", false, p);
        new piece(1, 1, "pawn", false, p);
        new piece(2, 1, "pawn", false, p);
        new piece(3, 1, "pawn", false, p);
        new piece(4, 1, "pawn", false, p);
        new piece(5, 1, "pawn", false, p);
        new piece(6, 1, "pawn", false, p);
        new piece(7, 1, "pawn", false, p);
}


    public static void main(String[] args) throws IOException {
        bilynatahu =true;
        BufferedImage all=ImageIO.read(new File("chess.png"));
        Image imgs[]=new Image[12];
       int ind=0;
        for(int y=0;y<400;y+=200){
        for(int x=0;x<1200;x+=200){
            imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
       ind++;
        }
        } 
       
        
        JFrame frame = new JFrame(); 
        frame.setBounds(10, 10, 562, 512);
        frame.setUndecorated(true);
        JTextField black = new JTextField("Cerny: "+bw);
        black.setBounds(512,100, 50,30);
        black.setEditable(false);
        JTextField white = new JTextField("Bily: "+ ww );
        white.setBounds(512,412, 50,30);
        white.setEditable(false);
        frame.add(white);
        frame.add(black);
        JPanel panel=new JPanel(){
            @Override
            public void paint(Graphics g){
                boolean white=true;
                for(int x=0; x<8; x++){
                    for(int y=0;y<8; y++){
                        if(white){
                    g.setColor(new Color(235,235, 208));
                }else{
                    g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x*64,y*64, 64, 64);
                    white=!white;
                    }
                    white=!white;
                }
                    for(piece P: p){
                        int ind=0;
                        if(P.typ.equalsIgnoreCase("king")){
                            ind=0;
                        }
                        if(P.typ.equalsIgnoreCase("queen")){
                            ind=1;
                        }
                        if(P.typ.equalsIgnoreCase("bishop")){
                            ind=2;
                        }
                        if(P.typ.equalsIgnoreCase("knight")){
                            ind=3;
                        }
                        if(P.typ.equalsIgnoreCase("rook")){
                            ind=4;
                        }
                        if(P.typ.equalsIgnoreCase("pawn")){
                            ind=5;
                        }
                        if(!P.iswhite){
                            ind +=6;
                        }
                        g.drawImage(imgs[ind], P.x, P.y, this);
                    }
                
            }
        };
        
        frame.add(panel); 
        start();
        frame.addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent e) {
                if(vybrany!=null){
                    vybrany.x=e.getX()-32;
                    vybrany.y=e.getY()-32;
                    frame.repaint();
                }
                frame.repaint();
            
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            
            }
                } );
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
               vybrany=getpiece(e.getX(), e.getY());
               if(vybrany.iswhite != bilynatahu){
                  vybrany = null; 
               }
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int proslo;
                cil=getpiece(e.getX(), e.getY());
                int border = e.getX();
                if(border> 513){
                    vybrany.vrat(vybrany.origx, vybrany.origy);
                    frame.repaint();
                } else if(cil==null || (cil.iswhite != vybrany.iswhite && cil.pozx < 8)){
                    
                   proslo = vybrany.pohyb(e.getX()/64, e.getY()/64);
                 frame.repaint(); 
                 if(proslo==1){
                     bilynatahu = !bilynatahu;
                 }else if(proslo>1){
                     if(proslo ==2){
                         bilynatahu = true;
                         bw++;
                           black.setText("Black: "+bw);
                           for(int pom1=0; pom1<=7;pom1++){
                              for(int pom2=0; pom2<=7;pom2++){ 
                                  if(getpiece(pom1*64, pom2*64)!=null){
                                     getpiece(pom1*64, pom2*64).smaz();
                                  }
                              }
                           }
                           start();
                     }else{
                         bilynatahu = true;
                         ww++;
                         white.setText("White: "+ww);
                         for(int pom1=0; pom1<=7;pom1++){
                              for(int pom2=0; pom2<=7;pom2++){ 
                                  if(getpiece(pom1*64, pom2*64)!=null){
                                     getpiece(pom1*64, pom2*64).smaz();
                                  }
                              }
                           }
                         start();
                     }
                 }
                }
                else {
                    vybrany.vrat(vybrany.origx, vybrany.origy);
                frame.repaint(); 
                }
            
             
                   
                }
                
            

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
             }
        } );
        
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    public static piece getpiece(int x, int y){
       int pozx=x/64;
       int pozy=y/64;
       
       for(piece P: p){
           if(P.pozx==pozx && P.pozy==pozy){
               P.origx=pozx;
               P.origy=pozy;
               return P;
           }
       }
       return null;
    }
   
    
    }