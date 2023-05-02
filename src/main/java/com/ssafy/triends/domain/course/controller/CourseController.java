package com.ssafy.triends.domain.course.controller;

import com.ssafy.triends.domain.course.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	private final Logger logger = LoggerFactory.getLogger(CourseController.class);
	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}
	
}
