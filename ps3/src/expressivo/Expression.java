/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.antlr.v4.runtime.misc.ParseCancellationException;

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
    public static Expression parse(String input) throws IllegalArgumentException {
        ExpressionParser parser = makeParser(input);
        ParseTree tree = parser.root();
        
//        Trees.inspect(tree, parser);
          
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionBuilder builder = new ExpressionBuilder();
        walker.walk(builder, tree);
        return builder.getExpression();
        
    }
    
    /**
     * Make a ExpressionParser from a input in String
     * 
     * @param input input in String
     * @return ExpressionParser made of the input
     */
    static ExpressionParser makeParser(String input){
        CharStream stream = new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.reportErrorsAsExceptions();
        
        return parser;
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
    
    /**
     * Produces an expression with the derivative respect to the giving variable. 
     * 
     * @param variable name of the variable in String
     * @return Expression, the derivative
     */
    public Expression differentiate(String variable);
}


/**
 * An implementation for interface ExpressionListener
 * a walker will walk over the parse tree with a listener object,
 * and call the methods on it to build an AST tree
 * 
 * variables, numbers will be the same order on the AST tree as in the input(left to right)
 * eg: x + 1 + y + 2 will be built as
 *       +
 *      / \
 *     +   2
 *    / \
 *   +   y
 *  / \
 * x   1
 */
class ExpressionBuilder implements ExpressionListener{
    
    private Stack<Expression> stack = new Stack<>();
    
    /**
     * Get the Expression build from the parser tree
     * must be called after a ParseTreeWalker walking over the parse tree
     * 
     * @return Expression
     */
    public Expression getExpression(){
        return stack.get(0);
    }
    
    @SuppressWarnings("unchecked")
    private Expression buildAddAndMulExpression(int elementNum, String concreteExpressionClassName){
        Expression result = null;
        try{
            Class c = Class.forName(concreteExpressionClassName);
            Constructor constructor = c.getDeclaredConstructor(Expression.class, Expression.class);
            
            List<Expression> elements = new ArrayList<>();
            for(int i = 0; i < elementNum; i++){
                elements.add(stack.pop());
            }
            
            result = elements.get(elementNum-1);
            for(int i = elementNum-2; i >= 0; i--){
                result = (Expression)(constructor.newInstance(result, elements.get(i)));
            }
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    public void exitExpression(ExpressionParser.ExpressionContext context){
        stack.push(buildAddAndMulExpression(context.addExpression().size(), "expressivo.ConcreteAddExpression"));
    }
    
    @Override
    public void exitAddExpression(ExpressionParser.AddExpressionContext context){
        stack.push(buildAddAndMulExpression(context.mulExpression().size(), "expressivo.ConcreteMulExpression"));
    }
    
    @Override
    public void exitMulExpression(ExpressionParser.MulExpressionContext context){
        if(context.NUMBER() != null){
            double number = Double.valueOf(context.NUMBER().getText());
            stack.push(new ConcreteNumberExpression(number));
        }
        else if(context.VARIABLE() != null){
            String name = context.VARIABLE().getText();
            stack.push(new ConcreteVariableExpression(name));
        }
    }
    
    @Override public void enterRoot(ExpressionParser.RootContext context){}
    @Override public void exitRoot(ExpressionParser.RootContext context){}
    @Override public void enterExpression(ExpressionParser.ExpressionContext context){}
    @Override public void enterMulExpression(ExpressionParser.MulExpressionContext context){}
    @Override public void enterAddExpression(ExpressionParser.AddExpressionContext context){}
    
    @Override public void visitTerminal(TerminalNode terminal){}
    @Override public void visitErrorNode(ErrorNode node){}
    @Override public void enterEveryRule(ParserRuleContext context){}
    @Override public void exitEveryRule(ParserRuleContext context){}
    
}

