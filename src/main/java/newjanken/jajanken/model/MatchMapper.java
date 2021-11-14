package newjanken.jajanken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper{

  @Select("select id,user1,user2,user1Hand,user2Hand FROM MATCHES")
  ArrayList<Match> selectAllMatch();

  @Select("select * FROM users where id=#{id}")
  ArrayList<Match> selectUserId(int id);

  @Insert("insert into matches (id,user1,user2,user1Hand,user2Hand) values (#{id},#{user1},#{user2},#{user1Hand},#{user2Hand});")
  void insertMatch(Match match);
}
