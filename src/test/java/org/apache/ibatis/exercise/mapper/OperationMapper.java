package org.apache.ibatis.exercise.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/21 11:02
 */
public interface OperationMapper {

  @Select(value = "select * from test_1 where id =#{id}")
  User select(int id);

  @Select(value = "select * from test_1")
  List<User> selectAll();

  @Update(value = "update test_1 set name=#{name} where id=#{id}")
  void update(int id, String name);

  class User {

    private Integer id;
    private String name;

    public Integer getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
