package com.murphy.docusign;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class DocuSignAPITrigger {

	public JSONObject docusignTrigger(DocuSignDocumentDto inputDto) {
		JSONObject resp = null;
		try {
			String url = "https://demo.docusign.net/restapi/v2/accounts/4550373/envelopes";
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			String authentication = "<DocuSignCredentials><Username>8e68056f-0913-4e2b-afa3-0721b31653b1</Username><Password>Password@1</Password><IntegratorKey>9b30886c-b74a-4263-ab11-d34a33e24596</IntegratorKey></DocuSignCredentials>";
			connection.setRequestMethod("POST");
			connection.setRequestProperty("X-DocuSign-Authentication", authentication);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
			// String payload = "{\"emailSubject\":\"Remote Envelope from Server
			// Template\",\"status\":\"sent\",\"enableWetSign\":\"false\",\"compositeTemplates\":[{\"compositeTemplateId\":\"1\",\"inlineTemplates\":[{\"sequence\":\"2\",\"recipients\":{\"signers\":[{\"email\":\"ramesh@incture.com\",\"name\":\"Ramesh
			// Reddy\",\"roleName\":\"Signer1\",\"recipientId\":\"1\"}],\"carbonCopies\":[{\"email\":\"amit.dey@incture.com\",\"name\":\"Amit
			// Dey\",\"recipientId\":\"2\"}]},\"customFields\":{\"textCustomFields\":[{\"name\":\"SchoolId\",\"value\":\"12345\"},{\"name\":\"SchoolLatLong\",\"value\":\"47.466547,-122.9795627\"}]}}],\"serverTemplates\":[{\"sequence\":\"1\",\"templateId\":\"96252e64-044e-4f4d-8616-a96cf085ec44\"}]}]}";

			dataOutputStream.write(inputDto.toString().getBytes());
			dataOutputStream.flush();
			dataOutputStream.close();
			connection.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			resp = new JSONObject(response.toString());

			System.out.println("API  Response Code :" + connection.getResponseCode());

		} catch (Exception e) {
			System.err.println("Trigger FAILURE " + e.getMessage());
			try {
				return resp = new JSONObject("Trigger FAILURE");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return resp;
	}

	public JSONObject getDocuSignUrl(DocumentSignatureLinkDto inputDto) {

		JSONObject resp = null;
		try {
			String url = "https://demo.docusign.net/restapi/v2/accounts/4550373/envelopes/" + inputDto.getEnvelopeId()
					+ "/views/recipient";
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			String authentication = "<DocuSignCredentials><Username>8e68056f-0913-4e2b-afa3-0721b31653b1</Username><Password>Password@1</Password><IntegratorKey>9b30886c-b74a-4263-ab11-d34a33e24596</IntegratorKey></DocuSignCredentials>";
			connection.setRequestMethod("POST");
			connection.setRequestProperty("X-DocuSign-Authentication", authentication);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

			String payload = "{\"clientUserId\": \"" + inputDto.getClientUserId() + "\",\"email\": \""
					+ inputDto.getEmail() + "\",\"userName\": \"" + inputDto.getUserName() + "\",\"recipientId\": \""
					+ inputDto.getUserName() + "\",\"returnUrl\": \"" + inputDto.getReturnUrl()
					+ "\",\"authenticationMethod\": \"" + inputDto.getAuthenticationMethod()
					+ "\",\"authenticationInstant\": \"" + inputDto.getAuthenticationInstant()
					+ "\",\"securityDomain\": \"" + inputDto.getSecurityDomain() + "\"}";

			dataOutputStream.write(payload.getBytes());
			dataOutputStream.flush();
			dataOutputStream.close();
			connection.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			resp = new JSONObject(response.toString());

			System.out.println("API  Response Code :" + connection.getResponseCode());

		} catch (Exception e) {
			System.err.println("Trigger FAILURE " + e.getMessage());
			try {
				return resp = new JSONObject("Trigger FAILURE");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}

		return resp;
	}
}
