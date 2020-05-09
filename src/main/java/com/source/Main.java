package com.source;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        final Main main = new Main();

        final SqlSessionFactory factory = main.sqlSessionFactoryByXml();
    }

    public SqlSessionFactory sqlSessionFactoryByXml() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputs = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputs);

        return sqlSessionFactory;
    }

    public SqlSessionFactory sqlSessionFactoryByCode() {
        DataSource dataSource = new PooledDataSourceFactory().getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(XXX.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }

    public void SqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.sqlSessionFactoryByXml();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Object o1 = sqlSession.selectOne("xxx.xxx.XXX.mapper.get", 101);

            final Object mapper = sqlSession.getMapper(Object.class);
//            final Object o2 = mapper.get(101);

        }

    }

}
