<div class="its-request">
 <h1 style="text-align: left;"> Get Products And Locations</h1> <br>
 <div class="container">
    <div class="row col-md-12">
<#if products?has_content >
            <table class="table table-bordered" style="width: 85%;">
            <thead>
                <tr>
                     <th>Product Name</th>
                     <th>Product Price</th>
                     <th>Product Category</th>
                     <th></th>
                </tr>
            </thead>
	                 <#list products as product>
	                 <tr>
	                    <td><input type="text"  class="form-control" id="product-${product.productLocationId}-${product.productName}" name="${product.productId}" value="${product.productName}" readonly></td>
	                    <td><input type="text"  class="form-control" id="price-${product.productLocationId}-${product.productPrice}" name="${product.productPrice}" value="${product.productPrice}" required></td>
	                    <td><input type="text"  class="form-control" id="location-${product.productLocationId}-${product.productLocationName}" name="${product.locationId}" value="${product.productLocationName}" readonly></td>
	                    <td><button type="submit" onclick="editProductsAndLocations(this.id)" id="product-${product.productLocationId}-edit" name="${product.productLocationId}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteProductsAndLocations(this.id)" id="product-${product.productLocationId}-edit" name="${product.productLocationId}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
	                 </tr>
	                 </#list>	           
        </table>
</#if>
</div>
</div>
</div>
