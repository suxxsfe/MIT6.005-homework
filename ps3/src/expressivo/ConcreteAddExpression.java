package expressivo;

import java.util.Map;

public class ConcreteAddExpression implements Expression{
    private Expression left, right;
    
    // Abstraction function:
    //   AF(left, right) = a expression: left + right
    // Representation invariant:
    //   left != null  right != null
    // Safety from rep exposure:
    //   All fields are private;
    //   left and right are immutable
    
    ConcreteAddExpression(Expression _left, Expression _right){
        left = _left;
        right = _right;
    }
    
    private void checkRep(){
        assert left != null;
        assert right != null;
    }
    
    /**
     * Get the expression on the left of add sign.
     * 
     * @return Expression
     */
    public Expression getLeft(){
        return left;
    }
    
    /**
     * Get the expression on the right of add sign.
     * 
     * @return Expression
     */
    public Expression getRight(){
        return right;
    }
    
    /**
     * Give a representation of the expression.
     * to ensure the operating order exactly same as the input, for the expression on the rightside
     *      of the add sign, there will be a pair of parentheses around it if it is composed of
     *      two expression merged by a add plus.
     *      eg. for 3 + ( 4 + 5 ), 4 + 5 is the expression on the rightside, and there should be
     *          a pair of parentheses around it.
     * there is a whitespace between parentheses, plus sign, and other expression
     * 
     * @return a String, representing the expression.
     */
    @Override
    public String toString(){
        String rightString = right.toString();
        if(right instanceof ConcreteAddExpression){
            rightString = "( "+rightString+" )";
        }
        return left.toString()+" + "+rightString;
    }
    
    @Override
    public boolean equals(Object that){
        if(that instanceof ConcreteAddExpression){
            ConcreteAddExpression thatAdd = (ConcreteAddExpression)that;
            return left.equals(thatAdd.getLeft()) && right.equals(thatAdd.getRight());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        long _left = left.hashCode();
        long _right = right.hashCode();
        return (int)(((_left*HASH_BASE%HASH_MOD+_right)%HASH_MOD*HASH_BASE%HASH_MOD+ADD_HASH_VALUE)%HASH_MOD);
    }
    
    @Override
    public Expression differentiate(String variable){
        return new ConcreteAddExpression(left.differentiate(variable), right.differentiate(variable));
    }
    
    @Override
    public Expression simplify(Map<String, Double> environment){
        Expression leftResult = left.simplify(environment);
        Expression rightResult = right.simplify(environment);
        Expression _0 = new ConcreteNumberExpression(0);
        
        if(leftResult.equals(_0)){
            return rightResult;
        }
        if(rightResult.equals(_0)){
            return leftResult;
        }
        if(leftResult instanceof ConcreteNumberExpression && rightResult instanceof ConcreteNumberExpression){
            ConcreteNumberExpression leftNumber = (ConcreteNumberExpression)leftResult;
            ConcreteNumberExpression rightNumber = (ConcreteNumberExpression)rightResult;
            return new ConcreteNumberExpression(leftNumber.getValue()+rightNumber.getValue());
        }
        return new ConcreteAddExpression(leftResult, rightResult);
    }
}
