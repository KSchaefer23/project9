//// Kevin Schaefer
//// CST 112 Eve -- Project 9

int many=5;

Squid school[]=  new Squid[many];
String names[]=  { "One", "Two", "Red", "Blue", "Fish" };

Boat fleet[]= new Boat[many];
String boatNames[]= {"Jenny", "Penny", "XPenny", "Minnow", "Mimi" };

Shark fins[]= new Shark[many];
String sharkNames[]= {"Bruce", "Ripster", "Jab", "Streex", "Slammu" };

float spacing;
float surface;
float moonX=0, moonY=100;
int score=0;

//// Size & reset
void setup() {
  size( 800, 600 );
  spacing=  width/(many+1);
  reset();
}

//// Reset function
void reset() {
  surface=  random(  height/4, height/2 );
  moonY=  surface/3;
  moonY=  random( 200, surface+200 );
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i]=  new Squid( names[i], x );      // spaces squids evenly
    x += spacing;
  }
  for (int i=0; i<many; i++ ) {
    fleet[i]=  new Boat( boatNames[i]);        // random boats
  }
  for (int i=0; i<many; i++ ) {
    fins[i]=  new Shark( sharkNames[i]);        // random boats
  }   
}

//// Iterate and sort value functions for boat
void sortBoatX(Boat[] a, int many) {         // Sorts the boats by X positions
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].x > a[k].x) k = j;
    }
    boatSwap(a, m-1, k);
  }
}
void sortBoatDX(Boat[] a, int many) {        // Sorts the boats by DX values
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].dx < a[k].dx) k = j;
    }
    boatSwap(a, m-1, k);
  }
}
void sortBoatCargo(Boat[] a, int many) {     // Sorts the boats by cargo amount
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].cargo < a[k].cargo) k = j;
    }
    boatSwap(a, m-1, k);
  }
}

void boatSwap(Boat[] a, int k, int j) {      // Swaps the values inputted
  float tmpX;
  float tmpDX;
  int tmpC;
  String tmpN;

  tmpX = a[k].x;
  a[k].x = a[j].x;
  a[j].x = tmpX;

  tmpDX = a[k].dx;
  a[k].dx = a[j].dx;
  a[j].dx = tmpDX;

  tmpC = a[k].cargo;
  a[k].cargo = a[j].cargo;
  a[j].cargo = tmpC;

  tmpN = a[k].name;
  a[k].name = a[j].name;
  a[j].name = tmpN;
}

//// Iterate and sort value functions for boat
void sortSquidX(Squid[] a, int many) {        // Sorts the squids by X positions
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].x > a[k].x) k = j;
    }
    swapSquid(a, m-1, k);
  }
}
void sortSquidY(Squid[] a, int many) {        // Sorts the squids by Y positions
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].y > a[k].y) k = j;
    }
    swapSquid(a, m-1, k);
  }
}
void sortSquidDY(Squid[] a, int many) {       // Sorts the squids by DY values
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].dy > a[k].dy) k = j;
    } 
    swapSquid(a, m-1, k);
  }
}
void sortSquidL(Squid[] a, int many) {        // Sorts the squids by leg number
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].legs > a[k].legs) k = j;
    }
    swapSquid(a, m-1, k);
  }
}

void swapSquid(Squid[] a, int k, int j) {    // Swaps the values inputted
  float tmpX;
  float tmpY;
  float tmpDY;
  int tmpL;
  String tmpN;

  tmpX = a[k].x;
  a[k].x = a[j].x;
  a[j].x = tmpX;

  tmpY = a[k].y;
  a[k].y = a[j].y;
  a[j].y = tmpY;

  tmpDY = a[k].dy;
  a[k].dy = a[j].dy;
  a[j].dy = tmpDY;

  tmpL = a[k].legs;
  a[k].legs = a[j].legs;
  a[j].legs = tmpL;

  tmpN = a[k].name;
  a[k].name = a[j].name;
  a[j].name = tmpN;
}

//// DRAW:  scene, show, action, messages, report
void draw() {
  scene();
  show();
  if (key >= 'A' && key <= 'Z') {              // Shows report if any caps pressed
    boatReport(100, fleet, fleet.length);      // while stopping action
    fishReport(200, school, school.length);
    //sharkReport(300, fins, fins.length);     *** UNCOMMENT TO SEE SHARK REPORT ***
    messages(); } 
  else {                                       // Runs action if no caps pressed
    action();
    messages();
  }
}

//// Display instructions, title, and score
void messages() {
  fill(0);
  textSize( 20 );
  text( "Gone Fishin'", width/2.5, 20 );  // Title
  textSize(12);
  text( "Hold B key to show boats in position order", 50, 40 );    // Instructions
  text( "Hold D key to show boats in speed order", 50, 55 );
  text( "Hold F key to show boats in cargo order", 50, 70 );
  
  text( "Hold X key to show fish in position order", width/2, 40 );
  text( "Hold Y key to show fish in height order", width/2, 55 );
  text( "Hold S key to show fish in speed order", width/2, 70 );
  text( "Hold L key to show fish in leg number order", width/2, 85 );

  if (score>0) text( "SCORE:  "+score, width*3/4, 20 );    // Score
}

//// METHODS TO MOVE & DRAW.
void scene() {
  background( 50,150,200 );      // Dark sky.
  // Moon
  if (moonX > width+100) {       // Moon
    moonX=  -100;
    moonY=  random( 100, surface+50 );
  }
  moonX += 1;
  fill( 200,150,50 );
  ellipse( moonX, moonY-150*sin( PI * moonX/width ), 40,40 );
  // Dark water.
  fill( 0,100,50 );
  noStroke();
  rect( 0,surface, width, height-surface );  
}

//// Moves the squid and boats
void action() {
  for (int i=0; i<many; i++ ) {    // Squids
    school[i].move();
  }
  for (int i=0; i<many; i++ ) {    // Boats
    fleet[i].move();
  }
  for (int i=0; i<many; i++ ) {    // Sharks
    fins[i].move();
  }
}

//// Show the squids in (sorted) order and show boats
void show() {
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i].x=  x;
    x += spacing;
    school[i].show();
  }
  for (int i=0; i<many; i++ ) {
    fleet[i].show();
  }
  /*
  for (int i=0; i<many; i++ ) {      ***UNCOMMENT TO SEE SHARKS***
    fins[i].show();
  }
  */
}

// Creates the report of boat properties
void boatReport( float top, Boat[] b, int many ) {
  fill(255,200,200,210);
  rect( 50,top, width-100, 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "Boat", x+20, y );
  text( "Cargo", x+70, y );
  text( "X", x+125, y );
  text( "DX", x+205, y );
  fill(0);
  //
  for (int i=0; i<many; i++) {      // Iterates through boat index to display
    y += 15;    // Next line.
    text( i, x, y );
    text( b[i].name, x+20, y );
    text( b[i].cargo, x+70, y );
    text( b[i].x, x+125, y );
    text( b[i].dx, x+205, y );
  }  
}

// Creates the report of squid properties
void fishReport( float top, Squid[] a, int many ) {
  fill(255,255,200, 200);
  rect( 50,top, width-100, 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "Fish", x+20, y );
  text( "Legs", x+70, y );
  text( "X", x+105, y );
  text( "Y", x+205, y );
  text( "DY", x+305, y );
  fill(0);
  for (int i=0; i<many; i++) {    // Iterates through squid index to display
    y += 15;    // Next line.
    text( i, x, y );
    text( a[i].name, x+20, y );
    text( a[i].legs, x+70, y );
    text( a[i].x, x+100, y );
    text( a[i].y, x+200, y );
    text( a[i].dy, x+300, y );
  }
}
  
// Creates the report of shark properties
void sharkReport( float top, Shark[] c, int many ) {
  fill(0,255,255,210);
  rect( 50,top, width-100, 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "Shark", x+20, y );
  text( "X", x+70, y );
  text( "Y", x+125, y );
  text( "DX", x+205, y );
  fill(0);
  //
  for (int i=0; i<many; i++) {      // Iterates through shark index to display
    y += 15;    // Next line.
    text( i, x, y );
    text( c[i].name, x+20, y );
    text( c[i].x, x+70, y );
    text( c[i].y, x+125, y );
    text( c[i].dx, x+205, y );
  }  
}    
    
//// EVENT HANDLERS:  keys & clicks 
void keyPressed() {
  if (key == 'B') {
    sortBoatX(fleet, fleet.length); }        // Sort position (x)
  if (key == 'D') {
    sortBoatDX(fleet, fleet.length ); }      // Sort speed (dx)
  if (key == 'F') {
    sortBoatCargo(fleet, fleet.length ); }   // Sort # cargo
    
  if (key == 'X') {
    sortSquidX(school, school.length); }     // Sort position (x)
  if (key == 'Y') {
    sortSquidY(school, school.length); }     // Sort height (y)
  if (key == 'S') {
    sortSquidDY(school, school.length); }    // Sort speed (dy)
  if (key == 'L') {
    sortSquidL(school, school.length); }     // Sort # legs
  
  if (key == 'r') reset();
  // Sends squid to the bottom!
  if (key == '0') school[0].bottom(); 
  if (key == '1') school[1].bottom(); 
  if (key == '2') school[2].bottom(); 
  if (key == '3') school[3].bottom(); 
  if (key == '4') school[4].bottom();  
  //// Send highest to bottom.
  if (key == 'h') {
    int k=0;
    for (int i=1; i<many; i++ ) {
      if (school[i].y < school[k].y) k=i;    // k is index of highest.
    }
    school[k].bottom();     
  }
  // Cheat codes:
  //// Send all to top or bottom.
  if (key == 'b') {
    for (int k=0; k<many; k++ ) {    // to the bottom
      school[k].bottom();     
    }
  }
  if (key == 't') {
    for (int k=0; k<many; k++ ) {    // to the top
      school[k].y=  surface+10;
      school[k].dy=  -0.1  ;
    }
  }
}

//// OBJECTS ////
class Squid {
  float x,y;        // Coordinates
  float dx=0,dy=0;  // Speed
  float w=40,h=40;
  int legs=10;      // Number of legs.
  String name="";
  float r,g,b;      // Color.
  int count=0;
  //// CONSTRUCTORS ////
  Squid( String s, float x ) {
    this.name=  s;
    this.x=x;
    bottom();
    // Purplish
    r=  random(100, 255);
    g=  random(0, 100);
    b=  random(100, 250);
  }
  //// Start again at bottom.  (Random speed.)
  void bottom() {
    y=  height - h;
    dy=  -random( 0.1, 0.9 );
    legs=  int( random(1, 10.9) );
  }
  //// METHODS:  move & show ////
  void move() {
    x += dx;
    y += dy;
    if (y>height) { 
      bottom();
      count++;
    }
    else if (y<surface) { 
      dy=  -3 * dy;        // Descend fast!
    }
  }
  //// Display the creature.
  void show() {
    fill(r,g,b);
    stroke(r,g,b);
    ellipse( x,y, w,h );         // round top
    rect( x-w/2,y, w,h/2 );      // flat bottom
    // Legs
    fill(r,g,b);                 // legs.
    float legX=  x-w/2, foot=0;
    foot=  dy>=0 ? 0 : (y%47 > 23) ? 5 : -5;
    strokeWeight(3);
    for (int i=0; i<legs; i++) {
      line( legX, y+h/2, legX+foot, 20+y+h/2 );
      legX += w / (legs-1);
    }
      strokeWeight(3);
    fill(200,200,0);
    text( name, x-w/2, y-10+h/2 );
    fill(0);
    text( legs, x+2-w/2, y+h/2 );
    fill(255);
    if (count>0) text( count, x, y+h/2 );
  }
  //// Return true if near
  boolean hit( float xx, float yy ) {
    return dist( xx,yy, x,y ) < h;
  }
}

//// Begin boat class
class Boat {
  String name="";
  float x, y=surface, dx=2; // initial properties
  int cargo=0, caught=0;
  
  Boat(String s) {          // assign name, random x and dx
    this.name=  s;
    x = random(0,width);
    dx = random(-3, 3);
  }  
  
  void move() {            // creates movement, determines cargo, speed & direction
    int caught=0;
    x += dx;    
    //// Fish before move:  check each squid.
    for (int i=0; i<many; i++ ) {
      if (school[i].hit( x, surface )) {
        caught += school[i].legs;
        school[i].bottom();
      }
    }
    
    cargo += caught;          
    
    if (caught>0) x += 2*dx;     //  Jump after catch.   
    
    if (x<0) {
      score += cargo;            // Add cargo to global score.
      cargo=0;
      dx = random( 1, 3 );      // Variable boat speed.
    }    
    if (x>width)  {
      dx = -random( 0.5, 3 );    // Slower return.
    }
  }  
  
  //// Draw the boat
  void show() {
    // Boat
    fill(255,0,0);
    noStroke();
    rect( x, surface-20, 50, 20 );
    if (dx>0) { triangle( x+50,surface, x+50,surface-20, x+70,surface-20 ); }
    else {      triangle( x,surface, x,surface-20, x-20,surface-20 ); }
    rect( x+12, surface-30, 25, 10 );      // Cabin.
    fill(0);
    rect( x+20, surface-40, 10, 10 );      // Smokestack.
    // Display name & cargo.
    fill(255);
    text( name, x+5, surface-10 );
    fill(0);
    text( cargo, x+20, surface );
  }    
}


//// Begin shark class
class Shark {
  String name="";
  float x,   dx=2; // initial properties
  float y; 
  int cargo=0, caught=0;
  
  Shark(String s) {          // assign name, random x and dx
    this.name=  s;
    x = random(0,width);
    y = random(surface+30, height);
    dx = random(-3, 3);
  }  
  
  void move() {                // creates shark movement, speed & direction
    x += dx;    
        
    if (caught>0) x += 2*dx;   //  Jump after catch.   
    
    if (x<0) {
      dx = random( 1, 4 );     // Shark speed
    }    
    if (x>width)  {
      dx = -random( 1, 4 );    // Return.
    }
  }  
  
  //// Draw the shark
  void show() {
    // Shark
    fill(165,165,165);
    noStroke();
    ellipse( x, y-20, 80, 20 );
    if (dx>0) {                                   // FACING RIGHT //
      triangle( x,y-5, x,y-20, x+20,y-20 );       // Bottom fin
      triangle( x+5,y-20, x,y-38, x+30,y-20 );    // Dorsal fin
      triangle(x-35,y-20, x-45,y-40, x-45,y);     // Tail
      fill(0);
      ellipse(x+28,y-23,3,3);                     // Eyes
    }
    else {                                        // FACING LEFT //
      triangle( x,y-5, x,y-20, x-20,y-20 );       // Bottom fin
      triangle( x-5,y-20, x,y-38, x-30,y-20 );    // Dorsal fin
      triangle(x+35,y-20, x+45,y-40, x+45,y);     // Tail      
      fill(0);
      ellipse(x-28,y-23,3,3);                     // Eyes
    }
    fill(255);
    text( name, x-15, y-15 );
    fill(0);
  }    
}

