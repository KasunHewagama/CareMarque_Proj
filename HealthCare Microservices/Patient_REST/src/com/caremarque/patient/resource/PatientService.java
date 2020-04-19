package com.caremarque.patient.resource;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import com.caremarque.patient.model.Patient;
import com.caremarque.patient.service.PatientServiceImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Patients")
public class PatientService {

	PatientServiceImpl patientServiceImpl = new PatientServiceImpl();

	Patient patient = new Patient();

	// register a patient to the system
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String registerPatient(
			@NotNull(message = "First Name cannot be empty..!") @Pattern(regexp = "/^[a-zA-Z]+$/", message = "First Name should have alphabets only") @FormParam("firstName") String firstName,

			@NotNull(message = "Last Name cannot be empty..!") @Pattern(regexp = "/^[a-zA-Z]+$/", message = "Last Name should have alphabets only") @FormParam("lastName") String lastName,

			@NotNull(message = "Gender cannot be empty..!") @Pattern(regexp = "/^[a-zA-Z]+$/", message = "Gender should have alphabets only") @FormParam("gender") String gender,

			@NotNull(message = "NIC cannot be empty..!") @Pattern(regexp = "/^[0-9]{9}[vVxX]$/", message = "Enter valid NIC") @FormParam("NIC") String nic,

			@NotNull(message = "DOB cannot be empty..!") @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", message = "Use dd/mm/yyyy format") @FormParam("DOB") String dob,

			@NotNull(message = "email cannot be empty..!") @Pattern(regexp = "/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/", message = "Enter valid email") @FormParam("email") String email,

			@NotNull(message = "phone cannot be empty..!") @Pattern(regexp = "/^\\d{10}$/", message = "Enter valid phone number") @FormParam("phone") String phone,

			@NotNull(message = "bloodGroup cannot be empty..!") @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Enter valid blood group") @FormParam("bloodGroup") String bloodGroup,

			@NotNull(message = "allergies cannot be empty..!") @FormParam("allergies") String allergies,

			@NotNull(message = "password cannot be empty..!") @Pattern(regexp = "/(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/", message = "password should consist with at least six characters containing one number, one lowercase and one uppercase letter...!") @FormParam("password") String password,

			@NotNull(message = "confirm Password cannot be empty..!") @FormParam("cPassword") String cPassword) {

				patient.setFirstName(firstName);
				patient.setLastName(lastName);
				patient.setGender(gender);
				patient.setNIC(nic);
				patient.setDOB(dob);
				patient.setEmail(email);
				patient.setPhone(phone);
				patient.setBloodGroup(bloodGroup);
				patient.setAllergy(allergies);
				patient.setPassword(password);
				patient.setConfirmPassword(cPassword);
		
				return patientServiceImpl.registerPatient(patient);

	}

	// getAllPatientDetails
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getAllPatients() {

		return patientServiceImpl.getAllPatients();

	}

	// get a patient detail by Id
	@GET
	@Path("/{patientId}")
	@Produces(MediaType.TEXT_HTML)
	public String getPatientDetail(@PathParam("patientId") String id) {

		return patientServiceImpl.getPatientDetailById(id);

	}

	// delete a patient
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {

		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

		String patientId = doc.select("patientId").text();

		return patientServiceImpl.deletePatient(patientId);

	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatientDetails(String patientData) {

		JsonObject patientObj = new JsonParser().parse(patientData).getAsJsonObject();

		String patientId = patientObj.get("patientId").getAsString();
		String firstName = patientObj.get("firstName").getAsString();
		String lastName = patientObj.get("lastName").getAsString();
		String gender = patientObj.get("gender").getAsString();
		String nic = patientObj.get("NIC").getAsString();
		String dob = patientObj.get("DOB").getAsString();
		String phone = patientObj.get("phone").getAsString();
		String bloodGroup = patientObj.get("bloodGroup").getAsString();
		String allergies = patientObj.get("allergies").getAsString();
		String password = patientObj.get("password").getAsString();
		String cPassword = patientObj.get("cPassword").getAsString();

		patient.setPatientId(patientId);
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setGender(gender);
		patient.setNIC(nic);
		patient.setDOB(dob);
		patient.setPhone(phone);
		patient.setBloodGroup(bloodGroup);
		patient.setAllergy(allergies);
		patient.setPassword(password);
		patient.setConfirmPassword(cPassword);

		return patientServiceImpl.updatePatientDetails(patient);

	}

	// update patient email
	@PUT
	@Path("/updateEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatientEmail(String patientData) {

		JsonObject patientObj = new JsonParser().parse(patientData).getAsJsonObject();

		String patientId = patientObj.get("patientId").getAsString();
		String email = patientObj.get("email").getAsString();

		patient.setPatientId(patientId);
		patient.setEmail(email);

		return patientServiceImpl.updatePatientEmail(patient);

	}

	@GET
	@Path("/fromPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getAllLoggingCredentials() {

		return patientServiceImpl.getAllLoggingCredentials();
	}
}
