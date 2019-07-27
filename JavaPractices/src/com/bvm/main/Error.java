package com.bvm.main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error
{

	@javax.validation.constraints.DecimalMin(value="100")
	@javax.validation.constraints.DecimalMax(value="599")
	@javax.validation.constraints.NotNull
	private java.lang.Integer _status;

	@javax.validation.constraints.Pattern(regexp="[a-z]+[a-z_]*[a-z]+")
	@javax.validation.constraints.NotNull
	private java.lang.String _type;

	private java.lang.String _message;

	private java.net.URI _moreInfo;

	@javax.validation.Valid
	//private java.util.List<Detail> _details;
	
	public java.lang.Integer getStatus()
	{
		return _status;
	}
	
	public java.lang.String getType()
	{
		return _type;
	}
	
	public java.lang.String getMessage()
	{
		return _message;
	}
	
	public java.net.URI getMoreInfo()
	{
		return _moreInfo;
	}
	
/*	public java.util.List<Detail> getDetails()
	{
		return _details;
	}
*/


	public void setStatus(final java.lang.Integer _status)
	{
		this._status = _status;
	}

	public void setType(final java.lang.String _type)
	{
		this._type = _type;
	}

	public void setMessage(final java.lang.String _message)
	{
		this._message = _message;
	}

	public void setMoreInfo(final java.net.URI _moreInfo)
	{
		this._moreInfo = _moreInfo;
	}

/*	public void setDetails(final java.util.List<Detail> _details)
	{
		this._details = _details;
	}*/

	@Override
	public String toString() {
		return "Error [_status=" + _status + ", _type=" + _type + ", _message="
				+ _message + "]";
	}
	
	public static Error createNew(){
		return new Error();
	}

	public Error add(String message){
		setMessage(message);
		return this;
	}
}
