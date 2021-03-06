package newjanken.jajanken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("select * FROM USERS")
  ArrayList<User> selectAllUser();

  @Select("select * FROM USERS WHERE id=#{id}")
  ArrayList<User> selectUserId(int id);

  @Select("select id from users where name=#{name}")
  User selectUserIdByName(String name);

}
