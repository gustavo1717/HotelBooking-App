$(document).ready(function() {
	
	var numGuests = 0; // Track the number of guests

	$('#bookingTbl tbody tr').on("click", '.review', function(e){
	        e.preventDefault();
	        var thisBookingId = $(this).parent().parent().children("td").eq("0").text();
	        $("#review_id").text(thisBookingId);
	        console.log($("#review_id").text())
	        $("#reviewModal").modal("toggle");
	
	})
	
	
	$(".review").click(function(e){
	var hotelName = $(this).data("hotel-name");
	$.get("http://localhost:8282/findHotelByName/" + hotelName, {}, function(responseText) {
	
	$("#reviewSubmit").click(function(e){
		
        var reviewData = {
            reviewId: $("#review_id").text(),
            text: $("#reviewComments").val(),
            overallRating: (parseInt($("#review_starRating").val()) + parseInt($("#review_starRating2").val()) + parseInt($("#review_starRating3").val()) + parseInt($("#review_starRating4").val()))/4,
            hotelId: responseText[0].hotelId
        }
        $.ajax({
            type:"POST",
            contentType:"application/json",
            url: "http://localhost:8282/saveReview/",
            data: JSON.stringify(reviewData),
            dataType: 'json',
            success: function(result){
                console.log(result)
                $("#reviewModal").modal("toggle");
            },
            error: function(e) {
                console.log(e)
            }
        })
        e.preventDefault();
    })
    })
    })
	
	$("#searchBtn").click(function() {
		var searchLocation = $("#searchLocation").val();
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "http://localhost:8282/findHotel/"+searchLocation,
			success: function(result) {
				$("#select_roomTypes").empty();
				
				$("#hotelTbl tr").not(".header").remove();
				$.each(result, function(key1, value1) {
					var buttonHtml = "<button class='btn btn-primary view-reviews-button' data-hotel-id='" + value1.hotelId + "'>View Reviews</button>";
					var amenitiesString = value1.amenities.map(function(amenity) {
    					return amenity.name; // Assuming 'name' is the property you want to display for each amenity
					}).join(', ');
                    $("#hotelTbl").append("<tr><td >" + value1.hotelName + "</td><td>" + value1.averagePrice + "</td><td>" + value1.starRating + "</td><td>" + amenitiesString + "</td><td><img  src='" + value1.imageURL + "' style='width:150px;height:auto;' class='imgLink'></td><td>" + buttonHtml + "</td></tr>");				});
			},
			error: function(e) {

			}
		});
	});
	
	$("#hotelTbl").on("click", ".view-reviews-button", function() {
        var hotelId = $(this).data("hotel-id");
        fetchAndDisplayReviews(hotelId);
    });
//});

function fetchAndDisplayReviews(hotelId) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8484/findReviewsByHotelId/${hotelId}`, // Adjust the URL to match your actual endpoint
        dataType: 'json',
        success: function(reviews) {
            $('#reviewsList').empty(); // Clear previous reviews
            if (reviews.length === 0) {
                $('#reviewsList').append('<li>No reviews available for this hotel.</li>');
            } else {
                reviews.forEach(function(review) {
                    var reviewItem = $('<li>').text('Overall Rating: ' + review.overallRating + ', Comments: ' + review.text);
                    $('#reviewsList').append(reviewItem);
                });
            }
            $('#reviewModal').modal('show'); // Open the modal
        },
        error: function(e) {
            console.error("Error fetching reviews: ", e);
        }
    });
}
	
	$("#hotelTbl").on('click', '.imgLink', function(){
	    console.log("Image clicked!");
	    
	    var hotelName = $(this).closest('tr').find('td:eq(0)').text();
	    var noGuests = $("#noGuests").val();
	    var noRooms = $("#noRooms").val();
	    var checkInDate = $("#checkInDate").val();
	    var checkOutDate = $("#checkOutDate").val();
	
	    $.get("http://localhost:8282/findHotelByName/" + hotelName, {}, function(responseText) {
    		$("#select_roomTypes").empty();
    		console.log(responseText);
    		var hotelRooms = responseText[0].hotelRooms;
  		
	    	var roomTypes = [];
	
	    	$.each(hotelRooms, function(key, value1) {
	    		var roomTypeName = value1.type.name;
	    		roomTypes.push(roomTypeName); 

			})
			    	
	    	$.each(roomTypes, function(key1, value1) {$("#select_roomTypes").append("<option>" + value1 + "</option>")})
	
		    $("#modal_hotelName").val(hotelName);
		    $("#modal_noGuests").val(noGuests);
		    $("#modal_noRooms").val(noRooms);
		    $("#modal_checkInDate").val(checkInDate);
		    $("#modal_checkOutDate").val(checkOutDate);
		    $("#myModal").modal("toggle");
		    
		    
		
		});
	});
	
	
	$(".btn-searchHotelRooms").click(function() {
		
        // Show the Guest Information modal for the specified number of guests
        numGuests = parseInt($("#modal_noGuests").val()) || 0; // Get the number of guests from input
        showGuestInfoModal();
        
    });

    function showGuestInfoModal() {
		var hotelName = $("#modal_hotelName").val();
		$.get("http://localhost:8282/findHotelByName/" + hotelName, {}, function(responseText) {
			var averagePrice = responseText[0].averagePrice;
			var discount = responseText[0].discount;

			 
			 
		
        if (numGuests > 0) {
            // Show the Guest Information modal
            $("#guestInfoModal").modal("show");
        } else {
        	$("#booking_hotelName").val($("#modal_hotelName").val());
			$("#booking_noGuests").val($("#modal_noGuests").val());
        	$("#booking_noRooms").val($("#modal_noRooms").val());
        	$("#booking_checkInDate").val($("#modal_checkInDate").val());
        	$("#booking_checkOutDate").val($("#modal_checkOutDate").val());
        	$("#booking_roomType").val($("#select_roomTypes").val());
        	$("#booking_price").text(averagePrice);        	
        	$("#booking_discount").text(discount);


        	
			$("#bookingHotelRoomModal").modal("show");
        }
    



     $("#submitGuest").click(function(event) {
        event.preventDefault(); // Prevent the form from actually submitting
        //alert("Button");

        // Access guest information
        var guestName = $("#guestName").val();
        var guestLastName = $("#guestLastName").val();
        var guestAge = $("#guestAge").val();
        var guestGender = $("#guestGender").val();

        // Create an object representing the guest
        var guestData = {
            firstName: guestName,
            lastName: guestLastName,
            age: guestAge,
            gender: guestGender
        };

        console.log(guestData);
        

        //POST request to booking microservice
        $.ajax({
            type: "POST",
            url: "http://localhost:8484/guests/saveguest", 
            contentType: "application/json",
            data: JSON.stringify(guestData),
            success: function(response) {
                console.log("Guest information saved successfully:", response);
            },
            error: function(error) {
                console.error("Error saving guest information:", error);
            }
        });

        numGuests--;
        if (numGuests > 0) {
            $("#guestInfoForm")[0].reset(); // Clear the form for the next guest
        } else {
            $("#guestInfoModal").modal("hide"); // Hide the guest information modal
            alert("Guest information entered for all guests.");
            $("#booking_hotelName").val($("#modal_hotelName").val());
			$("#booking_noGuests").val($("#modal_noGuests").val());
        	$("#booking_noRooms").val($("#modal_noRooms").val());
        	$("#booking_checkInDate").val($("#modal_checkInDate").val());
        	$("#booking_checkOutDate").val($("#modal_checkOutDate").val());
        	$("#booking_roomType").val($("#select_roomTypes").val());
        	$("#booking_price").text(averagePrice);        	
        	$("#booking_discount").text(discount);
			$("#bookingHotelRoomModal").modal("show");
        }
    });
});
}
    

    
	$(".btn-confirm-booking").click(function() {
		
		var hotelName = $("#booking_hotelName").val();
	  	$("#conf_hotelName").text(hotelName);
	  	$.get("http://localhost:8282/findHotelByName/" + hotelName, {}, function(responseText) {
		var averagePrice = responseText[0].averagePrice;
	    var discount = responseText[0].discount;
				
	  	var noRooms = $("#booking_noRooms").val();
	  	$("#conf_noRooms").text(noRooms);
	  	
	  	var checkIn = $("#booking_checkInDate").val();
	  	$("#conf_checkInDate").text(checkIn);
	  	
	  	var checkOut = $("#booking_checkOutDate").val();
	  	$("#conf_checkOutDate").text(checkOut);
	  	
	  	var roomT = $("#booking_roomType").val();
	  	$("#conf_roomType").text(roomT);
	  	
	  	//var discount = $("#booking_discount").val();
	  	$("#conf_discount").text(discount);
	  	
	  	//var totalPrice = $("#booking_price").val();
	  	$("#conf_price").text(averagePrice * noRooms);
	
		$("#bookingHotelRoomModal").modal("hide");
		$("#hotelRoomsModal").modal("show");
	});
		 
		$("#confirmDetailsButton").click(function() {
		$.ajax({
	        type: "GET",
	        url: "http://localhost:8282/getUserDetails", 
	        success: function(userDetails) {
	            console.log("Username:", userDetails.username);
	            console.log("User email:",userDetails.email );
	            
	    var bookingData = {
			hotelName: $("#conf_hotelName").text(),
	        hotelId: $("#booking_hotelId").text(),
	        hotelRoomId: $("#booking_hotelRoomId").val(),
	        noRooms: $("#booking_noRooms").val(),
	        guests: [], // You need to populate this with guest information
	        checkInDate: $("#booking_checkInDate").val(),
	        checkOutDate: $("#booking_checkOutDate").val(),
	        status: "UPCOMING", // Set the initial status as UPCOMING
	        price: parseFloat($("#conf_price").text()),
	        discount: parseFloat($("conf_discount").text()),
	        customerMobile: $("#booking_customerMobile").val(),
	        roomType: $("#booking_roomType").val(),
	        userEmail: userDetails.email,
	        userName: userDetails.username
	     };
	
	    var bookingNode = JSON.stringify(bookingData);
	
	    // Send a POST request to save the booking
	    $.ajax({
	        type: "POST",
	        url: "http://localhost:8484/bookings/saveBooking", 
	        contentType: "application/json",
	        data: bookingNode,
	        success: function(response) {
	            console.log("Booking saved successfully:", response);
	            alert("Booking confirmed!");
	
	        },
	        error: function(error) {
	            console.error("Error saving booking:", error);
	        }
	    }); 
	    }});
	 }); 
	});
 

    $("#filterBtn").click(function() {
        var priceRange = parseInt($("#priceRange").val());
        var tblRow = $("#hotelTbl tr").not(".header");
        $(tblRow).show();
        $.each(tblRow, function(key, value) {
			var row = $(this);
            var hotelName = $(this).find('td:eq(0)').text();
            $.get("http://localhost:8282/findHotelByName/" + hotelName, {}, function(responseText) {
			var flag = 0;
            var amenity_flag = 1;
			var hotelPrice = responseText[0].averagePrice;
			console.log(hotelPrice);
            var hotelRating = parseInt($(value).children("td").eq("2").text());
            var hotelAmenities = responseText[0].amenities.map(a => a.name);
            var allUnchecked = true;
            if(hotelPrice > priceRange){
				row.hide();
            } else {
                $.each($(".hotel_amenity"), function(k,v){
										
                    if($(this).prop('checked')== true){
                        var amenity = $(this).val();
         
                        if(!hotelAmenities.includes(amenity)){
                            amenity_flag=0;
                        }

                    }
                })

                $.each($(".star_rating"), function(key, value){
                    if($(this).prop('checked')==true){
                        var starRating = parseInt($(this).val());
                        if(starRating == hotelRating) {
                            flag = 1;
                        }
                        allUnchecked=false;
                    }
                })
            }
            if(allUnchecked) {
                if(amenity_flag == 0)
                   row.hide();
            } else {
                if(flag == 0 || amenity_flag==0){
                    row.hide();
                }
            }
        })
        })
            
    })
		
});
	
