/*
 * Copyright 2015, The Querydsl Team (http://www.querydsl.com/team)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.querydsl.sql.mssql;

import java.sql.Connection;

import javax.inject.Provider;

import com.querydsl.sql.AbstractSQLQueryFactory;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLServerTemplates;
import com.querydsl.sql.SQLTemplates;

/**
 * SQL Server specific implementation of SQLQueryFactory
 *
 * @author tiwe
 *
 */
public class SQLServerQueryFactory extends AbstractSQLQueryFactory<SQLServerQuery<?>> {

    public SQLServerQueryFactory(Configuration configuration, Provider<Connection> connection) {
        super(configuration, connection);
    }

    public SQLServerQueryFactory(Provider<Connection> connection) {
        this(new Configuration(new SQLServerTemplates()), connection);
    }

    public SQLServerQueryFactory(SQLTemplates templates, Provider<Connection> connection) {
        this(new Configuration(templates), connection);
    }

    @Override
    public SQLServerQuery<?> query() {
        return new SQLServerQuery<Void>(connection, configuration);
    }

}
