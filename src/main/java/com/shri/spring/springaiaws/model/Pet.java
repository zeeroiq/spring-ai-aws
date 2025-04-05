package com.shri.spring.springaiaws.model;

import org.springframework.data.annotation.Id;

public record Pet(@Id Long id,
                  String name,
                  String owner,
                  String description) {
}
