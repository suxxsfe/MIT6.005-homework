package expressivo;


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
}
