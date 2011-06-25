package org.apache.shindig.sample.container;

import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.common.uri.Uri;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.shindig.gadgets.GadgetException;
import org.apache.shindig.gadgets.http.HttpFetcher;
import org.apache.shindig.gadgets.http.HttpRequest;
import org.apache.shindig.gadgets.http.HttpResponse;


import java.util.concurrent.Future;
import java.util.Map;

import com.google.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import me.moimoi.social.herqlweb.spi.OpenSocialDataService;


@Service(name = "samplecontainer", path = "/{type}/{doevil}")
public class SampleContainerHandler {

  private final OpenSocialDataService service;
  private final HttpFetcher fetcher;
  @Inject
  public SampleContainerHandler(OpenSocialDataService dbService, HttpFetcher fetcher) {
    this.service = dbService;
    this.fetcher = fetcher;
  }

  /**
   * We don't distinguish between put and post for these urls.
   */
  @Operation(httpMethods = "PUT")
  public Future<?> update(RequestItem request) throws ProtocolException {
    return create(request);
  }

  /**
   * Handles /samplecontainer/setstate and /samplecontainer/setevilness/{doevil}. TODO(doll): These
   * urls aren't very resty. Consider changing the samplecontainer.html calls post.
   */
  @Operation(httpMethods = "POST", bodyParam = "data")
  public Future<?> create(RequestItem request) throws ProtocolException {
    String type = request.getParameter("type");
    if ("setstate".equals(type)) {
      try {
        @SuppressWarnings("unchecked")
        Map<String, String> bodyparams = request.getTypedParameter("data", Map.class);
        String stateFile = bodyparams.get("fileurl");
        service.setDb(new JSONObject(fetchStateDocument(stateFile)));
      } catch (JSONException e) {
        throw new ProtocolException(HttpServletResponse.SC_BAD_REQUEST,
            "The json state file was not valid json", e);
      }
    } else if ("setevilness".equals(type)) {
      throw new ProtocolException(HttpServletResponse.SC_NOT_IMPLEMENTED,
          "evil data has not been implemented yet");
    }

    return ImmediateFuture.newInstance(null);
  }

  /**
   * Handles /samplecontainer/dumpstate
   */
  @Operation(httpMethods = "GET")
  public Future<?> get(RequestItem request) {
    return ImmediateFuture.newInstance(service.getDb());
  }

  private String fetchStateDocument(String stateFileLocation) {
    String errorMessage = "The json state file " + stateFileLocation
        + " could not be fetched and parsed.";

    try {
      HttpResponse response = fetcher.fetch(new HttpRequest(Uri.parse(stateFileLocation)));
      if (response.getHttpStatusCode() != 200) {
        throw new RuntimeException(errorMessage);
      }
      return response.getResponseAsString();
    } catch (GadgetException e) {
      throw new RuntimeException(errorMessage, e);
    }
  }
}
