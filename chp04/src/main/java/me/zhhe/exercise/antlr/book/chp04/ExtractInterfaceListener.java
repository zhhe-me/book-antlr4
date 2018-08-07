/* &copy; 2018 zhhe.me*/
package me.zhhe.exercise.antlr.book.chp04;

import org.antlr.v4.runtime.TokenStream;

import me.zhhe.exercise.antlr.book.chp04.parser.JavaBaseListener;
import me.zhhe.exercise.antlr.book.chp04.parser.JavaParser;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class ExtractInterfaceListener extends JavaBaseListener {
    private final JavaParser parser;

    public ExtractInterfaceListener(final JavaParser parser) {
        this.parser = parser;
    }

    /** Listen to matches of importDeclaration. */
    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
//        System.out.format("import %s;%n", ctx.qualifiedName().getText());
        System.out.println(parser.getTokenStream().getText(ctx));
    }

    /** Listen to matches of classDeclaration */
    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.format("interface I%s {%n", ctx.Identifier());
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("}\n");
    }

    /** Listen to matches of methodDeclaration */
    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        final TokenStream tokens = parser.getTokenStream();
        String type = "void";
        if (ctx.type() != null) {
            type = tokens.getText(ctx.type());
        }
        final String args = tokens.getText(ctx.formalParameters());
        System.out.format("\t%s %s%s;%n", type, ctx.Identifier(), args);
    }
}
