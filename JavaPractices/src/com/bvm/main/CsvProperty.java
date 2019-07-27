package com.bvm.main;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generated dto.
 */
@XmlRootElement
public class CsvProperty {

	private java.lang.String _propertyName;

	private boolean isSeparator;

	public CsvProperty(String _propertyName) {
		super();
		this._propertyName = _propertyName;
	}

	public boolean isSeparator() {
		return isSeparator;
	}

	public void setSeparator(boolean isSeparator) {
		this.isSeparator = isSeparator;
	}

	public java.lang.String getPropertyName() {
		return _propertyName;
	}

	public void setPropertyName(final java.lang.String _propertyName) {
		this._propertyName = _propertyName;
	}

	@Override
	public String toString() {
		return "CsvProperty [_propertyName=" + _propertyName + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CsvProperty){
			super.equals(obj);
			//obj = (CsvProperty)obj;
			return this._propertyName.equals(((CsvProperty) obj).getPropertyName()) ? true : false;
		} else 
			return false;
	}

}
