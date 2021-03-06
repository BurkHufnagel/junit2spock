package com.github.opaluchlukasz.junit2spock.core.node;

import java.util.Objects;

import static java.lang.String.format;

public final class SpockBlockNode {

    private final String block;

    private SpockBlockNode(String block) {
        this.block = block;
    }

    public static SpockBlockNode given() {
        return new SpockBlockNode("given");
    }

    public static SpockBlockNode when() {
        return new SpockBlockNode("when");
    }

    public static SpockBlockNode then() {
        return new SpockBlockNode("then");
    }

    public static SpockBlockNode expect() {
        return new SpockBlockNode("expect");
    }

    @Override
    public String toString() {
        return format("%s:", block);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpockBlockNode that = (SpockBlockNode) o;
        return Objects.equals(block, that.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block);
    }
}
