/*
 */
package com.noproject.test;

import com.noproject.action.StringEditor;
import com.noproject.action.EString;
import com.noproject.processor.StringProcessor;

/**
 *
 * @author
 */
public class TestClass {

    public static void main(String[] args) {
        String str = """
                     Hello World
                     I am ${fisrtName} ${lastName}""";

        StringEditor editor = new StringEditor(str);

        editor.change("${fisrtName}", "Ako");

        EString gs = editor.useExpressionString();
        gs.setValue(gs.findExpression("lastName"), "Vitania");

        System.out.println(gs);

        PerformanceTest.showRuntime(() -> {

            System.out.println(
                    StringProcessor.ignoreStringAndGet(gs.toString(), "Ako"));
        });
    }
}
