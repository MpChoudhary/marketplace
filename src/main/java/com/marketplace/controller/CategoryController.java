package com.marketplace.controller;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import com.marketplace.entity.*;
import com.marketplace.service.CategoryService;
import com.marketplace.service.JsonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("Duplicates")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Transactional
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = "/CategoryHandler", method = RequestMethod.POST)
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
        if(request.equalsIgnoreCase("new_category")) {
            return Action.ADD;
        }
        if(request.equalsIgnoreCase("get_all")) {
            return Action.GET_ALL;
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
        Category category;

        switch (action) {
            case ADD:
                newCategory(jsonObject);
                jsonService.flushMessage("Successfully added new category", res);
                break;

            case DELETE:
                int id = Integer.parseInt(((String) jsonObject.get("request")).substring(6));
                category = categoryService.getCategory(id);

                if(category != null) {
                    if(category.getProducts() != null) {
                        for (Product product : category.getProducts()) {
                            categoryService.deleteProduct(product.getId());
                        }
                    }
                    categoryService.deleteCategory(id);
                    jsonService.flushMessage("Successfully deleted", res);
                } else {
                    jsonService.flushMessage("Project id not found!", res);
                }
                break;

            case GET_ALL:
                jsonService.flushCategories(res, categoryService.getCategories());
                break;

            case FIND:
                int key = Integer.parseInt(((String) jsonObject.get("request")).substring(4));
                jsonService.flushCategory(res, key);
                break;

            case NOT_FOUND:
            default:
                jsonService.flushMessage("Error, Category not found", res);
        }
    }

    private void newCategory(JSONObject jsonObject) {
        Category c = new Category((String)jsonObject.get("name"));
        categoryService.saveCategory(c);
    }

}
