package com.wxapp.web.action;

import com.wxapp.frame.base.BaseAction;
import com.wxapp.service.iface.ParentingIface;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/parenting")
public class ParentingAction extends BaseAction {
    @Autowired
    private ParentingIface excelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody String getList(int rowNo, Model model) {
        return JSONArray.fromObject(excelService.getList(rowNo)).toString();
    }
}
