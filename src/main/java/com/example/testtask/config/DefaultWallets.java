package com.example.testtask.config;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class DefaultWallets implements CustomTaskChange {
    @Override
    public String getConfirmationMessage() {
        return null;
    }
    @Override
    public void setUp() throws SetupException {
    }
    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
    }
    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
    @Override
    public void execute(Database database) throws CustomChangeException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(((JdbcConnection)database.getConnection()).getUnderlyingConnection(), false));
        jdbcTemplate.update("INSERT INTO wallets (uuid, balance) VALUES(?, ?)", new Object[] { java.util.UUID.randomUUID(), 1000});
        jdbcTemplate.update("INSERT INTO wallets (uuid, balance) VALUES(?, ?)", new Object[] { java.util.UUID.randomUUID(), 10000});
        jdbcTemplate.update("INSERT INTO wallets (uuid, balance) VALUES(?, ?)", new Object[] { java.util.UUID.randomUUID(), 100000});
    }
}
