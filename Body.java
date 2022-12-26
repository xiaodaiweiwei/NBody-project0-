public class Body{
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        this(b.xxPos,b.yyPos,b.xxVel,b.yyVel,b.mass,b.imgFileName);
    }

    double calcDistance(Body b){
        double r;
        r = Math.sqrt((this.xxPos-b.xxPos)*(this.xxPos-b.xxPos)+(this.yyPos-b.yyPos)*(this.yyPos-b.yyPos));
        return r;
    }

    double calcForceExertedBy(Body b){
        double r;
        double f;
        r = this.calcDistance(b);
        f = 6.67*1E-11*this.mass*b.mass/(r*r);
        return f;
    }
    double calcForceExertedByX(Body b){
        double r, fx, f;
        r = this.calcDistance(b);
        f = this.calcForceExertedBy(b);
        fx = f*(b.xxPos-this.xxPos) / r;
        return fx;

    }

    double calcForceExertedByY(Body b){
        double r, fy, f;
        r = this.calcDistance(b);
        f = this.calcForceExertedBy(b);
        fy = f*(b.yyPos-this.yyPos) / r;
        return fy;
    }
 
    double calcNetForceExertedByX(Body[] b){
        double fx = 0.0;
        for(int i=0;i<b.length;i++){
            if(this.equals(b[i])){
                continue;
            }
            fx = fx + this.calcForceExertedByX(b[i]);
        }
        return fx;
    }
    
    double calcNetForceExertedByY(Body[] b){
        double fy = 0.0;
        for(int i=0;i<b.length;i++){
            if(this.equals(b[i])){
                continue;
            }
            fy = fy + this.calcForceExertedByY(b[i]);
        }
        return fy;
    }

    void update(double dt, double fx, double fy){
        double ax, ay, vx, vy, xpos, ypos;
        ax = fx/this.mass;
        ay = fy/this.mass;
        vx = ax * dt + this.xxVel;
        vy = ay * dt + this.yyVel;

        xpos = this.xxPos + vx*dt;
        ypos = this.yyPos + vy*dt;

        this.xxVel = vx;
        this.yyVel = vy;
        this.xxPos = xpos;
        this.yyPos = ypos;
    }

    void draw(){
        String img_root = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos,this.yyPos,img_root);
    }



}