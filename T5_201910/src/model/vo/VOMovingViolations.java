package model.vo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations implements Comparable<VOMovingViolations> 
{

	private int objectID;
	
	private String location;
	
	private int fineAmt;
	
	private boolean accident; 
	
	private String ticketIssueDate;
	
	private double totalPaid;
	
	private String accidentIndicator;
	
	private String violationDescription;
	
	private String violationCode;
	
	private String addressID;
	
	private int streetID;
	
	
	

	public VOMovingViolations(int pId, String pLocation,int pFineAmt, boolean pAccident, String pTicketIssueDate, double pTotalPaid, String pAccidentIndicator, String pViolationDescription,String pViolationCode, String pAddressID, int pstreetID)
	{
		objectID=pId;
		location=pLocation;
		fineAmt = pFineAmt;
		accident = pAccident;
		ticketIssueDate = pTicketIssueDate;
		totalPaid = pTotalPaid;
		accidentIndicator = pAccidentIndicator;
		violationDescription = pViolationDescription;
		violationCode = pViolationCode;
		addressID = pAddressID;
		streetID = pstreetID;
	}

	/**
	 * @return id - Identificador �nico de la infracci�n
	 */
	public int objectId() 
	{
		return objectID;
	}	
	
	/**
	 * @return location - Direcci�n en formato de texto.
	 */
	public String getLocation() 
	{
		return location;
	}
	
	public int getFineAMT()
	{
		return fineAmt ;
	}

	public boolean accident()
	{
		return accident;
	}
	
	/**
	 * @return date - Fecha cuando se puso la infracci�n .
	 */
	public String getTicketIssueDate() 
	{
		return ticketIssueDate;
	}

	/**
	 * @return totalPaid - Cuanto dinero efectivamente pag� el que recibi� la infracci�n en USD.
	 */
	public double getTotalPaid() 
	{
		return totalPaid;
	}
	
	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public String  getAccidentIndicator() 
	{
		return accidentIndicator;
	}

	/**
	 * @return description - Descripci�n textual de la infracci�n.
	 */
	public String  getViolationDescription() 
	{
		return violationDescription;
	}

	public String getViolationCode()
	{
		return violationCode;
	}
	
	public String getAddressId()
	{
		return addressID;
	}
	
	public int getStreetSegId()
	{
		return streetID;
	}

	
	public int compareTo(VOMovingViolations pComparar) 
	{
		if(ticketIssueDate.compareTo(pComparar.getTicketIssueDate())==0)
		{
			return (objectID -pComparar.objectId());
		}

		else return ticketIssueDate.compareTo(pComparar.getTicketIssueDate());
	}

	public String toString()
	{
		return "Object ID: " + objectID + " Location: " + location + " Ticket Issue Date: " + ticketIssueDate + " Total Paid: " + totalPaid + " Accident Indicator: " + accidentIndicator + " Violation Description: " + violationDescription + " Violation Code: " + violationCode;
	}

	public static class comparatorDate implements Comparator <VOMovingViolations>
	{
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1 )
		{
			LocalDateTime fecha1 = LocalDateTime.parse(arg0.getTicketIssueDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
			LocalDateTime fecha2 = LocalDateTime.parse(arg1.getTicketIssueDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
			return fecha1.compareTo(fecha2);
		}
	}

	public static class comparatorViolationDesc implements Comparator <VOMovingViolations>
	{
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1 )
		{
			return arg0.getViolationDescription().compareTo(arg1.getViolationDescription());
		}
	}

	public static class comparadorStreetID implements Comparator<VOMovingViolations>
	{

		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) 
		{
			return arg0.getStreetSegId() - arg1.getStreetSegId();
		}

	}
}
