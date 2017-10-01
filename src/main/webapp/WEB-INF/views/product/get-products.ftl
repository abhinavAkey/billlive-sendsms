<div class="its-request">
 <h1 style="text-align: left;"> Get Products</h1><br>
 
 <div class="container">
    <div class="row col-md-12">
<#if products?has_content >
            <table class="table table-bordered" style="width: 85%;">
            <thead>
                <tr >
                     <th>Product Name</th>
                     <th>Product Category</th>
                     <th></th>
                </tr>
            </thead>
	                 <#list products as product>
	                 	<tr>             
		                    <td><input type="text"  class="form-control" id="name-${product.productId}-${product.productName}" name="${product.productName}" value="${product.productName}" required></td>
		                    <td><input type="text"  class="form-control" id="category-${product.productId}-${product.productCategory}" name="${product.productCategory}" value="${product.productCategory}" required></td>
			                <td><button type="submit" onclick="editProduct(this.id)" id="product-${product.productId}-edit" name="${product.productId}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteProduct(this.id)" id="product-${product.productId}-delete" name="${product.productId}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
		                </tr>
	                 </#list>	           
        </table>
</#if>  
</div>
</div>
</div>