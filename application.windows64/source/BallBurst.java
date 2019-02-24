import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class BallBurst extends PApplet {



final int BOTTOM_GAP = 20;

ArrayList<Bullet> bullets;
ArrayList<Bullet> deadBullets;
Cannon cannon ;
ArrayList<Ball> balls;

ArrayList<Ball> deadBalls;
ArrayList<Bullet> hittedBullets;

PImage img;
PShape s;

final int BULLET_POWER = 10;
ArrayList<Integer> colors;


public void storeColors(){
  
  colors.add(color(250,91,61));//orange
  colors.add(color(253,58,78));//red salsa
  colors.add(color(255,170,29));// bright yellow
  colors.add(color(167,244,40));// green lizard
  colors.add(color(93,172,236));// blue jeans
  colors.add(color(34,67,182));// denim blue
  colors.add(color(89,70,178));//plump purple
  colors.add(color(156,81,182));//purple plum
  colors.add(color(168,55,49));// sweet brown
  colors.add(color(255,84,112));//fiery rose
  colors.add(color(255,219,0));//sizzling sunshine
  colors.add(color(255,112,0));// heat wave
  colors.add(color(135,255,42));// spring frost
  colors.add(color(0,72,186));// absolute zero(blue)
  colors.add(color(255,0,124));// winter sky
  


}



public void setup(){
 
  
  colorMode(RGB);
  img = loadImage("battle2.jpg");
  //background(0);
  //s= loadShape("ball.svg");
  bullets = new ArrayList<Bullet>();
  deadBullets = new ArrayList<Bullet>();
  cannon = new Cannon(BOTTOM_GAP);
  balls = new ArrayList<Ball>();
  hittedBullets = new ArrayList<Bullet>();
  deadBalls = new ArrayList<Ball>();
  colors = new ArrayList<Integer>();
  storeColors();
  
  for(int i = 0;i<1;i++){
   // balls.add(new Ball(100,color(255,0,244)));//floor(random(50,100))));
   // balls.add(new Ball(50,color(255,0,244)));//floor(random(50,100))));
   // balls.add(new Ball(75,color(255,0,244)));//floor(random(50,100))));

}
  //print(balls);
  
}

public void draw(){
  image(img,0,0);
  //background(0);

   deadBullets = new ArrayList<Bullet>();
   hittedBullets = new ArrayList<Bullet>();
   deadBalls = new ArrayList<Ball>();
   
   if(frameCount % 100 ==0){
     
      int ballSize = floor(random(50,100));
      int rem = ballSize % BULLET_POWER;
      int adder = BULLET_POWER - rem;
      balls.add(new Ball(ballSize+adder,colors.get(floor(random(colors.size())))));
   }
   
  if(mousePressed){
    if(frameCount % 3==0)
      bullets.add(new Bullet(mouseX,height-4*BOTTOM_GAP,20,BULLET_POWER));
  }
  
  for(int i = 0 ; i<balls.size();i++){
    
    Ball ball2 = balls.get(i);
    PVector can = new PVector(cannon.x,height-2*cannon.size);
    PVector p = new PVector(ball2.x,ball2.y);//ball 
    int distOfBallandCannon = floor(can.dist(p));
    
    //line(can.x,can.y,p.x,p.y);
        
      if(distOfBallandCannon<=cannon.size+ball2.size/2){
        
        textSize(30);
        stroke(255);
        text("Game over",width/2,height/2 );
        noLoop();    
      
    }
    
    for(int j = 0; j<bullets.size();j++){
      Ball ball = balls.get(i);
      Bullet bullet = bullets.get(j);
      
      PVector p3 = new PVector(bullet.x,bullet.y);//bullet
      PVector p4 = new PVector(ball.x,ball.y);//ball
      
      int d3 = floor(p4.dist(p3));

        if(d3 <= ball.size/2){ 
          
          stroke(ball.c+100);
          fill(ball.c);
          ellipse(p4.x,p4.y,ball.size*2,ball.size*2);
          //splitting
          if(abs(ball.weight - PApplet.parseInt(ball.initialWeight /BULLET_POWER)) <= BULLET_POWER && !ball.splitted ){
            
            stroke(0,0,0);
            strokeWeight(10);
              
            int ballSize = ball.weight/2;
            int rem = ballSize % BULLET_POWER;
            int adder = BULLET_POWER - rem;
                    
            balls.add(new Ball(ballSize+adder,true,ball.x,ball.y,0,ball.c));
            balls.add(new Ball(ballSize+adder,true,ball.x,ball.y,1,ball.c));
            deadBalls.add(ball);
          
        }
         
        if(ball.weight == BULLET_POWER ){
           
          deadBalls.add(ball);
         
       }else if(ball.weight>0){
       
         ball.hit(bullet.power);  
     
       }
         
          hittedBullets.add(bullet);
           
      }
      
      
      
    }
    
    
  }
 
  for(Bullet b : bullets){
   
    b.move();
    b.show();
    
    if(b.y < 0){
      
     deadBullets.add(b); 
    
    }
   
    
  }
  
  
  for(Ball ball : balls){
    
    ball.move();
    ball.show();
    
    
  }
  
  
  bullets.removeAll(hittedBullets);
  balls.removeAll(deadBalls);
  
  bullets.removeAll(deadBullets);
  cannon.showCannon(mouseX,mouseY);
 
}

public void keyPressed(){
 print("restarting");
  setup();
  
  loop();
  
}
class Ball{
  
 int size;
 float x;
 float y;
 float ySpeed;
 float xSpeed;
 int dir;
 int count;
 int weight;
 int initialWeight ;
 boolean splitted= false;
 int c;
 
  Ball(int size,int c){
         
    this.initialWeight = size;
    this.size = size;
    weight = size;
    
    this.c = c;
     dir = floor(random(2));
    
    if(dir==0){
       x= -size/2; 
    }else{
       x= width+size/2; 
    }
    
    y=size/2;
    ySpeed = random(2,5);
    xSpeed = random(2,3);
    count=0;
   // print(x,y,ySpeed,xSpeed,size,count);
  
  }
  
  Ball(int size,boolean splitted,float x,float y,int dir,int c){
     
    this.initialWeight = size;
    this.size = size*size/3;
    weight = size;
    
    this.c = c;
    this.splitted= splitted;
    this.dir = dir;
    this.x = x;
    this.y = y;
    ySpeed = random(2,5);
    xSpeed = random(2,3);
    count=size;
    
    
  }
  
  
  
  public void hit(int val){
    
    weight-=val;
    ySpeed = - ySpeed;
    //xSpeed = - xSpeed;
    //ySpeed *=.999999;
    size-=val*.11f;
    
  }
  
  public void move(){
    
    if(count<size*2 && !splitted){
      
       if(dir==0){
      x=x+xSpeed;
      y=y+.5f;
      count++;
    
    }else{
      x=x-xSpeed;
      y=y+.5f;
      count++;
   
    }
      
    }else if(count<size*2 && splitted){
        
      if(dir==0){
          if(x+size>=width){
            x=width-size;
            count=2*size;
          }
          else{  
            x=x+xSpeed;
          }
      y=y+.5f;
      count++;
    
      }else{
        
          if(x-size<=0){
            x=size;
            count = 2*size;
          }
          else{
          x=x-xSpeed;
          }
        y=y+.5f;
        count++;
   
    }
     
      
      
    }else{
      
      y=y+ySpeed;
      if(dir==0){
          x = x +xSpeed; 
      }else{
         x = x -xSpeed;
      }
     

      
      if(y+size/2 >= height- 2*BOTTOM_GAP || y-size/2 <=0){
        ySpeed = -ySpeed;
        //xSpeed = -xSpeed;
        //println("down");
        
      }
      if(x+size/2 >= width || x-size/2 <=0){
        //ySpeed = -ySpeed;
        xSpeed = -xSpeed;

        //println("up");
      }
     
      
    }
 
  }
  
  public void show(){
   
   stroke(c);
   strokeWeight(1);
    fill(c);
    ellipse(x,y,size,size);
    //line(0,y+size/2,width,y+size/2);
    stroke(0);
    fill(0);
    textSize(100/4);
    
    text(weight,x-(weight+"").length()*8,y+5);
    
    
  }
  
  
  
  
}
class Bullet{
  
  float x;
  float y;
  int size;
  float ySpeed;
  int power;
  PImage img;
  
  Bullet(float x, float y ,int size,int power){
    this.x = x;
    this.y = y;
    this.size = size;
    this.power= power;
    ySpeed = 5;//random(2,5);
    img = loadImage("bullet2.png");
    
  }
  
  public void move(){
   y = y-ySpeed; 
  }
  
  public void show(){
    stroke(255);
    strokeWeight(5);
    fill(255);
    line(x,y,x,y+10); 
    image(img,x,y);
  }
 
  
  
  
}
class Cannon{
  int size;
  float x;
  float y;
  PImage img;
  Cannon(int size){
    this.size = size;
    img = loadImage("battle_tank4.png");
  
  }
  
  public void showCannon(float x, float y){
    
    this.x = x;
    this.y = y;
    if(x<=25){
      x=25;
    }else if(x>=width-25){
      x=width-25;
    }
    
    
     fill(255);
     stroke(255);
     
      line(0,height-2*BOTTOM_GAP,width,height-2*BOTTOM_GAP);
      image(img,x-size,height-4*size);
     
    /*
      line(x-size,height-4*size,x+size,height-4*size);
      line(x-size,height-4*size,x-size,height-2*size);
      line(x+size,height-4*size,x+size,height-2*size);
      line(x-size,height-2*size,x+size,height-2*size);
    */
    
  }
  
  
  
  
  
  
  
}
  public void settings() {  size(600,700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "BallBurst" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
