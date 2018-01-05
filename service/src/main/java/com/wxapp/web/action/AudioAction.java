package com.wxapp.web.action;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/audio")
public class AudioAction {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) {



        return "";
    }
}
