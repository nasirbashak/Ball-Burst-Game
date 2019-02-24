class Cannon{
  int size;
  float x;
  float y;
  PImage img;
  Cannon(int size){
    this.size = size;
    img = loadImage("battle_tank4.png");
  
  }
  
  void showCannon(float x, float y){
    
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
