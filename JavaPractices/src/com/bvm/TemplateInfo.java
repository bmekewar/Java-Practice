package com.bvm;

public class TemplateInfo {
	private String objectId;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	public TemplateInfo() {
		super();
	}

	public TemplateInfo(String objectId) {
		super();
		this.objectId = objectId;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != this.getClass()) {
			result = false;
		} else {
			TemplateInfo templateInfo = (TemplateInfo) object;
			if (this.getObjectId().equals(templateInfo.getObjectId())) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return this.objectId.hashCode();
	}

	@Override
	public String toString() {
		return "TemplateInfo [objectId=" + objectId + "]";
	}
}