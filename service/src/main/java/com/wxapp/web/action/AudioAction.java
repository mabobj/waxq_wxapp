package com.wxapp.web.action;


import com.wxapp.service.iface.AudioIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/audio")
public class AudioAction {

    @Autowired
    private AudioIface excelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) {
        excelService.getList(0);
        return "";
    }
}
