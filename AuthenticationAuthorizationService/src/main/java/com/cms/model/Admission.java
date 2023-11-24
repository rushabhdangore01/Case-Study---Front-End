package com.cms.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admission implements Serializable{
	
	private long registrationId;	
	private String courseId	;
	private String associateId	;
	private int fees;	
	private String feedback	;
	
}




