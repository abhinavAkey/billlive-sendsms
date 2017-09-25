<div class="its-request">
 <h1> Get Locations</h1>
 
 <div class="container">
    <div class="row col-md-12">
 <#if locations?has_content >
            <table class="table table-striped table-bordered" style="background-color:white;">
            <thead>
                <tr style="background-color:#006400;color:white;">
                     <th>Location Name</th>
                     <th>Location City</th>
                     <th>Location District</th>
                     <th>Location State</th>
                     <th></th>
                </tr>
            </thead>
	                 <#list locations as location>
	                 <tr>
	                    <td><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationId}-${location.locationName}" name="${location.locationName}" value="${location.locationName}"></td>
	                    <td><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationId}-${location.locationCity}" name="${location.locationCity}" value="${location.locationCity}"></td>
	                    <td><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationId}-${location.locationDistrict}" name="${location.locationDistrict}" value="${location.locationDistrict}"></td>
	                    <td><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationId}-${location.locationState}" name="${location.locationState}" value="${location.locationState}"></td>
	                    <td><button type="submit" onclick="editLocation(this.id)" id="location-${location.locationId}-edit" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteLocation(this.id)" id="location-${location.locationName}-delete" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
	                   </tr>
	                 </#list>	           
        </table>
</#if> 
</div>
</div>
</div>