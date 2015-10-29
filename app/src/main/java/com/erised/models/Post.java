
package com.erised.models;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public Integer id;
    public String slug;
    public String url;
    public String status;
    public String title;
    public String title_plain;
    public String content;
    public String excerpt;
    public String date;
    public String modified;
    public List<Object> categories = new ArrayList<Object>();
    public List<Object> tags = new ArrayList<Object>();
    public Author author;
    public List<Object> comments = new ArrayList<Object>();
    public List<Object> attachments = new ArrayList<Object>();
    public Integer comment_count;
    public String comment_status;
    public String thumbnail;
    public Custom_fields custom_fields;
    public String thumbnail_size;
    public Thumbnail_images thumbnail_images;
    public List<Taxonomy_job_listing_region> taxonomy_job_listing_region = new ArrayList<Taxonomy_job_listing_region>();
    public List<Taxonomy_job_listing_category> taxonomy_job_listing_category = new ArrayList<Taxonomy_job_listing_category>();
    public List<Object> taxonomy_job_listing_type = new ArrayList<Object>();

}
