package edu.csula.datascience.acquisition;

/**
 * Mock raw data
 */
public class MockData {
	private final String registration_state;
	private final String descrption;
	private final String code;
	private final String plate_id;
	private final String plate_type;
	private final String violation_time;
	
	
	public MockData(String registration_state, String descrption, String code, String plate_id, String plate_type,
			String violation_time) {
		
		this.registration_state = registration_state;
		this.descrption = descrption;
		this.code = code;
		this.plate_id = plate_id;
		this.plate_type = plate_type;
		this.violation_time = violation_time;
	}
	public String getRegistration_state() {
		return registration_state;
	}
	public String getDescrption() {
		return descrption;
	}
	public String getCode() {
		return code;
	}
	public String getPlate_id() {
		return plate_id;
	}
	public String getPlate_type() {
		return plate_type;
	}
	public String getViolation_time() {
		return violation_time;
	}
	
	

	
    
}
