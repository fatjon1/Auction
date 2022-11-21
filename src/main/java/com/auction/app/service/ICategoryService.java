package com.auction.app.service;


import com.auction.app.model.Category;

import java.util.List;

public interface ICategoryService {

 List<Category> getCategories();

 void save(Category category);
}
