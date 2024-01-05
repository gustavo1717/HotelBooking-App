$(document).ready(function() {
		
	$("#searchBtn").click(function() {
		var searchLocation = $("#searchLocation").val();
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "http://localhost:8282/findHotel/"+searchLocation,
			success: function(result) {
				$("#select_roomTypes").empty();
				
				
				//$("select").append("<option>"+name+"</option>");
				$("#hotelTbl tr").not(".header").remove();
				$.each(result, function(key1, value1) {
                    $("#hotelTbl").append("<tr><td >" + value1.hotelName + "</td><td>" + value1.averagePrice + "</td><td>" + value1.starRating + "</td><td><img  src='" + value1.imageURL + "' style='width:150px;height:auto;' class='imgLink'></td></tr>");				});
			},
			error: function(e) {

			}
		});
	});
	
	
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

    $("#filterBtn").click(function () {
        var priceRange = parseInt($("#priceRange").val());

         $("#hotelTbl tr").not(".header").each(function () {
            var hotelPrice = parseInt($(this).find("td:eq(1)").text());
            var hotelRating = parseInt($(this).find("td:eq(2)").text());
            var starRatingChecked = false;

            $(".star_rating:checked").each(function () {
                var starRating = parseInt($(this).val());
                if (starRating === hotelRating) {
                    starRatingChecked = true;
                    return false;
                }
            });

            if (hotelPrice <= priceRange && (starRatingChecked || $(".star_rating:checked").length === 0) ) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
		
});
	
