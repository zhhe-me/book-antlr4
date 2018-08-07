/* &copy; 2018 zhhe.me. */
package me.zhhe.exercise.antlr.book.chp03;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.PrintStream;

import me.zhhe.exercise.antlr.book.chp03.parser.ArrayInitBaseListener;
import me.zhhe.exercise.antlr.book.chp03.parser.ArrayInitLexer;
import me.zhhe.exercise.antlr.book.chp03.parser.ArrayInitParser;
import me.zhhe.exercise.antlr.book.common.AntlrHelper;

/**
 * @author zhhe.me.
 * @since 5/8/2018
 */
/** Convert short array inits like {1,2,3} to "\u0001\u0002\u0003". */
public class ShortToUnicodeString extends ArrayInitBaseListener {

    private PrintStream out = System.out;

    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        out.print("\"");
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        out.print("\"");
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        final int value = Integer.parseInt(ctx.INT().getText());
        out.format("\\u%04x", value);
    }

    public static void main(final String... args) throws Exception {
        final ArrayInitParser parser = AntlrHelper.newParser(ArrayInitParser.class, ArrayInitLexer.class);
        final ArrayInitParser.InitContext tree = parser.init();
        new ParseTreeWalker().walk(new ShortToUnicodeString(), tree);
        System.out.println();
    }
}
