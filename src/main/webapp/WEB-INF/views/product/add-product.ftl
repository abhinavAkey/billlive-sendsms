<div class="its-request">
 <h1> Add Product</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>

            <div class="pure-control-group">
                <label for="productName">Product Name</label>
                <input type="text" name="productName" id="productName" placeholder="Product Name"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            <div class="pure-control-group">
            <label for="productImage">Product Image</label>
            	<input type='file' name="productImage" onchange="readURL(this);" />
    			<img id="blah" src="#" />
    		</div>
            
            <div class="pure-control-group">
                <label for="productCategory">Product Category</label>
                <input type="text" name="productCategory" id="productCategory" placeholder="Product Category"
                       required>
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

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script>
function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
console.log(input.files[0]);
            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result)
                    .width(100)
                    .height(150);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    
    </script>