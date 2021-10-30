<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="resources/css/materialize.min.css"  media="screen,projection"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      
		<meta charset="ISO-8859-1">
		<title>University Event Viewer Home</title>
	</head>
	
	<body>
	
	<div class="section white">
	    <div class="row container">
	        <h2 class="header">Event Lookup by University</h2>
			<div class="col s12">
				<div class="row">
					<form class="col s12">
				  		<div class="row">
							<div class="col s12">University:
							  <div class="input-field inline">
									<input type="text" id="autocomplete-input" class="autocomplete">
									<label for="autocomplete-input">University Name</label>
								</div>
							<button style="margin-left: 5%" class="btn waves-effect waves-light" type="submit" name="action">Submit
								<i class="material-icons right">send</i>
						  	</button>
							</div>
						</div>
					</form>
				</div>
				<!-- University Data Retainer -->
				<div>
					<ul class="collection with-header">
						<li class="collection-header"><h4>Events around ${universityName}</h4></li>
						<li class="collection-item"><div>${eventName} - ${eventDate} - ${eventGenre}<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
				  	</ul>
				</div>
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
  <script type="text/javascript">
  $(document).ready(function() {
	  //Autocomplete
	  $(function() {
	    $.ajax({
	      type: 'GET',
	      url: 'https://gist.githubusercontent.com/hakimelek/147f364c449104ba66da9a3baca9d0c0/raw/7e914fc578d3176f1f752f571f5b3761ea2b22fa/us_institutions.json',
	      success: function(response) {
	        var universityArray = $.parseJSON(response);
	        var dataUniversity = {};
	        for (var i = 0; i < universityArray.length; i++) {
	        	dataUniversity[universityArray[i].institution] = null;
	        }
	        $('input.autocomplete').autocomplete({
	          data: dataUniversity,
	          limit: 5,
	        });
	      }
	    });
	  });
	});
  </script>
  <script src="resources/js/materialize.js"></script>
  <script src="resources/js/init.js"></script>
</html>