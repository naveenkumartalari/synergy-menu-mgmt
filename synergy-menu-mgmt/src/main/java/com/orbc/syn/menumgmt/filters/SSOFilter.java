package com.orbc.syn.menumgmt.filters;

import java.io.IOException;
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
import com.orbc.syn.menumgmt.dto.User;
import com.orbc.syn.menumgmt.exception.ErrorDetails;
import com.orbc.syn.menumgmt.utils.Utils;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SSOFilter implements Filter {
	
	private static final Logger log = LogManager.getLogger(SSOFilter.class);

	private static final boolean CONDITION = true;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token=((HttpServletRequest) request).getHeader("AUTH-TOKEN");
		User user=new User();
		if (CONDITION == validateSSOToken(user) || true) //TO-DO need to change this logic
			chain.doFilter(request, response);
		else {
			
			//throw new AuthenticationFailedException("Unauthorized access");
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
			post = new HttpPost(AppConstants.SSO_TOKEN_URL);
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
		SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(getInsecureSSLContext(AppConstants.SEC_TLS),
				NoopHostnameVerifier.INSTANCE);
		builder.setSSLSocketFactory(sslConnectionFactory);
		HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(
				RegistryBuilder.<ConnectionSocketFactory>create().register(AppConstants.HTTPS, sslConnectionFactory).build());
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

}
