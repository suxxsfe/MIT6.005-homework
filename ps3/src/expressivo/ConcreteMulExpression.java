package expressivo;

import java.util.Map;


public class ConcreteMulExpression implements Expression{
    private Expression left, right;
    
    // Abstraction function:
    //   AF(left right) = a expression: left * right
    // Representation invariant:
    //   left != null  right != null
    // Safety from rep exposure:
    //   All fields are private;
    //   left and right are immutable
    
    ConcreteMulExpression(Expression _left, Expression _right){
        left = _left;
        right = _right;
    }
    
    private void checkRep(){
        assert left != null;
        assert right != null;
    }
    
    /**
     * Get the expression on the left of multiple sign.
     * 
     * @return Expression
     */
    public Expression getLeft(){
        return left;
    }
    
    /**
     * Get the expression on the right of multiple sign.
     * 
     * @return Expression
     */
    public Expression getRight(){
        return right;
    }
    
    /**
     * Give a representation of the expression
     * 
     * @return a String, representing the expression
     *         there is a whitespace between multiple sign and other expression
     */
    @Override
    public String toString(){
        return left.toString()+" * "+right.toString();
    }
    
    @Override
    public boolean equals(Object that){
        if(that instanceof ConcreteMulExpression){
            ConcreteMulExpression thatMul = (ConcreteMulExpression)that;
            return left.equals(thatMul.getLeft()) && right.equals(thatMul.getRight());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        long _left = left.hashCode();
        long _right = right.hashCode();
        return (int)(((_left*HASH_BASE%HASH_MOD+_right)%HASH_MOD*HASH_BASE%HASH_MOD+MUL_HASH_VALUE)%HASH_MOD);
    }
    
    @Override
    public Expression differentiate(String variable){
        Expression uv = new ConcreteMulExpression(left.differentiate(variable), right);
        Expression vu = new ConcreteMulExpression(left, right.differentiate(variable));
        return new ConcreteAddExpression(uv, vu);
    }
    
    @Override
    public Expression simplify(Map<String, Double> environment){
        Expression leftResult = left.simplify(environment);
        Expression rightResult = right.simplify(environment);
        Expression _0 = new ConcreteNumberExpression(0);
        Expression _1 = new ConcreteNumberExpression(1);
        
        if(leftResult.equals(_0) || rightResult.equals(_0)){
            return _0;
        }
        if(leftResult.equals(_1)){
            return rightResult;
        }
        if(rightResult.equals(_1)){
            return leftResult;
        }
        if(leftResult instanceof ConcreteNumberExpression && rightResult instanceof ConcreteNumberExpression){
            ConcreteNumberExpression leftNumber = (ConcreteNumberExpression)leftResult;
            ConcreteNumberExpression rightNumber = (ConcreteNumberExpression)rightResult;
            return new ConcreteNumberExpression(leftNumber.getValue()*rightNumber.getValue());
        }
        return new ConcreteMulExpression(leftResult, rightResult);
    }
}
