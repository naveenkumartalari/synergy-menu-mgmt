package com.orbc.syn.menumgmt.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbc.syn.menumgmt.constants.AppConstants;
import com.orbc.syn.menumgmt.exception.ErrorDetails;
import com.orbc.syn.menumgmt.tokens.cache.CacheManager;
import com.orbc.syn.menumgmt.utils.DateUtil;
import com.orbc.syn.menumgmt.utils.Utils;
import com.orbc.syn.menumgmt.vo.TokenInfo;
import com.orbc.syn.menumgmt.vo.User;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SSOFilter implements Filter {

	private static final Logger log = LogManager.getLogger(SSOFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//String token = ((HttpServletRequest) request).getHeader(AppConstants.AUTHORIZATION);

		boolean flag = true;

		/*TokenInfo tokenInfo = CacheManager.getFromCache(token);
		if (tokenInfo == null) {

			tokenInfo = validateSSOToken(token);
			if (tokenInfo != null) {
				CacheManager.addToCache(token, tokenInfo);
			} else {
				flag = false;
			}
		} else {
			if (DateUtil.isTokenExpired(tokenInfo.getExpiry()))
				flag = false;
		}*/

		if (flag) {
			chain.doFilter(request, response);
		} else {

			ErrorDetails errorResponse = new ErrorDetails();
			errorResponse.setErrorCode(HttpServletResponse.SC_UNAUTHORIZED);
			errorResponse.setMessage("Unauthorized Access");

			byte[] responseToSend = restResponseBytes(errorResponse);
			((HttpServletResponse) response).setHeader(AppConstants.ACCT_KEY, MediaType.APPLICATION_JSON);
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getOutputStream().write(responseToSend);
			return;
		}

	}

	private byte[] restResponseBytes(ErrorDetails eErrorResponse) throws IOException {
		String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
		return serialized.getBytes();
	}

	@Override
	public void destroy() {

	}

	public boolean validateSSOToken(User user) {

		log.info("validateSSOToken(User user): starts");

		HttpPost post = null;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = createHTTPSClient();
			post = new HttpPost(AppConstants.SSO_TOKEN_VALIDATION_URL);
			post.addHeader(AppConstants.ACCC_KEY, AppConstants.ACCC_VAL);
			post.addHeader(AppConstants.ACAP_KEY, MediaType.APPLICATION_JSON);
			post.addHeader(AppConstants.ACCT_KEY, MediaType.APPLICATION_FORM_URLENCODED);
			List<NameValuePair> params = new ArrayList<>();
			try {
				params.add(new BasicNameValuePair(AppConstants.CLID_KEY, AppConstants.CLID_VAL));
				params.add(new BasicNameValuePair(AppConstants.CLGR_KEY, user.getGrantType()));
				if (AppConstants.CLPASS_KEY.equalsIgnoreCase(user.getGrantType())) {
					params.add(new BasicNameValuePair(AppConstants.CLTX_KEY, AppConstants.CLTX_VAL));
					params.add(new BasicNameValuePair(AppConstants.CLSE_KEY, AppConstants.CLSE_VAL));
					params.add(new BasicNameValuePair(AppConstants.CLUSR_KEY, user.getUserName()));
					params.add(new BasicNameValuePair(AppConstants.CLPASS_KEY, user.getPassword()));
				} else if (AppConstants.CLRTK_KEY.equalsIgnoreCase(user.getGrantType())) {
					params.add(new BasicNameValuePair(AppConstants.CLRTK_KEY, user.getRefreshToken()));
				}
				post.setEntity(new UrlEncodedFormEntity(params));
				response = client.execute(post);
			} finally {
				Utils.clear(params);
			}

			if (response.getStatusLine().getStatusCode() != 200)
				return false;

		} catch (Exception e) {
			log.error("exception occured while validating SSO Token");
			log.error(e.getMessage());
			return false;
		} finally {
			try {
				if (!Utils.isEmpty(post))
					post.releaseConnection();
				if (!Utils.isEmpty(response))
					response.close();
				if (!Utils.isEmpty(client))
					client.close();
			} catch (Exception e) {
				log.error(AppConstants.E_CCC);
			}
			log.info("validateSSOToken(User user): ends");
		}
		return true;
	}

	private CloseableHttpClient createHTTPSClient() throws NoSuchAlgorithmException, KeyManagementException {
		HttpClientBuilder builder = HttpClientBuilder.create();
		SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(
				getInsecureSSLContext(AppConstants.SEC_TLS), NoopHostnameVerifier.INSTANCE);
		builder.setSSLSocketFactory(sslConnectionFactory);
		HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(RegistryBuilder
				.<ConnectionSocketFactory>create().register(AppConstants.HTTPS, sslConnectionFactory).build());
		builder.setConnectionManager(ccm);
		return builder.build();
	}

	private SSLContext getInsecureSSLContext(String algorithm) throws KeyManagementException, NoSuchAlgorithmException {
		final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(final java.security.cert.X509Certificate[] arg0, final String arg1)
					throws CertificateException {
			}

			@Override
			public void checkServerTrusted(final java.security.cert.X509Certificate[] arg0, final String arg1)
					throws CertificateException {
			}

		} };
		final SSLContext sslcontext = SSLContext.getInstance(algorithm);
		sslcontext.init(null, trustAllCerts, new java.security.SecureRandom());
		return sslcontext;
	}

	public TokenInfo validateSSOToken(String token) {

		URL url;
		StringBuilder sCurrentLineBuf = null;
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String response;
		TokenInfo tokenInfo = null;

		try {

			url = new URL(AppConstants.SSO_TOKEN_VALIDATION_URL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty(AppConstants.ACCC_KEY, AppConstants.ACCC_VAL);
			conn.setRequestProperty(AppConstants.ACAP_KEY, MediaType.APPLICATION_JSON);
			conn.setRequestProperty(AppConstants.ACCT_KEY, MediaType.APPLICATION_FORM_URLENCODED);
			conn.setRequestProperty(AppConstants.AUTHORIZATION, token);

			conn.setDoOutput(true);

			out = new OutputStreamWriter(conn.getOutputStream());

			Utils.closeResource(out);

			log.info("invoking SSO for token validation");

			if (conn.getResponseCode() != 200) {

				log.error("Failed : HTTP error code : " + conn.getResponseCode());
				return tokenInfo;
			}

			br = new BufferedReader(new InputStreamReader(

					(conn.getInputStream())));

			sCurrentLineBuf = new StringBuilder();

			String dtmresponse = AppConstants.EMPTY_STRING;

			while (!Utils.isEmpty((dtmresponse = br.readLine()))) {
				sCurrentLineBuf.append(dtmresponse);
			}

			conn.disconnect();
			response = sCurrentLineBuf.toString();

			if (Utils.isEmpty(response)) {
				log.error("Got empty response from SSO");
				return tokenInfo;
			}

			/**
			 * converting JSON (SSO's) response to Java object (TokenInfo)
			 */
			ObjectMapper mapper = new ObjectMapper();
			tokenInfo = mapper.readValue(response, TokenInfo.class);

		} catch (Exception e) {

			log.error(e.getMessage());
			return tokenInfo;

		} finally {

			Utils.closeResource(out);
			Utils.closeResource(br);
		}

		return tokenInfo;
	}

}
