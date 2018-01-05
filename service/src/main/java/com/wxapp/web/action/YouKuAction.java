package com.wxapp.web.action;


import com.wxapp.frame.base.BaseAction;
import com.wxapp.frame.util.HttpTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/yk")
public class YouKuAction extends BaseAction {

    @RequestMapping(value = "/gotoPage", method = RequestMethod.GET)
    public String gotoPage(Model model, String uri) {
        String url = uri;

        try {
            url = URLDecoder.decode(uri, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String domain = url.substring(0, url.indexOf("/", 9));
        logger.debug("domain == " + domain);

        HttpTools ht = new HttpTools();
        ht.setUrl(url);
        logger.debug(url);

        ht.addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.3; en-us; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        String html = ht.getInnerHTML();

        Pattern p = Pattern.compile("target=\"(.[^\\\"]*)?\"");
        Matcher m = p.matcher(html);
        html = m.replaceAll("");

        p = Pattern.compile("<a(.*)?href=\"(.[^\\\"]*)?\"[\\s\\S]*?>[\\s\\S]*?</a>");
        m = p.matcher(html);

        String t;
        while (m.find()) {
            t = m.group(2);
            if (t.indexOf("//") == 0) {
                t = "http:" + t;
            } else if (t.indexOf("/") == 0 && !t.substring(1, 2).equals("/")) {
                t = domain + t;
            }
            logger.debug(m.group(2) + " == " + t);
            t = m.group().replaceAll(m.group(2).replaceAll("\\.", "\\\\.").replaceAll("\\?", "\\\\?"),
                    "http://localhost:8080/yk/gotoPage?uri=" + t);
            logger.debug(t);
            html = html.replace(m.group(), t);
        }


        p = Pattern.compile("<script(.*)?src=\"(.[^\\\"]*?)\"([\\s\\S]*?)>([\\s\\S]*?)<(\\\\/|/){1}script>");
        m = p.matcher(html);
        while (m.find()) {
            t = m.group(2);
            if (t.indexOf("/") != 0) {
                t = url.substring(0, url.lastIndexOf("/")) + "/" + t;
            }
            t = m.group().replaceAll(m.group(2).replaceAll("\\.", "\\\\.").replaceAll("\\?", "\\\\?"),
                    t);
            html = html.replace(m.group(), t);
        }


        p = Pattern.compile("<link(.*?)href=\"(.[^\\\"]*?)\"([\\s\\S]*?)(/){0,1}>");
        m = p.matcher(html);
        while (m.find()) {
            t = m.group(2);
            if (t.indexOf("/") != 0) {
                t = url.substring(0, url.lastIndexOf("/")) + "/" + t;
            }
            t = m.group().replaceAll(m.group(2).replaceAll("\\.", "\\\\.").replaceAll("\\?", "\\\\?"),
                    t);
            html = html.replace(m.group(), t);
        }


        model.addAttribute("html", html);

        return "page";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> h = request.getHeaderNames();
        while (h.hasMoreElements()) {
            System.out.println(request.getHeader(h.nextElement()));
        }
        return "";
    }
}
