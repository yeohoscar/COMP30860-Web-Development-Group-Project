package com.yysw;

import com.yysw.general.AIModel;
import com.yysw.general.AIModelRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DatabaseTest {
    @Test
    void contextLoads() {
        System.out.println("project running");
    }
    @Autowired
    private DataSource dataSrc;

    @Test
    void dataConnectCheck() throws SQLException {
        System.out.println(dataSrc.getConnection());
    }
}
