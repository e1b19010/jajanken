package newjanken.jajanken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import newjanken.jajanken.model.Entry;

@Controller
@RequestMapping("/lec02")
public class Sample2Controller{

  @Autowired
  private Entry entry;

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
}
