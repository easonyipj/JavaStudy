package multithread;

import com.sun.deploy.net.HttpRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class SimpleHttpServer {
    // 处理HttpRequest的线程池
    private static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(5);
    // SimpleHttpServer的根路径
    private static String basePath;
    private static int port = 8080;

    public static void setPort(int port) {
        if(port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    // 启动
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = null;
            while((socket = serverSocket.accept()) != null) {
                threadPool.execute(new HttpRequestHandler(socket));
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class HttpRequestHandler implements Runnable{

        private Socket socket;
        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if(filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while((i = in.read()) != -1) {
                        baos.write(i);
                    }

                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Eason Yi's Server");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                }else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Eason Yi's Server");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();
            } catch (IOException e) {
                assert out != null;
                out.println("HTTP/1.1 200 OK");
                out.println("");
                out.flush();
                e.printStackTrace();
            } finally {
                close(br, in, reader, out, socket);
            }
        }
    }

    private static void close(Closeable... closeables) {
        if(closeables != null) {
            for(Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
