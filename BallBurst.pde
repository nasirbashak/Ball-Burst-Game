
ArrayList<Bullet> bullets;
ArrayList<Bullet> deadBullets;
Cannon cannon ;
ArrayList<Ball> balls;

ArrayList<Ball> deadBalls;
ArrayList<Bullet> hittedBullets;

PImage img;
PShape s;

final int BULLET_POWER = 3;

void setup(){
 
  size(600,600);
  
  
  //background(0);
  
  img = loadImage("cb4.jpg");
  //s= loadShape("ball.svg");
  bullets = new ArrayList<Bullet>();
  deadBullets = new ArrayList<Bullet>();
  cannon = new Cannon(25);
  balls = new ArrayList<Ball>();
  hittedBullets = new ArrayList<Bullet>();
  deadBalls = new ArrayList<Ball>();
  
  for(int i = 0;i<1;i++){
    //balls.add(new Ball(50));//floor(random(50,100))));
  }
  //print(balls);
  

}

void draw(){
  image(img,0,0);
  //background(0);
  //stroke(0);
  //translate(width/2,height/2);
  //sphere(40);
   //shape(s, 10, 10, 80, 80);
   deadBullets = new ArrayList<Bullet>();
   hittedBullets = new ArrayList<Bullet>();
   deadBalls = new ArrayList<Ball>();
   
   if(frameCount % 100 ==0){
    //print(frameCount);
    int ballSize = floor(random(50,100));
    int rem = ballSize % BULLET_POWER;
    int adder = BULLET_POWER - rem;
      balls.add(new Ball(ballSize+adder));
   }
   
   

  if(mousePressed){
    if(frameCount % 3==0)
    bullets.add(new Bullet(mouseX,height-100,10,BULLET_POWER));
  }
  
  for(int i = 0 ; i<balls.size();i++){
    Ball ball2 = balls.get(i);
    
    PVector can = new PVector(cannon.x,height-4*cannon.size);
      PVector p = new PVector(ball2.x,ball2.y);//ball 
      int distOfBallandCannon = floor(can.dist(p));
      
      //line(can.x,can.y,p.x,p.y);
      
      
      if(distOfBallandCannon<=cannon.size+ball2.size/2){
        textSize(30);
        stroke(255);
        text("game over",width/2,height/2 );
        //noLoop();
        
      }
    
    
    
    for(int j = 0; j<bullets.size();j++){
      Ball ball = balls.get(i);
      Bullet bullet = bullets.get(j);
      
  
      
      PVector p3 = new PVector(bullet.x,bullet.y);//bullet
      PVector p4 = new PVector(ball.x,ball.y);//ball
      
      int d3 = floor(p4.dist(p3));

        if(d3 <= ball.size/2){ 
          stroke(255,0,0);
          fill(0,255,0);
          ellipse(p4.x,p4.y,ball.size*2,ball.size*2);
          
          if(ball.weight == BULLET_POWER){
           deadBalls.add(ball);
         }
          
       else if(ball.weight>0){
       
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

void keyPressed(){
 print("restarting");
  setup();
  
  loop();
  
}
