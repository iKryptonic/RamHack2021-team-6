<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="resources/css/materialize.min.css"  media="screen,projection"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<meta charset="ISO-8859-1">
		<title>Event View - ${eventName}</title>
	</head> 
	<%!
		private void outNewLine(String Bits, jakarta.servlet.jsp.JspWriter myOut)
		{  
		  try{ myOut.println(Bits); } 
		  catch(Exception eek) { }
		}
	%>
	<body>
	<div class="section white">
	    <div class="row container">
	        <h2 class="header">Event Details</h2>
	        <div class="col s12">
				<h5 class="subheader">${eventName}</h5>
			</div>
			 <hr class="divider" />
			<div class="col s6">
				<div>
					<h6 class="subheader">Description</h6>
				</div>
				<p>${eventDescription}</p>
				
			</div>
			<div class="col s6"> <p></p></div>
			<div class="col s4 offset-s1">
				<div class="col s11 offset-s5">Date <span class="blue-grey-text">${eventDate}</span><hr class="divider" /></div>
				<div class="col s11 offset-s5">Genre <span class="blue-grey-text">${eventGenre}</span><hr class="divider" /></div>
				<div class="col s11 offset-s5">21+ only: <span class="blue-grey-text">${eventAgeRestrict}</span><hr class="divider" /></div>
				<div>
					<ul>
						<li>
							<span><div class="col s12 offset-s6" style="margin-bottom: 5%; margin-top: 10%"><a target="_blank" rel="noopener noreferrer" href="${eventPage}" class="waves-effect waves-light btn-large">Visit event page</a></div></span>
						</li>
						<li>
							<span><div class="col s12 offset-s7"><a href="/RamsHack2021-team-6/index" class="waves-effect waves-light btn">Return Home</a></div></span>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="container col s12">
				<div class="col s12"><h5>Relevant Images</h5></div>
				<table class="responsive-table striped" style="table-layout: fixed; width: 100%;">
					<tbody>
						<tr>
						<% String[] imageList = (String[]) request.getAttribute("eventImages");
					        int pointer = 0;
				            outNewLine("<tr>", out);
					        while(pointer < imageList.length)
					        {
					            if(pointer%5 == 0){
					        		outNewLine("</tr>", out);
					            	outNewLine("<tr>", out);
					            }
					            pointer++;
					            if(imageList[pointer-1] != null)
					            	outNewLine("<td style=\"width:250px; height:250px; text-align:center; vertical-align:middle\"><img style=\"max-height:100%; max-width:100%\" src=\""+imageList[pointer-1]+"\"></td>", out);
					        } 
					       outNewLine("</tr>", out);
					   %>
						</tr>
						<tr>
							<td>
							</td>
							<td>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	    </div>
	</div>
	</body>
	<footer class="page-footer teal">
	  <div class="container">
	    <div class="row">
	      <div class="col l6 s12">
	        <h5 class="white-text">RamHack 2021 Team 6</h5>
	        <p class="grey-text text-lighten-4">This project was created in hopes to connect college student with their peers in their community.</p>
	      </div>
	    </div>
	  </div>
	  <div class="footer-copyright">
	    <div class="container">
	    Made with <a class="brown-text text-lighten-3" href="http://materializecss.com">Materialize</a>
	    </div>
	  </div>
	</footer>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="resources/js/materialize.js"></script>
  <script src="resources/js/init.js"></script>
</html>