package com.github.opaluchlukasz.junit2spock.core.feature.mockito;

import com.github.opaluchlukasz.junit2spock.core.ASTNodeFactory;
import com.github.opaluchlukasz.junit2spock.core.node.GroovyClosureBuilder;

public class GivenWillThrowFeature extends MockitoThrowFeature {

    public static final String WILL_THROW = "willThrow";
    private static final String GIVEN = "given";

    public GivenWillThrowFeature(ASTNodeFactory nodeFactory, MatcherHandler matcherHandler,
                                 GroovyClosureBuilder groovyClosureBuilder) {
        super(nodeFactory, matcherHandler, groovyClosureBuilder, GIVEN, WILL_THROW);
    }
}
