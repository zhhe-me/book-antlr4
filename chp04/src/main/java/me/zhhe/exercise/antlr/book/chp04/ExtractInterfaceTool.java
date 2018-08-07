/* &copy; 2018 zhhe.me*/
package me.zhhe.exercise.antlr.book.chp04;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

import me.zhhe.exercise.antlr.book.chp04.parser.JavaLexer;
import me.zhhe.exercise.antlr.book.chp04.parser.JavaParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class ExtractInterfaceTool {

    public static void main(final String... args) throws Exception {
        final JavaParser parser = AntlrHelper.newParser(JavaParser.class, JavaLexer.class);
        final ExtractInterfaceListener listener = new ExtractInterfaceListener(parser);
        AntlrHelper.walk(parser.compilationUnit(), listener);
    }
}
