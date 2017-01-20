package netease.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import netease.test.service.WebAppService;

/**
 * 缺省WebAppService实现
 * 
 * @author RUIZ
 */
@Service
public class WebAppServiceImpl implements WebAppService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(String words) {
        logger.debug("[op:handle_input, words:" + words + "]");
        System.out.println("you said: \"" + words + "\"");
    }

}
