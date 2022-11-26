package com.kamelia.gustavebikeservice;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private RMIClient rmiClient;

    public void init() {
        message = "Hello World!";
        rmiClient = RMIClient.create();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        var user = rmiClient.userStorage().save("Pimmy", "p@ssw0rd").orElseThrow();
        var token = rmiClient.userStorage().login("Pimmy", "p@ssw0rd").orElseThrow().second();

        var bike = rmiClient.bikeStorage().addOwnedBike(user, token).orElseThrow();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<p>" + user.username() + "</p>");
        out.println("<p> Votre vélo : " + bike.id() + " (" + bike.owner().username() + ")</p>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}