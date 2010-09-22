/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.jpa;

import org.junit.Test;

public class OrderExpressionsTest extends AbstractQueryTest {

    @Test
    public void testOrderExpressionInFunctionalWay() {
        cat.bodyWeight.asc();
        cat.bodyWeight.add(kitten.bodyWeight).asc();
    }

}