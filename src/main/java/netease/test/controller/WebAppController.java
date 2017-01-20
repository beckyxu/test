package netease.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import netease.test.service.WebAppService;

/**
 * Controller
 * 
 * @author YourName
 */
@Controller
public class WebAppController {

    private static final Logger logger = LoggerFactory
        .getLogger(WebAppController.class);

    @Autowired
    private WebAppService webAppService;

    @RequestMapping("/index.do")
    public String index(@RequestParam("words") String words) {
        logger.debug("[opt:index]");
        webAppService.handle(words);
        return "index";
    }

    @RequestMapping("/index1.do")
    public String index1() {
        logger.debug("[opt:index1]");
        return "index1";
    }

}
