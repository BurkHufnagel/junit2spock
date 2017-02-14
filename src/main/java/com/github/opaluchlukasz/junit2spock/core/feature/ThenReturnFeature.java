package com.github.opaluchlukasz.junit2spock.core.feature;

import com.github.opaluchlukasz.junit2spock.core.ASTNodeFactory;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.util.List;

import static com.github.opaluchlukasz.junit2spock.core.util.AstNodeFinder.methodInvocation;
import static org.eclipse.jdt.core.dom.InfixExpression.Operator.RIGHT_SHIFT_SIGNED;

public class ThenReturnFeature implements Feature {

    public static final String THEN_RETURN = "thenReturn";
    public static final String WHEN = "when";

    private final ASTNodeFactory astNodeFactory;

    ThenReturnFeature(ASTNodeFactory astNodeFactory) {
        this.astNodeFactory = astNodeFactory;
    }

    @Override
    public boolean applicable(Object astNode) {
        return methodInvocation(astNode, THEN_RETURN)
                .flatMap(thenReturnInvocation -> methodInvocation(thenReturnInvocation.getExpression(), WHEN))
                .filter(whenInvocation -> whenInvocation.arguments().size() == 1)
                .isPresent();
    }

    @Override
    public InfixExpression apply(Object object) {
        MethodInvocation methodInvocation = methodInvocation(object, THEN_RETURN).get();
        List arguments = methodInvocation.arguments();
        if (arguments.size() == 1) {
            MethodInvocation whenMethodInvocation = methodInvocation(methodInvocation.getExpression(), WHEN).get();
            return astNodeFactory.infixExpression(RIGHT_SHIFT_SIGNED,
                    argumentAsExpression(whenMethodInvocation.arguments().get(0)),
                    argumentAsExpression(arguments.get(0)));
        }
        throw new UnsupportedOperationException("Supported only 1-arity thenReturn invocation");
    }

    private Expression argumentAsExpression(Object argument) {
        return argument instanceof Expression ? astNodeFactory.clone((Expression) argument) :
                astNodeFactory.simpleName(argument.toString());
    }
}