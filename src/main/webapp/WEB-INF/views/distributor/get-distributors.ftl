<div class="its-request">
 <h1> Get Distributors</h1>
<#if distributors?has_content >
            <table class="table table-bordered" style="background-color: #92ca7a;">
            <thead id="bgcolor">
                <tr>
                     <th>Distributor Name</th>
                     <th>Distributor Location</th>
                     <th>Distributor Phone</th>
                </tr>
            </thead>
	            <tr>
	                 <#list distributors as distributor>
	                    <th><input type="text" onchange="changeDistributor(this.id)" class="form-control" id="distributor-${distributor.distributorName}" name="${distributor.distributorName}" value="${distributor.distributorName}"></th>
	                    <th><input type="text" onchange="changeDistributor(this.id)" class="form-control" id="distributor-${distributor.distributorName}" name="${distributor.distributorLocation}" value="${distributor.distributorLocation}"></th>
	                    <th><input type="text" onchange="changeDistributor(this.id)" class="form-control" id="distributor-${distributor.distributorName}" name="${distributor.distributorPhone}" value="${distributor.distributorPhone}"></th>
	                    <th><button type="submit" onclick="editDistributor(this.id)" id="distributor-${distributor.distributorName}" class="btn">Edit Distributor</button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteDistributor(this.id)" id="distributor-${distributor.distributorName}" class="btn">Delete Distributor</button></th>
	                 </#list>	           
	            </tr>
        </table>
</#if> 
</div>
