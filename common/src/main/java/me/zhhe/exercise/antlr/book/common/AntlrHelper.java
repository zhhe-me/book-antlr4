package me.zhhe.exercise.antlr.book.common;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Scanner;

/**
 * @author zhhe.me
 * @since 05/08/2018
 */
public class AntlrHelper {

    public static void walk(final ParseTree tree, final ParseTreeListener listener) {
        final ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
    }

    public static <T extends Parser> T newParser(final Class<T> parserClass,
                                   final Class<? extends Lexer> lexerClass) throws Exception {
        final TokenStream tokens = newTokenStream(lexerClass);
        return parserClass.getConstructor(TokenStream.class).newInstance(tokens);
    }

    public static TokenStream newTokenStream(Class<? extends Lexer> lexerClass)
            throws InstantiationException, IllegalAccessException,
            java.lang.reflect.InvocationTargetException, NoSuchMethodException {

        final ANTLRInputStream inputStream = new ANTLRInputStream(receiveInput());
        final Lexer lexer = lexerClass.getConstructor(CharStream.class).newInstance(inputStream);
        return new CommonTokenStream(lexer);
    }

    private static String receiveInput() {
        System.out.println("Please type your input. Terminate with 2 continuing blank line.");
        final Scanner scanner = new Scanner(System.in);
        final StringBuilder builder = new StringBuilder(1024);
        String preInput = null;
        String currInput = null;

        while ( !isEmptyLine((currInput=scanner.nextLine())) || !isEmptyLine(preInput)) {
            builder.append(currInput).append("\n");
            preInput = currInput;
        }

        scanner.close();

        System.out.println("Your result is: ");

        return builder.toString();
    }

    private static boolean isEmptyLine(final String value) {
        return value==null || value.trim().length() == 0;
    }

}
