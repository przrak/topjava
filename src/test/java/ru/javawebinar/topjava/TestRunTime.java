package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestRunTime implements TestRule {
    @Override
    public Statement apply(final Statement base, Description descrtiption) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long startTime = System.currentTimeMillis();
                base.evaluate();
                long endTime = System.currentTimeMillis();
                long duration = (endTime - startTime);
                System.out.println("Test execution time " + duration + " milliseconds");
            }
        };
    }
}
