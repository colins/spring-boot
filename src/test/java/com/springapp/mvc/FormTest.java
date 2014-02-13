package com.springapp.mvc;

/**
 * Created by pivotal on 2/12/14.
 */

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.googlecode.flyway.test.annotation.FlywayTest;
//import com.googlecode.flyway.*;
import com.googlecode.flyway.test.junit.FlywayTestExecutionListener;
import org.aspectj.lang.annotation.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.net.SocketPermission;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-dispatcher-servlet.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
@FlywayTest
public class FormTest {
    @Test
    public void homePage() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("http://localhost:3000/");
        Assert.assertTrue(page.asText().contains("Users"));
    }

    @Test
    public void postForm() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("http://localhost:3000/");
        Assert.assertTrue(page.asText().contains("Users"));

        final HtmlForm form = page.getFormByName("user");
        final HtmlSubmitInput button = form.getInputByValue("Add User");

        final HtmlTextInput firstName = form.getInputByName("firstName");
        firstName.setValueAttribute("Jim");
        final HtmlTextInput lastName = form.getInputByName("lastName");
        lastName.setValueAttribute("Smith");
        final HtmlTextInput email = form.getInputByName("email");
        email.setValueAttribute("jim@smith.com");

        final HtmlPage page2 = button.click();


        Assert.assertTrue(page2.asText().contains("Smith, Jim\tjim@smith.com"));
    }
}
