package br.com.pipa.common.mapper;

import java.io.Serializable;


public interface BaseEntity<S extends Serializable>{
	
	S getId();
	
}
