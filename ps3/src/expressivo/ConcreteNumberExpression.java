package expressivo;

import java.lang.Math;
import java.util.Map;

/**
 * An implementation of Expression.
 * 
 * used for number in a expression.
 */
public class ConcreteNumberExpression implements Expression{
    private double number;
    
    // Abstraction function:
    //   AF(number) = the number in expression.
    // Representation invariant:
    //   number >= 0
    // Safty from rep exposure:
    //   number is private
    //   number is a basic datatype double, it will be copied when returned as a result
    
    ConcreteNumberExpression(double _number){
        number = Math.round(_number/EPS)*EPS;
    }
    
    ConcreteNumberExpression(ConcreteNumberExpression that){
        number = that.getValue();
    }
    
    private void checkRep(){
        assert number >= 0;
    }
    
    double getValue(){
        return number;
    }
    
    /**
     * Give a representation of the number
     * 
     * @return a String, representing the number, with at most five decimal places.
     */
    @Override
    public String toString(){
        String snum = String.format("%.5f", number);
        
        int pos = snum.length()-1;
        while(snum.charAt(pos) == '0'){
            pos--;
        }
        if(snum.charAt(pos) == '.'){
            pos--;
        }
        
        return snum.substring(0, pos+1);
    }
    
    @Override
    public boolean equals(Object that){
        if(that instanceof ConcreteNumberExpression){
            ConcreteNumberExpression thatNumber = (ConcreteNumberExpression)that;
            return Math.round(number/EPS) == Math.round(thatNumber.number/EPS);
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        long hash = (long)Math.round(number/EPS);
        return (int)(hash%HASH_MOD);
    }
    
    @Override
    public Expression differentiate(String variable){
        return new ConcreteNumberExpression(0);
    }
    
    @Override
    public Expression simplify(Map<String, Double> environment){
        return new ConcreteNumberExpression(this);
    }
    
}
