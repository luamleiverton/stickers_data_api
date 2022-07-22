import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class readFile {
  
  public String obterToken() throws IOException {
    String file ="path/src/token.txt";
    String result = null;

    DataInputStream reader = new DataInputStream(new FileInputStream(file));
    int nBytesToRead = reader.available();
    if(nBytesToRead > 0) {
        byte[] bytes = new byte[nBytesToRead];
        reader.read(bytes);
        result = new String(bytes);
    }
    return result;
}
}
