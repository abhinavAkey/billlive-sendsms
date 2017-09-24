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

function serviceCalls(serviceObject, success, fail) {
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
			alert('Success')
		},
		error : function(e) {

		}
	});
}