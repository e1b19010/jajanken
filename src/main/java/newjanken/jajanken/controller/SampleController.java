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
public class SampleController{

  @Autowired
  private Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;

  /**
   * @param model
   * @param name
   * @return
   */
  @PostMapping("/form")
  public String form(@RequestParam String name, ModelMap model){
    String yourname=name;
    model.addAttribute("yourname",yourname);
    return "lec02.html";
  }

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

  @GetMapping("/lec02")
  public String lec02(ModelMap model, Principal prin){
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);

    model.addAttribute("entry",this.entry);
    model.addAttribute("yourname",loginUser);

    ArrayList<User> userlist=userMapper.selectAllUser();
    model.addAttribute("userlist",userlist);

    ArrayList<Match> matchlist=matchMapper.selectAllMatch();
    model.addAttribute("matchlist",matchlist);

    ArrayList<MatchInfo> matchtrue=matchInfoMapper.selectActiveMatch();
    model.addAttribute("matchtrue",matchtrue);
    return "lec02.html";
  }


}
