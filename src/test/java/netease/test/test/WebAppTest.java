package netease.test.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Webapp单元测试
 *
 * @author YourName
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
    @ContextConfiguration(name = "parent", locations = "classpath:META-INF/config/spring/**/*.xml"),
    @ContextConfiguration(name = "child", locations = "classpath:META-INF/web-config/spring/**/*.xml") })
public class WebAppTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(webAppContext)
            .alwaysExpect(status().isOk())
            .alwaysExpect(content().contentType("text/html;charset=utf-8"))
            .alwaysDo(print()).build();
    }

    @Test
    public void controllerMock() throws Exception {
        mockMvc.perform(get("/index.do?words=Hello World!"))
            .andExpect(view().name("index"));
    }

}
