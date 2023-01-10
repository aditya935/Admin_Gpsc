package com.techwithadi.gpscbudy.Models;

public class CategoryModel {
    public CategoryModel() {

    }

    private String docid;
    private String cat_name;
    private Long no_of_test;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Long getNo_of_test() {
        return no_of_test;
    }

    public void setNo_of_test(Long no_of_test) {
        this.no_of_test = no_of_test;
    }

    public CategoryModel(String docid, String cat_name, Long no_of_test) {
        this.docid = docid;
        this.cat_name = cat_name;
        this.no_of_test = no_of_test;
    }
}
