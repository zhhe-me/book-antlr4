/* &copy; 2018 zhhe.me@gmail.com. */
package me.zhhe.exercise.antlr.book.common;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;

import org.antlr.v4.gui.TestRig;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Set;

import me.zhhe.cli.menu.BeanMenuBuilder;
import me.zhhe.cli.menu.Option;

/**
 * @author zhhe.me@gmail.com.
 * @since 13/8/2018
 */
public class AntlrSampleRunner {

    private final Set<String> MODES = ImmutableSet.of("tokens", "tree", "gui");

    @Option(longArgName = "root", description = "root package which is the container of Antlr sample")
    private String rootPackage = "me.zhhe.exercise.antlr.book";

    @Option(longArgName = "chapter", description = "# of chapter")
    private String chapterNo;

    @Option(longArgName = "grammar", description = "grammar of Antlr, like Csv for Csv.g4")
    private String grammar;

    @Option(longArgName = "ruler", description = "start ruler where the explorer kicks off.")
    private String startRuler = "token";

    @Option(description = "mode of 'tokens | tree | gui'")
    private String mode = "tokens";

    private String fullGrammar;
    private String dataFile;


    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public void setChapterNo(String chapterNo) {
        final String msg = "Must be an Int great than 0";
        try {
            final int i = Integer.parseInt(chapterNo.trim());
            if (i < 1) {
                throw new IllegalArgumentException(msg);
            }
            this.chapterNo = "" + i;
        } catch (Exception e) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void setGrammar(final String grammar) {
        if (StringUtils.isBlank(rootPackage))
            throw new IllegalArgumentException("Please set root package first.");
        if (StringUtils.isBlank(chapterNo))
            throw new IllegalArgumentException("Please set chapter no first.");
        if (StringUtils.isBlank(grammar))
            throw new IllegalArgumentException("Grammar can't be empty");

        final String grammarBase = String.format("%s.chp%02d.parser.%s", rootPackage, Integer.valueOf(chapterNo), grammar.trim());
        final String lexerClass = String.format("%sLexer", grammarBase);
        System.out.format("I guess it's %s%n", lexerClass);
        try {
            Class.forName(lexerClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format("Class '%s' not found", lexerClass));
        }
        this.grammar = grammar.trim();
        this.fullGrammar = grammarBase;

        // check default input file
        try {
            dataFile = getClass().getResource(String.format("/%s.input", grammar.trim())).getPath();
            if (StringUtils.isNotBlank(dataFile)) {
                System.out.format("default data file: %s%n", dataFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStartRuler(String startRuler) {
        this.startRuler = startRuler.trim();
    }

    public void setMode(String mode) {
        if (StringUtils.isBlank(mode))
            throw new IllegalArgumentException("mode can't be empty");
        final String formattedMode = mode.trim().toLowerCase(Locale.ENGLISH);
        if (!MODES.contains(formattedMode))
            throw new IllegalArgumentException(String.format("mode must be one of '%s'", Joiner.on('|').join(MODES)));

        this.mode = formattedMode;
    }

    public static void main(final String... args) throws Exception {
        final AntlrSampleRunner runner = new AntlrSampleRunner();

        new BeanMenuBuilder().bean(runner).build(args).render();

        if (StringUtils.isBlank(runner.fullGrammar) || StringUtils.isBlank(runner.startRuler))
            throw new IllegalStateException("grammar and start ruler must be specified.");

        TestRig.main(new String[] {runner.fullGrammar, runner.startRuler, "-" + runner.mode, runner.dataFile});
    }
}
