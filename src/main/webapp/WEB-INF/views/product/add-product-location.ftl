<div class="its-request">
 <h1> Add Product</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>

            <div class="pure-control-group">
                <label for="productId">Product Name</label>
                <select id="productId" name="productId" required>
                    <#list products as product>
                        <option value="${product.productId}">${product.productName}
                        </option>
                    </#list>
                </select>
                <span class="pure-form-message-inline">Required</span>              
            </div>

            <div class="pure-control-group">
                <label for="productPrice">Product Price</label>
                <input type="text" name="productPrice" id="productPrice" placeholder="Product Price"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
            <div class="pure-control-group">
                <label for="locationId">Product Location</label>
                <select id="locationId" name="locationId" required>
                    <#list locations as location>
                        <option value="${location.locationId}">${location.locationName}
                        </option>
                    </#list>
                </select>
                <span class="pure-form-message-inline">Required</span>              
            </div>

            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>

                <button type="submit" class="pure-button pure-button-primary">Add Product Location
                </button>
            </div>
        </fieldset>
    </form>
</div>
