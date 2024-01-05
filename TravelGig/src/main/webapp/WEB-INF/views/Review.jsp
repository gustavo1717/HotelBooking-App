<!DOCTYPE html>
<html>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta charset="ISO-8859-1">
<title>Home Page of Travel Gig</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src='./js/hotel.js'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
	<div class="col-7 border rounded" style="margin-left:300px;">
	<table id="bookingTbl" border='1'><tr class='header'><th>Booking ID</th><th>Hotel</th><th>Rooms</th><th>Check In</th><th>Check Out</th><th>Status</th><th>Action</th>
		
			<c:forEach  var="booking" items="${bookings}">
			<tr><td>${booking.bookingId}</td><td>${booking.hotelName}</td><td>${booking.noRooms}</td><td>${booking.checkInDate}</td><td>${booking.checkOutDate}</td><td>${booking.status}</td><td>
	
	
					<a href="http://localhost:8282/deleteBooking?id=${booking.bookingId}" class="btn btn-danger">Cancel</a></td>
	
			</td>
			<td><a href="" class="btn btn-primary review" data-hotel-name="${booking.hotelName}">Review</a></td>
			</tr>
		</c:forEach> 
		</table>
	</div>
		<div class="modal" id="reviewModal">
			<div class="modal-dialog">
			  <div class="modal-content">
		  
				<!-- Modal Header -->
				<div class="modal-header">
				  <h4 class="modal-title">Review</h4>
				  <button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
		  
				<!-- Modal body -->
				<div class="modal-body">
				  <div class="col">
					  <form>
							  <label>How was your experience staying with us?</label>
							  <select class="form-control" id="review_starRating">
								  <option value=0>Select</option>
								  <option value=1>1</option>
								  <option value=2>2</option>
								  <option value=3>3</option>
								  <option value=4>4</option>
								  <option value=5>5</option>
							  </select>
							  <label>How were the amenities?</label>
							  <select class="form-control" id="review_starRating2">
								  <option value=0>Select</option>
								  <option value=1>1</option>
								  <option value=2>2</option>
								  <option value=3>3</option>
								  <option value=4>4</option>
								  <option value=5>5</option>
							  </select>
							  <label>How were the staff?</label>
							  <select class="form-control" id="review_starRating3">
								  <option value=0>Select</option>
								  <option value=1>1</option>
								  <option value=2>2</option>
								  <option value=3>3</option>
								  <option value=4>4</option>
								  <option value=5>5</option>
							  </select>
							  <label>How was the service?</label>
							  <select class="form-control" id="review_starRating4">
								  <option value=0>Select</option>
								  <option value=1>1</option>
								  <option value=2>2</option>
								  <option value=3>3</option>
								  <option value=4>4</option>
								  <option value=5>5</option>
							  </select>
							  <label>Additional comments:</label>
							  <input id="reviewComments" type="text"/>
						  <input type="submit" id="reviewSubmit"/>
					  </form>
		  
		  
				  </div>
		  
				</div>
		  
				<!-- Modal footer -->
				<div class="modal-footer">
				  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
		  
			  </div>
			</div>
		  </div>
		  
		  <button style="position: fixed; top: 10px; right: 10px; padding: 10px; background-color: #008CBA; color: white; border: none; border-radius: 5px; cursor: pointer;" onclick="window.location.href = 'http://localhost:8282/home';">Home</button>

</body>
</html>