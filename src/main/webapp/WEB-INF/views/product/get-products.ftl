<div class="its-request">
 <h1> Get Products</h1>
 
 <div class="container">
    <div class="row col-md-12">
<#if products?has_content >
            <table class="table table-striped table-bordered" style="background-color:white;">
            <thead>
                <tr style="background-color:#006400;color:white;">
                     <th>Product Name</th>
                     <th>Product Image</th>
                     <th>Product Category</th>
                     <th></th>
                </tr>
            </thead>
	                 <#list products as product>
	                 	<tr>
	                 
		                    <td><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productName}" value="${product.productName}"></td>
		                    <td>  <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAwBAMAAACh2TSJAAAALVBMVEUAAADtNTX////3n5/+9fX719f7zMz5tLTzfHzuQED//f31jY3ybGzxXV3wVFRaxp+rAAAAAXRSTlMAQObYZgAAALVJREFUOMut0rENAjEQRNHdC4kY0QBaAQUQX0QAFSAKIKQEKiAA6VqgIkriApuV1x7pQPz0aWwHljLMpZ0CRDBGoXmeghGYKFJsUo90giAImCgV5OJF+oOgKE48MlGgs2VLBIunWesw0a1ZHqF82c7GmmIfUSpgotOly29DFPFJFDEhkgIT/V5mZuvj6XofKrHU6vyI4u37IYi36aN4h5tL7PJyif1dvCgEpapzISbCTEj5R78BZq5A5Ldh2XYAAAAASUVORK5CYII"></td>
		                    <td><input type="text" onchange="changeProduct(this.id)" class="form-control" id="product-${product.productName}" name="${product.productCategory}" value="${product.productCategory}"></td>
		                    <td><button type="submit" onclick="editProduct(this.id)" id="product-${product.productName}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteProduct(this.id)" id="product-${product.productName}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
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