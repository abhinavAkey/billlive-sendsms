<div class="its-request">
 <h1> Get Products</h1>
 <#list products as product>
            <ul>
                <li>Product Name: <strong>${product.productName}</strong></li>
                <li>Product Category: <strong>${product.productCategory}</strong></li>
                <br>
            </ul>
</#list> 
</div>
