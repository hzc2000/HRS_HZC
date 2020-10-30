//package org.hzc.security;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.Serializable;
//
///**
// * This authentication entry point implementation returns 401 Unauthorized
// * whenever a user accesses a secured REST end-point without supplying any
// * credentials.
// */
//@Component
//public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
//
//  /**
//   * The serial version UID.
//   */
//  private static final long serialVersionUID = -3689082180290503236L;
//
//  /**
//   * The 401 JSON response body.
//   */
//  private static final String ERROR_MESSAGE = "{ \"message\": \"%s\" }";
//
//  /**
//   * Commence the authentication schema. This implementation returns 401 error
//   * message in JSON format.
//   *
//   * @param request
//   *          that resulted in an <code>AuthenticationException</code>
//   * @param response
//   *          so that the user agent can begin authentication
//   * @param authException
//   *          that caused the invocation
//   */
//  @Override
//  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//      throws IOException {
//    response.setContentType("application/json");
//    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//    response.getOutputStream().println(String.format(ERROR_MESSAGE, authException.getMessage()));
//  }
//}
