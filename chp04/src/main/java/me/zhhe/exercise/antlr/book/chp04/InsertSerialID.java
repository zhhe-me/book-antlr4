/* &copy; 2018 zhhe.me*/
package me.zhhe.exercise.antlr.book.chp04;

import org.antlr.v4.runtime.TokenStream;

import me.zhhe.exercise.antlr.book.chp04.parser.JavaLexer;
import me.zhhe.exercise.antlr.book.chp04.parser.JavaParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class InsertSerialID {

    public static void main(final String... args) throws Exception {
        final TokenStream tokens = AntlrHelper.newTokenStream(JavaLexer.class);
        final JavaParser parser = new JavaParser(tokens);
        final InsertSerialIDListener listener = new InsertSerialIDListener(tokens);
        AntlrHelper.walk(parser.compilationUnit(), listener);

        System.out.println(listener.rewriter.getText());
    }
}
