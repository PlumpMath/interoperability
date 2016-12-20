package gr.gov.minedu.osteam;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Base64;
import java.security.*;

public class AcademicIdentityAuthMediator extends AbstractMediator {
	private static final DateFormat ts = new SimpleDateFormat("MM/dd HH:mm:ss");

	private void mylog(String msg) {
		Date date = new Date();
		String outMsg = this.getClass().getSimpleName() + " :: " + format(date) + " :: " + msg;
		System.out.println(outMsg);
		return;
	}

	public boolean mediate(MessageContext context) {

		// defaults: these can also be provided as PROPERTIES
		String username = (context.getProperty("uri.var.username") == null) ? "uname"
				: context.getProperty("uri.var.username").toString();
		String password = (context.getProperty("uri.var.password") == null) ? "password"
				: context.getProperty("uri.var.password").toString();

		// set the properties IF NEEDED
		context.setProperty("username", username);
		context.setProperty("password", password);
		context.setProperty("auth", "");

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF-8"));
			byte[] HashedPasswordBytes = md.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for (byte bytes : HashedPasswordBytes) {
				stringBuffer.append(String.format("%02x", bytes & 0xff));
			}
			String userpass = username + ":" + stringBuffer.toString();
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
			context.setProperty("auth", basicAuth);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		mylog("--------------------------------------------------");
		mylog("MEDIATOR CLASS URI VARS " + context.getProperty("uri.var.username") + " , " + context.getProperty("uri.var.password") + " , " + context.getProperty("uri.var.identity"));
		mylog("--------------------------------------------------");
		mylog("MEDIATOR CLASS IN CONTEXT " + context.getProperty("username") + " , " + context.getProperty("password") + " , " + context.getProperty("auth"));

		return true;
	}
}
