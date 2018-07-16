package com.pilicon.client.jpa;


import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilTest {

    @Test
    public void testGetConnection() throws SQLException, ClassNotFoundException {
        Connection connection=JDBCUtil.getConnection();
        Assert.assertNotNull(connection);
    }
}
