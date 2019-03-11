package model.vo;
import org.apache.commons.lang3.text.translate.NumericEntityEscaper;

public class LocationVO implements Comparable<LocationVO>
{
	private int addressId;
	private String location;
	private int numberOfRegisters;
	
	public LocationVO(int pAddressId, String pLocation, int pNumberOfRegisters)
	{
		addressId = pAddressId;
		location = pLocation;
		numberOfRegisters = pNumberOfRegisters;		
	}
	
	public int darAddressId()
	{
		return addressId;
	}
	
	public String darLocation()
	{
		return  location;
	}
	
	public int darNumberOfRegisters()
	{
		return numberOfRegisters;
	}
	
	public int compareTo(LocationVO object)
	{
		if (this.numberOfRegisters < object.numberOfRegisters)
			return -1;
		else if (this.numberOfRegisters > object.numberOfRegisters)
			return 1;
		else 
			return (this.location.compareTo(object.location));
	}

	
}
