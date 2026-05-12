package com.sabic.explorworld.utils;

import java.util.List;

public class SQLUtils {

	public static final void addClause(Object criteriaParameter, List<String> conditions,
										String condition, List<Object> parameterValues,
										Object parameterValue) { 

		if (criteriaParameter!=null) {
			conditions.add(condition);
			parameterValues.add(parameterValue);
		}
	}
}
