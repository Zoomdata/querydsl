/*
 * Copyright (c) 2008 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.hql;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.mysema.query.hql.hibernate.HQLQuery;


/**
 * HibernatePersistenceTest provides.
 * 
 * @author tiwe
 * @version $Id$
 */
@RunWith(HibernateTestRunner.class)
@Hibernate(namingStrategy=CustomNamingStrategy.class, properties="default.properties")
public class HqlIntegrationTest extends HqlParserTest{
    
    private Session session;
        
    protected TestQuery q(){
    	return new TestQuery(){
    		public void parse() throws RecognitionException, TokenStreamException{
    	        try{
    	            System.out.println("query : " + toString().replace('\n', ' '));

    	            // create Query and execute it
    	            Query query = session.createQuery(toString());
    	            HQLQuery.setConstants(query, getConstants());
    	            try{
    	                query.list();    
    	            }catch(Exception e){
    	                e.printStackTrace();
    	                throw new RuntimeException(e);
    	            }    
    	        }finally{            
    	            System.out.println();
    	        }    	                
    	    }
    	};
    }
    
    @Override
    
    
    @Test
    public void testGroupBy() throws Exception {
        // NOTE : commented out, because HQLSDB doesn't support these queries
    }

    @Test
    public void testOrderBy() throws Exception {
        // NOTE : commented out, because HQLSDB doesn't support these queries
    }
    
    @Test
    public void testDocoExamples910() throws Exception {
     // NOTE : commented out, because HQLSDB doesn't support these queries
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
}
