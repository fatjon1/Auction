package com.auction.app.service;

import com.auction.app.model.Category;
import com.auction.app.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    @NonNull protected CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {

        categoryRepository.save(category);
    }
}
