package com;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebServlet("/CartPageServlet")
public class CartPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

     // Inside your existing CartPageServlet
     // Retrieve the ordered items from the session
     HttpSession session = request.getSession();
     List<String> orderedItems = (List<String>) session.getAttribute("cart");

     // Display the cart items
     out.println("<html><head><title>Shopping Cart</title></head><body>");
     out.println("<h2>Your Shopping Cart</h2>");

     // Rest of the code remains the same...


        if (orderedItems != null && !orderedItems.isEmpty()) {
            out.println("<ul>");
            for (String item : orderedItems) {
                out.println("<li>" + item + "</li>");
            }
            out.println("</ul>");

            // Add buttons for clearing the cart and confirming the order
            out.println("<form action='ClearCart' method='post'>");
            out.println("<input type='submit' value='Clear Cart'>");
            out.println("</form>");

            out.println("<form action='ConfirmOrder' method='post'>");
            out.println("<input type='submit' value='Confirm Order'>");
            out.println("</form>");
        } else {
            out.println("<p>Your cart is empty.</p>");
        }

        out.println("</body></html>");
    }
}

