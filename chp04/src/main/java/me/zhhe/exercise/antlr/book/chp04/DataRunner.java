/* &copy; 2018 zhhe.me*/
package me.zhhe.exercise.antlr.book.chp04;

import me.zhhe.exercise.antlr.book.chp04.parser.DataLexer;
import me.zhhe.exercise.antlr.book.chp04.parser.DataParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class DataRunner {

    public static void main(final String... args) throws Exception {
        final DataParser parser = AntlrHelper.newParser(DataParser.class, DataLexer.class);
        final DataParser.FileContext tree = parser.file();
        System.out.println(tree.toStringTree(parser));
    }

}
