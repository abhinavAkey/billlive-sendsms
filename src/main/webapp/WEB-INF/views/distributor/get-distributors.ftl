<div class="its-request">
 <h1 style="text-align: left;"> Get Distributors</h1><br>
 
  <div class="container">
    <div class="row col-md-12">
<#if distributors?has_content >
            <table class="table table-bordered" style="width: 85%;">
            <thead>
                <tr>
                     <th>Distributor Name</th>
                     <th>Distributor Location</th>
                     <th>Distributor Phone</th>
                     <th></th>
                </tr>
            </thead>
	                 <#list distributors as distributor>
	                 <tr>
	                    <td><input type="text" class="form-control" id="name-${distributor.distributorId}-${distributor.distributorName}" name="${distributor.distributorName}" value="${distributor.distributorName}" required></td>
	                    <td><input type="text" class="form-control" id="location-${distributor.distributorId}-${distributor.distributorLocation}" name="${distributor.locationId}" value="${distributor.distributorLocation}" readonly></td>
	                    <td><input type="text" class="form-control" id="phone-${distributor.distributorId}-${distributor.distributorPhone}" name="${distributor.distributorPhone}" value="${distributor.distributorPhone}" required></td>
	                    <td style="width: 20%;"><button type="submit" onclick="editDistributor(this.id)" id="distributor-${distributor.distributorId}-edit" name="${distributor.distributorId}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteDistributor(this.id)" id="${distributor.distributorId}-remove" name="${distributor.distributorId}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
	                </tr>
	                 </#list>	           
        </table>
</#if> 
</div>
</div>
</div>
