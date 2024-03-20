package com.example.joblisting.repository;

import com.example.joblisting.model.Post;
import com.sun.jdi.StringReference;

import java.util.List;

public interface SearchRepository {
     List<Post>findByText(String text);
}
