package com.application.library.entity;

public enum Category {

	NARRATIVE, POETRY, THEATER, EPISTOLARY, ARGUMENTATIVE;

	public static Category from(final String value) {
		for (final Category category : values()) {
			if (category.name().equals(value)) {
				return category;
			}
		}
		return null;
	}
}
