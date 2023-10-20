/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionListener;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 *   
 *   two expressions are equal if:
 *      - the expressions contain the same variables, numbers, and operators
 *      - those variables, numbers, and operators are in the same order, read left-to-right
 *      - they are grouped in the same way
 *      - variables and numbers should be calculated in the same order: (3+4)+5 is not equal to 3+(4+5)
 *      - two numbers a and b are equal if and only if round(a/EPS) == round(b/EPS):
 *          1.0000000012 is equal to     1.0000000014
 *          1.0000000012 is not equal to 1.0000000015
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    static final double EPS = 1e-9;
    static final long HASH_BASE = 233;
    static final long HASH_MOD = 1000000007;
    static final long ADD_HASH_VALUE = 1;
    static final long MUL_HASH_VALUE = 2;
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        ExpressionParser parser = makeParser(input);
        ParseTree tree = parser.root();
        
        Trees.inspect(tree, parser);
        
        return new ConcreteNumberExpression(1);
    }
    
    static ExpressionParser makeParser(String input){
        CharStream stream = new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        return new ExpressionParser(tokens);
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods
    
}
