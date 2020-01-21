package org.apache.ibatis.exercise;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.exercise.mapper.OperationMapper;
import org.apache.ibatis.exercise.mapper.OperationMapper.User;
import org.apache.ibatis.exercise.plugins.TestPluginsExecutor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.submitted.permissions.Resource;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/21 10:53
 */
public class BaseOperationTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeClass
  public static void setup() throws IOException {

    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    //配置文件
    Reader reader = Resources
        .getResourceAsReader("org/apache/ibatis/exercise/resources/mybatis-config.xml");
    sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
    Configuration configuration = sqlSessionFactory.getConfiguration();
    configuration.addInterceptor(new TestPluginsExecutor());
    configuration.setCacheEnabled(false);
    configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
    configuration.setDefaultExecutorType(ExecutorType.BATCH);
    reader.close();
    //SqlSession sqlSession = sqlSessionFactory.openSession();
    //脚本文件
    /*ScriptRunner scriptRunner = new ScriptRunner(sqlSession.getConnection());
    //
    reader = Resources.getResourceAsReader("org/apache/ibatis/exercise/resources/CreateDB.sql");
    scriptRunner.setLogWriter(null);
    scriptRunner.runScript(reader);
    reader.close();
    sqlSession.close();*/
  }

  @Test
  public void testSelect() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    ResultHandler resultHandler = new DefaultResultHandler();
    List<Object> objects = sqlSession
        .selectList("org.apache.ibatis.exercise.mapper.OperationMapper.selectAll");
    System.out.println();
  }

}
