<div class="its-request">
 <h1> Add Product</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>

            <div class="pure-control-group">
                <label for="productName">Product Name</label>
                <select id="productName" name="productName" required>
                    <#list products as product>
                        <option value="${product.productName}">${product.productName}
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
                <label for="productLocation">Product Location</label>
                <select id="productLocation" name="productLocation" required>
                    <#list locations as location>
                        <option value="${location.locationName}">${location.locationName}
                        </option>
                    </#list>
                </select>
                <span class="pure-form-message-inline">Required</span>              
            </div>

            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>

                <button type="submit" class="pure-button pure-button-primary">Add Product
                </button>
            </div>
        </fieldset>
    </form>
</div>
