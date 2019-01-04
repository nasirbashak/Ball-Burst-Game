class Cannon{
  int size;
  float x;
  float y;
  Cannon(int size){
    this.size = size;
  
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
    
      line(x-size,height-4*size,x+size,height-4*size);
      line(x-size,height-4*size,x-size,height-2*size);
      line(x+size,height-4*size,x+size,height-2*size);
      line(x-size,height-2*size,x+size,height-2*size);
    
  }
  
  
  
  
  
  
  
}
