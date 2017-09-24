<div class="its-request">
 <h1> Get Locations</h1>
 <#if locations?has_content >
            <table class="table table-bordered" style="background-color: #92ca7a;">
            <thead id="bgcolor">
                <tr>
                     <th>Location Name</th>
                     <th>Location City</th>
                     <th>Location District</th>
                     <th>Location State</th>
                </tr>
            </thead>
	            <tr>
	                 <#list locations as location>
	                    <th><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationName}" name="${location.locationName}" value="${location.locationName}"></th>
	                    <th><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationName}" name="${location.locationCity}" value="${location.locationCity}"></th>
	                    <th><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationName}" name="${location.locationDistrict}" value="${location.locationDistrict}"></th>
	                    <th><input type="text" onchange="changeLocation(this.id)" class="form-control" id="location-${location.locationName}" name="${location.locationState}" value="${location.locationState}"></th>
	                    <th><button type="submit" onclick="editLocation(this.id)" id="location-${location.locationName}" class="btn">Edit Location</button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteLocation(this.id)" id="location-${location.locationName}" class="btn">Delete Location</button></th>
	                 </#list>	           
	            </tr>
        </table>
</#if> 
</div>
