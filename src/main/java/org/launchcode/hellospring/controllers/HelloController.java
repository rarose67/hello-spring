package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.hellospring.CompareTest;
import org.launchcode.hellospring.CompareType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
            //String compare = CompareType.EQUAL.name();
            //String value = CompareType.EQUAL.getName();
            CompareType[] values = CompareType.values();
            String valueString = "";

            for (int i=0; i<values.length; i++)
            {
                valueString += values[i] + " : " + values[i].getName();
                valueString += " || ";
            }

            return valueString;
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

    @RequestMapping(value = "add", method = RequestMethod.GET)  //set get Route for '/add'.
    public String addForm(Model model)
    {
        CompareType[] types = CompareType.values();

        model.addAttribute("types", types);
        model.addAttribute("compareTest", new CompareTest());

        return "add-oo";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)  //set post Route for '/add'.
    //@ResponseBody  //Return plain text
    public String addPost(@ModelAttribute @Valid CompareTest newTest, Errors errors, Model model)
    {
        String response = "";

        if (errors.hasErrors())
        {
            CompareType[] types = CompareType.values();

            model.addAttribute("types", types);

            return "add-oo";
        }

        CompareTest test = new CompareTest(newTest.getNum2(), newTest.getDec(), newTest.getDay(),
                newTest.getMonth(), newTest.getYear(), newTest.getIntComp(), newTest.getFloatComp(),
                newTest.getDateComp());

        if (test.getIntComp().getName().equals(CompareType.NONE.getName()))
        {
            response += "<p> newInt " + test.getNum2() + " wasn't compared.</p>";
        }
        else if(CompareTest.compareInt(test.getNum2(), test.getIntComp()))
        {
            response += "<p> newInt " + test.getNum2() + " is " + test.getIntComp().name() + "</p>";
        }
        else
        {
            response += "<p> newInt " + test.getNum2() + " is not " + test.getIntComp().name() + "</p>";
        }

        if (test.getFloatComp().getName().equals(CompareType.NONE.getName()))
        {
            response += "<p> newFloat " + test.getDec() + " wasn't compared.</p>";
        }
        else if(CompareTest.compareDec(test.getDec(), test.getFloatComp()))
        {
            response += "<p> newFloat " + test.getDec() + " is " + test.getFloatComp().name() + "</p>";
        }
        else
        {
            response += "<p> newFloat " + test.getDec() + " is not " + test.getFloatComp().name() + "</p>";
        }

        //GregorianCalendar newDate = new GregorianCalendar(year, (month-1), day);

        if (test.getDateComp().getName().equals(CompareType.NONE.getName()))
        {
            response += "<p> newDate " + test.getDate1().get(GregorianCalendar.DATE) + " wasn't compared.</p>";
        }
        else if(CompareTest.compareDate(test.getDate1(), test.getDateComp()))
        {
            response += "<p> newDate is " + test.getDateComp().name() + "</p>";
        }
        else
        {
            response += "<p> newDate is not " + test.getDateComp().name() + "</p>";
        }

        return response;
    }

}
