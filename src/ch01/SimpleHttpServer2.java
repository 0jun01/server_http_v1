package ch01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer2 {

	public static void main(String[] args) {

		try {
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

			httpServer.createContext("/jun", new JunHandler());
			httpServer.start();
			System.out.println(">> My Http Server started on port 8080");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static class JunHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String method = exchange.getRequestMethod();
			System.out.println("method : " + method);

			if ("GET".equalsIgnoreCase(method)) {
				junGetRequest(exchange);
			}
		}

		private void junGetRequest(HttpExchange exchange) throws IOException {
			String response = """
					<!DOCTYPE html>
					<html lang=ko>
						<head><head>
						<body>
							<h1 style="background-color:pink"> tjcldnjs </h1>
						<body>
					</html>
					""";
			exchange.sendResponseHeaders(200, response.length());
			PrintWriter pw = new PrintWriter(exchange.getResponseBody());
			pw.write(response);
			pw.flush();
			pw.close();

		}
	}
}
