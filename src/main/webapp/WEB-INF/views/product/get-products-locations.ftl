<div class="its-request">
 <h1> Get Products</h1> 
<#if products?has_content >
            <table class="table table-bordered" style="background-color: #92ca7a;">
            <thead id="bgcolor">
                <tr>
                     <th>Product Name</th>
                     <th>Product Category</th>
                </tr>
            </thead>
	            <tr>
	                 <#list products as product>
	                    <th><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productName}" value="${product.productName}"></th>
	                    <th><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productPrice}" value="${product.productPrice}"></th>
	                    <th><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productLocation}" value="${product.productLocation}"></th>
	                    <th><button type="submit" onclick="editProduct(this.id)" id="product-${product.productName}" class="btn">Edit Product</button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteProduct(this.id)" id="product-${product.productName}" class="btn">Delete Product</button></th>
	                 </#list>	           
	            </tr>
        </table>
</#if>
</div>
