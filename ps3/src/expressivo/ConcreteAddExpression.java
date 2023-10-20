package expressivo;

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
    
    public Expression getLeft(){
        return left;
    }
    
    public Expression getRight(){
        return right;
    }
    
    /**
     * Give a representation of the expression
     * 
     * @return a String, representing the expression.
     *         there is a pair of parenthnesses around the expression
     *         there is a whitespace between parenthnesses, plus sign, and other expression
     */
    @Override
    public String toString(){
        return "( "+left.toString()+" + "+right.toString()+" )";
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
}
