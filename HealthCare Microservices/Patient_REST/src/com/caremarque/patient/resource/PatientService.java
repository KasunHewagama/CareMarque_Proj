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

	/*@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Patient registerPatient(
			@FormParam("firstName") String firstName,		
			@FormParam("lastName") String lastName,
			@FormParam("gender") String gender,
			@FormParam("NIC") String NIC,
			@FormParam("DOB") String DOB,
			@FormParam("email") String email,
			@FormParam("phone") String phone,
			@FormParam("bloodGroup") String bloodGroup,
			@FormParam("allergies") String allergies,
			@FormParam("password") String password,
			@FormParam("cPassword") String cPassword){*/

		//String output = null;
		
		/*String alphaPattern = "/^[a-zA-Z]+$/";
		String nicPattern = "/^[0-9]{9}[vVxX]$/";
		String emailPattern = "/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/";
		String dobPattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		String bloodTypePattern = "^(A|B|AB|O)[+-]$";
		String pwdPattern = "/(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/";
		String phonePattern = "/^\\d{10}$/";
		
		if(firstName == null || !firstName.matches(alphaPattern)) {
			System.out.println("1");
			output = "Please use alphabets only for first name..!";
		}
		else if(lastName.trim().length() < 0 || !lastName.matches(alphaPattern)) {
			output = "Please use alphabets only for last name..!";
		}
		else if(gender.trim().length() < 0 || !gender.matches(alphaPattern)) {
			output = "Please use alphabets only for gender..!";
		}
		else if(NIC.trim().length() < 0 || !firstName.matches(nicPattern)) {
			output = "Please enter a correct NIC number...!";
		}
		else if(DOB.trim().length() < 0 || !firstName.matches(dobPattern)) {
			output = "Please use dd/mm/yyyy format...!";
		}
		else if(email.trim().length() < 0 || !firstName.matches(emailPattern)) {
			output = "Please enter a valid email...!";
		}
		else if(phone.trim().length() < 0 || !firstName.matches(phonePattern)) {
			output = "Please enter a valid phone number...!";
		}
		else if(bloodGroup.trim().length() < 0 || !firstName.matches(bloodTypePattern)) {
			output = "Please enter correct blood group...!";
		}
		else if(allergies.trim().length() < 0 || !firstName.matches(alphaPattern)) {
			output = "Please use alphabets only for allergies..!";
		}
		else if(password.trim().length() < 0 || !firstName.matches(pwdPattern)) {
			output = "Please enter a password with at least six characters containing one number, one lowercase and one uppercase letter..!";
		}
		else if(cPassword.trim().length() < 0 || !cPassword.equals(password)) {
			output = "Passwords are not match..!";
		}
		else {*/
			/*patient.setFirstName(firstName);
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

			//output = patientServiceImpl.registerPatient(patient);
			return patientServiceImpl.registerPatient(patient);
		//}
		//return output;
	}*/
		
		/*patient.setFirstName(firstName);
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

		output = patientServiceImpl.registerPatient(patient);
		//patientService.registerPatient(patient);
		return output;
	}*/

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Patient registerPatient(
			 @Pattern(regexp = "/^[a-zA-Z]+$/", message="use alphabets only") @FormParam("firstName") String firstName,			
			 @Pattern(regexp = "/^[a-zA-Z]+$/") @FormParam("lastName") String lastName,
			 @Pattern(regexp = "/^[a-zA-Z]+$/") @FormParam("gender") String gender,
			 @Pattern(regexp = "/^[0-9]{9}[vVxX]$/") @FormParam("NIC") String NIC,
			 @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)") @FormParam("DOB") String DOB,
			 @Pattern(regexp = "/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/") @FormParam("email") String email,
			 @Pattern(regexp = "/^\\d{10}$/") @FormParam("phone") String phone,
			 @Pattern(regexp = "^(A|B|AB|O)[+-]$") @FormParam("bloodGroup") String bloodGroup,
			 @FormParam("allergies") String allergies,
			@NotNull @Pattern(regexp = "/(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/") @FormParam("password") String password,
			@FormParam("cPassword") String cPassword){

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

		//String output = patientServiceImpl.registerPatient(patient);
		return patientServiceImpl.registerPatient(patient);
		//return output;
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getAllPatients() {

		return patientServiceImpl.getAllPatients();

	}

	@GET
	@Path("/{patientId}")
	@Produces(MediaType.TEXT_HTML)
	public String getPatientDetail(@PathParam("patientId") int id) {

		return patientServiceImpl.getPatientDetail(id);

	}

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
