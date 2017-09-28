<div class="its-request">
 <h1> Get Distributors</h1>
 
  <div class="container">
    <div class="row col-md-12">
<#if distributors?has_content >
            <table class="table table-striped table-bordered" style="width: 85%; background-color:white;">
            <thead>
                <tr style="background-color:#006400;color:white;">
                     <th>Distributor Name</th>
                     <th>Distributor Location</th>
                     <th>Distributor Phone</th>
                     <th></th>
                </tr>
            </thead>
	                 <#list distributors as distributor>
	                 <tr>
	                    <th><input type="text" class="form-control" id="name-${distributor.distributorId}-${distributor.distributorName}" name="${distributor.distributorName}" value="${distributor.distributorName}" required></th>
	                    <th><input type="text" class="form-control" id="location-${distributor.distributorId}-${distributor.distributorLocation}" name="${distributor.locationId}" value="${distributor.distributorLocation}" readonly></th>
	                    <th><input type="text" class="form-control" id="phone-${distributor.distributorId}-${distributor.distributorPhone}" name="${distributor.distributorPhone}" value="${distributor.distributorPhone}" required></th>
	                    <th><button type="submit" onclick="editDistributor(this.id)" id="distributor-${distributor.distributorId}-edit" name="${distributor.distributorId}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteDistributor(this.id)" id="${distributor.distributorId}-remove" name="${distributor.distributorId}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></th>
	                </tr>
	                 </#list>	           
        </table>
</#if> 
</div>
</div>
</div>
