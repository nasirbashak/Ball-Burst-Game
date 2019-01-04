class Ball{
 int size;
 float x;
 float y;
 float ySpeed;
 float xSpeed;
 int r;
 int count;
 int weight;
 
  Ball(int size){
    this.size = size;
    weight = size;
     r = floor(random(2));
    if(r==0){
     x= 0-2*size; 
    }else{
     x= width+2*size; 
    }
    y=2*size;
    ySpeed = random(2,5);
    xSpeed = random(2,3);
    count=0;
   // print(x,y,ySpeed,xSpeed,size,count);
  }
  
  void hit(int val){
    weight-=val;
    ySpeed = - ySpeed*.999999;
    //ySpeed *=.999999;
    size-=val*.11;
    
  }
  
  void move(){
    
    if(count<size*2){
      
       if(r==0){
      x=x+xSpeed;
      y=y+.5;
      count++;
    
    }else{
      x=x-xSpeed;
      y=y+.5;
      count++;
   
    }
      
    }else{
      
      y=y+ySpeed;
      
      if(y+size/2 >= height){
        ySpeed = -ySpeed;
        //println("down");
        
      }
      if(y-size/2 <=0){
        ySpeed = -ySpeed;
        //println("up");
      }
     
      
    }
 
  }
  
  void show(){
   
    stroke(0);
    fill(255);
    ellipse(x,y,size,size);
    stroke(0);
    fill(0);
    textSize(100/4);
    
    text(weight,x-(weight+"").length()*4,y);
    
    
  }
  
  
  
  
}
