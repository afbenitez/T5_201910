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
		int resta = numberOfRegisters - object.numberOfRegisters;
		
		if(resta == 0)
		{
			return location.compareTo(object.darLocation());
		}
		else return resta;
	}

	
}
