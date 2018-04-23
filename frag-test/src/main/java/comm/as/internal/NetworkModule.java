package comm.as.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface NetworkModule {

    void start() throws IOException;

    void stop() throws IOException;

    InputStream getInputStream() throws Exception;

    OutputStream getOutputStream() throws Exception;

    String getServerURI();
}
