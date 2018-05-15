package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping(value = "")  //set Route for home page
    @ResponseBody  //Return plain text
    public String index(HttpServletRequest request)
    {
        String name = request.getParameter("name");

        if (name != null) {
            return "Hello " + name + "!";
        }
        else
        {
            return  "Hello World!";
        }
    }

    @RequestMapping(value = "goodbye")  //set Route for '/goodbye'.
    public String goodbye()
    {
        return "redirect:/";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)  //set get Route for '/hello'.
    @ResponseBody  //Return plain text
    public String helloForm()
    {
        String html= "<form method='POST'>" +
                "<input type='text' name='name' />" +
                "<select name='lang'>" +
                "<option value='eng' selected>English</option>" +
                "<option value='frn'>French</option>" +
                "<option value='spn'>Spanish</option>" +
                "<option value='jpn'>Japanese</option>" +
                "<option value='ger'>German</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me' />" +
                "</form>";

        return  html;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)  //set post Route for '/hello'.
    @ResponseBody  //Return plain text
    public String helloPost(HttpServletRequest request)
    {
        String name = request.getParameter("name");
        String language = request.getParameter("lang");


        if (name != null && !(name.equals(""))) {
            return  "<h2>" + createMessage(name, language) + "</h2>";
        }
        else
        {
            return  "Hello World!";
        }
    }

    public static  String createMessage(String name, String language)
    {
        String greeting;

        switch (language)
        {
            case "eng":
                greeting = "Hello";
                break;
            case "frn":
                greeting = "Bonjour";
                break;
            case "spn":
                greeting = "Hola";
                break;
            case "jpn":
                greeting = "Ohayo";
                break;
            case "ger":
                greeting = "Guten tag";
                break;
            default:
                greeting = "Hello";
        }

        return greeting + " " + name + "!";
    }

    @RequestMapping(value = "hello/{name}")  //set Route for '/hello/{name}'.
    @ResponseBody  //Return plain text
    public String helloUrlSegment(@PathVariable String name)
    {
        return "Hello " + name + "!";
    }
}
