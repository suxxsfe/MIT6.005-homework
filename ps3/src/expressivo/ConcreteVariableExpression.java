package expressivo;

import java.util.Map;

public class ConcreteVariableExpression implements Expression{
    private final String name;
    
    // Abstraction function:
    //   AF(name) = a variable in expression with a name in String.
    // Representation invariant:
    //   name != null, name.length() != 0, name only contains letters
    // Safety from rep exposure:
    //   All fields are private;
    //   name is immutable.
    
    ConcreteVariableExpression(String _name){
        name = _name;
    }
    
    ConcreteVariableExpression(ConcreteVariableExpression that){
        name = that.getName();
    }
    
    private void checkRep(){
        assert name != null;
        assert name.length() != 0;
        for(int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            assert (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
        }
    }
    
    String getName(){
        return name;
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
            return name.equals(thatVariable.getName());
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
   
    @Override
    public Expression differentiate(String variable){
        if(name.equals(variable)){
            return new ConcreteNumberExpression(1);
        }
        return new ConcreteNumberExpression(0);
    }
    
    @Override
    public Expression simplify(Map<String, Double> environment){
        if(environment.containsKey(name)){
            return new ConcreteNumberExpression(environment.get(name));
        }
        return new ConcreteVariableExpression(this);
    }
}
