/* &copy; 2018 zhhe.me. */
package me.zhhe.exercise.antlr.book.chp03;

import me.zhhe.exercise.antlr.book.chp03.parser.ArrayInitLexer;
import me.zhhe.exercise.antlr.book.chp03.parser.ArrayInitParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me.
 * @since 5/8/2018
 */
public class Test {

    public static void main(final String... args) throws Exception {
        final ArrayInitParser parser = AntlrHelper.newParser(ArrayInitParser.class, ArrayInitLexer.class);
        final ArrayInitParser.InitContext tree = parser.init();

        System.out.println(tree.toStringTree(parser));
    }

}
