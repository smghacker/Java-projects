package com.vmware.garbagecollector;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

/**
 * A simple and light REST client The SSL server should be in the same secure
 * network environment, as the client won't check its certificate=
 */
public class SimpleRestClient {

	private static String addressOfServer = "http://localhost:8080/api/sector/";
	private static String companyName = "vcolov";

	/**
	 * Implement a HTTP get
	 * 
	 * @param id
	 *            URL to use for the post request
	 * @param un
	 *            user name for basic authentication
	 * @param pass
	 *            password
	 * @param expectedCodes
	 *            Codes that are expected and which should not throw exception
	 * @throws IOException
	 */
	public static String httpGetObjects(String id, int... expectedCodes)
			throws IOException {
		String location = addressOfServer + id + "/objects";
		URL url = new URL(location);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setInstanceFollowRedirects(true);

		int code = conn.getResponseCode();

		if (code != 200) {
			if (code == 302) {
				System.out.println("Attempting to redirect to "
						+ conn.getHeaderField("Location"));
			}
			boolean found = false;
			for (int c : expectedCodes) {
				if (c == code) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IOException(conn.getResponseMessage() + " "
						+ conn.getResponseCode());
			}
		}
		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

	public static String httpGetRoots(String id, int... expectedCodes)
			throws IOException {
		String location = addressOfServer + id + "/roots";
		URL url = new URL(location);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setInstanceFollowRedirects(true);

		int code = conn.getResponseCode();

		if (code != 200) {
			if (code == 302) {
				System.out.println("Attempting to redirect to "
						+ conn.getHeaderField("Location"));
			}
			boolean found = false;
			for (int c : expectedCodes) {
				if (c == code) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IOException(conn.getResponseMessage() + " "
						+ conn.getResponseCode());
			}
		}
		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

	/**
	 * Implement a HTTP post
	 * 
	 * @param urlStr
	 *            URL to use for the post requst
	 * @param un
	 *            user name for basic authentication
	 * @param pass
	 *            password
	 * @param param
	 *            Request parameters - ordered map
	 * @param body
	 *            Request body
	 * @param expectedCodes
	 *            Codes that are expected and which should not throw exception
	 * @return The response body
	 * @throws IOException
	 *             http://server:port/api/sector/[id]/company/[your-company-name
	 *             ]/trajectory
	 */
	public static String httpPost(String id, String body, int... expectedCodes)
			throws IOException {
		String location = addressOfServer + id + "/company/" + companyName
				+ "/trajectory";
		URL url = new URL(location);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setInstanceFollowRedirects(true);
		OutputStream out = conn.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");
		writer.write("trajectory=");
		writer.write(URLEncoder.encode(body, "UTF-8"));
		writer.close();
		out.close();

		int code = conn.getResponseCode();
		//System.out.print("C:" + code);
		if (code != 200) {
			if (code == 302) {
				System.out.println("Attempting to redirect to "
						+ conn.getHeaderField("Location"));
			}
		}
		boolean found = false;
		for (int c : expectedCodes) {
			if (c == code) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new IOException(conn.getResponseMessage() + " "
					+ conn.getResponseCode());
		}
		StringBuilder sb = new StringBuilder();
		if (code != 418) {
			// Buffer the result into a string
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			rd.close();
		}
		conn.disconnect();
		return sb.toString();
	}
	
	public static String httpFakeGet(String un, String pass, int... expectedCodes) throws IOException {
		String location = addressOfServer + "1/roots";
        URL url = new URL(location);
        HttpURLConnection conn =  (HttpURLConnection)url.openConnection();
        conn.setInstanceFollowRedirects(true);
        if (un != null) {
            String auth = un+":"+pass;
            String encoding = Base64.encodeBase64String(auth.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encoding);
        }
        int code = conn.getResponseCode();

        if (code != 200) {
            if (code==302) {
                System.out.println("Attempting to redirect to "+conn.getHeaderField("Location"));
            }
            boolean found = false;
            for (int c: expectedCodes) {
                if (c==code) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IOException(conn.getResponseMessage()+" "+conn.getResponseCode());
            }
        }
        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        rd.close();

        conn.disconnect();
        return sb.toString();
    }
}