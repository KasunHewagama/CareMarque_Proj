package com.caremarque.patient.resource;

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

	//register a patient to the system
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Patient registerPatient(
			@NotNull @Pattern(regexp = "/^[a-zA-Z]+$/", message = "use alphabets only") @FormParam("firstName") String firstName,
			@NotNull @Pattern(regexp = "/^[a-zA-Z]+$/") @FormParam("lastName") String lastName,
			@NotNull @Pattern(regexp = "/^[a-zA-Z]+$/") @FormParam("gender") String gender,
			@NotNull @Pattern(regexp = "/^[0-9]{9}[vVxX]$/") @FormParam("NIC") String NIC,
			@NotNull @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)") @FormParam("DOB") String DOB,
			@NotNull @Pattern(regexp = "/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/") @FormParam("email") String email,
			@NotNull @Pattern(regexp = "/^\\d{10}$/") @FormParam("phone") String phone,
			@NotNull @Pattern(regexp = "^(A|B|AB|O)[+-]$") @FormParam("bloodGroup") String bloodGroup,
			@NotNull @FormParam("allergies") String allergies,
			@NotNull @Pattern(regexp = "/(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/") @FormParam("password") String password,
			@NotNull @FormParam("cPassword") String cPassword) {

		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setGender(gender);
		patient.setNIC(NIC);
		patient.setDOB(DOB);
		patient.setEmail(email);
		patient.setPhone(phone);
		patient.setBloodGroup(bloodGroup);
		patient.setAllergy(allergies);
		patient.setPassword(password);
		patient.setConfirmPassword(cPassword);

		return patientServiceImpl.registerPatient(patient);
	
	}

	//getAllPatientDetails
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getAllPatients() {

		return patientServiceImpl.getAllPatients();

	}

	//get a patient detail by Id
	@GET
	@Path("/{patientId}")
	@Produces(MediaType.TEXT_HTML)
	public String getPatientDetail(@PathParam("patientId") int id) {

		return patientServiceImpl.getPatientDetail(id);

	}

	//delete a patient
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {

		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

		String patientId = doc.select("patientId").text();

		String output = patientServiceImpl.deletePatient(patientId);

		return output;

	}

	//update patient detail
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
		String NIC = patientObj.get("NIC").getAsString();
		String DOB = patientObj.get("DOB").getAsString();
		String email = patientObj.get("email").getAsString();
		String phone = patientObj.get("phone").getAsString();
		String bloodGroup = patientObj.get("bloodGroup").getAsString();
		String allergies = patientObj.get("allergies").getAsString();
		String password = patientObj.get("password").getAsString();
		String cPassword = patientObj.get("cPassword").getAsString();

		patient.setPatientId(Integer.parseInt(patientId));
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setGender(gender);
		patient.setNIC(NIC);
		patient.setDOB(DOB);
		patient.setEmail(email);
		patient.setPhone(phone);
		patient.setBloodGroup(bloodGroup);
		patient.setAllergy(allergies);
		patient.setPassword(password);
		patient.setConfirmPassword(cPassword);

		String output = patientServiceImpl.updatePatientDetails(patient);

		return output;

	}
	
}
