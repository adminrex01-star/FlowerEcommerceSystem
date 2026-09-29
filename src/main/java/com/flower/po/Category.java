package com.flower.po;

public class Category {
    private Integer categoryId;
    private String categoryName;
    private Integer sort;
    private String description;

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public Integer getSort() { return sort; }
    public void setSort(Integer sort) { this.sort = sort; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}