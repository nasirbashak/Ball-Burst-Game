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
 color c;
 
  Ball(int size,color c){
    this.size = size;
    weight = size;
    initialWeight = size;
    this.c = c;
     dir = floor(random(2));
    if(dir==0){
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
  
  Ball(int size,boolean splitted,float x,float y,int dir,color c){
    this.size = size*size/3;
    weight = size;
    initialWeight = size;
    this.c = c;
    this.splitted= splitted;
    this.dir = dir;
    this.x = x;
    this.y = y;
    ySpeed = random(2,5);
    xSpeed = random(2,3);
    count=size;
    
    
  }
  
  
  
  void hit(int val){
    weight-=val;
    ySpeed = - ySpeed*.999999;
    //ySpeed *=.999999;
    size-=val*.11;
    
  }
  
  void move(){
    
    if(count<size*2 && !splitted){
      
       if(dir==0){
      x=x+xSpeed;
      y=y+.5;
      count++;
    
    }else{
      x=x-xSpeed;
      y=y+.5;
      count++;
   
    }
      
    }else if(count<size*2 && splitted){
        if(dir==0){
          if(x+size>=width){
            x=width-size;
            count=2*size;
          }
          else  
            x=x+xSpeed;
      y=y+.5;
      count++;
    
    }else{
        if(x-size<=0){
          x=size;
          count = 2*size;
        }
        else
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
   
   stroke(c);
   strokeWeight(1);
    fill(c);
    ellipse(x,y,size,size);
    stroke(0);
    fill(0);
    textSize(100/4);
    
    text(weight,x-(weight+"").length()*8,y+5);
    
    
  }
  
  
  
  
}
