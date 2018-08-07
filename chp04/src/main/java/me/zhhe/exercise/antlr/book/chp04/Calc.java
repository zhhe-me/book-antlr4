/* &copy; 2018 zhhe.me */
package me.zhhe.exercise.antlr.book.chp04;

import me.zhhe.exercise.antlr.book.chp04.parser.LabeledExprLexer;
import me.zhhe.exercise.antlr.book.chp04.parser.LabeledExprParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class Calc {

    public static void main(final String... args) throws Exception {
        final LabeledExprParser parser = AntlrHelper.newParser(LabeledExprParser.class, LabeledExprLexer.class);
        final LabeledExprParser.PragContext tree = parser.prag();
        final EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }

}
