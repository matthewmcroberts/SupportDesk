package com.matthewmcroberts.supportdesk.repository;

import com.matthewmcroberts.supportdesk.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
