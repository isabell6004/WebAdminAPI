package net.fashiongo.webadmin.dao.primary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import net.fashiongo.webadmin.model.primary.Category;

@Projection(name = "loginDateOnly", types = Category.class)
public interface CategoryProjection extends JPAProjection{
    String getCategoryName();

    Integer getCategoryID();

    Integer getParentCategoryID();
    
    Integer getLvl();
}