
package com.cms.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	
	private String courseId;
	
	private String courseName;
	private Integer fees;
	private Integer duration;
	
	private String courseType;
	private float rating;

	
}
