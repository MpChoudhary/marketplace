package com.marketplace.controller;

import com.google.gson.Gson;
import com.marketplace.entity.Action;
import com.marketplace.entity.Category;
import com.marketplace.entity.Product;
import com.marketplace.service.CategoryService;
import com.marketplace.service.JsonService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Transactional
public class ProductController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JsonService jsonService;


    @RequestMapping(value = "/ProductHandler", method = RequestMethod.POST)
    public void categoryPostHandler(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ParseException {
        String body = jsonService.requestToString(req);
        JSONObject jsonObject = jsonService.parseRequest(body);

        if (jsonService.isJson(body)) {
            String requestType = (String) jsonObject.get("request");
            Action action = validateRequestType(requestType);
            requestHandler(jsonObject, action, res);
        }
    }

    private Action validateRequestType(String request) {
        if(request.equalsIgnoreCase("new_product")) {
            return Action.ADD;
        }
        if(request.equalsIgnoreCase("get_all")) {
            return Action.GET_ALL;
        }
        if(request.equalsIgnoreCase("get_all_with_columns")) {
            return Action.GET_ALL_WITH_COLUMNS;
        }
        if(request.equalsIgnoreCase("find_with_columns")) {
            return Action.FIND_WITH_COLUMNS;
        }
        if(request.startsWith("find")) {
            return Action.FIND;
        }
        if(request.startsWith("delete")) {
            return Action.DELETE;
        }
        return Action.NOT_FOUND;
    }

    private void requestHandler(JSONObject jsonObject, Action action, HttpServletResponse res) throws IOException {
        Product product;
        String selectedColumns;

        switch (action) {
            case ADD:
                Category category = categoryService.getCategory(getCategoryId(jsonObject));
                newProduct(jsonObject, category);
                jsonService.flushMessage("Successfully added new product!", res);
                break;

            case DELETE:
                int id = Integer.parseInt(((String) jsonObject.get("request")).substring(6));
                product = categoryService.getProduct(id);

                if(product != null) {
                    categoryService.deleteProduct(id);
                    jsonService.flushMessage("Successfully deleted " + id + "!", res);
                } else {
                    jsonService.flushMessage("Product id " + id + " not found!", res);
                }
                break;

            case GET_ALL:
                jsonService.flushProducts(res, categoryService.getProducts());
                break;

            case GET_ALL_WITH_COLUMNS:
                selectedColumns = cleanupColumn((String)jsonObject.get("columns"));
                jsonService.flushObjects(res, categoryService.getProducts(selectedColumns));
                break;

            case FIND:
                int key = Integer.parseInt(((String) jsonObject.get("request")).substring(4));
                jsonService.flushProduct(res, key);
                break;

            case FIND_WITH_COLUMNS:
                selectedColumns = cleanupColumn((String)jsonObject.get("columns"));
                int productId = Integer.parseInt(((String) jsonObject.get("id")));
                jsonService.flushObject(res, categoryService.getProduct(productId, selectedColumns));
                break;

            case NOT_FOUND:
            default:
                jsonService.flushMessage("Error, Product not found!", res);
        }
    }

    private int getCategoryId(JSONObject jsonObject) {
        return Integer.parseInt((String) jsonObject.get("category_id"));
    }

    private String cleanupColumn(String columns) {
        return columns.replaceAll("\\[", "")
                      .replaceAll("\\]", "")
                      .replaceAll("\\'", "")
                      .replaceAll("\"", "");
    }

    private void newProduct(JSONObject jsonObject, Category category) {
        jsonObject.remove("request");
        jsonObject.remove("category_id");
        Gson gson= new Gson();
        Product p = gson.fromJson(jsonObject.toString(),Product.class);
        p.setCategory(category);
        categoryService.saveProduct(p);
    }

}
