package com.auction.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.auction.app.model.Category;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {
    Category findByTitle(String title);
}
