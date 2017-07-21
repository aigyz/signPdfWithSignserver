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
      ISigningAndValidation signServer = new SigningAndValidationWS("localhost", 8080, false);

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