<div class="its-request">
 <h1> Get Locations</h1>
 
 <div class="container">
    <div class="row col-md-12">
 <#if locations?has_content >
            <table class="table table-striped table-bordered" style="width: 85%; background-color:white;">
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
	                    <td><input type="text" class="form-control" id="name-${location.locationId}-${location.locationName}" name="${location.locationName}" value="${location.locationName}" required></td>
	                    <td><input type="text" class="form-control" id="city-${location.locationId}-${location.locationCity}" name="${location.locationCity}" value="${location.locationCity}" required></td>
	                    <td><input type="text" class="form-control" id="district-${location.locationId}-${location.locationDistrict}" name="${location.locationDistrict}" value="${location.locationDistrict}" required></td>
	                    <td><input type="text" class="form-control" id="state-${location.locationId}-${location.locationState}" name="${location.locationState}" value="${location.locationState}" required></td>
	                    <td><button type="submit" onclick="editLocation(this.id)" id="location-${location.locationId}-edit" name="${location.locationId}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteLocation(this.id)" id="location-${location.locationName}-delete" name="${location.locationId}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
	                   </tr>
	                 </#list>	           
        </table>
</#if> 
</div>
</div>
</div>