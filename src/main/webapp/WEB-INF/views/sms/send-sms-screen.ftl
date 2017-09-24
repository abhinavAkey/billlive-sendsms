    <div class="its-request">
        <h1>Billlive Send SMS</h1><br><br>
        
        <table class="table table-bordered" style="background-color: #92ca7a;">
            <thead id="bgcolor">
                <tr>
                    <th>Check</th>
                    <th></th>
                     <#list products as product>
	                    <th><strong>${product.productName}</strong><br><input type="text" onchange="changeValues(this.id)" class="form-control" id="product-${product.productName}" name="${product.productName}" value=""></th>
	                 </#list>
                </tr>
            </thead>
            <#list locsAndPrices as locAndPrice>
	            <tr>
	                <td id="bgcolor"><input type="checkbox" id="${locAndPrice.locationName}" name="${locAndPrice.locationName}" value=""></td>
	                <td id="bgcolor"><strong>${locAndPrice.locationName}</strong></td>
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
        <center><button type="submit" onclick="sendsms()" class="btn">Send SMS</button></center>
    </div>
