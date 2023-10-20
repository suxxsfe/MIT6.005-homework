package expressivo;

public class ConcreteVariableExpression implements Expression{
    private final String name;
    
    // Abstraction function:
    //   AF(name) = a variable in expression with a name in String.
    // Representation invariant:
    //   name != null, name.length() != 0
    // Safety from rep exposure:
    //   All fields are private;
    //   name is immutable.
    
    ConcreteVariableExpression(String _name){
        name = _name;
    }
    
    private void checkRep(){
        assert name != null;
        assert name.length() != 0;
    }
    
    /**
     * Give a representation of the variable
     * 
     * @return a String, name of the variable
     */
    @Override
    public String toString(){
        return name;
    }
    
    @Override
    public boolean equals(Object that){
        if(that instanceof ConcreteVariableExpression){
            ConcreteVariableExpression thatVariable = (ConcreteVariableExpression)that;
            return name.equals(thatVariable.name);
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        long hash = 0;
        for(int i = 0; i < name.length(); i++){
            hash = hash*HASH_BASE+(int)(name.charAt(i));
            hash %= HASH_MOD;
        }
        return (int)hash;
    }
   
}
