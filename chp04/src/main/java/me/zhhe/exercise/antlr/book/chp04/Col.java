/* &copy; 2018 zhhe.me*/
package me.zhhe.exercise.antlr.book.chp04;

import org.antlr.v4.runtime.TokenStream;

import me.zhhe.exercise.antlr.book.chp04.parser.RowsLexer;
import me.zhhe.exercise.antlr.book.chp04.parser.RowsParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class Col {

    public static void main(final String... args) throws Exception {
        final TokenStream tokenStream = AntlrHelper.newTokenStream(RowsLexer.class);
        final RowsParser parser = new RowsParser(tokenStream, 2);
        parser.setBuildParseTree(false);
        parser.file();  // parse
    }

}
