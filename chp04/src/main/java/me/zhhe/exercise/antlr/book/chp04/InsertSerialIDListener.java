/* &copy; 2018 zhhe.me*/
package me.zhhe.exercise.antlr.book.chp04;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

import me.zhhe.exercise.antlr.book.chp04.parser.JavaBaseListener;
import me.zhhe.exercise.antlr.book.chp04.parser.JavaParser;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class InsertSerialIDListener extends JavaBaseListener {

    final TokenStreamRewriter rewriter;

    public InsertSerialIDListener(final TokenStream tokenStream) {
        this.rewriter = new TokenStreamRewriter(tokenStream);
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        final String field = "\n\tpublic static final long serialVersionUID = 1L;";
        rewriter.insertAfter(ctx.start, field);
    }
}
