<div class="its-request">
 <h1> Get Products</h1> 
 <div class="container">
    <div class="row col-md-12">
<#if products?has_content >
            <table class="table table-striped table-bordered" style="background-color:white;">
            <thead>
                <tr style="background-color:#006400;color:white;">
                     <th>Product Name</th>
                     <th>Product Price</th>
                     <th>Product Category</th>
                     <th></th>
                </tr>
            </thead>
	                 <#list products as product>
	                 <tr>
	                    <th><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productName}" value="${product.productName}"></th>
	                    <th><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productPrice}" value="${product.productPrice}"></th>
	                    <th><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productLocation}" value="${product.productLocation}"></th>
	                    <th><button type="submit" onclick="editProduct(this.id)" id="product-${product.productName}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteProduct(this.id)" id="product-${product.productName}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></th>
	                 </tr>
	                 </#list>	           
        </table>
</#if>
</div>
</div>
</div>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

