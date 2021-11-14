package newjanken.jajanken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import newjanken.jajanken.model.Entry;
import newjanken.jajanken.model.User;
import newjanken.jajanken.model.UserMapper;
import newjanken.jajanken.model.Match;
import newjanken.jajanken.model.MatchMapper;

@Controller
@RequestMapping("/lec02")
public class Sample2Controller{

  @Autowired
  private Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @GetMapping("/janken/{hand}")
  public String janken(@PathVariable String hand, ModelMap model){
    String enemy="gu";
    String judge="";
    if(hand.equals("gu")){
      judge="Draw";
    }else if(hand.equals("cho")){
      judge="You Lose";
    }else if(hand.equals("pa")){
      judge="You Win!";
    }
    model.addAttribute("you",hand);
    model.addAttribute("enemy",enemy);
    model.addAttribute("judge",judge);
    return "lec02.html";
  }

  @GetMapping("selectAll")
  public String selectAll(ModelMap model){
    ArrayList<User> userlist=userMapper.selectAllUser();
    model.addAttribute("userlist",userlist);
    return "lec02.html";
  }

  @GetMapping("allMatch")
  public String allMatch(ModelMap model){
    ArrayList<Match> matchlist=matchMapper.selectAllMatch();
    model.addAttribute("matchlist",matchlist);
    return "lec02.html";
  }

  @GetMapping("match")
  public String match(@RequestParam Integer id,Principal prin, ModelMap model){
    ArrayList<User> user=userMapper.selectUserId(id);
    model.addAttribute("user",user);

    String loginUser = prin.getName();
    model.addAttribute("yourname",loginUser);

    return "match.html";
  }

  @GetMapping("/mjanken/{hand}")
  public String mjanken(@PathVariable String hand, ModelMap model){
    String enemy="gu";
    String judge="";
    if(hand.equals("gu")){
      judge="Draw";
    }else if(hand.equals("cho")){
      judge="You Lose";
    }else if(hand.equals("pa")){
      judge="You Win!";
    }
    model.addAttribute("you",hand);
    model.addAttribute("enemy",enemy);
    model.addAttribute("judge",judge);

    Match x=new Match();
    x.setId(4);
    x.setUser1(1);
    x.setUser2(2);
    x.setUser1Hand(hand);
    x.setUser2Hand(enemy);

    matchMapper.insertMatch(x);

    return "match.html";
  }
}
