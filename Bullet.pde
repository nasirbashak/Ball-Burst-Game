class Bullet{
  
  float x;
  float y;
  int size;
  float ySpeed;
  int power;
  
  Bullet(float x, float y ,int size,int power){
    this.x = x;
    this.y = y;
    this.size = size;
    this.power= power;
    ySpeed = 5;//random(2,5);
    
  }
  
  void move(){
   y = y-ySpeed; 
  }
  
  void show(){
    stroke(255);
    strokeWeight(5);
    fill(255);
    line(x,y,x,y+10);    
  }
 
  
  
  
}
