package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Product
 */
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        
	     
			String url = "jdbc:mysql://localhost:3306/kitcheners";
			String user="root";
			String passwordDB="#Elebaby";
		   
			String category = request.getParameter("category");

	        // JDBC database connection parameters
	      

	        try {
	            // Load the JDBC driver
	        	Class.forName("com.mysql.jdbc.Driver");

	            // Establish a connection to the database
	            Connection connection = DriverManager.getConnection(url, user, passwordDB);

	            // Fetch vegetable data
	            String query = "SELECT * FROM products WHERE category = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, category); // Set the category parameter in the query
	            ResultSet resultSet = preparedStatement.executeQuery();
	            int num=1;
	            // Display vegetable information
	            out.println("<html><head><title>Vegetables - Online Food Store</title></head><body>");
	            out.println("<header><h1>Kitchener's Online Store</h1></header>  <link rel=\"stylesheet\" href=\"MainPage.css\">");
	            
	            out.println("<nav><a href='HomePage.html'>Home</a><a href='CartPageServlet'>Go to cart</a></nav>");
	            out.println("<div class='main-content'><section class='vegetables-section'>");
	            out.println("<h2>"+category+"</h2>");
	            HttpSession session = request.getSession();
	            List<String> cartItems = (List<String>) session.getAttribute("cart");
	            while (resultSet.next()) {
	                out.println("<div class='vegetable-item'>");	               
	                out.println("<a href=\"#\" onclick=\"addToCart( " + num + ", '" + resultSet.getString("name") + "')\"><img src=\"/KitchenStory/Images/" + resultSet.getString("image_path") + "\"></a>");
	          
	                out.println("<h3>" + resultSet.getString("name") + "</h3>");
	                out.println("<p>" + resultSet.getString("description") + "</p>");
	                out.println("<span>R" + resultSet.getDouble("price") + "");
	                // Add the "Add to Cart" button with a unique identifier for each item
	                out.println("<button onclick=\"addToCart(" + num + ", '" + resultSet.getString("name") + "')\">Add to Cart</button>");
	                out.println("</div>");
	                out.println("<script>");
	                out.println("function addToCart(itemId, itemName) {");
	                out.println("  // Your existing addToCart logic...");
	                out.println("  alert(itemName + ' added to cart!');");
	                out.println("}");
	                out.println("</script>");

	                out.println("</div>");
	                num += 1;
	               
	   	         

	   	         // If the cart doesn't exist, create a new one
	   	         if (cartItems == null) {
	   	             cartItems = new ArrayList<>();
	   	         }

	   	     

	            }
	         // ... (existing code)

	            out.println("</section></div>");
	            out.println("  <div class=\"footer\">\r\n"
	            		+ "   <footer>\r\n"
	            		+ "    &copy; 2023 Kitchener's Online Store. All rights reserved.\r\n"
	            		+ "	\r\n"
	            		+ "    <!-- Social Media Icons and Links -->\r\n"
	            		+ "    <div class=\"social-icons\">\r\n"
	            		+ "		Please Follow US On Our Socials<br/><br/>\r\n"
	            		+ "        <a href=\"#\" target=\"_blank\"><img src=\"Images/facebook-icon.png\" alt=\"Facebook\"></a>\r\n"
	            		+ "        <a href=\"#\" target=\"_blank\"><img src=\"Images/twitter-icon.png\" alt=\"Twitter\"></a>\r\n"
	            		+ "        <a href=\"#\" target=\"_blank\"><img src=\"Images/instagram-icon.png\" alt=\"Instagram\"></a>\r\n"
	            		+ "        <!-- Add more social media icons and links as needed -->\r\n"
	            		+ "    </div>\r\n"
	            		+ "</footer>\r\n"
	            		+ "    </div>");

	           
	            out.println("</body></html>");

	            // ... (existing code)






	            // Close the database connection
	            connection.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            out.println("Error: " + e.getMessage());
	        }
	}

}
