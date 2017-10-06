<div class="its-request">
 <h1 style="text-align: left;"> Get Products And Locations</h1> <br>
 <div class="container">
    <div class="row col-md-12">
<#if products?has_content >
		<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for Products.." style = " background-image: url('/css/searchicon.png'); background-position: 10px 12px; background-repeat: no-repeat; width: 100%; font-size: 16px; padding: 12px 20px 12px 40px; border: 1px solid #ddd; margin-bottom: 12px;">

         <table id="myTable" class="table table-bordered" style="display:block;width: 85%; overflow-x:auto;">
            
             <tr onclick="javascript:showRow(this);">
	                 <#list products as product>
	                	<td style="min-width: 200px;"><input type="text"  class="form-control" id="product-${product.productLocationId}-${product.productName}" name="${product.productId}" value="${product.productName}"></td>
	                 </#list>	
	                 </tr>           
        </table>
        <br /><br />
        <table id="myDisplayTable" class="table table-bordered" style="width: 85%;">
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
        <script>
		function myFunction() {
		  var input, filter, table, tr, td, i;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr[0].getElementsByTagName("td").length; i++) {
		    td = tr[0].getElementsByTagName("td")[i];
		    if (td) {
		      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
		        td.style.display = "";
		      } else {
		        td.style.display = "none";
		      }
		    }       
		  }
		}
		</script>
		<script language="javascript" type="text/javascript">
		function showRow(row)
		{
		
		var x=row.cells;
	    var table = document.getElementById("myDisplayTable");
        var rowCount = table.rows.length;
		var rowInserted = table.insertRow(rowCount);
	    for (var j = 0, col; col = row.cells[j]; j++) {
	    	var cell = rowInserted.insertCell(j);
	    	cell.innerHTML = "<div contenteditable>"+ col.innerHTML+"</div>";
	    }
		}
		</script>
</#if>
</div>
</div>
</div>
