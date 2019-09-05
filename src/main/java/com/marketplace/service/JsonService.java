package com.marketplace.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.marketplace.entity.Product;
import com.marketplace.entity.Category;

public interface JsonService {
    String requestToString(HttpServletRequest req) throws IOException;
    boolean isJson(String content);
    JSONObject parseRequest(String body) throws ParseException;

    void flushJSON(JSONObject json, HttpServletResponse res) throws IOException;
    void flushMessage(String message, HttpServletResponse res) throws IOException;

    void flushObjects(HttpServletResponse res, List<Object> objects) throws IOException;
    void flushObject(HttpServletResponse res, Object object) throws IOException;

    void flushCategories(HttpServletResponse res, List<Category> categories) throws IOException;
    void flushCategory(HttpServletResponse res, int categoryId) throws IOException;

    void flushProducts(HttpServletResponse res, List<Product> products) throws IOException;
    void flushProduct(HttpServletResponse res, int productId) throws IOException;
}
