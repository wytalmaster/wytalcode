package com.wytal.commons.reponse;

import java.util.Collections;
import java.util.Map;

import javax.xml.bind.annotation.XmlList;

import com.wytal.commons.seed.service.Type;

public class TypeMap< T extends Type> {
		@XmlList
		private Map<String,T>  data;

		public T getData(String key) {
			return data.get(key);
		}

		public void setData( Map<String,T> data) {
			this.data = data;
		}
		
		public Map<String,T> getData(){
			return Collections.unmodifiableMap(data);
		}
		
		
		

	}
