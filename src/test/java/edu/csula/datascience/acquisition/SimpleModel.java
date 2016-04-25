package edu.csula.datascience.acquisition;

/**
 * A simple model for testing
 */
public class SimpleModel {
	private final String Registration_state;
	private final String descrption;
	private final String code;
	private final String plate_id;
	private final String plate_type;
	private final String Vilation_time;

	public SimpleModel(String registration_state, String descrption, String code, String plate_id, String plate_type,
			String vilation_time) {

		this.Registration_state = registration_state;
		this.descrption = descrption;
		this.code = code;
		this.plate_id = plate_id;
		this.plate_type = plate_type;
		this.Vilation_time = vilation_time;
	}

	public String getRegistration_state() {
		return Registration_state;
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

	public String getVilation_time() {
		return Vilation_time;
	}

	public static SimpleModel build(MockData data) {
		return new SimpleModel(data.getRegistration_state(), data.getDescrption(), data.getCode(), data.getPlate_id(),
				data.getPlate_type(), data.getViolation_time());
	}
}
