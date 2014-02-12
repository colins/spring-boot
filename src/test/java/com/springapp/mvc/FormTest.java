package com.springapp.mvc;

/**
 * Created by pivotal on 2/12/14.
 */

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.Assert;
import org.junit.Test;


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

        Assert.assertTrue(page.asText().contains("Smith, Jim\tjim@smith.com"));
    }
}
