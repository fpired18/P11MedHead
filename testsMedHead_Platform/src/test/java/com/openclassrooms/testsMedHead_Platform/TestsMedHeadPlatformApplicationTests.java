package com.openclassrooms.testsMedHead_Platform;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class TestsMedHeadPlatformApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void testListJson() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format("http://localhost:9010/hospital")))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				String jsonString = response.body();
				ObjectMapper mapper = new ObjectMapper();

				List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
				});

				int numberRecord = 0;
				numberRecord = posts.lastIndexOf(jsonString);
				System.out.println("Voila ce que donne numberRecord: " + numberRecord);

				posts.forEach(System.out::println);

				System.out.println("\n------------------------------------");
				System.out.println("            Tout est OK");
				System.out.println("------------------------------------");
				System.out.println("Response.statusCode = " + response.statusCode());
				System.out.println("Voila ce que donne un jsonString: " + jsonString);

			} else {
				System.out.println("------------------------------------");
				System.out.println("          Il y a un code 500 ");
				System.out.println("------------------------------------");
				System.out.println(response);
			}
			if (response.body().contains("numberOfBeds")) {
				System.out.println("------------------------------------");
				System.out.println("  Y a t'il un nombre de lits? " + response.body().contains("numberOfBeds"));
				System.out.println("------------------------------------");
			}
		} catch (Exception e) {
			System.out.println("\n***********************************\n");
			System.out.println("    Il y a un problème! :" + e);
			System.out.println("\n***********************************");
			e.printStackTrace();
		}
	}

	@Test
	void testParseJson() {
		int jsonNumber = 0;
		while (jsonNumber < 5) {
			jsonNumber += 1;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(String.format("http://localhost:9010/hospital/" + jsonNumber))).GET().build();

			try {
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				if (response.statusCode() == 200) {
					String jsonString = response.body();
					System.out.println("\n------------------------------------");
					System.out.println("            Tout est OK");
					System.out.println("------------------------------------");
					System.out.println("Response.statusCode = " + response.statusCode());
					System.out.println("Voila le jsonString numéro " + jsonNumber + ": " + jsonString);

					Object obj = new JSONParser().parse(jsonString);
					JSONObject jo = (JSONObject) obj;

					String specialityGroup = (String) jo.get("specialityGroup");
					System.out.println("\n****************************************");
					System.out.println("     Voila le contenu du Json n°: " + jsonNumber);
					System.out.println("****************************************");
					System.out.println("   specialityGroup: " + specialityGroup);

					String speciality = (String) jo.get("speciality");
					System.out.println("   speciality: " + speciality);

					String hospitalCenter = (String) jo.get("hospitalCenter");

					System.out.println("   hospitalCenter: " + hospitalCenter);

					int numberOfBeds = Integer.parseInt(jo.get("numberOfBeds").toString());
					System.out.println("   numberOfBeds: " + numberOfBeds);

					int numberOfPatients = Integer.parseInt(jo.get("numberOfPatients").toString());
					System.out.println("   numberOfPatients: " + numberOfPatients);

					int numberOfBedsAvailable = Integer.parseInt(jo.get("numberOfBedsAvailable").toString());
					System.out.println("   numberOfBedsAvailable: " + numberOfBedsAvailable);

					int geographicalPositionLong = Integer.parseInt(jo.get("geographicalPositionLong").toString());
					System.out.println("   geographicalPositionLong: " + geographicalPositionLong);

					int geographicalPositionLat = Integer.parseInt(jo.get("geographicalPositionLat").toString());
					System.out.println("   geographicalPositionLong: " + geographicalPositionLat);
					System.out.println("****************************************\n");
					/*int longtravel = tripdistance.distanceGPS(8, 3, geographicalPositionLong, geographicalPositionLat);
					System.out.println("\n**************************************************************************");
					System.out.println("La distance est de : "+ longtravel);*/
					
					
				} else {
					System.out.println("------------------------------------");
					System.out.println("          Il y a un code 500 ");
					System.out.println("------------------------------------");
					System.out.println(response);
				}
				if (response.body().contains("numberOfBeds")) {
					System.out.println("------------------------------------");
					System.out.println("  Y a t'il un nombre de lits? " + response.body().contains("numberOfBeds"));
					System.out.println("------------------------------------");
				}

			} catch (Exception e) {
				System.out.println("\n***********************************\n");
				System.out.println("    Il y a un problème! :" + e);
				System.out.println("\n***********************************");
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testCreate() {
		HospitalDTO hospitalDTO = new HospitalDTO();

		hospitalDTO.specialityGroup = "Groupe specialité";
		hospitalDTO.speciality = "specialité";
		hospitalDTO.hospitalCenter = "Orléans";
		hospitalDTO.numberOfBeds = 10;
		hospitalDTO.numberOfPatients = 5;
		hospitalDTO.geographicalPositionLong = 5;
		hospitalDTO.geographicalPositionLat = 7;
		hospitalDTO.numberOfBedsAvailable = 5;

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format("http://localhost:9010/hospital")))
				.POST((BodyPublisher) hospitalDTO).build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response.statusCode = " + response.statusCode());
		} catch (Exception e) {
			System.out.println("\n***********************************\n");
			System.out.println("    Il y a un problème! :" + e);
			System.out.println("\n***********************************");
			e.printStackTrace();
		}

	}
	
	@Test
	void testNumberPatient() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format("http://localhost:9010/hospital/numberOfPatients")))
				.GET().build();

		try {
			
		} catch (Exception e) {
			System.out.println("\n***********************************\n");
			System.out.println("    Il y a un problème! :" + e);
			System.out.println("\n***********************************");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHomeController() {
		//MainController mainController = new MainController;
	}

}
