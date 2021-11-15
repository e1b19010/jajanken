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
import newjanken.jajanken.model.MatchInfo;
import newjanken.jajanken.model.MatchInfoMapper;

@Controller
@RequestMapping("/lec02")
public class Sample2Controller{

  @Autowired
  private Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;

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
  public String mjanken(@PathVariable String hand,ModelMap model,Principal prin){
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

    String loginUser=prin.getName();
    model.addAttribute("yourname",loginUser);

    User user1=userMapper.selectUserIdByName(loginUser);


    MatchInfo matchinfo2=new MatchInfo();
    matchinfo2.setUser1(user1.getId());
    matchinfo2.setUser2(1);
    matchinfo2.setUser1Hand(hand);
    matchinfo2.setIsActive(true);
    matchInfoMapper.insertMatchInfo(matchinfo2);
    model.addAttribute("matchinfo",matchinfo2);
    return "wait.html";

  }

  @PostMapping("matchinfo")
  @Transactional
  public String matchinfo(@RequestParam Integer user1id,@RequestParam Integer user2id, ModelMap model, Principal prin){
    String loginUser=prin.getName();
    MatchInfo matchinfo=new MatchInfo();
    matchinfo.setUser1(user1id);
    matchinfo.setUser2(user2id);
    model.addAttribute("yourname",loginUser);
    return "wait.html";
  }
}
