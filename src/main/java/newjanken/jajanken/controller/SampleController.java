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
public class SampleController{

  @Autowired
  private Entry entry;

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
    return "lec02.html";
  }


}
