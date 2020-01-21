package org.apache.ibatis.exercise.plugins;

import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.hsqldb.Row;

/**
 * @author: TangFenQi
 * @description: 测试插件
 * @date：2020/1/21 13:53
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class TestPluginsExecutor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("intercept method name: " + invocation.getMethod().getName());
    System.out.println("intercept method arguments size: " + invocation.getArgs().length);
    RowBounds bounds = new RowBounds(1, 2);
    invocation.getArgs()[2] = bounds;
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
    System.out.println("set properties！");
  }
}
