window.onload = function() {
	(function(window, document) {

		var layout = document.getElementById('layout'), menu = document
				.getElementById('menu'), menuLink = document
				.getElementById('menuLink'), content = document
				.getElementById('main');

		function toggleClass(element, className) {
			var classes = element.className.split(/\s+/), length = classes.length, i = 0;

			for (; i < length; i++) {
				if (classes[i] === className) {
					classes.splice(i, 1);
					break;
				}
			}
			// The className is not found
			if (length === classes.length) {
				classes.push(className);
			}

			element.className = classes.join(' ');
		}

		function toggleAll(e) {
			var active = 'active';

			e.preventDefault();
			toggleClass(layout, active);
			toggleClass(menu, active);
			toggleClass(menuLink, active);
		}

		menuLink.onclick = function(e) {
			toggleAll(e);
		};

	}(this, this.document));

}

function changeValues(id) {
	var inputs = document.getElementsByTagName('input');
	var product = document.getElementById(id);

	var value = product.value;
	var name = product.name;

	var NUM = /^[0-9]*$/;
	if (value.match(NUM)) {
		for ( var i = 0; i < inputs.length; i++) {
			if (value) {
				if((value == "")){
					value = 0;
				}				
				if (inputs[i].type == "text") {
					inputs[i].focus();
					if (inputs[i].name && inputs[i].name.includes('##' +name)) {
						inputs[i].value = parseInt(inputs[i].value)
								+ parseInt(value);
					}
				}
			}
		}
	}
}

function editDistributor(id) {
	var inputs = document.getElementsByTagName('input');
	var distributor = document.getElementById(id);

	var name = distributor.name;
	var distributorName, distributorLocation, distributorPhone;
	
	for ( var i = 0; i < inputs.length; i++) {						
		if (inputs[i].type == "text") {
			inputs[i].focus();
			if (inputs[i].name && inputs[i].id.includes('name-' +name+"-")) {
				distributorName = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('location-' +name+"-")) {
				distributorLocation = inputs[i].name;
			}
			if (inputs[i].name && inputs[i].id.includes('phone-' +name+"-")) {
				distributorPhone = inputs[i].value;
			}
		}
	}
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/distributor/editDistributor";

	var serviceObject = {
		url : webapi,
		data : {
			distributorId : name,
			distributorName : distributorName,
			locationId : distributorLocation,
			distributorPhone : distributorPhone
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);

}

function deleteDistributor(id) {
	var inputs = document.getElementsByTagName('input');
	var distributor = document.getElementById(id);

	var name = distributor.name;
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/distributor/deleteDistributor";

	var serviceObject = {
		url : webapi,
		data : {
			distributorId : name
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);
	window.location.reload();
}

function editProduct(id) {
	var inputs = document.getElementsByTagName('input');
	var product = document.getElementById(id);

	var name = product.name;
	var productName, productCategory;
	
	for ( var i = 0; i < inputs.length; i++) {						
		if (inputs[i].type == "text") {
			inputs[i].focus();
			if (inputs[i].name && inputs[i].id.includes('name-' +name+"-")) {
				productName = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('category-' +name+"-")) {
				productCategory = inputs[i].value;
			}
		}
	}
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/product/editProduct";

	var serviceObject = {
		url : webapi,
		data : {
			productId : name,
			productName : productName,
			productCategory : productCategory
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);

}

function deleteProduct(id) {
	var inputs = document.getElementsByTagName('input');
	var product = document.getElementById(id);

	var name = product.name;
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/product/deleteProduct";

	var serviceObject = {
		url : webapi,
		data : {
			productId : name
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);
	window.location.reload();
}

function editLocation(id) {
	var inputs = document.getElementsByTagName('input');
	var locationInput = document.getElementById(id);

	var name = locationInput.name;
	var locationName, locationCity, locationDistrict, locationState;
	
	for ( var i = 0; i < inputs.length; i++) {						
		if (inputs[i].type == "text") {
			inputs[i].focus();
			if (inputs[i].name && inputs[i].id.includes('name-' +name+"-")) {
				locationName = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('city-' +name+"-")) {
				locationCity = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('district-' +name+"-")) {
				locationDistrict = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('state-' +name+"-")) {
				locationState = inputs[i].value;
			}
		}
	}
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/location/editLocation";

	var serviceObject = {
		url : webapi,
		data : {
			locationId : name,
			locationName : locationName,
			locationCity : locationCity,
			locationDistrict : locationDistrict,
			locationState : locationState		
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);

}

function deleteLocation(id) {
	var inputs = document.getElementsByTagName('input');
	var locationInput = document.getElementById(id);

	var name = locationInput.name;
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/location/deleteLocation";

	var serviceObject = {
		url : webapi,
		data : {
			locationId : name
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);
	window.location.reload();
}

function editProductsAndLocations(id) {
	var inputs = document.getElementsByTagName('input');
	var productsAndLocations = document.getElementById(id);

	var name = productsAndLocations.name;
	var productId, locationId, productPrice;
	
	for ( var i = 0; i < inputs.length; i++) {						
		if (inputs[i].type == "text") {
			inputs[i].focus();
			if (inputs[i].name && inputs[i].id.includes('product-' +name+"-")) {
				productId = inputs[i].name;
			}
			if (inputs[i].name && inputs[i].id.includes('location-' +name+"-")) {
				locationId = inputs[i].name;
			}
			if (inputs[i].name && inputs[i].id.includes('price-' +name+"-")) {
				productPrice = inputs[i].value;
			}
		}
	}
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/product/editProductsAndLocations";

	var serviceObject = {
		url : webapi,
		data : {
			productLocationId : name,
			productId : productId,
			locationId : locationId,
			productPrice :productPrice
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);

}

function deleteProductsAndLocations(id) {
	var inputs = document.getElementsByTagName('input');
	var productsAndLocations = document.getElementById(id);

	var name = productsAndLocations.name;
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/product/deleteProductsAndLocations";

	var serviceObject = {
		url : webapi,
		data : {
			productLocationId : name
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);
	window.location.reload();
}

function editConfiguration(id) {
	var inputs = document.getElementsByTagName('input');
	var smsConfiguration = document.getElementById(id);

	var name = smsConfiguration.name;
	var smsUrl, user, pass, code, header, footer;
	
	for ( var i = 0; i < inputs.length; i++) {						
		if (inputs[i].type == "text") {
			inputs[i].focus();
			if (inputs[i].name && inputs[i].id.includes('smsUrl-' +name+"-")) {
				smsUrl = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('user-' +name+"-")) {
				user = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('pass-' +name+"-")) {
				pass = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('code-' +name+"-")) {
				code = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('header-' +name+"-")) {
				header = inputs[i].value;
			}
			if (inputs[i].name && inputs[i].id.includes('footer-' +name+"-")) {
				footer = inputs[i].value;
			}
		}
	}
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/sms/editSmsConfiguration";

	var serviceObject = {
		url : webapi,
		data : {
			configurationId : name,
			smsUrl : smsUrl,
			parameterUsername : user,
			parameterPassword :pass,
			sendCode : code,
			messageHeader : header,
			messageFooter : footer
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);

}

function sendsms() {
	var inp = document.getElementsByTagName('input');
	var names = [];
	var checkboxes = document.getElementsByName('checkbox-location');
	  var checkboxesChecked = [];
	  // loop over them all
	  for (var i=0; i<checkboxes.length; i++) {
	     // And stick the checked ones onto an array...
	     if (checkboxes[i].checked) {
	        checkboxesChecked[i] = checkboxes[i].id;
	     }
	  }
	  console.log(checkboxesChecked)
	for ( var i in inp) {
		if (inp[i].type == "text") {
			inp[i].focus();
			// alert(inp[i].value);
			if (inp[i].name) {
				for ( var j in checkboxesChecked){
					if(checkboxesChecked[j] && inp[i].name.includes(checkboxesChecked[j])){
						console.log(checkboxesChecked[j]);
						names[i] = inp[i].name + '##' + inp[i].value;
						console.log(names[i]);
					}
				}
			}
		}
	}
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/sms/sendsmsScreen";

	var serviceObject = {
		url : webapi,
		data : {
			productNameLocAndPrice : names
		}
	};
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);

}

function processSavePasswordResponse(data) {
	
}

function getProductById(id){
console.log("id is " +id);
	var http = location.protocol;
	var slashes = http.concat("//");
	var host = slashes.concat(window.location.host);
	var webapi = host + "/billlive-sendsms/product/getProductById";
	var get = "get"
	var serviceObject = {
		url : webapi,
		type : get,
		data : {
			productId : id,
		}
	};
	console.log("serviceObject is " +serviceObject);
	this.serviceCalls(serviceObject, this.processSavePasswordResponse);

}


function serviceCalls(serviceObject, success, fail) {
	console.log("In service call is " );

	$.ajax({
		url : serviceObject.url,
		data : serviceObject.data,
		dataType : "json",
		type : serviceObject.type == undefined ? "post" : serviceObject.type,
		cache : false,
		xhrFields : serviceObject.xhrFields,
		beforeSend : serviceObject.beforeSend,
		statusCode : {
			401 : function(header) {
				var respObj = $.parseJSON(header.responseText);
				window.location.href = respObj.status;
			}
		},
		success : function(response) {
			
			alert(JSON.stringify(response));
			alert('Success')
		},
		error : function(e) {

		}
	});
}