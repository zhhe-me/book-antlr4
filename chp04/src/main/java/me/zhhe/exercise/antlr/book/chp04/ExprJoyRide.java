/* &copy; 2018 zhhe.me. */
package me.zhhe.exercise.antlr.book.chp04;

import me.zhhe.exercise.antlr.book.chp04.parser.ExprLexer;
import me.zhhe.exercise.antlr.book.chp04.parser.ExprParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me@gmail.com.
 * @since 6/8/2018
 */
public class ExprJoyRide {

    public static void main(final String... args) throws Exception {
        final ExprParser parser = AntlrHelper.newParser(ExprParser.class, ExprLexer.class);
        System.out.println(parser.prag().toStringTree(parser));
    }

}
