    <div class="its-request">
        <h1 style="text-align: left; margin-left: 16%;">Billlive Send SMS</h1><br>
        <div class="container">
    <div class="row col-md-12">
<#if locsAndPrices?has_content>        
        <table class="table table-bordered" style="width: 85%; margin-left: 10%;">
            <thead>
                <tr>
                    <th>Check</th>
                    <th>Locations</th>
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