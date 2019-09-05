package com.marketplace.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hibernate.Hibernate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.marketplace.service.CategoryService;

import com.marketplace.entity.Product;
import com.marketplace.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class JsonServiceImpl implements JsonService {

    @Autowired
    CategoryService categoryService;

    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public String requestToString(HttpServletRequest req) throws IOException {
        return req.getReader().lines().collect(Collectors.joining()).trim();
    }

    @Override
    public boolean isJson(String content) {
        return content.startsWith("{") && content.endsWith("}");
    }

    @Override
    public JSONObject parseRequest(String body) throws ParseException {
        if (isJson(body)) {
            System.out.println("Received JSON request!");
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(body);
        }
        JSONObject jsonObject = new JSONObject();
        String[] lines = body.split("&");
        for (String line : lines) {
            String[]words = line.split("=");
            if(words.length > 1) {
                jsonObject.put(words[0], words[1]);
            } else {
                jsonObject.put(words[0], null);
            }
        }
        return jsonObject;
    }

    @Override
    public void flushJSON(JSONObject json, HttpServletResponse res) throws IOException {
        PrintWriter pw = res.getWriter();
        pw.println(json.toString());
    }

    @Override
    public void flushMessage(String message, HttpServletResponse res) throws IOException {
        PrintWriter pw = res.getWriter();
        JSONObject json = new JSONObject();
        json.put("Message", message);
        pw.println(json.toString());
    }

    /**
     * 2 new functions here to flush customized object(Product that carry required columns only)
     */

    @Override
    public void flushObjects(HttpServletResponse res, List<Object> objects) throws IOException {
        PrintWriter pw = res.getWriter();
        ArrayList<Object> strArr = new ArrayList<>();
        for(Object object: objects) {
            String json = objectWriter.writeValueAsString(object);
            strArr.add(json);
        }
        pw.println(Arrays.toString(strArr.toArray()));
    }

    @Override
    public void flushObject(HttpServletResponse res, Object object) throws IOException {
        PrintWriter pw = res.getWriter();
        String json = objectWriter.writeValueAsString(object);
        pw.println(json);
    }

    @Override
    public void flushCategories(HttpServletResponse res, List<Category> categories) throws IOException {
        PrintWriter pw = res.getWriter();
        ArrayList<String> strArr = new ArrayList<>();
        for (Category category : categories) {
            String json = objectWriter.writeValueAsString(category);
            strArr.add(json);
        }
        pw.println(Arrays.toString(strArr.toArray()));
    }

    @Override
    public void flushCategory(HttpServletResponse res, int categoryId) throws IOException {
        PrintWriter pw = res.getWriter();
        Category category = categoryService.getCategory(categoryId);
        if(category != null) {
            String json = objectWriter.writeValueAsString(category);
            pw.println(json);
        } else {
            flushMessage("Category id not found!", res);
        }
    }

    @Override
    public void flushProducts(HttpServletResponse res, List<Product> products) throws IOException {
        PrintWriter pw = res.getWriter();
        ArrayList<String> strArr = new ArrayList<>();
        for (Product product : products) {
            String json = objectWriter.writeValueAsString(product);
            strArr.add(json);
        }
        pw.println(Arrays.toString(strArr.toArray()));
    }

    @Override
    public void flushProduct(HttpServletResponse res, int productId) throws IOException {
        PrintWriter pw = res.getWriter();
        Product product = categoryService.getProduct(productId);
        if(product != null) {
            String json = objectWriter.writeValueAsString(product);
            pw.println(json);
        } else {
            flushMessage("Product id not found!", res);
        }
    }

}
