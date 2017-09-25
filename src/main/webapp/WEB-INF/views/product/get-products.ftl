<div class="its-request">
 <h1> Get Products</h1>
 
 <div class="container">
    <div class="row col-md-12">
        <div class="panel panel-primary filterable">
<#if products?has_content >
            <table class="table table-striped table-bordered" style="background-color:white;">
            <thead>
                <tr style="background-color:#006400;color:white;" >
                     <th>Product Name</th>
                     <th>Product Image</th>
                     <th>Product Category</th>
                     <th></th>
                </tr>
                
                <tr class="filters">
                	<th><input type="text" class="form-control" placeholder="Product Name" ></th>
                        <th></th>
                        <th><input type="text" class="form-control" placeholder="Product Category"></th>
                        </tr>
            </thead>
	                 <#list products as product>
	                 	<tr>
	                 
		                    <td class="edit">${product.productName}</td>
		                    <td>  <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAwBAMAAACh2TSJAAAALVBMVEUAAADtNTX////3n5/+9fX719f7zMz5tLTzfHzuQED//f31jY3ybGzxXV3wVFRaxp+rAAAAAXRSTlMAQObYZgAAALVJREFUOMut0rENAjEQRNHdC4kY0QBaAQUQX0QAFSAKIKQEKiAA6VqgIkriApuV1x7pQPz0aWwHljLMpZ0CRDBGoXmeghGYKFJsUo90giAImCgV5OJF+oOgKE48MlGgs2VLBIunWesw0a1ZHqF82c7GmmIfUSpgotOly29DFPFJFDEhkgIT/V5mZuvj6XofKrHU6vyI4u37IYi36aN4h5tL7PJyif1dvCgEpapzISbCTEj5R78BZq5A5Ldh2XYAAAAASUVORK5CYII"></td>
		                    <td class="edit">${product.productCategory}</td>
		                    <td><button type="submit" onclick="editProduct(this.id)" id="product-${product.productName}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteProduct(this.id)" id="product-${product.productName}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
	                 	</tr>
	                 </#list>	           
        </table>
</#if>  
</div>
</div>
</div>
</div>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/billlive-sendsms/resources/css/bootstrap-combined.min.css">
<link rel="stylesheet" href="/billlive-sendsms/resources/css/bootstrap-editable.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/billlive-sendsms/resources/js/bootstrap-editable.js"></script>
    
    
    <script>
    
    $.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.showbuttons = true;
$.fn.editable.defaults.url = '/post';
$.fn.editable.defaults.type = 'text';

// make all items having class 'edit' editable
$('.edit').editable();
    
    $(document).ready(function(){

    $('.filterable .filters input').keyup(function(e){
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;
        /* Useful DOM data and selectors */
        var $input = $(this),
        inputContent = $input.val().toLowerCase(),
        $panel = $input.parents('.filterable'),
        column = $panel.find('.filters th').index($input.parents('th')),
        $table = $panel.find('.table'),
        $rows = $table.find('tbody tr');
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function(){
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
        }
    });
});

</script>