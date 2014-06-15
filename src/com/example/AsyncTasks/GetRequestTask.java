package com.example.AsyncTasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class GetRequestTask extends AsyncTask<String, Integer, JSONObject> {
	
	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType) {
			// No need to implement.
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType) {
			// No need to implement.
		}
	} };

	

	@Override
	protected JSONObject doInBackground(String... params) {
		String result="";
		try {
			URL url = new URL(params[0]);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;

			while ((inputLine = in.readLine()) != null){
				result = result + inputLine;
			}
			in.close();
			Log.i("RESULTSTRING", result);
			JSONObject resultObj = new JSONObject(result);
//			Log.i("RESULT", resultObj.toString());
			return resultObj;
			//TODO remove this after connection errors(expired certificate) are fixed
		}catch(Exception e){
			/*result = "{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[],\"pictureURL\":\"/profile/picture.html?type\u003dnormal\u0026personId\u003d8\"}";
			result = "[{\"person\":{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[]},\"when\":\"Apr 24, 2014 4:39:20 PM\",\"mood\":{\"id\":16},\"description\":\"\"},{\"person\":{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[]},\"when\":\"May 27, 2013 11:33:08 PM\",\"mood\":{\"id\":16},\"description\":\"\"},{\"person\":{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[]},\"when\":\"May 27, 2013 10:40:51 PM\",\"mood\":{\"id\":16},\"description\":\"\"},{\"person\":{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[]},\"when\":\"May 25, 2013 10:48:38 AM\",\"mood\":{\"id\":16},\"description\":\"\"},{\"person\":{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[]},\"when\":\"May 24, 2013 4:12:30 PM\",\"mood\":{\"id\":16},\"description\":\"\"},{\"person\":{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[]},\"when\":\"May 24, 2013 11:16:52 AM\",\"mood\":{\"id\":16},\"description\":\"\"},{\"person\":{\"id\":8,\"fbId\":705340,\"accessLevel\":{\"id\":2,\"description\":\"User\"},\"firstName\":\"Peter\",\"lastName\":\"Gloor\",\"gender\":\"M\",\"activated\":true,\"nickName\":\"Peter\",\"description\":\"an uncurable optimist\",\"memberSince\":\"May 23, 2013\",\"lastOnline\":\"Apr 24, 2014\",\"birthday\":\"Jul 5, 1961\",\"useFBMatching\":true,\"useQuestionMatching\":true,\"useDistanceMatching\":true,\"useSymptomsMatching\":true,\"useMedicationMatching\":true,\"location\":{\"id\":2661881,\"name\":\"Aarau, Aargau, Switzerland\",\"longitude\":8.044223785400390,\"latitude\":47.392539024961850},\"tags\":[{\"id\":20,\"category\":\"Symptom\",\"name\":\"flu\"},{\"id\":21,\"category\":\"Symptom\",\"name\":\"nausea\"},{\"id\":8,\"category\":\"Symptom\",\"name\":\"Headache\"},{\"id\":22,\"category\":\"ProfileTag\",\"name\":\"ever curious\"},{\"id\":23,\"category\":\"Medication\",\"name\":\"oxycodone\"},{\"id\":24,\"category\":\"Medication\",\"name\":\"honey\"}],\"tagsRemoved\":[]},\"when\":\"May 23, 2013 2:52:54 PM\",\"mood\":{\"id\":16},\"description\":\"\"}]";
			JSONObject resultObj;
			try {
				resultObj = new JSONObject(result);
				return resultObj;
			} catch (JSONException e1) {
				e1.printStackTrace();
			}*/
			e.printStackTrace();
		}
//		catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} 
		return null;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
//		Log.i("RESULT", result.toString());
		super.onPostExecute(result);
	}

}
