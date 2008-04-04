/*
 * Copyright (c) 2008 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysema.query.grammar.OrderSpecifier;
import com.mysema.query.grammar.types.Expr;
import static com.mysema.query.grammar.types.Expr.*;

/**
 * QueryBase provides a basic implementation of the Query interface
 *
 * @author tiwe
 * @version $Id$
 */
@SuppressWarnings("unchecked")
public class QueryBase<A extends QueryBase<A>> implements Query<A> {
    protected final List<Expr<?>> groupBy = new ArrayList<Expr<?>>();
    
    protected final CascadingBoolean having = new CascadingBoolean();
    
    protected final List<JoinExpression> joins = new ArrayList<JoinExpression>();
    protected final List<OrderSpecifier<?>> orderBy = new ArrayList<OrderSpecifier<?>>();
    protected final List<Expr<?>> select = new ArrayList<Expr<?>>();
    protected final CascadingBoolean where = new CascadingBoolean();
    protected void clear(){
        joins.clear();
        groupBy.clear();
        having.clear();
        orderBy.clear();
        select.clear();
        where.clear();
    }
    
    private final Metadata metadata = new Metadata();
    
    public A from(Entity<?>... o) {
        for (Entity<?> expr : o){
            joins.add(new JoinExpression(JoinType.DEFAULT,expr));
        }
        return (A) this;
    }
    
    public A groupBy(Expr<?>... o) {
        groupBy.addAll(Arrays.asList(o));
        return (A) this;
    }
        
    public A having(Expr.Boolean o) {
        having.and(o);
        return (A) this;
    }
    
    public A innerJoin(Entity<?> o) {
        joins.add(new JoinExpression(JoinType.INNERJOIN,o));
        return (A) this;
    }
    
    public A fullJoin(Entity<?> o) {
        joins.add(new JoinExpression(JoinType.FULLJOIN,o));
        return (A) this;
    }
 
    public A join(Entity<?> o) {
        joins.add(new JoinExpression(JoinType.JOIN,o));
        return (A) this;
    }
 
    public A leftJoin(Entity<?> o) {
        joins.add(new JoinExpression(JoinType.LEFTJOIN,o));
        return (A) this;
    }
    
    public A orderBy(OrderSpecifier<?>... o) {
        orderBy.addAll(Arrays.asList(o));
        return (A) this;
    }

    public A select(Expr<?>... o) {
        select.addAll(Arrays.asList(o));
        return (A) this;
    }

    public A where(Expr.Boolean o) {
        where.and(o);
        return (A) this;
    }
    
    public A with(Expr.Boolean o) {
        if (!joins.isEmpty()){
            joins.get(joins.size()-1).setCondition(o);
        }
        return (A) this;
    }
    
    public Metadata getMetadata(){
        return metadata;
    }

    public class Metadata{
        public List<Expr<?>> getGroupBy() {
            return groupBy;
        }
        public Expr.Boolean getHaving() {
            return having.self();
        }
        public List<JoinExpression> getJoins() {
            return joins;
        }
        public List<OrderSpecifier<?>> getOrderBy() {
            return orderBy;
        }
        public List<Expr<?>> getSelect() {
            return select;
        }
        public Expr.Boolean getWhere() {
            return where.self();
        }
    }

}
