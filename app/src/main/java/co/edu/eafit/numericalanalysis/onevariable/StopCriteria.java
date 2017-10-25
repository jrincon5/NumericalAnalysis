package co.edu.eafit.numericalanalysis.onevariable;

/**
 * Created by Jrincon on 25/03/2016.
 */

public class StopCriteria {

    public boolean c1(int iterations, int i){
        if(i<iterations) return true;
        return false;
    }

    public boolean c2(double Fx, double delta){
        if(Math.abs(Fx)<=delta){return true;}
        return false;
    }

    public boolean c3(double x, double xprev, double tolerance){
        if(Math.abs(x-xprev)<=tolerance)return true;
        return false;
    }

    public boolean c4(double a, double b, double x){
        if(x<a || x>b){
            return true;
        }
        return false;
    }
}