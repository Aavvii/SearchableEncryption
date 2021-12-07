package apis.account;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Identifies the application path that serves as the base URI for all resource URIs provided by Path.
 */
@ApplicationPath("/api")
public class AccountApplication extends Application {

}