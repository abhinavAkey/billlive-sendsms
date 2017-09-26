    <div class="its-request">
        <h1>Billlive Send SMS</h1><br><br>
        <div class="container">
    <div class="row col-md-12">
<#if locsAndPrices?has_content>        
        <table class="table table-striped table-bordered" style="background-color: white;">
            <thead>
                <tr style="background-color:#006400;color:white;" >
                    <th>Check</th>
                    <th></th>
                     <#list products as product>
	                    <th><strong>${product.productName}</strong><br><input type="text" onchange="changeValues(this.id)" class="form-control" id="product-${product.productName}" name="${product.productName}" value=""></th>
	                 </#list>
                </tr>
            </thead>
            <#list locsAndPrices as locAndPrice>
	            <tr>
	                <td><input type="checkbox" id="${locAndPrice.locationName}" name="checkbox-location" value="" checked></td>
	                <td><strong>${locAndPrice.locationName}</strong></td>
	                  <#list products as product>
	                   	<#assign isAdded = 'false'>
	                    <#list locAndPrice.productAndPrices as productAndPrice>	                     	                     
	                      <#if productAndPrice.productName == product.productName >
	                          <#assign isAdded = 'true'>
		                      <td>Price<br><input type="text" class="form-control" id="location-product-${locAndPrice.locationName}##${product.productName}" name="${locAndPrice.locationName}##${product.productName}" value="${productAndPrice.price}"></td>
		                  </#if>
		               </#list>
		               <#if isAdded == "false">
		                      <td>No Data</td>                  
		               </#if>
	                </#list>	           
	            </tr>
            </#list>
        </table>       
        <center><button type="submit" onclick="sendsms()" class="pure-button pure-button-primary">Send SMS</button></center>
</#if>     
</div>
</div>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

