<div class="its-request">
 <h1> Get Distributors</h1>
 
  <div class="container">
    <div class="row col-md-12">
<#if distributors?has_content >
            <table class="table table-striped table-bordered" style="background-color:white;">
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
	                    <th><input type="text" onchange="changeDistributor(this.id)" class="form-control" id="distributor-${distributor.distributorName}" name="${distributor.distributorName}" value="${distributor.distributorName}"></th>
	                    <th><input type="text" onchange="changeDistributor(this.id)" class="form-control" id="distributor-${distributor.distributorName}" name="${distributor.distributorLocation}" value="${distributor.distributorLocation}"></th>
	                    <th><input type="text" onchange="changeDistributor(this.id)" class="form-control" id="distributor-${distributor.distributorName}" name="${distributor.distributorPhone}" value="${distributor.distributorPhone}"></th>
	                    <th><button type="submit" onclick="editDistributor(this.id)" id="distributor-${distributor.distributorName}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteDistributor(this.id)" id="distributor-${distributor.distributorName}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></th>
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
