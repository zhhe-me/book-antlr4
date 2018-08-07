/* &copy; 2018 zhme.me */
package me.zhhe.exercise.antlr.book.chp04;

import java.util.HashMap;
import java.util.Map;

import me.zhhe.exercise.antlr.book.chp04.parser.LabeledExprBaseVisitor;
import me.zhhe.exercise.antlr.book.chp04.parser.LabeledExprParser;

/**
 * @author zhhe.me.
 * @since 6/8/2018
 */
public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
    /** "memory" for our calculator; variable/value pairs go here */
    final Map<String, Integer> memory = new HashMap<>();

    /** ID '=' expr NEWLINE */
    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        final String id = ctx.ID().getText();
        final Integer value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    /** expr NEWLINE */
    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        final Integer value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }

    /** INT */
    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    /** ID */
    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        final String id = ctx.ID().getText();
        if (!memory.containsKey(id)) {
            System.out.format("UNKNOWN id '%s'", id);
            return 0;
        } else {
            return memory.get(id);
        }
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        final Integer leftValue = visit(ctx.expr(0));
        final Integer rightValue = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.MUL)
            return leftValue * rightValue;
        else
            return leftValue / rightValue;
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        final Integer leftValue = visit(ctx.expr(0));
        final Integer rightValue = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.ADD)
            return leftValue + rightValue;
        else
            return leftValue - rightValue;
    }

    /** '(' expr ')' */
    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
