import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.signserver.client.api.ISigningAndValidation;
import org.signserver.client.api.SigningAndValidationWS;
import org.signserver.common.GenericSignResponse;

public class Application {

  public static void main(String[] args) {
    try {

      System.setProperty("javax.net.ssl.trustStore", "src/main/resources/keystore.jks");
      System.setProperty("javax.net.ssl.trustStorePassword", "mypassword");
//      System.setProperty("javax.net.ssl.trustAnchors", "/etc/ssl/certs/java/cacerts");

      ISigningAndValidation signServer = new SigningAndValidationWS("localhost", 8443,
          true);

      Path path = Paths.get("/home/laptop/temp/old.pdf");
      final byte[] bytes = Files.readAllBytes(path);

      GenericSignResponse signResp = signServer.sign("PDFSigner", bytes);
      byte[] signed = signResp.getProcessedData();
      FileOutputStream fos = new FileOutputStream("/home/laptop/temp/newSigned.pdf");
      fos.write(signed);
      fos.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}